package de.api.devtools.common.command;

import org.bukkit.command.CommandSender;

public interface ICommandExecutor {
    void execute(CommandSender sender, String[] args);
}
