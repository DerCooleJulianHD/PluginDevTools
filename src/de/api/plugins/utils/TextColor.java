package de.api.plugins.utils;

import org.bukkit.ChatColor;

public class TextColor {

    public static char COLOR_CHAR = ChatColor.COLOR_CHAR;

    public static char ALTERNATE_COLOR_CHAR = '&';

    public static String colorize(String string) {
        return ChatColor.translateAlternateColorCodes(ALTERNATE_COLOR_CHAR, string);
    }

    public static String stripColor(String string) {
        return ChatColor.stripColor(string);
    }
}
