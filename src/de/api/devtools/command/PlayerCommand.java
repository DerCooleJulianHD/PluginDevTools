package de.api.devtools.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class PlayerCommand extends PluginCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        final boolean canExecute = checkSender(sender);

        if (args.length <= info.args().length)
            sendSyntax(sender);

        if (canExecute)
            execute((Player) sender, args);

        return true;
    }

    public abstract void execute(Player player, String[] args);

    @Override
    protected final boolean checkSender(CommandSender sender) {
        final boolean isPlayer = sender instanceof Player;

        if (!isPlayer) {
            sender.sendMessage(ChatColor.RED + "Only Players are allowed to execute this command.");
            return false;
        }

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
