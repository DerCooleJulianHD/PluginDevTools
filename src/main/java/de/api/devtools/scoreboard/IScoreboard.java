package de.api.devtools.scoreboard;

import de.api.devtools.utils.TextUtil;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public interface IScoreboard {

    @Nonnull Scoreboard getBoard();

    default void setTitle(String s) {
        this.setTitle(getObjective(), s);
    }

    default void setTitle(Objective objective, String s) {
        if (objective != null) Objects.requireNonNull(objective).setDisplayName(TextUtil.colorize(s));
    }

    @Nonnull Objective registerObjective(boolean replace);

    @Nonnull Objective getObjective();

    default @Nullable Objective getObjective(String id) {
        return getBoard().getObjective(id);
    }

    default void removeObjective(String id) {
        if (getObjective(id) != null) Objects.requireNonNull(getObjective(id)).unregister();
    }

    default void setScore(@Nonnull String content, int id) {
        setScore(content, null, id);
    }

    void setScore(@Nonnull String prefix, String content, int id);

    void removeScore(int id);

    default void set(Player player) {
        player.setScoreboard(getBoard());
    }

    enum Entry {
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
