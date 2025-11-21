package de.api.plugins.utils;

public interface Loadable {

    void load();

    boolean isLoaded();

    void setLoaded(boolean loaded);
}

