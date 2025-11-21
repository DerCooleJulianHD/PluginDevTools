package de.api.plugins.plugin;

public interface Prefixable {

    // returns the prefix.
    default String getPrefix() {
        return getPluginPrefix(SpigotPlugin.getPlugin().getConfiguration());
    }

    // sets the prefix
    default void setPrefix(String prefix) {
    }

    // returns the prefix.
    default String getPluginPrefix(PluginConfigFile config) {
        return config.getPrefix();
    }
}


