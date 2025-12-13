package de.api.devtools.command;

public interface PermissibleCommand {

    String getPermission();

    default boolean hasPermission() {
        return getPermission() != null && !getPermission().isEmpty();
    }
}
