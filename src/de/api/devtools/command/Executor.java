package de.api.devtools.command;

import org.bukkit.command.CommandExecutor;

public interface Executor<T> extends CommandExecutor {
    void execute(T sender, String[] args);
}
