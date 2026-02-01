package de.api.devtools.utils;

import javax.annotation.Nullable;

public interface Permissible {

    @Nullable String getPermission();

    void setPermission(String value);

    default boolean requiresPermission() {
        return getPermission() != null && !getPermission().isEmpty();
    }
}
