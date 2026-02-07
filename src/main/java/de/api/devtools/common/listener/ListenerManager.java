package de.api.devtools.common.listener;

import de.api.devtools.common.plugin.MinecraftPlugin;
import org.bukkit.Server;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;

import java.util.HashMap;
import java.util.Map;

public final class ListenerManager {

    private final MinecraftPlugin plugin;
    private final Server server;

    private final Map<String, ListenerBundle> bundles;

    public ListenerManager(MinecraftPlugin plugin) {
        this.plugin = plugin;
        this.server = plugin.getServer();
        this.bundles = new HashMap<>();
    }

    // adds a new listener bundle to the server.
    public void addListeners(ListenerBundle bundle) {
        this.bundles.put(bundle.getName(), bundle);

        final PluginManager manager = server.getPluginManager();

        bundle.getActives().values().forEach((listener) -> {
            if (listener.isEnabled()) return;
            // enables the listener on the server.
            manager.registerEvents(listener, plugin);
            listener.setEnabled(true);
        });
    }

    // removes a listener bundle from the server.
    public void removeListeners(String name) {
        final ListenerBundle bundle = getListeners(name);

        if (bundle == null)
            return;

        bundle.getActives().values().forEach(HandlerList::unregisterAll);

        bundle.clear();
        bundles.remove(bundle.getName());
    }

    // returns a listener bundle which is registered on the server.
    public ListenerBundle getListeners(String name) {
        return getRegisteredBundles().get(name);
    }

    // returns the storage of all registered bundles
    public Map<String, ListenerBundle> getRegisteredBundles() {
        return bundles;
    }

    public boolean contains(String name) {
        return !getRegisteredBundles().isEmpty() && getRegisteredBundles().containsKey(name);
    }
}
