package de.api.devtools.load;

public interface AutoLoadable {

    default boolean getAutoLoad() {
        return getClass().isAnnotationPresent(AutoLoad.class);
    }
}
