package de.api.devtools.plugin;
import de.api.devtools.listener.ListenerManager;
import org.bukkit.ChatColor;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.File;

public interface MinecraftPlugin extends Prefixable {

    // this is the [bukkit] console sender.
    @NonNull Console getConsole();

    default void init() {} // plugin load logic

    void onPluginStart(); // plugin enable logic

    default void onPluginStop() {} // plugin disable logic

    @NonNull ListenerManager getListenerManager();

    // printing out to console, that the plugin has been enabled.
    default void sendStartMessage() {
        getConsole().sendMessage(ChatColor.GREEN + getPluginFullName() + " has been successfully Enabled!");
    }

    // printing out to console, that the plugin has been disabled.
    default void sendStopMessage() {
        getConsole().sendMessage(ChatColor.RED + getPluginName() + " has been successfully Disabled!");
    }

    // the 'config.yml' file in the main plugin data folder.
    @NonNull PluginConfigFile getPluginConfig();

    // loads or creates a new instance of the 'config.yml' file.
    void loadPluginConfig(boolean def);

    /* returns the plugin name in this format: '$plugin_name$[from: plugin.yml]-v.$version$'
    example: yourplugin-v.1.0 */
    default String getPluginFullName() {
        return getPluginName() + "-v." + getPluginVersion();
    }

    // returns the version string from the plugin description file.
    @NonNull String getPluginVersion();

    // returns the simple plugin name from the plugin description file.
    @NonNull String getPluginName();

    default @NonNull MinecraftPlugin getSubType() {
        return this;
    }

    void loadPluginsFromFile(File dir);
}
