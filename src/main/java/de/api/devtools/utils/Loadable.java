package de.api.devtools.utils;

public interface Loadable {

    void load();

    boolean isLoaded();

    default boolean isAutoLoad() {
        return getClass().isAnnotationPresent(AutoLoad.class);
    }
}

