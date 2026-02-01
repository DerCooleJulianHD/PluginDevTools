package de.api.devtools.scoreboard.score;

import de.api.devtools.scoreboard.builder.ScoreboardBuilder;
import de.api.devtools.utils.TextUtil;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nullable;
import java.util.List;

public class AnimatedScore extends BukkitRunnable implements IScore<List<String>> {

    private final ScoreboardBuilder builder;

    private final int id;
    private List<String> content;
    protected int index = 0;
    private final long ticks;

    public AnimatedScore(ScoreboardBuilder builder, long ticks, @NonNull List<String> content, int id) {
        this.builder = builder;
        this.content = content;
        this.id = id;
        this.ticks = ticks;
        showScore();
        runTaskTimer(builder.getPlugin(), 0, ticks);
    }

    public final long getTicks() {
        return ticks;
    }

    @Override
    public final int getScore() {
        return id;
    }

    @Override
    public final void setContent(List<String> content) {
        this.content = content;
    }

    public final void addLine(String s) {
        content.add(s);
    }

    public final void setLine(int id, String s) {
        final int index = content.indexOf(s);

        if (index < 0)
            addLine(s);

        content.set(index, s);
    }

    @Override
    public final List<String> getContent() {
        return content;
    }

    @Override
    public final @NonNull ScoreboardBuilder getScoreboardBuilder() {
        return builder;
    }

    public final String getPrefix(String line) {
        return TextUtil.colorize(line.substring(0, 24));
    }

    public final String getSuffix(String line) {
        return TextUtil.colorize(line.substring(24, line.length() - 1));
    }

    @Nullable
    private Entry getEntry() {
        for (Entry name : Entry.values()) {
            if (id == name.getId()) {
                return name;
            }
        }

        return null;
    }

    @Nullable
    private Team getScoreTeam() {
        final Scoreboard scoreboard = builder.getBoard();
        final Entry name = getEntry();

        if (name == null)
            return null;

        Team team = scoreboard.getEntryTeam(name.getEntryName());
        if (team == null) team = scoreboard.registerNewTeam(name.name());
        team.addEntry(name.getEntryName());
        return team;
    }

    @Override
    public final void showScore() {
        final Objective objective = builder.getMainObjective();
        final Entry name = getEntry();

        if (name == null)
            return;

        if (objective.getScore(name.getEntryName()).isScoreSet())
            return;

        objective.getScore(name.getEntryName()).setScore(id);
    }

    @Override
    public final void hideScore() {
        final Scoreboard scoreboard = builder.getBoard();
        final Objective objective = builder.getMainObjective();
        final Entry name = getEntry();

        if (name == null)
            return;

        if (!objective.getScore(name.getEntryName()).isScoreSet())
            return;

        scoreboard.resetScores(name.getEntryName());
    }

    @Override
    public void run() {
        final String line = content.get(index);
        if (line == null) return;
        final Team team = getScoreTeam();
        if (team == null) return;
        team.setPrefix(getPrefix(line));
        team.setSuffix(getSuffix(line));
        setIndex(index + 1);
        if (index >= content.size() - 1) index = 0;
    }

    public void setIndex(int index) {
        this.index = index;
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
