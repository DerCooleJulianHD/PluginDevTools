package de.api.plugins.plugin;

import de.api.plugins.bundle.Bundle;
import de.api.plugins.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.Utility;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public abstract class SpigotPlugin extends JavaPlugin implements MinecraftPlugin {

    private static SpigotPlugin plugin;
    private final PluginDescriptionFile meta = getDescription();
    private final Map<String, Map<String, Bundle<?>>> bundles = new HashMap<>();
    private PluginConfigFile config;

    public static SpigotPlugin getPlugin() {
        return plugin;
    }

    @Override
    public void onLoad() {
        // init the instance of the plugin
        plugin = this;

        // creating the Plugin folder
        FileManager.mkdirIfNotExists(getDataFolder());

        // creating the config file and load it.
        config = new PluginConfigFile(plugin);
        onPluginInit();
    }

    @Override
    public void onEnable() {
        onPluginStart();
        sendEnableMessage();
    }

    @Override
    public void onDisable() {
        onPluginStop();
        sendDisableMessage();
    }

    @Override
    public PluginConfigFile getConfiguration() {
        return config;
    }

    @Override
    public String getPluginVersion() {
        return meta.getVersion();
    }

    @Override
    public String getPluginName() {
        return meta.getName();
    }

    @Override
    public Map<String, Map<String, Bundle<?>>> getBundles() {
        return bundles;
    }

    @Utility
    // does check if a plugin, which is using a dependency, also has the dependency plugin installed.
    public static void checkForDependencyPlugin(String pluginNameOfDepend) {
        final Server server = plugin.getServer();
        final PluginManager manager = server.getPluginManager();

        // dependency-plugin which needs to be installed.
        final Plugin depend = manager.getPlugin(pluginNameOfDepend);

        // when dependency is installed and works fine.
        if (depend != null)
            return;

        // here when dependency-plugin is not installed:
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "'" + pluginNameOfDepend + "' plugin not found, please install it before continue.");

        if (plugin.isEnabled())
            manager.disablePlugin(plugin);
    }
}
