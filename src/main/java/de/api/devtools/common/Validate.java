package de.api.devtools.common;

public class Validate {

    public static void isTrue(boolean expression, String message) {
        if (!expression) throw new RuntimeException(message);
    }
}
