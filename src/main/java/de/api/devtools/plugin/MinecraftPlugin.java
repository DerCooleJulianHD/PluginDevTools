package de.api.devtools.plugin;

import de.api.devtools.bundle.Bundle;
import de.api.devtools.bundle.ListenerBundle;
import de.api.devtools.utils.Console;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import javax.annotation.Nullable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MinecraftPlugin extends Prefixable {

    // this is the [bukkit] console sender.
    Console getConsole();

    default void init() {} // plugin load logic

    void onPluginStart(); // plugin enable logic

    default void onPluginStop() {} // plugin disable logic

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

    default void addBundle(String k, Bundle<?> bundle) {
        getRegisteredBundles(k).add(bundle);
    }

    // adds a new listener bundle to the server.
    default void addListeners(ListenerBundle bundle) {
        addBundle("listeners", bundle);
    }

    // removes a listener bundle from the server.
    default void removeListeners(String name) {
        final ListenerBundle bundle = getListeners(name);

        if (bundle == null)
            return;

        bundle.clear();

        final List<Bundle<?>> listeners = getRegisteredBundles("listeners");
        listeners.remove(bundle);
    }

    default void removeBundle(String k, Bundle<?> bundle) {
        if (getRegisteredBundles(k) == null)
            return;

        final List<Bundle<?>> bundles = getRegisteredBundles(k);

        if (bundle != null)
            bundle.clear();

        bundles.remove(bundle);
    }

    @Nullable
    // returns a listener bundle which is registered on the server.
    default ListenerBundle getListeners(String name) {
        if (getRegisteredBundles("listeners") == null)
            return null;

        final List<Bundle<?>> bundles = getRegisteredBundles("listeners");

        if (bundles == null)
            return null;

        for (Bundle<?> entry : bundles) {
            if (entry.name().equals(name))
                return (ListenerBundle) entry;
        }

        return null;
    }

    // returns the storage of all registered bundles
    Map<String, List<Bundle<?>>> getRegisteredBundles();

    default List<Bundle<?>> getRegisteredBundles(String id) {
        return getRegisteredBundles().get(id) != null ? getRegisteredBundles().get(id) : new ArrayList<>();
    }

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
