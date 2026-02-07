package de.api.devtools.common.listener;

import de.api.devtools.common.bundle.Bundle;
import de.api.devtools.common.plugin.MinecraftPlugin;
import de.api.devtools.common.utils.load.Loadable;

//: type of bundle to store and manage listener objects
public class ListenerBundle extends Bundle<EventListener> implements Loadable {

    public ListenerBundle(MinecraftPlugin plugin, final String name) {
        super(plugin, name);
        if (getAutoLoad()) load();
    }

    @Override
    public void load() {
        plugin.getListenerManager().addListeners(this);
    }

    @Override
    public boolean isLoaded() {
        return plugin.getListenerManager().contains(getName());
    }

    public final void add(EventListener listener) {
        this.add(listener.getKey(), listener);
    }

    public final void remove(EventListener listener) {
        this.remove(listener.getKey());
    }

    public final boolean contains(EventListener listener) {
        return this.contains(listener.getKey());
    }
}
