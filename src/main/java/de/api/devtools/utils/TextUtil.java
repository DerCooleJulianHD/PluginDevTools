package de.api.devtools.utils;

import org.bukkit.ChatColor;

import java.util.Arrays;

public class TextUtil {

    public static char COLOR_CHAR = ChatColor.COLOR_CHAR;

    public static char ALTERNATE_COLOR_CHAR = '&';

    public static String colorize(String v) {
        return ChatColor.translateAlternateColorCodes(ALTERNATE_COLOR_CHAR, v);
    }

    public static String stripColor(String v) {
        return ChatColor.stripColor(v);
    }

    public static String boundifyCapitalized(String v) {
        final StringBuilder builder = new StringBuilder();
        String[] split = v.split("_"); // EXP_ORB -> [EXP], [_], [ORB]

        for (String s : split) {
            s = s.toLowerCase();
            char[] chars = s.toCharArray();
            chars[0] = String.valueOf(chars[0]).toUpperCase().charAt(0);
            builder.append(s);
            // [EXP], [_], [ORB] -> Exp_Orb
        }

        return builder.toString().replaceAll("_", " "); // Exp_Orb -> Exp Orb
    }
}
