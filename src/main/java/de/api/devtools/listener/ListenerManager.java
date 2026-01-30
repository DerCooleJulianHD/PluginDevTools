package de.api.devtools.listener;

import de.api.devtools.plugin.SpigotPlugin;
import org.bukkit.Server;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public final class ListenerManager {

    private final SpigotPlugin plugin = SpigotPlugin.getInstance();

    private final Server server;
    private final Map<String, ListenerBundle> bundles;

    public ListenerManager() {
        this.bundles = new HashMap<>();
        this.server = plugin.getServer();
    }

    // adds a new listener bundle to the server.
    public void addListeners(ListenerBundle bundle) {
        this.bundles.put(bundle.name(), bundle);

        final PluginManager manager = server.getPluginManager();

        bundle.getActives().values().forEach((listener) -> {
            if (bundle.isEnabled(listener)) return;
            // enables the listener on the server.
            manager.registerEvents(listener, plugin);
        });
    }

    // removes a listener bundle from the server.
    public void removeListeners(String name) {
        final ListenerBundle bundle = getListeners(name);

        if (bundle == null)
            return;

        bundle.getActives().values().forEach(HandlerList::unregisterAll);

        bundle.clear();
        bundles.remove(bundle.name());
    }

    @Nullable
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
