package de.api.devtools.common.utils.load;

public interface Loadable {

    void load();

    boolean isLoaded();

    default boolean getAutoLoad() {
        return getClass().isAnnotationPresent(AutoLoad.class);
    }
}

