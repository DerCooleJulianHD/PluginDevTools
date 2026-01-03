package de.api.devtools.bundle;

import de.api.devtools.plugin.SpigotPlugin;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.Nullable;

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

    // adds a new object to the bundle and enables it on the server
    public final void add(T t) {
        this.add(t, this::onPostObjectRegister);
    }

    public final void set(Class<Listener> k, T t) {
        this.actives.put(k.getName(), t);
    }

    public final void add(T t, Consumer<T> action) {
        final Class<?> k = t.getClass();

        if (contains(k))
            return; // if it's already in this bundle we aren't going to add it.

        actives.put(k.getName().toLowerCase(), t); // storing it.

        if (action != null)
            action.accept(t);
    }

    protected void onPostObjectRegister(T t) {}

    protected void onPostObjectRemove(T t) {}

    // removes and disables the object from key
    public final void remove(Class<?> k) {
        this.remove(k, this::onPostObjectRemove);
    }

    public final void remove(Class<?> k, Consumer<T> action) {
        if (!contains(k))
            return; // if it's not in this bundle, then stop any execution of code here.

        final T t = get(k); // this is the object

        if (t == null)
            return; // cancel if the object on key 'k' does not exist or has a null value.

        actives.remove(k.getName().toLowerCase()); // removing it from the map.

        if (action != null)
            action.accept(t); // executing the action that will happen on remove
    }

    // returns the object of type T
    public final T get(Class<?> k) {
        return actives.get(k.getName().toLowerCase());
    }

    // returns true when object does contain in the map.
    public final boolean contains(Class<?> k) {
        return actives.containsKey(k.getName().toLowerCase());
    }

    @Nullable
    // returns the specified object by its name
    public final T getByName(String name) {
        if (isEmpty())
            return null;

        for (String entry : actives.keySet()) {
            if (entry == null)
                continue;

            final String key = entry.toLowerCase();

            if (!name.equals(key))
                continue;

            return actives.get(key);
        }

        return null;
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
        this.removeAll(this::onPostObjectRemove);
    }

    public final void removeAll(Consumer<T> action) {
        if (!isEmpty()) actives.keySet().forEach(entry -> {
            final String key = entry.toLowerCase();
            final T t = actives.get(key);

            if (t == null)
                return;

            actives.remove(key); // removing it from the map.

            if (action != null)
                action.accept(t);
        });
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
