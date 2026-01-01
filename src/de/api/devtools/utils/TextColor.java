package de.api.devtools.utils;

import org.bukkit.ChatColor;

public class TextColor {

    public static char COLOR_CHAR = ChatColor.COLOR_CHAR;

    public static char ALTERNATE_COLOR_CHAR = '&';

    public static String colorize(String v) {
        return ChatColor.translateAlternateColorCodes(ALTERNATE_COLOR_CHAR, v);
    }

    public static String stripColor(String v) {
        return ChatColor.stripColor(v);
    }
}
