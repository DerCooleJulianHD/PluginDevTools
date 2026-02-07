package de.api.devtools.common.command;

import de.api.devtools.common.utils.load.Loadable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public abstract class SimpleCommand extends PluginCommand implements CommandExecutor, Loadable {

    protected final org.bukkit.command.PluginCommand bukkitPluginCommand;

    protected SimpleCommand(@Nonnull String name, @Nullable String permission, boolean requiresPlayer) {
        super(name, permission, requiresPlayer);
        this.bukkitPluginCommand = Objects.requireNonNull(plugin.getCommand(name), "No such Command found. do you forget to register it in the plugin.yml ?!");
        if (getAutoLoad()) load();
    }

    @Override
    public void load() {
        bukkitPluginCommand.setExecutor(this);
    }

    @Override
    public boolean isLoaded() {
        return plugin.getCommand(name) != null;
    }

    @Override
    public final boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        return onCommandExecute(sender, args);
    }

    @Override
    public @Nullable String getDescription() {
        return getPluginCommand().getDescription();
    }

    public final org.bukkit.command.PluginCommand getPluginCommand() {
        return bukkitPluginCommand;
    }
}
