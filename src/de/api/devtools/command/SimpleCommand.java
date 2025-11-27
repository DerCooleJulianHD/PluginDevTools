package de.api.devtools.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class SimpleCommand extends PluginCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        final boolean canExecute = checkSender(sender);

        if (args.length <= info.args().length)
            sendSyntax(sender);

        if (canExecute)
            execute(sender, args);

        return true;
    }

    public abstract void execute(CommandSender sender, String[] args);

    @Override
    protected final boolean checkSender(CommandSender sender) {
        final String permission = info.permission();

        if (permission != null && permission.isEmpty()) {
            if (!sender.hasPermission(permission)) {
                sender.sendMessage(getNoPermissionMessage());
                return false;
            }
        }

        return true;
    }
}
