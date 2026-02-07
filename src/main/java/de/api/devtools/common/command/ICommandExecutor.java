package de.api.devtools.common.command;

import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface ICommandExecutor {
    void execute(@NonNull CommandSender sender, @NonNull String[] args);
}
