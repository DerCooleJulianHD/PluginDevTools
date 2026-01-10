package de.api.devtools.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface Permissible {

    default void setPermission(String s, boolean value) {
        getPermissions().put(s, value);
    }

    default void unsetPermission(String s) {
        getPermissions().remove(s);
    }

    default Set<String> getActivePermissions() {
        final Set<String> actives = new HashSet<>();

        getPermissions().keySet().forEach((s) -> {
            if (isActive(s))
                actives.add(s);
        });

        return actives;
    }

    default Set<String> getDisabledPermissions() {
        final Set<String> map = new HashSet<>();

        getPermissions().keySet().forEach((s) -> {
            if ((!isActive(s)))
                map.add(s);
        });

        return map;
    }

    Map<String, Boolean> getPermissions();

    default boolean hasPermission(String s) {
        return hasPermissions() && getPermissions().containsKey(s);
    }

    default boolean isActive(String s) {
        return getPermissions().get(s);
    }

    default boolean hasPermissions() {
        return !getPermissions().isEmpty();
    }
}
