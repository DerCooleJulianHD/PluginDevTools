package de.api.plugins.listener;

import de.api.plugins.bundle.Bundle;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class ListenerBundle extends Bundle<Listener> {

    // returns a new empty Listener Bundle.
    public static ListenerBundle EMPTY_BUNDLE = new ListenerBundle();

    private final PluginManager pluginManager = Bukkit.getPluginManager();

    public ListenerBundle() {}

    @Override
    // enables the listener on the server.
    public final void register(Listener listener) {
        pluginManager.registerEvents(listener, plugin);
    }

    @Override
    // disables ist from the server.
    public final void unregister(Listener listener) {
        HandlerList.unregisterAll(listener);
    }
}
