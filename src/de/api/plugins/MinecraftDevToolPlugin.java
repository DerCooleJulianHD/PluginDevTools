package de.api.plugins;

import de.api.plugins.plugin.SpigotPlugin;

public final class MinecraftDevToolPlugin extends SpigotPlugin {

    @Override
    public void onPluginStart() {
        getLogger().info("Successfully Enabled :D");
    }

}
