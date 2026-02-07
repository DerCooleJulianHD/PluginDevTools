package de.api.devtools.common.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public final class Dependencies {
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
}
