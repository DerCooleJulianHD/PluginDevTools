package de.api.devtools.plugin;

public interface Prefixable {

    // returns the prefix.
    String getPrefix();

    // sets the prefix
    default void setPrefix(String prefix) {
    }
}


