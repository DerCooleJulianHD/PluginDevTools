package de.api.devtools.scoreboard;

import de.api.devtools.scoreboard.score.AnimatedScore;
import de.api.devtools.scoreboard.score.SimpleScore;
import de.api.devtools.scoreboard.util.Criteria;
import de.api.devtools.utils.TextUtil;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface IScoreboard {

    @Nonnull Map<Integer, SimpleScore> getSimpleScores();

    @Nonnull Map<Integer, AnimatedScore> getAnimatedScores();

    @Nonnull Scoreboard getBoard();

    default void setTitle(String s) {
        this.setTitle(getMainObjective(), s);
    }

    default void setTitle(Objective objective, String s) {
        if (objective != null) Objects.requireNonNull(objective).setDisplayName(TextUtil.colorize(s));
    }

    @Nonnull Objective registerObjective(String id, Criteria criteria, String displayname, boolean replace);

    @Nonnull Objective getMainObjective();

    default @Nullable Objective getObjective(String id) {
        return getBoard().getObjective(id);
    }

    default void removeObjective(String id) {
        if (getObjective(id) != null) Objects.requireNonNull(getObjective(id)).unregister();
    }

    void setSimpleScore(@Nonnull String content, int id);

    void setAnimatedScore(long ticks, @Nonnull List<String> content, int id);

    default void removeSimpleScore(int id) {
        final SimpleScore score = getSimpleScore(id);
        if (score == null) return;
        score.hideScore();
        getSimpleScores().remove(id);
    }

    default void removeAnimatedScore(int id) {
        final AnimatedScore score = getAnimatedScore(id);
        if (score == null) return;
        score.hideScore();
        getAnimatedScores().remove(id);
    }

    default @Nullable SimpleScore getSimpleScore(int id) {
        return getSimpleScores().get(id);
    }

    default @Nullable AnimatedScore getAnimatedScore(int id) {
        return getAnimatedScores().get(id);
    }

    default void set(Player player) {
        player.setScoreboard(getBoard());
    }

}
