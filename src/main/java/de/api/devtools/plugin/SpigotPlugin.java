package de.api.devtools.plugin;

import de.api.devtools.bundle.Bundle;
import de.api.devtools.utils.Console;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//: base class for every plugin
public abstract class SpigotPlugin extends JavaPlugin implements MinecraftPlugin {

    protected Console console;

    protected static SpigotPlugin plugin;
    protected final Map<String, List<Bundle<?>>> bundles = new HashMap<>();

    protected PluginConfigFile config;

    @Deprecated
    @Override public final void onLoad() {
        // init the instance of the plugin
        plugin = this;
        console = new Console(plugin);

        onPluginInit();
    }

    @Deprecated
    @Override public final void onEnable() {
        onPluginStart();
    }

    @Deprecated
    @Override public final void onDisable() {
        onPluginStop();
    }

    @Override
    public final PluginConfigFile getPluginConfig() {
        return Objects.requireNonNull(config, "Plugin Config is not Loaded!");
    }

    public final void loadPluginConfig(boolean def) {
        config = new PluginConfigFile(this, def);

        if (!config.isLoaded())
            config.load();
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
    public Map<String, List<Bundle<?>>> getRegisteredBundles() {
        return bundles;
    }

    // does check if a plugin, which is using a dependency, also has the dependency plugin installed on the server.
    public static boolean checkForDependencyPlugin(Plugin using, String pluginNameOfDepend) {
        final Server server = using.getServer();
        final PluginManager manager = server.getPluginManager();

        // dependency-plugin which needs to be installed.
        final Plugin depend = manager.getPlugin(pluginNameOfDepend);

        // when dependency is installed and works fine.
        if (depend != null)
            return true;

        // here when dependency-plugin is not installed:
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "'" + pluginNameOfDepend + "' plugin not found, please install it before continue.");

        if (using.isEnabled())
            manager.disablePlugin(using);

        return false;
    }

    public static Plugin hookDependency(String name) {
        if (!checkForDependencyPlugin(plugin, name))
            return null;

        final long start = System.currentTimeMillis();
        final Server server = plugin.getServer();
        final PluginManager manager = server.getPluginManager();

        // dependency-plugin which will be hooked.
        final Plugin depend = manager.getPlugin(name);

        // when dependency could not be found
        if (depend == null)
            return null;

        if (!depend.isEnabled())
            manager.enablePlugin(depend);

        final long end = System.currentTimeMillis();
        Bukkit.getConsoleSender().sendMessage(depend.getName() + " was found and has been hooked. (took: " + (end - start) + "ms)");
        return depend;
    }

    @Override
    public Console getConsole() {
        return console;
    }

    @Override
    public String getPrefix() {
        return config != null ? config.getPrefix() : "";
    }

    public static SpigotPlugin getInstance() {
        return plugin;
    }
}
