package de.api.devtools.scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public interface IScoreboard {

    Scoreboard getBoard();

    Objective getObjective();

    default Objective getObjective(String id) {
        return getBoard().getObjective(id);
    }

    void setScore(String content, int score);

    void setScore(String prefix, String suffix, int score);

    void removeScore(int score);

    void setTitle(String s);

    void addPlayer(Player player);

    default Objective registerObjective(String id, Criteria criteria, String displayname, boolean replace) {
        if (getObjective(id) != null) {
            if (!replace) {
                return getObjective(id);
            }

            getObjective(id).unregister();
        }

        final Objective objective = getBoard().registerNewObjective(id, criteria.getId());

        objective.setDisplayName(displayname);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        return objective;
    }

    default void removeObjective(String id) {
        if (getObjective(id) != null) getObjective(id).unregister();
    }
}
