package de.api.devtools.scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import javax.annotation.Nullable;

public abstract class AnimatedScoreboard extends ScoreboardBuilder {

    public AnimatedScoreboard(String displayname, boolean replace) {
        super(displayname, replace);
    }

    public AnimatedScoreboard(Player player, String displayname, boolean replace) {
        super(player, displayname, replace);
    }

    public abstract void update();

    @Override
    public final void setScore(String content, int score) {
        final Team team = getScoreTeam(score);

        if (team == null)
            return;

        team.setPrefix(content.length() > 16 ? content.substring(0, 16) : content);

        if (content.length() > 16) {
            final String suffix = content.substring(17, content.length() - 1);

            team.setSuffix(suffix);
        }

        showScore(score);
    }

    @Override
    public final void removeScore(int score) {
        final EntryName name = getEntryName(score);

        if (name == null)
            return;

        if (!objective.getScore(name.getEntryName()).isScoreSet())
            return;

        scoreboard.resetScores(name.getEntryName());
    }

    @Nullable
    private EntryName getEntryName(int score) {
        for (EntryName name : EntryName.values()) {
            if (score == name.getEntry()) {
                return name;
            }
        }

        return null;
    }

    @Nullable
    private Team getScoreTeam(int score) {
        final EntryName name = getEntryName(score);

        if (name == null)
            return null;

        Team team = scoreboard.getEntryTeam(name.getEntryName());

        if (team == null)
            team = scoreboard.registerNewTeam(name.name());

        team.addEntry(name.getEntryName());
        return team;
    }

    private void showScore(int score) {
        final EntryName name = getEntryName(score);

        if (name == null)
            return;

        if (objective.getScore(name.getEntryName()).isScoreSet())
            return;

        objective.getScore(name.getEntryName()).setScore(score);
    }



    private enum EntryName {

        ENTRY_0(0 ,ChatColor.WHITE.toString()),
        ENTRY_1(1 ,ChatColor.RED.toString()),
        ENTRY_2(2 ,ChatColor.YELLOW.toString()),
        ENTRY_3(3 ,ChatColor.GREEN.toString()),
        ENTRY_4(4 ,ChatColor.GRAY.toString()),
        ENTRY_5(5 ,ChatColor.DARK_RED.toString()),
        ENTRY_6(6 ,ChatColor.BOLD.toString()),
        ENTRY_7(7 ,ChatColor.UNDERLINE.toString()),
        ENTRY_8(8 ,ChatColor.AQUA.toString()),
        ENTRY_9(9 ,ChatColor.DARK_GRAY.toString()),
        ENTRY_10(10 ,ChatColor.BLACK.toString()),
        ENTRY_11(11 ,ChatColor.GOLD.toString()),
        ENTRY_12(12 ,ChatColor.DARK_PURPLE.toString()),
        ENTRY_13(13 ,ChatColor.LIGHT_PURPLE.toString()),
        ENTRY_14(14 ,ChatColor.STRIKETHROUGH.toString()),
        ENTRY_15(15 ,ChatColor.RESET.toString());

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
