package de.api.devtools.common.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.logging.Level;

public final class Console {

    @NonNull private final MinecraftPlugin plugin;
    @NonNull private final ConsoleCommandSender sender = Bukkit.getConsoleSender();

    public Console(@NonNull MinecraftPlugin plugin) {
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

    public @NonNull MinecraftPlugin getPlugin() {
        return plugin;
    }

    public @NonNull ConsoleCommandSender getSender() {
        return sender;
    }
}
