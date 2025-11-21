package de.api.plugins.bundle;

import de.api.plugins.plugin.SpigotPlugin;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public abstract class Bundle<T> {

    protected final SpigotPlugin plugin = SpigotPlugin.getInstance();

    // this is the map, all objects of type T will be stored in.
    protected final Map<Class<?>, T> actives = new HashMap<>();

    public Bundle() {
    }

    // adds a new object to the bundle and enables it on the server
    public final void add(T t) {
        final Class<?> k = t.getClass();

        if (contains(k))
            return; // if it's already in this bundle we aren't going to add it.

        actives.put(k, t); // storing it.
        register(t); // enabling it.
    }

    public abstract void register(T t);

    public abstract void unregister(T t);

    // removes and disables the listener from key
    public final void remove(Class<?> k) {
        if (!contains(k))
            return; // if it's not in this bundle, then stop any execution of code here.

        final T t = get(k); // this is the object

        if (t == null)
            return; // cancel if the object on key 'k' does not exist or has a null value.

        actives.remove(k); // removing it from the map.
        unregister(t); // disabling it
    }

    // returns the object of type T
    public final T get(Class<?> k) {
        return actives.get(k);
    }

    // returns true when object is in map.
    public final boolean contains(Class<?> k) {
        return actives.containsKey(k);
    }

    @Nullable
    // returns the specified object by its name
    public final T getByName(String name) {
        if (isEmpty())
            return null;

        for (Class<?> entry : actives.keySet()) {
            if (entry == null)
                continue;

            if (!name.equals(entry.getSimpleName()))
                continue;

            return get(entry);
        }

        return null;
    }

    public final Map<Class<?>, T> getActives() {
        return actives;
    }

    // returns true when map is empty
    public final boolean isEmpty() {
        return actives.isEmpty();
    }

    // unregister all objects without removing it from the map
    public final void unregisterAll() {
        if (!isEmpty()) actives.values().forEach(this::unregister);
    }

    // loop
    public final void forEach(BiConsumer<Class<?>, ? super T> action) {
        if (isEmpty())
            return;

        if (action == null)
            return;

        actives.forEach(action);
    }

    // unregisters and removes each object
    public final void clear() {
        unregisterAll();
        actives.clear();
    }

    // returns how many objects of type T the bundle is holding.
    public final int getSize() {
        return actives.size();
    }

    // returns true when listener does contain in the map.
    public boolean isRegistered(Class<?> k) {
        return contains(k);
    }
}
