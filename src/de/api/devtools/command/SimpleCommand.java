package de.api.devtools.command;

import org.bukkit.command.CommandSender;

public abstract class SimpleCommand extends PluginCommandExecutor<CommandSender> {

    public SimpleCommand(String name) {
        super(name);
    }

    @Deprecated
    @Override public final boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (hasPermission() && !sender.hasPermission(getPermission())) {
            sender.sendMessage(getNoPermissionMessage());
            return false;
        }

        execute(sender, args);
        return true;
    }

    @Override
    public final boolean requiresPlayer() {
        return false;
    }
}
