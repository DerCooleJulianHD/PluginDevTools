package de.api.devtools.plugin;

import de.api.devtools.bundle.Bundle;
import de.api.devtools.listener.ListenerManager;
import de.api.devtools.utils.Console;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.io.File;

public interface MinecraftPlugin extends Prefixable {

    // this is the [bukkit] console sender.
    Console getConsole();

    default void init() {} // plugin load logic

    void onPluginStart(); // plugin enable logic

    default void onPluginStop() {} // plugin disable logic

    ListenerManager getListenerManager();

    // printing out to console, that the plugin has been enabled.
    default void sendStartMessage() {
        getConsole().sendMessage(ChatColor.GREEN + getPluginFullName() + " has been successfully Enabled!");
    }

    // printing out to console, that the plugin has been disabled.
    default void sendStopMessage() {
        getConsole().sendMessage(ChatColor.RED + getPluginName() + " has been successfully Disabled!");
    }

    // the 'config.yml' file in the main plugin data folder.
    PluginConfigFile getPluginConfig();

    // loads or creates a new instance of the 'config.yml' file.
    void loadPluginConfig(boolean def);

    /* returns the plugin name in this format: '$plugin_name$[from: plugin.yml]-v.$version$'
    example: yourplugin-v.1.0 */
    default String getPluginFullName() {
        return getPluginName() + "-v." + getPluginVersion();
    }

    // returns the version string from the plugin description file.
    String getPluginVersion();

    // returns the simple plugin name from the plugin description file.
    String getPluginName();

    default MinecraftPlugin getSubType() {
        return this;
    }

    default void loadPluginsFromFile(File dir) {
        final Server server = Bukkit.getServer();
        final PluginManager manager = server.getPluginManager();

        final Plugin[] plugins = manager.loadPlugins(dir);

        if (plugins.length > 0) {
            server.getConsoleSender().sendMessage(ChatColor.GREEN + "found some plugins in: " + dir.getName());

            for (Plugin target : plugins) {
                manager.enablePlugin(target);
            }
        }

        server.getConsoleSender().sendMessage(ChatColor.GREEN + "all plugins from: " + dir.getName() + " has been loaded and enabled on the server!");
    }
}
