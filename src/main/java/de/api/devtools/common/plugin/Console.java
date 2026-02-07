package de.api.devtools.common.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import javax.annotation.Nonnull;
import java.util.logging.Level;

public final class Console {

    @Nonnull private final MinecraftPlugin plugin;
    @Nonnull private final ConsoleCommandSender sender = Bukkit.getConsoleSender();

    public Console(@Nonnull MinecraftPlugin plugin) {
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

    public @Nonnull MinecraftPlugin getPlugin() {
        return plugin;
    }

    public @Nonnull ConsoleCommandSender getSender() {
        return sender;
    }
}
