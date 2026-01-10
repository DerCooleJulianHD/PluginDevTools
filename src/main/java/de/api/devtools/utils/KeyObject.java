package de.api.devtools.utils;

public interface KeyObject {

    default String getKey() {
        return getKeyAsClass(getClass());
    }

    static String getKeyAsClass(Class<?> clazz) {
        return clazz.getPackageName() + ":" + clazz.getSimpleName().toLowerCase();
    }
}