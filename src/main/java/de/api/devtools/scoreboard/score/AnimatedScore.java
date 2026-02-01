package de.api.devtools.scoreboard.score;

import de.api.devtools.plugin.SpigotPlugin;
import de.api.devtools.scoreboard.builder.ScoreboardBuilder;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public final class AnimatedScore implements IScore {

    private final ScoreboardBuilder builder;

    private final int id;
    private String content;

    private final long ticks;

    private final Consumer<AnimatedScore> update;

    public AnimatedScore(ScoreboardBuilder builder, long ticks, String content, int id, Consumer<AnimatedScore> update) {
        this.builder = builder;
        this.content = content;
        this.id = id;
        this.ticks = ticks;
        this.update = update;
        run(ticks);
    }

    public long getTicks() {
        return ticks;
    }

    @Override
    public int getScore() {
        return id;
    }

    @Override
    public void setContent(String content) {
        this.content = content;

        final Team team = getScoreTeam();

        if (team == null)
            return;

        team.setPrefix(getPrefix());
        team.setSuffix(getContent());

        showScore();
    }

    @Override
    public @NonNull String getPrefix() {
        final String prefix = content;
        return prefix.substring(0, 16);
    }

    @Override
    public String getContent() {
        final String suffix = content;
        return suffix.substring(16, suffix.length() -1);
    }

    @Override
    public @NonNull String getFullContent() {
        return content;
    }

    @Override
    public @NonNull ScoreboardBuilder getScoreboardBuilder() {
        return builder;
    }

    @Nullable
    private EntryName getEntryName() {
        for (EntryName name : EntryName.values()) {
            if (id == name.getEntry()) {
                return name;
            }
        }

        return null;
    }

    @Nullable
    private Team getScoreTeam() {
        final Scoreboard scoreboard = builder.getBoard();
        final EntryName name = getEntryName();

        if (name == null)
            return null;

        Team team = scoreboard.getEntryTeam(name.getEntryName());

        if (team == null)
            team = scoreboard.registerNewTeam(name.name());

        team.addEntry(name.getEntryName());
        return team;
    }

    @Override
    public void showScore() {
        final Objective objective = builder.getMainObjective();
        final EntryName name = getEntryName();

        if (name == null)
            return;

        if (objective.getScore(name.getEntryName()).isScoreSet())
            return;

        objective.getScore(name.getEntryName()).setScore(id);
    }

    @Override
    public void hideScore() {
        final Scoreboard scoreboard = builder.getBoard();
        final Objective objective = builder.getMainObjective();
        final EntryName name = getEntryName();

        if (name == null)
            return;

        if (!objective.getScore(name.getEntryName()).isScoreSet())
            return;

        scoreboard.resetScores(name.getEntryName());
    }

    private void update() {
        if (update != null) update.accept(this);
    }

    private void run(long period) {
        new BukkitRunnable() {
            @Override
            public void run() {
                update();
            }
        }.runTaskTimer(SpigotPlugin.getInstance(), 0, period);
    }

    private enum EntryName {

        ENTRY_0(0, ChatColor.WHITE.toString()),
        ENTRY_1(1, ChatColor.RED.toString()),
        ENTRY_2(2, ChatColor.YELLOW.toString()),
        ENTRY_3(3, ChatColor.GREEN.toString()),
        ENTRY_4(4, ChatColor.GRAY.toString()),
        ENTRY_5(5, ChatColor.DARK_RED.toString()),
        ENTRY_6(6, ChatColor.BOLD.toString()),
        ENTRY_7(7, ChatColor.UNDERLINE.toString()),
        ENTRY_8(8, ChatColor.AQUA.toString()),
        ENTRY_9(9, ChatColor.DARK_GRAY.toString()),
        ENTRY_10(10, ChatColor.BLACK.toString()),
        ENTRY_11(11, ChatColor.GOLD.toString()),
        ENTRY_12(12, ChatColor.DARK_PURPLE.toString()),
        ENTRY_13(13, ChatColor.LIGHT_PURPLE.toString()),
        ENTRY_14(14, ChatColor.STRIKETHROUGH.toString()),
        ENTRY_15(15, ChatColor.RESET.toString());

        private final int entry;
        private final String entryName;

        EntryName(int entry, String entryName) {
            this.entry = entry;
            this.entryName = entryName;
        }

        public final int getEntry() {
            return entry;
        }

        public final String getEntryName() {
            return entryName;
        }
    }
}
