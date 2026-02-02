package de.api.devtools.command;

import de.api.devtools.common.Validate;
import de.api.devtools.load.AutoLoadable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class SimpleCommand extends PluginCommand implements CommandExecutor, AutoLoadable {

    protected final org.bukkit.command.PluginCommand bukkitPluginCommand;

    public SimpleCommand(@Nonnull String name, @Nullable String permission, Class<? extends CommandSender> type) {
        super(name, permission, type);
        this.bukkitPluginCommand = Validate.nonNull(plugin.getCommand(name), "No such Command found. do you forget to register it in the plugin.yml ?");
        if (getAutoLoad()) bukkitPluginCommand.setExecutor(this);
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
