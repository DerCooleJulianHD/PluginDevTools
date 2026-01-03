package de.api.devtools;

import de.api.devtools.plugin.SpigotPlugin;
import de.api.devtools.utils.TextColor;
import org.bukkit.ChatColor;

public final class MinecraftDevToolPlugin extends SpigotPlugin {

    @Override
    public void onPluginStart() {
        getConsole().sendMessage(getPrefix() + ChatColor.GREEN + "Successfully Enabled :D");
    }

    @Override
    public String getPrefix() {
        return TextColor.colorize("&8[&cPluginDevTools&8] &r");
    }
}
