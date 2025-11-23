package de.api.plugins.plugin;

import de.api.plugins.bundle.Bundle;
import de.api.plugins.listener.ListenerBundle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MinecraftPlugin extends Prefixable {

    // this is the [bukkit] console sender.
    default ConsoleCommandSender getConsole() {
        return Bukkit.getConsoleSender();
    }

    default void onPluginLoad() {} // plugin load logic

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

    /* returns the plugin name in this format: '$plugin_name$[from: plugin.yml]-v.$version$'
    example: yourplugin-v.1.0 */
    default String getPluginFullName() {
        return getPluginName() + "-v." + getPluginVersion();
    }

    // returns the version string from the plugin description file.
    String getPluginVersion();

    // returns the simple plugin name from the plugin description file.
    String getPluginName();

    // adds a new listener bundle to the server.
    default void addListeners(ListenerBundle bundle) {
        if (getBundles("listeners") == null)
            // put the map for holding listener bundles if it doesn't exist yet.
            getBundles().put("listeners", new ArrayList<>());

        final List<Bundle<?>> bundles = getBundles("listeners");

        if (bundles.contains(bundle))
            return;

        bundles.add(bundle);
    }

    // removes a listener bundle from the server.
    default void removeListeners(String name) {
        final ListenerBundle bundle = getListeners(name);

        if (bundle == null)
            return;

        bundle.clear();

        final List<Bundle<?>> listeners = getBundles("listeners");
        listeners.remove(bundle);
    }

    // returns a listener bundle which is registered on the server.
    @Nullable default ListenerBundle getListeners(String name) {
        if (getBundles("listeners") == null)
            return null;

        final List<Bundle<?>> bundles = getBundles("listeners");

        if (bundles == null)
            return null;

        for (Bundle<?> entry : bundles) {
            if (entry.name().equals(name))
                return (ListenerBundle) entry;
        }

        return null;
    }

    // returns the storage of all registered bundles
    Map<String, List<Bundle<?>>> getBundles();

    default List<Bundle<?>> getBundles(String id) {
        return getBundles().get(id);
    }

    default MinecraftPlugin getSubType() {
        return this;
    }
}
