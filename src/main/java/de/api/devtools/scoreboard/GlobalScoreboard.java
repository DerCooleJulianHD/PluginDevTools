package de.api.devtools.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;

public abstract class GlobalScoreboard implements ScoreboardBuilder {

    protected final Scoreboard scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();

    protected final Objective objective;

    public GlobalScoreboard(String displayName, boolean override) {
        this.objective = registerObjective("main", override, displayName);

        createScoreboard();
    }

    @Override
    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    @Override
    public Objective getObjective() {
        return objective;
    }

    public void addPlayers() {
        Bukkit.getOnlinePlayers().forEach(player -> player.setScoreboard(this.scoreboard));
    }
}
