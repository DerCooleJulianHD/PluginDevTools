package de.api.devtools.scoreboard;

import de.api.devtools.utils.TextUtil;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public interface ScoreboardBuilder {

    Scoreboard getScoreboard();

    Objective getObjective();

    default void setDisplayName(String displayName) {
        this.getObjective().setDisplayName(TextUtil.colorize(displayName));
    }

    void createScoreboard();

    default Objective registerObjective(String id, boolean override, String displayName) {

        if (getScoreboard().getObjective(id) != null) {
            if (!override) return getScoreboard().getObjective(id);
            getScoreboard().getObjective(id).unregister();
        }

        final Objective obj = getScoreboard().registerNewObjective(id, "dummy");

        obj.setDisplayName(TextUtil.colorize(displayName));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        return obj;
    }

    default void unregisterObjective(String id) {
        getScoreboard().getObjective(id).unregister();
    }

    default void setScore(String content, int score) {
        getObjective().getScore(TextUtil.colorize(content)).setScore(score);
    }

    default void removeScore(String content) {
        getScoreboard().resetScores(content);
    }
}
