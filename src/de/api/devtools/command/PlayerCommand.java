package de.api.devtools.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//: type of command that only players can execute
public abstract class PlayerCommand extends CommandExecutor<Player> {

    public PlayerCommand(String name) {
        super(name);
    }

    @Override
    @Deprecated
    public final boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (hasPermission() && !sender.hasPermission(getPermission())) {
            sender.sendMessage(MESSAGE_NO_PERMISSION);
            return false;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MESSAGE_INVALID_SENDER);
            return false;
        }

        execute(player, args);
        return true;
    }

    @Override
    public final boolean requiresPlayer() {
        return true;
    }
}
