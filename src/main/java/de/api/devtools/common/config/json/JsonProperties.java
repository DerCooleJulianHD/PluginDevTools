package de.api.devtools.common.config.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
//: settings on how the JSON config writes the data
public @interface JsonProperties {
    boolean prettyPrinting() default false;

    boolean htmlEscaping() default true;

    boolean innerClassSerialisation() default true;
}
