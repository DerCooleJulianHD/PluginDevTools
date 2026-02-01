package de.api.devtools.scoreboard;

import de.api.devtools.plugin.SpigotPlugin;
import de.api.devtools.utils.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class ScoreboardBuilder implements IScoreboard {

    protected final SpigotPlugin plugin = SpigotPlugin.getInstance();
    protected final Scoreboard scoreboard;
    protected final Objective objective;

    protected ScoreboardBuilder(boolean replace) {
        this.scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        this.objective = this.registerObjective(replace);
    }

    protected ScoreboardBuilder(Player player, boolean replace) {
        player.setScoreboard(Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard());
        this.scoreboard = player.getScoreboard();
        this.objective = this.registerObjective(replace);
    }

    @Override
    public final @Nonnull Objective registerObjective(boolean replace) {
        Objective objective = getObjective("display");

        // here when the old objective is exists.
        if (objective != null) {
            if (!replace) return objective; //: and won't be replaced.
            objective.unregister(); //: and will be removed for replacing it
        }

        // here when the objective is not exist or will be created after replace.
        objective = getBoard().registerNewObjective("display", Criteria.DUMMY.getId());
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        return objective;
    }

    @Override
    public final @Nonnull Scoreboard getBoard() {
        return scoreboard;
    }

    @Override
    public final @Nonnull Objective getObjective() {
        return objective;
    }

    @Override
    public final void setScore(@NonNull String prefix, String content, int id) {
        final Team team = getScoreTeam(id);

        if (team == null)
            return;

        team.setPrefix(TextUtil.colorize(prefix));

        if (content == null)
            return;

        team.setSuffix(TextUtil.colorize(content));
    }

    @Override
    public final void removeScore(int id) {
        final Entry name = getEntry(id);

        if (name == null)
            return;

        if (!objective.getScore(name.getEntryName()).isScoreSet())
            return;

        scoreboard.resetScores(name.getEntryName());
    }

    @Nullable
    private Entry getEntry(int id) {
        for (Entry name : Entry.values()) {
            if (id == name.getId()) {
                return name;
            }
        }

        return null;
    }

    public final void animate(int id, BukkitRunnable runnable) {}

    @Nullable
    private Team getScoreTeam(int id) {
        final Entry name = getEntry(id);

        if (name == null)
            return null;

        Team team = scoreboard.getEntryTeam(name.getEntryName());
        if (team == null) team = scoreboard.registerNewTeam(name.name());
        team.addEntry(name.getEntryName());
        return team;
    }

    private void showScore(int id) {
        final Entry name = getEntry(id);

        if (name == null)
            return;

        if (objective.getScore(name.getEntryName()).isScoreSet())
            return;

        objective.getScore(name.getEntryName()).setScore(id);
    }

    public final SpigotPlugin getPlugin() {
        return plugin;
    }
}
