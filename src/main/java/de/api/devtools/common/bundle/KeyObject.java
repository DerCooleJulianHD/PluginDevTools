package de.api.devtools.common.bundle;

import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nonnull;

public interface KeyObject {

    default @Nonnull String getKey() {
        return getKeyAsClass(getClass());
    }

    static @NonNull String getKeyAsClass(Class<?> clazz) {
        return clazz.getPackageName() + ":" + clazz.getSimpleName().toLowerCase();
    }
}