package de.api.devtools.common.utils;

public class Validate {

    public static void isTrue(boolean expression, String message) {
        if (!expression) throw new IllegalStateException(message);
    }

    public static <T> T nonNull(T object, String message) {
        if (object == null) {
            throw new IllegalStateException(message);
        }

        return object;
    }
}
