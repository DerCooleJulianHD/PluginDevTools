package de.api.devtools.utils.load;

public interface AutoLoadable {

    default boolean getAutoLoad() {
        return getClass().isAnnotationPresent(AutoLoad.class);
    }
}
