package de.api.devtools.common.scoreboard;

public enum Entry {

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