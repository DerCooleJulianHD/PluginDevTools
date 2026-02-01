package de.api.devtools.command;

import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface ICommandExecutor {

    void execute(@NonNull CommandSender sender, @NonNull String[] args);
}
