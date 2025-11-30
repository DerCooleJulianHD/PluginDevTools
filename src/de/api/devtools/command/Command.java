package de.api.devtools.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public interface Command extends Permissible {

    default String getNoPermissionMessage() {
        return ChatColor.RED + "Sorry! but you don't have the Permission to run this command!";
    }

    default String getWrongSenderMessage() {
        return ChatColor.RED + "You can't run this command!";
    }

    default boolean isPlayer(CommandSender sender) {
        return sender instanceof Player player;
    }

    default boolean requiresPlayer() {
        return getClass().isAnnotationPresent(PlayerRequired.class);
    }
}
