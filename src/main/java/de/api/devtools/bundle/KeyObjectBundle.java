package de.api.devtools.bundle;

import de.api.devtools.utils.KeyObject;

public abstract class KeyObjectBundle<T extends KeyObject> extends Bundle<T> {

    public KeyObjectBundle(String name) {
        super(name);
    }

    public void add(T t) {
        this.add(t.getKey(), t);
    }

    public void remove(T t) {
        this.remove(t.getKey());
    }

    public boolean contains(T t) {
        return this.contains(t.getKey());
    }
}
