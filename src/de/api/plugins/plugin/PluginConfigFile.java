package de.api.plugins.plugin;

import de.api.plugins.config.YamlConfigFile;

public final class PluginConfigFile extends YamlConfigFile {

    public PluginConfigFile(SpigotPlugin plugin) {
        super(plugin.getDataFolder(), "config.yml", true);
    }

    // returns the String that is set in plugin config.
    public String getPrefix() {
        return readString("prefix");
    }

    // sets the String value in plugin config on key 'prefix'
    public void setPrefix(String v) {
        writeString("prefix", v);
    }

    public void setPrefix() {
        this.setPrefix("");
    }
}
