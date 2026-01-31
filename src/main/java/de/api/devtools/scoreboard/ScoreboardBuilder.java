package de.api.devtools.scoreboard;

import de.api.devtools.plugin.SpigotPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;

public abstract class ScoreboardBuilder implements IScoreboard {

    protected final SpigotPlugin plugin = SpigotPlugin.getInstance();
    protected final Scoreboard scoreboard;
    protected final Objective objective;

    public ScoreboardBuilder(String displayname, boolean replace) {
        this.scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        this.objective = registerObjective("main", Criteria.DUMMY, displayname, replace);
    }

    public ScoreboardBuilder(Player player, String displayname, boolean replace) {
        if (player.getScoreboard().equals(Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard()))
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        this.scoreboard = player.getScoreboard();

        this.objective = registerObjective("main", Criteria.DUMMY, displayname, replace);
    }

    @Override
    public Scoreboard getBoard() {
        return scoreboard;
    }

    @Override
    public Objective getObjective() {
        return objective;
    }

    public SpigotPlugin getPlugin() {
        return plugin;
    }

    @Override
    public void addPlayer(Player player) {
        player.setScoreboard(scoreboard);
    }

    @Override
    public void setTitle(String s) {
        objective.setDisplayName(s);
    }
}
