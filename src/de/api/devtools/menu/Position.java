package de.api.devtools.menu;

public @interface Position {

    int value();

    int offset() default 0;
}
