package de.api.devtools.command;

import de.api.devtools.common.Validate;
import de.api.devtools.plugin.SpigotPlugin;
import de.api.devtools.utils.AutoLoad;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;

import java.util.Objects;

//: base class for any type of custom command
public abstract class CommandExecutor<T> implements org.bukkit.command.CommandExecutor {

    public final String MESSAGE_NO_PERMISSION = ChatColor.RED + "Sorry! but you don't have the Permission to run this command!";
    public final String MESSAGE_INVALID_SENDER = ChatColor.RED + "Sorry! but only players are allowed to run this command!";

    protected final SpigotPlugin plugin = SpigotPlugin.getInstance();
    private final PluginCommand bukkitPluginCommand;
    protected final String name;

    public CommandExecutor(String name) {
        this.name = name;
        this.bukkitPluginCommand = Validate.nonNull(plugin.getCommand(name), "No such Command found. do you forgot to register it in the plugin.yml ?"); ;
        if (isAutoLoad()) bukkitPluginCommand.setExecutor(this);
    }

    public abstract void execute(T sender, String[] args);

    public final String getName() {
        return name;
    }

    public final PluginCommand getPluginCommand() {
        return bukkitPluginCommand;
    }

    public final SpigotPlugin getPlugin() {
        return plugin;
    }

    //@Overrideable
    public String getPermission() {
        return getClass().isAnnotationPresent(PermissionRequired.class) ? getClass().getDeclaredAnnotation(PermissionRequired.class).value() : "";
    }

    //@Overrideable
    public abstract boolean requiresPlayer();

    public final boolean hasPermission() {
        return getPermission() != null && !getPermission().isEmpty();
    }

    public final boolean isAutoLoad() {
        return getClass().isAnnotationPresent(AutoLoad.class);
    }

    //@Overrideable
    public String getDescription() {
        return bukkitPluginCommand.getDescription();
    }
}
