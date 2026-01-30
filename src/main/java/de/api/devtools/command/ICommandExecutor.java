package de.api.devtools.command;

import org.bukkit.command.CommandSender;

public interface ICommandExecutor {

    void execute(CommandSender sender, String[] args);
}
