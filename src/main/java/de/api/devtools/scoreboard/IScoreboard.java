package de.api.devtools.scoreboard;

import de.api.devtools.utils.TextUtil;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public interface IScoreboard {

    @Nonnull Scoreboard getBoard();

    default void setTitle(String s) {
        this.setTitle(getObjective(), s);
    }

    default void setTitle(Objective objective, String s) {
        if (objective != null) Objects.requireNonNull(objective).setDisplayName(TextUtil.colorize(s));
    }

    @Nonnull Objective registerObjective(boolean replace);

    @Nonnull Objective getObjective();

    default @Nullable Objective getObjective(String id) {
        return getBoard().getObjective(id);
    }

    default void removeObjective(String id) {
        if (getObjective(id) != null) Objects.requireNonNull(getObjective(id)).unregister();
    }

    default void setScore(@Nonnull String content, int id) {
        setScore(content, null, id);
    }

    void setScore(@Nonnull String prefix, String content, int id);

    void removeScore(int id);

    default void set(Player player) {
        player.setScoreboard(getBoard());
    }
}
