package de.api.devtools.scoreboard;

import de.api.devtools.plugin.SpigotPlugin;
import de.api.devtools.utils.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public abstract class ScoreboardBuilder implements IScoreboard {

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
    public final void setScore(@Nonnull String prefix, String content, int score) {
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

    @Override
    public final void removeScore(int score) {
        if (score == 0) {
            return;
        }

        final Entry name = getEntry(score);

        if (name == null)
            return;

        if (!objective.getScore(name.getEntryName()).isScoreSet())
            return;

        scoreboard.resetScores(name.getEntryName());
    }

    @Nullable
    private Entry getEntry(int score) {
        for (Entry name : Entry.values()) {
            if (score == name.getId()) {
                return name;
            }
        }

        return null;
    }

    public final void animate(long ticks, @Nonnull ScoreAnimation animation) {
        animation.runTaskTimer(plugin, 0, ticks);
    }

    @Nullable
    private Team getScoreTeam(int score) {
        final Entry name = getEntry(score);

        if (name == null)
            return null;

        Team team = scoreboard.getEntryTeam(name.getEntryName());
        if (team == null) team = scoreboard.registerNewTeam(name.name());
        team.addEntry(name.getEntryName());
        return team;
    }

    private void showScore(int score) {
        final Entry name = getEntry(score);

        if (name == null)
            return;

        if (objective.getScore(name.getEntryName()).isScoreSet())
            return;

        objective.getScore(name.getEntryName()).setScore(score);
    }

    public final SpigotPlugin getPlugin() {
        return plugin;
    }

    private enum Entry {
        ENTRY_0(0, "§f"),
        ENTRY_1(1, "§c"),
        ENTRY_2(2, "§e"),
        ENTRY_3(3, "§a"),
        ENTRY_4(4, "§7"),
        ENTRY_5(5, "§4"),
        ENTRY_6(6, "§l"),
        ENTRY_7(7, "§m"),
        ENTRY_8(8, "§b"),
        ENTRY_9(9, "§8"),
        ENTRY_10(10, "§0"),
        ENTRY_11(11, "§6"),
        ENTRY_12(12, "§5"),
        ENTRY_13(13, "§d"),
        ENTRY_14(14, "§n"),
        ENTRY_15(15, "§r");

        private final int id;
        private final String entryName;

        Entry(int id, String entryName) {
            this.id = id;
            this.entryName = entryName;
        }

        public final int getId() {
            return id;
        }

        public final String getEntryName() {
            return entryName;
        }
    }
}
