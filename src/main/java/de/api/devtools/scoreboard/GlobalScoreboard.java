package de.api.devtools.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public abstract class GlobalScoreboard implements ScoreboardBuilder {

    private final Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

    private final Objective objective;

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
}
