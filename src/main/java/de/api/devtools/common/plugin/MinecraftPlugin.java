package de.api.devtools.common.plugin;

import de.api.devtools.common.config.yaml.PluginConfigFile;
import de.api.devtools.common.listener.ListenerManager;
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
public abstract class MinecraftPlugin extends JavaPlugin implements Prefixable {

    protected Console console;
    protected PluginConfigFile config;
    protected ListenerManager listenerManager;

    @Deprecated
    @Override public final void onLoad() {
        // init the instance of the plugin
        console = new Console(this);
        onPluginInit();
    }

    @Deprecated
    @Override public final void onEnable() {
        listenerManager = new ListenerManager(this);
        onPluginStart();
    }

    @Deprecated
    @Override public final void onDisable() {
        onPluginStop();
    }

    public void onPluginInit() {} // plugin load logic

    public abstract void onPluginStart(); // plugin enable logic

    public void onPluginStop() {}; // plugin disable logic

    // class who manages listeners on the server.
    public @NonNull ListenerManager getListenerManager() {
        return listenerManager;
    }

    // the 'config.yml' file in the main plugin data folder.
    public final @NonNull PluginConfigFile getPluginConfig() {
        return Objects.requireNonNull(config, "Plugin Config is not Loaded!");
    }

    // loads or creates a new instance of the 'config.yml' file.
    public final void loadPluginConfig(boolean def) {
        config = new PluginConfigFile(this, def);

        if (!config.isLoaded())
            config.load();
    }

    // returns the version string from the plugin description file.
    public final @NonNull String getPluginVersion() {
        return getDescription().getVersion();
    }

    // returns the simple plugin name from the plugin description file.
    public final @NonNull String getPluginName() {
        return getDescription().getName();
    }

    // loads plugins from other folders
    public final void loadPluginsFromFile(File dir) {
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

    // this is the console sender.
    public final @NonNull Console getConsole() {
        return console;
    }

    @Override
    // returns the prefix by config file.
    public final String getPrefix() {
        return config != null ? config.getPrefix() : "";
    }
}
