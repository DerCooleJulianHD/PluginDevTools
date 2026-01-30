package de.api.devtools.utils;

public interface Permissible {

    String getPermission();

    void setPermission(String value);

    default boolean requiresPermission() {
        return getPermission() != null && !getPermission().isEmpty();
    }
}
