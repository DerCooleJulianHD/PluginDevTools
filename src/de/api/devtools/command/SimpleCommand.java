package de.api.devtools.command;

import de.api.devtools.plugin.SpigotPlugin;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class SimpleCommand implements Command, CommandExecutor {

    protected final String name;

    public SimpleCommand(String name) {
        this.name = name;
        if (isAutoLoad())
            SpigotPlugin.getInstance().registerCommand(this);
    }

    @Deprecated
    @Override public final boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (hasPermission() && !sender.hasPermission(getPermission())) {
            sender.sendMessage(getNoPermissionMessage());
            return true;
        }

        if (requiresPlayer()) {
            if (!isPlayer(sender)) {
                sender.sendMessage(getWrongSenderMessage());
                return true;
            }

            execute((Player) sender, args);
        }

        execute(sender, args);
        return true;
    }

    @Override
    public final String getName() {
        return name;
    }

    public void execute(CommandSender sender, String[] args) {}

    public void execute(Player player, String[] args) {}
}
