package de.api.devtools.common.command;

import de.api.devtools.common.plugin.MinecraftPlugin;
import de.api.devtools.common.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CommandGroup extends SimpleCommand implements TabCompleter {

    @NonNull protected final Map<String, PluginCommand> commands = new HashMap<>();

    public CommandGroup(@NonNull MinecraftPlugin plugin, @NonNull String name, @Nullable String permission) {
        super(plugin, name, permission, false);
    }

    @Override
    public final void execute(@NonNull CommandSender sender, @NonNull String[] args) {
        if (args.length == 0) {
            this.sendHelpInfo(sender);
            return;
        }

        final String index = args[0].toLowerCase();
        final PluginCommand command = getCommand(index);

        if (command == null) {
            sender.sendMessage(ChatColor.RED + "This command doesn't exist!");
            return;
        }

        command.onCommandExecute(sender, args);
    }

    @Override
    public final List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String[] args) {
        final int index = args.length;

        if (index == 1) {
           return commands.keySet().stream().toList();
        }

        final PluginCommand pluginCommand = getCommand(args[0]);

        if (!(pluginCommand instanceof TabCompleter tabCompleter))
            return new ArrayList<>();

        return tabCompleter.onTabComplete(sender, command, label, args);
    }

    public final void sendHelpInfo(CommandSender sender) {
        if (commands.isEmpty()) {
            sender.sendMessage(Messages.COMMAND_EMPTY_HELP.getValue());
            return;
        }

        final StringBuilder format = new StringBuilder(ChatColor.GOLD  + " Help Info: \n");

        commands.forEach((name, command) -> {
            final String line = ChatColor.DARK_GRAY + "- " +  ChatColor.YELLOW + "/" + name + ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + command.getDescription();

            format.append(line);
            format.append("\n");
        });

        sender.sendMessage(format.toString());
    }

    public final void addCommand(PluginCommand command) {
        this.commands.put(command.getName().toLowerCase(), command);
    }

    public final void removeCommand(String name) {
        this.commands.remove(name.toLowerCase());
    }

    public final @Nullable PluginCommand getCommand(String name) {
        return this.commands.get(name.toLowerCase());
    }

    @NonNull public Map<String, PluginCommand> getCommands() {
        return commands;
    }
}
