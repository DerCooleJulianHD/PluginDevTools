package de.api.devtools.plugin;

import de.api.devtools.listener.ListenerManager;
import de.api.devtools.utils.Console;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.File;
import java.util.Objects;

//: base class for every plugin
public abstract class SpigotPlugin extends JavaPlugin implements MinecraftPlugin {

    protected static SpigotPlugin plugin;

    protected Console console;
    protected PluginConfigFile config;
    protected ListenerManager listenerManager;

    @Deprecated
    @Override public final void onLoad() {
        // init the instance of the plugin
        plugin = this;
        console = new Console(plugin);

        init();
    }

    @Deprecated
    @Override public final void onEnable() {
        listenerManager = new ListenerManager();
        onPluginStart();
    }

    @Deprecated
    @Override public final void onDisable() {
        onPluginStop();
    }

    @Override
    public @NonNull ListenerManager getListenerManager() {
        return listenerManager;
    }

    @Override
    public final @NonNull PluginConfigFile getPluginConfig() {
        return Objects.requireNonNull(config, "Plugin Config is not Loaded!");
    }

    public final void loadPluginConfig(boolean def) {
        config = new PluginConfigFile(this, def);

        if (!config.isLoaded())
            config.load();
    }

    @Override
    public @NonNull String getPluginVersion() {
        return getDescription().getVersion();
    }

    @Override
    public @NonNull String getPluginName() {
        return getDescription().getName();
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

    @Override
    public void loadPluginsFromFile(File dir) {
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

    @Override
    public @NonNull Console getConsole() {
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
