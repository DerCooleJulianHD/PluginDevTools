package de.api.plugins.plugin;

import de.api.plugins.bundle.Bundle;
import de.api.plugins.listener.ListenerBundle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

import java.util.Map;

public interface MinecraftPlugin extends Prefixable {

    // this is the [bukkit] console sender.
    default ConsoleCommandSender getConsole() {
        return Bukkit.getConsoleSender();
    }

    default void onPluginInit() {} // plugin load logic

    void onPluginStart(); // plugin enable logic

    default void onPluginStop() {} // plugin disable logic

    // printing out to console, that the plugin has been enabled.
    default void sendEnableMessage() {
        getConsole().sendMessage(ChatColor.GREEN + getPluginFullName() + " has been successfully Enabled!");
    }

    // printing out to console, that the plugin has been disabled.
    default void sendDisableMessage() {
        getConsole().sendMessage(ChatColor.RED + getPluginFullName() + " has been successfully Disabled!");
    }

    // the 'config.yml' file in the main plugin data folder.
    PluginConfigFile getConfiguration();

    /* returns the plugin name in this format: '$plugin_name$:[from plugin.yml file]-v.$version$'
    example: yourplugin-v.1.0.0. */
    default String getPluginFullName() {
        return getPluginName() + "-v." + getPluginVersion();
    }

    // returns the version string from the plugin description file.
    String getPluginVersion();

    // returns the simple plugin name from the plugin description file.
    String getPluginName();

    // adds a new listener bundle to the server.
    default void addListeners(String name, ListenerBundle bundle) {
        if (!getBundles("listeners").containsKey(name)) getBundles("listeners").put(name, bundle);
    }

    // removes a listener bundle from the server.
    default void removeListeners(String name) {
        final ListenerBundle bundle = getListeners(name);

        if (bundle == null)
            return;

        bundle.unregisterAll();
        getBundles("listeners").remove(name);
    }

    // returns a listener bundle which is registered on the server.
    default ListenerBundle getListeners(String name) {
        return (getBundles("listeners") != null) ? (ListenerBundle) getBundles("listeners").get(name) : ListenerBundle.EMPTY_BUNDLE;
    }

    // returns the storage of all registered bundles
    Map<String, Map<String, Bundle<?>>> getBundles();

    default Map<String, Bundle<?>> getBundles(String id) {
        return getBundles().get(id);
    }
}
