package de.api.devtools.command;

import de.api.devtools.plugin.SpigotPlugin;
import org.bukkit.command.PluginCommand;

public abstract class PluginCommandExecutor<T> implements Command<T> {

    private final SpigotPlugin plugin = SpigotPlugin.getInstance();

    private final PluginCommand bukkitPluginCommand;

    private final String name;

    public PluginCommandExecutor(String name) {
        this.name = name;
        this.bukkitPluginCommand = plugin.getCommand(name);
        if (isAutoLoad())
            SpigotPlugin.getInstance().registerCommand(this);
    }

    @Override
    public String getName() {
        return name;
    }

    public PluginCommand getPluginCommand() {
        return bukkitPluginCommand;
    }

    public SpigotPlugin getPlugin() {
        return plugin;
    }
}
