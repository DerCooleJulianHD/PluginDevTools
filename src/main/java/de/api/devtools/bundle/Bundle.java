package de.api.devtools.bundle;

import de.api.devtools.plugin.SpigotPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

//: object to store other objects by type
public abstract class Bundle<T> {

    protected final String name;

    protected final SpigotPlugin plugin = SpigotPlugin.getInstance();

    // this is the map, all objects of type T will be stored in.
    protected final Map<String, T> actives = new HashMap<>();

    public Bundle(String name) {
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

    // returns the object of type T
    public final T get(String k) {
        return actives.get(k);
    }

    // returns true when object does contain in the map.
    public final boolean contains(String k) {
        return actives.containsKey(k);
    }

    public final Map<String, T> getActives() {
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

    // returns how many objects of type T the bundle is holding.
    public final int getSize() {
        return actives.size();
    }

    // returns the name for exact identify
    public final String name() {
        return name;
    }
}
