package de.api.devtools.common.bundle;

import de.api.devtools.common.plugin.MinecraftPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

//: object to store other objects by type
public abstract class Bundle<T> {

    @Nonnull protected final MinecraftPlugin plugin;
    @Nonnull protected final String name;

    // this is the map, all objects of type T will be stored in.
    @Nonnull protected final Map<String, T> actives = new HashMap<>();

    public Bundle(@NonNull MinecraftPlugin plugin, @NonNull String name) {
        this.plugin = plugin;
        this.name = name;
    }

    public final void set(String k, T t) {
        this.actives.put(k, t);
    }

    // adds a new object to the bundle and enables it on the server
    public final void add(String k, T t) {
        if (!contains(k)) set(k, t); // storing it.
    }

    // removes and disables the object from key
    public final void remove(String k) {
        if (!contains(k))
            return; // if it's not in this bundle, then stop any execution of code here.

        final T t = get(k); // this is the object

        if (t == null)
            return; // cancel if the object on key 'k' does not exist or has a null value.

        actives.remove(k); // removing it from the map.
    }

    // loop
    public final void forEach(BiConsumer<String, ? super T> action) {
        if (isEmpty())
            return;

        if (action == null)
            return;

        actives.forEach(action);
    }

    // unregisters and removes each object
    public final void clear() {
        removeAll();

        if (!actives.isEmpty()) actives.clear();
    }

    // returns the object of type T
    public final T get(String k) {
        return actives.get(k);
    }

    // returns true when object does contain in the map.
    public final boolean contains(String k) {
        return actives.containsKey(k);
    }

    public final @NonNull Map<String, T> getActives() {
        return actives;
    }

    // returns true when map is empty
    public final boolean isEmpty() {
        return actives.isEmpty();
    }

    // removes all objects without removing it from the map
    public final void removeAll() {
        this.actives.keySet().forEach(this::remove);
    }

    // returns how many objects of type T the bundle is holding.
    public final int getSize() {
        return actives.size();
    }

    // returns the name for exact identify
    public final @NonNull String getName() {
        return name;
    }
}
