package de.api.devtools.listener;

import de.api.devtools.bundle.Bundle;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredListener;

import java.util.ArrayList;

public class ListenerBundle extends Bundle<Listener> {

    private final PluginManager pluginManager = Bukkit.getPluginManager();

    public ListenerBundle(final String name) {
        super(name);
    }

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

    // returns true when it's enabled on the server.
    public final boolean isRegistered(Listener listener) {
        final Class<?> k = listener.getClass();

        if (!contains(k))
            return false;

        final ArrayList<RegisteredListener> list = HandlerList.getRegisteredListeners(plugin);

        if (list.isEmpty())
            return false;

        return list.contains((RegisteredListener) listener);
    }
}
