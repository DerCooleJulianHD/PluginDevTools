package de.api.devtools.utils;

public interface Loadable {

    void load();

    boolean isLoaded();

    default void setLoaded(boolean loaded) {}

    default void unload() {}

    default boolean isAutoLoad() {
        return getClass().isAnnotationPresent(AutoLoad.class);
    }
}

