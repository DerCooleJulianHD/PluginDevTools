package de.api.devtools.common.utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface Permissible {

    @Nullable String getPermission();

    void setPermission(@Nonnull String value);

    default boolean requiresPermission() {
        return getPermission() != null && !getPermission().isEmpty();
    }
}
