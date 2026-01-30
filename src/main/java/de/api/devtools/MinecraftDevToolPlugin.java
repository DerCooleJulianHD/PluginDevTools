package de.api.devtools;

import de.api.devtools.plugin.SpigotPlugin;
import org.bukkit.ChatColor;

public final class MinecraftDevToolPlugin extends SpigotPlugin {

    @Override
    public void onPluginStart() {
        console.sendMessage(ChatColor.GREEN + "Successfully Enabled :D");
    }
}
