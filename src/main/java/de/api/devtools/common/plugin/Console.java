package de.api.devtools.common.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.logging.Level;

public final class Console {

    private final MinecraftPlugin plugin;
    private final ConsoleCommandSender sender = Bukkit.getConsoleSender();

    public Console(MinecraftPlugin plugin) {
        this.plugin = plugin;
    }

    public void sendMessage(String prefix, String message) {
        sender.sendMessage(prefix + message);
    }

    public void sendMessage(String message) {
        sendMessage(plugin.getPrefix(), message);
    }

    public void sendMessage() {
        sendMessage("");
    }

    public void log(Level level, String message) {
        plugin.getLogger().log(level, message);
    }

    public MinecraftPlugin getPlugin() {
        return plugin;
    }

    public ConsoleCommandSender getSender() {
        return sender;
    }
}
