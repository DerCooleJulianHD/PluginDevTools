package de.api.devtools.utils.functionals;

public interface Loadable {

    void load();

    boolean isLoaded();

    default void setLoaded(boolean loaded) {}

    default void unload() {}
}

