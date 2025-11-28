package de.api.devtools.command;

import de.api.devtools.plugin.SpigotPlugin;
import org.apache.commons.lang3.Validate;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class PluginCommand implements CommandExecutor, TabCompleter {

    protected final SpigotPlugin plugin = SpigotPlugin.getInstance();

    @NotNull protected final CommandInfo info = getClass().getDeclaredAnnotation(CommandInfo.class);

    public PluginCommand() {
        Validate.notNull(info, "Commands must have CommandInfo annotations");
        Validate.notNull(plugin.getCommand(info.name()), "This Command hasn't been found! forgot to register in the plugin.yml ?");

        if (getClass().isAnnotationPresent(AutoLoad.class))
            plugin.getCommand(getInfo().name()).setExecutor(this);
    }

    public String getSyntaxFormat() {
        return ChatColor.RED + plugin.getCommand(getInfo().name()).getUsage();
    }

    public final void sendSyntax(CommandSender sender) {
        sender.sendMessage(getSyntaxFormat());
    }

    @Override public abstract boolean onCommand(CommandSender sender, Command command, String s, String[] args);

    @Override public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        return getAutoCompletions(sender, args, args.length);
    }

    public List<String> getAutoCompletions(CommandSender sender, String[] args, int index) {
        return new ArrayList<>();
    }

    public final @NotNull CommandInfo getInfo() {
        return info;
    }

    protected abstract boolean checkSender(CommandSender sender);

    public String getNoPermissionMessage() {
        return ChatColor.RED + "You doun't have the permission.";
    }

    protected boolean checkPermission(CommandSender sender) {
        final String permission = info.permission();

        if (permission != null && permission.isEmpty()) {
            if (!sender.hasPermission(permission)) {
                sender.sendMessage(getNoPermissionMessage());
                return false;
            }
        }

        return true;
    }

    public boolean canExecute(CommandSender sender) {
        return checkSender(sender);
    }
}
