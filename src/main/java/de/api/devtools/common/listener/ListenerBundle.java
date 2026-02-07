package de.api.devtools.common.listener;

import de.api.devtools.common.bundle.KeyObjectBundle;
import de.api.devtools.common.plugin.SpigotPlugin;

//: type of bundle to store and manage listener objects
public class ListenerBundle extends KeyObjectBundle<KeyListener> {

    protected final SpigotPlugin plugin = SpigotPlugin.getInstance();

    public ListenerBundle(final String name) {
        super(name);
    }

    // returns true when it's enabled on the server.
    /* public final boolean isEnabled(KeyListener listener) {
        if (!contains(listener))
            return false;

        final ArrayList<RegisteredListener> list = HandlerList.getRegisteredListeners(plugin);

        if (list.isEmpty())
            return false;

        return list.contains((RegisteredListener) listener);
    } */
}
