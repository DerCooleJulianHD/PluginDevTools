package de.api.devtools.command;

import de.api.devtools.common.Validate;
import de.api.devtools.utils.Messages;
import de.api.devtools.load.AutoLoadable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

//: base object for any type of command
public abstract class SimpleCommand extends PluginCommand implements CommandExecutor, AutoLoadable {

    protected final Class<? extends CommandSender> type;
    protected final org.bukkit.command.PluginCommand bukkitPluginCommand;

    public SimpleCommand(String name, Class<? extends CommandSender> type) {
        super(name);
        this.type = type;
        this.bukkitPluginCommand = Validate.nonNull(plugin.getCommand(name), "No such Command found. do you forget to register it in the plugin.yml ?");
        if (getAutoLoad()) bukkitPluginCommand.setExecutor(this);
    }

    @Override
    public final boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (requiresPermission() && (!sender.hasPermission(getPermission()))) {
            sender.sendMessage(Messages.COMMAND_NO_PERMISSION.getValue());
            return false;
        }

        if (requiresPlayer() && (!(sender instanceof Player))) {
            sender.sendMessage(Messages.COMMAND_INVALID_SENDER.getValue());
            return false;
        }

        execute(sender, args);
        return true;
    }

    @Override
    public @Nullable String getDescription() {
        return getPluginCommand().getDescription();
    }

    public final boolean requiresPlayer() {
        return getType().equals(Player.class);
    }

    public final org.bukkit.command.PluginCommand getPluginCommand() {
        return bukkitPluginCommand;
    }

    public final Class<?> getType() {
        return type;
    }
}
