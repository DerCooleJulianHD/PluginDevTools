package de.api.devtools.command;

public interface Permissible {

    String getPermission();

    default boolean hasPermission() {
        return getPermission() != null && !getPermission().isEmpty();
    }
}
