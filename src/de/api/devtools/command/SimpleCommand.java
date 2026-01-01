package de.api.devtools.command;

import org.bukkit.command.CommandSender;

//: type of command that all commandSender instances can execute
public abstract class SimpleCommand extends CommandExecutor<CommandSender> {

    public SimpleCommand(String name) {
        super(name);
    }

    @Override
    @Deprecated
    public final boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (hasPermission() && !sender.hasPermission(getPermission())) {
            sender.sendMessage(MESSAGE_NO_PERMISSION);
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
