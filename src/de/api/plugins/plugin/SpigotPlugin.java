package de.api.plugins.plugin;

import de.api.plugins.bundle.Bundle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SpigotPlugin extends JavaPlugin implements MinecraftPlugin {

    private static SpigotPlugin instance;
    private final Map<String, List<Bundle<?>>> bundles = new HashMap<>();
    private PluginConfigFile config;

    @Override
    public void onLoad() {
        // init the instance of the plugin
        instance = this;
        // creating the config file and load it.
        config = new PluginConfigFile(this);
        onPluginLoad();
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
        return getDescription().getVersion();
    }

    @Override
    public String getPluginName() {
        return getDescription().getName();
    }

    @Override
    public Map<String, List<Bundle<?>>> getBundles() {
        return bundles;
    }

    // does check if a plugin, which is using a dependency, also has the dependency plugin installed on the server.
    public static void checkForDependencyPlugin(Plugin using, String pluginNameOfDepend) {
        final Server server = using.getServer();
        final PluginManager manager = server.getPluginManager();

        // dependency-plugin which needs to be installed.
        final Plugin depend = manager.getPlugin(pluginNameOfDepend);

        // when dependency is installed and works fine.
        if (depend != null)
            return;

        // here when dependency-plugin is not installed:
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "'" + pluginNameOfDepend + "' plugin not found, please install it before continue.");

        if (using.isEnabled())
            manager.disablePlugin(using);
    }

    @Override
    public String getPrefix() {
        return config != null ? config.getPrefix() : "";
    }

    public static SpigotPlugin getInstance() {
        return instance;
    }
}
