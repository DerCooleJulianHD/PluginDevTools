package de.api.devtools.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public interface AutoTabComplete extends TabCompleter {

    default List<String> onAutoTabComplete(CommandSender sender, String[] args, int index) {
        return List.of();
    }

    @Deprecated
    @Override default List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        return onAutoTabComplete(sender, args, args.length) != null ? onAutoTabComplete(sender, args, args.length) : new ArrayList<>();
    }
}
