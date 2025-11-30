package de.api.devtools.command;

import de.api.devtools.plugin.SpigotPlugin;
import de.api.devtools.utils.functionals.AutoLoad;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public abstract class SimpleCommand implements Command, CommandExecutor, AutoTabComplete {

    protected final String name;

    public SimpleCommand(String name) {
        this.name = name;
        if (getClass().isAnnotationPresent(AutoLoad.class))
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
                sender.sendMessage(getNoPermissionMessage());
                return true;
            }

            execute((Player) sender, args);
        }

        execute(sender, args);
        return true;
    }

    public String getDescription() {
        return SpigotPlugin.getInstance().getCommand(name).getDescription();
    }

    public final String getName() {
        return name;
    }

    public void execute(CommandSender sender, String[] args) {}

    public void execute(Player player, String[] args) {}
}
