package de.api.devtools;

import de.api.devtools.plugin.SpigotPlugin;
import de.api.devtools.utils.TextUtil;
import org.bukkit.ChatColor;

public final class MinecraftDevToolPlugin extends SpigotPlugin {

    @Override
    public void onPluginStart() {
        console.sendMessage(ChatColor.GREEN + "Successfully Enabled :D");
    }

    @Override
    public String getPrefix() {
        return TextUtil.colorize("&8[&cPluginDevTools&8] &r");
    }
}
