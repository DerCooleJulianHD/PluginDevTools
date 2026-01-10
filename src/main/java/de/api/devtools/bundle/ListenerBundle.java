package de.api.devtools.bundle;

import de.api.devtools.plugin.SpigotPlugin;
import de.api.devtools.utils.KeyObject;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredListener;

import java.util.ArrayList;

//: type of bundle to store and manage listener objects
public class ListenerBundle extends KeyObjectBundle<KeyListener> {

    protected final SpigotPlugin plugin = SpigotPlugin.getInstance();
    private final PluginManager pluginManager = Bukkit.getPluginManager();

    public ListenerBundle(final String name) {
        super(name);

        plugin.addListeners(this);
    }

    @Override
    // enables the listener on the server.
    protected final void onPostObjectRegister(KeyListener listener) {
        pluginManager.registerEvents(listener, plugin);
    }

    @Override
    // disables ist from the server.
    protected final void onPostObjectRemove(KeyListener listener) {
        HandlerList.unregisterAll(listener);
    }

    // returns true when it's enabled on the server.
    public final boolean isEnabled(KeyListener listener) {
        if (!contains(listener))
            return false;

        final ArrayList<RegisteredListener> list = HandlerList.getRegisteredListeners(plugin);

        if (list.isEmpty())
            return false;

        return list.contains((RegisteredListener) listener);
    }
}
