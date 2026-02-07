package de.api.devtools.common.utils;

import org.bukkit.ChatColor;

public enum Messages {

    COMMAND_NO_PERMISSION(ChatColor.RED + "Sorry! but you don't have the Permission to run this command!"),
    COMMAND_INVALID_SENDER(ChatColor.RED + "Only players are allowed to run this command!"),
    COMMAND_EMPTY_HELP(ChatColor.RED + "There is no Help available!");

    private final String value;

    Messages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
