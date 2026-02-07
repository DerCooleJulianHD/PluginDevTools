package de.api.devtools.common.utils;


import org.checkerframework.checker.nullness.qual.NonNull;

public final class Key {
    public static @NonNull String getKeyAsClass(Class<?> clazz) {
        return clazz.getPackageName() + ":" + clazz.getSimpleName().toLowerCase();
    }
}