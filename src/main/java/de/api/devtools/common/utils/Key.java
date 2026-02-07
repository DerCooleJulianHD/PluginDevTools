package de.api.devtools.common.utils;


import javax.annotation.Nonnull;

public final class Key {
    public static @Nonnull String getKeyAsClass(Class<?> clazz) {
        return clazz.getPackageName() + ":" + clazz.getSimpleName().toLowerCase();
    }
}