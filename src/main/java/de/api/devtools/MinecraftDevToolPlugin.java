package de.api.devtools;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftDevToolPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info(ChatColor.GREEN + "Successfully Enabled :D");
    }
}
