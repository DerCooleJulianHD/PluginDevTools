package de.api.devtools.command;

import de.api.devtools.plugin.SpigotPlugin;
import org.apache.commons.lang3.Validate;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.logging.Level;

public abstract class PluginCommand implements CommandExecutor, TabCompleter {

    protected final SpigotPlugin plugin = SpigotPlugin.getInstance();
    protected final CommandInfo info = getClass().getDeclaredAnnotation(CommandInfo.class);;

    public PluginCommand() {
        Validate.notNull(this.info, getClass().getSimpleName() + " misses CommandInfo Annotation");
        Validate.notNull(plugin.getServer().getPluginCommand(info.name()), "No Such Command: " + info.name() + ", please register in the plugin.yml");
        plugin.getServer().getPluginCommand(info.name()).setExecutor(this);
    }

    @Override
    // from: CommandExecutor
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (!info.permission().isEmpty() && (!sender.hasPermission(info.permission()))) {
                sender.sendMessage(ChatColor.RED + "You doun't have the permission to execute this command.");
                return false;
            }

            if (info.requiresPlayer()) {
                if (!(sender instanceof Player player)) {
                    sender.sendMessage(ChatColor.RED + "Only players can execute this type of command.");
                    return false;
                }

                this.execute(player, args);
                return true;
            }

            execute(sender, args);
            return true;
        } catch (Exception e) {
            plugin.getLogger().log(Level.SEVERE, "couldn't execute command: '" + info.name() + "'.", e);
        }

        return false;
    }

    @Override
    // from: TabCompleter
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        return this.onTabComplete(sender, args);
    }

    public final void sendSyntax(CommandSender sender, String... args) {
        String arguments = "";

        for (String arg : args)
           arguments = ChatColor.AQUA + arg + ChatColor.GRAY + ", ";

        final String out = ChatColor.RED + "Syntax: " +
                ChatColor.DARK_GRAY + "/ " +
                ChatColor.GRAY + getInfo().name() +
                arguments;

        sender.sendMessage(out);
    }

    public void execute(CommandSender sender, String[] args) {}

    public void execute(Player player, String[] args) {}

    public List<String> onTabComplete(CommandSender sender, String[] strings) {
        return List.of();
    }

    public final CommandInfo getInfo() {
        return info;
    }
}


