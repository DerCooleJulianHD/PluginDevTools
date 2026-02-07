package de.api.devtools.common.utils;


import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public interface Permissible {

    @Nullable String getPermission();

    void setPermission(@NonNull String value);

    default boolean requiresPermission() {
        return getPermission() != null && !getPermission().isEmpty();
    }
}
