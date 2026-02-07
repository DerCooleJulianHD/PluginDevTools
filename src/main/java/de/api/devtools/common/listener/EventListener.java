package de.api.devtools.common.listener;

import de.api.devtools.common.plugin.MinecraftPlugin;
import de.api.devtools.common.utils.Key;
import org.bukkit.event.Listener;

import javax.annotation.Nonnull;

public class EventListener implements Listener {

    @Nonnull protected final MinecraftPlugin plugin;
    @Nonnull protected final String name;

    protected boolean enabled = false;

    public EventListener(@Nonnull MinecraftPlugin plugin, @Nonnull String name) {
        this.plugin = plugin;
        this.name = name;
    }

    @Nonnull public final MinecraftPlugin getPlugin() {
        return plugin;
    }

    @Nonnull public final String getName() {
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
