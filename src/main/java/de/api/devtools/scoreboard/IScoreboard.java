package de.api.devtools.scoreboard;

import de.api.devtools.scoreboard.score.AnimatedScore;
import de.api.devtools.scoreboard.score.IScore;
import de.api.devtools.scoreboard.score.SimpleScore;
import de.api.devtools.scoreboard.util.Criteria;
import de.api.devtools.utils.TextUtil;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public interface IScoreboard {

    @NonNull Map<Integer, IScore> getScores();

    @NonNull Scoreboard getBoard();

    default void setTitle(String s) {
        this.setTitle(getMainObjective(), s);
    }

    default void setTitle(Objective objective, String s) {
        if (objective != null) Objects.requireNonNull(objective).setDisplayName(TextUtil.colorize(s));
    }

    @NonNull Objective registerObjective(String id, Criteria criteria, String displayname, boolean replace);

    @NonNull Objective getMainObjective();

    default @Nullable Objective getObjective(String id) {
        return getBoard().getObjective(id);
    }

    default void removeObjective(String id) {
        if (getObjective(id) != null) Objects.requireNonNull(getObjective(id)).unregister();
    }

    void setSimpleScore(@NonNull String content, int id);

    default void setSimpleScore(@NonNull String prefix, @NonNull String content, int id) {
        setSimpleScore((prefix + content), id);
    }

    void setAnimatedScore(long ticks, @NonNull String content, int id, Consumer<AnimatedScore> update);

    default void setAnimatedScore(long ticks, @NonNull String prefix, @NonNull String content, int id, Consumer<AnimatedScore> update) {
        setAnimatedScore(ticks, (prefix + content), id, update);
    }

    default void removeScore(int id) {
        getScores().remove(id);
    }

    default @Nullable IScore getScore(int id) {
        return getScores().get(id);
    }

    default void set(Player player) {
        player.setScoreboard(getBoard());
    }

}
