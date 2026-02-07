package de.api.devtools;

import de.api.devtools.common.listener.ItemClickListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftDevToolPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§8[§aPluginDevTools§8] " + ChatColor.GREEN + "Successfully Enabled :D");

        final PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new ItemClickListener(), this);
    }
}
