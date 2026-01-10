package de.api.devtools.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public abstract class PerPlayerScoreboard implements ScoreboardBuilder {

    private final Scoreboard scoreboard;
    private final Objective objective;

    private final Player owner;

    public PerPlayerScoreboard(Player owner, String displayName, boolean override) {
        this.owner = owner;

        if (owner.getScoreboard().equals(Bukkit.getScoreboardManager().getMainScoreboard())) {
            owner.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }

        this.scoreboard = owner.getScoreboard();
        this.objective = registerObjective("main", override, displayName);

        createScoreboard();
    }

    public Player getOwner() {
        return owner;
    }

    @Override
    public Objective getObjective() {
        return objective;
    }

    @Override
    public Scoreboard getScoreboard() {
        return scoreboard;
    }
}
