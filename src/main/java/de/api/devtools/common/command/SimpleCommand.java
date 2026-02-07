package de.api.devtools.common.command;

import de.api.devtools.common.plugin.MinecraftPlugin;
import de.api.devtools.common.utils.load.Loadable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

public abstract class SimpleCommand extends PluginCommand implements CommandExecutor, Loadable {

    protected final org.bukkit.command.PluginCommand bukkitPluginCommand;

    public SimpleCommand(@NonNull MinecraftPlugin plugin, @NonNull String name, @Nullable String permission, boolean requiresPlayer) {
        super(plugin, name, permission, requiresPlayer);
        this.bukkitPluginCommand = Objects.requireNonNull(plugin.getCommand(name), "No such Command found. do you forget to register it in the plugin.yml ?!");
        if (getAutoLoad()) load();
    }

    @Override
    public final void load() {
        Objects.requireNonNull(bukkitPluginCommand).setExecutor(this);
    }

    @Override
    public final boolean isLoaded() {
        return plugin.getCommand(name) != null;
    }

    @Override
    public final boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        return onCommandExecute(sender, args);
    }

    @Override
    public @Nullable String getDescription() {
        return Objects.requireNonNull(bukkitPluginCommand).getDescription();
    }

    public final org.bukkit.command.PluginCommand getBukkitPluginCommand() {
        return bukkitPluginCommand;
    }
}
