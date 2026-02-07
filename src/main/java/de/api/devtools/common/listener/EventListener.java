package de.api.devtools.common.listener;

import de.api.devtools.common.plugin.MinecraftPlugin;
import de.api.devtools.common.utils.Key;
import org.bukkit.event.Listener;
import org.checkerframework.checker.nullness.qual.NonNull;

public class EventListener implements Listener {

    protected final MinecraftPlugin plugin;
    protected final String name;

    protected boolean enabled = false;

    public EventListener(MinecraftPlugin plugin, @NonNull String name) {
        this.plugin = plugin;
        this.name = name;
    }

    public final MinecraftPlugin getPlugin() {
        return plugin;
    }

    public final String getName() {
        return name;
    }

    // returns true when it's enabled on the server.
    public final boolean isEnabled() {
        return enabled;
    }

    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public final String getKey() {
        return Key.getKeyAsClass(getClass());
    }
}
