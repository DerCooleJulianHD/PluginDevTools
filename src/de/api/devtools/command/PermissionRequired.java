package de.api.devtools.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//: can be put over custom command classes which extends from: CommandExecutor<T> to set a required permission
public @interface PermissionRequired {
    String value();
}
