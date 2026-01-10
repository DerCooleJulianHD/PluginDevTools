package de.api.devtools.bundle;

import de.api.devtools.utils.KeyObject;

import java.util.function.Consumer;

public abstract class KeyObjectBundle<T extends KeyObject> extends Bundle<T> {

    public KeyObjectBundle(String name) {
        super(name);
    }

    public void add(T t) {
        this.add(t.getKey(), t);
    }

    public void add(T t, Consumer<T> action) {
        this.add(t.getKey(), t, action);
    }

    public void remove(T t) {
        this.remove(t.getKey());
    }

    public void remove(T t, Consumer<T> action) {
        this.remove(t.getKey(), action);
    }

    public boolean contains(T t) {
        return this.contains(t.getKey());
    }
}
