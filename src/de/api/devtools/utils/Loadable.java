package de.api.devtools.utils;

public interface Loadable {

    void load();

    boolean isLoaded();

    void setLoaded(boolean loaded);

    default void unload() {}
}

