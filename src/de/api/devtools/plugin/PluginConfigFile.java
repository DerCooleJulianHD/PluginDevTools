package de.api.devtools.plugin;

import de.api.devtools.utils.functionals.AutoLoad;
import de.api.devtools.config.YamlConfigFile;

@AutoLoad
// the config.yml file from the plugin
public final class PluginConfigFile extends YamlConfigFile {

    public PluginConfigFile(SpigotPlugin plugin) {
        super(plugin.getDataFolder(), "config.yml");
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
