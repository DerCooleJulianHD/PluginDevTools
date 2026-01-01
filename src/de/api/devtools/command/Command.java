package de.api.devtools.command;

import de.api.devtools.plugin.SpigotPlugin;
import de.api.devtools.utils.AutoLoad;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface Command extends AutoTabComplete {

    String getName();

    default String getPermission() {
        final Class<? extends Command> clazz = getClass();

        return clazz.isAnnotationPresent(PermissionRequired.class) ? clazz.getDeclaredAnnotation(PermissionRequired.class).value() : null;
    }

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

    default boolean hasPermission() {
        return getPermission() != null && !getPermission().isEmpty();
    }

    default boolean isAutoLoad() {
        return getClass().isAnnotationPresent(AutoLoad.class);
    }

    default String getDescription() {
        return SpigotPlugin.getInstance().getCommand(getName()).getDescription();
    }
}
