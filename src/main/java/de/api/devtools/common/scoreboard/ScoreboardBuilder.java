package de.api.devtools.common.scoreboard;

import de.api.devtools.common.plugin.MinecraftPlugin;
import de.api.devtools.common.utils.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public abstract class ScoreboardBuilder {

    protected final MinecraftPlugin plugin;

    protected final Scoreboard scoreboard;
    protected final Objective objective;

    protected ScoreboardBuilder(MinecraftPlugin plugin, boolean replace) {
        this.plugin = plugin;
        this.scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        this.objective = this.registerObjective(replace);
    }

    protected ScoreboardBuilder(MinecraftPlugin plugin, Player player, boolean replace) {
        this.plugin = plugin;
        player.setScoreboard(Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard());
        this.scoreboard = player.getScoreboard();
        this.objective = this.registerObjective(replace);
    }

    public final Objective registerObjective(String criteria, boolean replace) {
        Objective objective = getObjective("display");

        // here when the old objective is exists.
        if (objective != null) {
            if (!replace) return objective; //: and won't be replaced.
            objective.unregister(); //: and will be removed for replacing it
        }

        // here when the objective is not exist or will be created after replace.
        objective = getBoard().registerNewObjective("display", criteria);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        return objective;
    }

    public final Objective registerObjective(boolean replace) {
        return this.registerObjective("dummy", replace);
    }

    public final Scoreboard getBoard() {
        return scoreboard;
    }

    public final Objective getObjective() {
        return objective;
    }

    public final void setTitle(String s) {
        this.setTitle(getObjective(), s);
    }

    public final void setTitle(Objective objective, String s) {
        if (objective != null) Objects.requireNonNull(objective).setDisplayName(TextUtil.colorize(s));
    }

    public final void setFooter(String s) {
        this.setFooter(getObjective(), s);
    }

    public final void setFooter(Objective objective, String s) {
        final int titleLength = objective.getDisplayName().length();
        final int diffToCenter =  (titleLength / 2) - (s.length() / 2);
        final String footer = " ".repeat(Math.max(0, diffToCenter)) + TextUtil.colorize(s);
        objective.getScore(footer).setScore(0);
    }

    public final void setScore(String prefix, String content, int score) {
        if (score == 0) {
            return;
        }

        final Team team = getScoreTeam(score);

        if (team == null)
            return;

        team.setPrefix(TextUtil.colorize(prefix));
        if (content != null) team.setSuffix(TextUtil.colorize(content));

        showScore(score);
    }

    public final void removeScore(int score) {
        if (score == 0) {
            return;
        }

        final Entry entry = getEntry(score);

        if (entry == null)
            return;

        if (!objective.getScore(entry.getEntryName()).isScoreSet())
            return;

        scoreboard.resetScores(entry.getEntryName());
    }

    private Entry getEntry(int score) {
        for (Entry entry : Entry.values()) {
            if (score == entry.getId()) {
                return entry;
            }
        }

        return null;
    }

    public final void animate(long ticks, ScoreAnimation animation) {
        animation.runTaskTimer(plugin, 0, ticks);
    }

    private Team getScoreTeam(int score) {
        final Entry entry = getEntry(score);

        if (entry == null)
            return null;

        Team team = scoreboard.getEntryTeam(entry.getEntryName());

        if (team != null)
            return team;

        team = scoreboard.registerNewTeam(entry.name());
        team.addEntry(entry.getEntryName());
        return team;
    }

    private void showScore(int score) {
        final Entry entry = getEntry(score);

        if (entry == null)
            return;

        if (objective.getScore(entry.getEntryName()).isScoreSet())
            return;

        objective.getScore(entry.getEntryName()).setScore(score);
    }

    public final Objective getObjective(String id) {
        return getBoard().getObjective(id);
    }

    public final void removeObjective(String id) {
        if (getObjective(id) != null) Objects.requireNonNull(getObjective(id)).unregister();
    }

    public final void setScore( String content, int id) {
        setScore(content, null, id);
    }

    public final void set(Player player) {
        player.setScoreboard(getBoard());
    }

    public final MinecraftPlugin getPlugin() {
        return plugin;
    }
}
