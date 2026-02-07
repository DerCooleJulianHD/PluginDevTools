package de.api.devtools.common.plugin;

import de.api.devtools.common.config.YamlConfigFile;
import de.api.devtools.common.utils.load.AutoLoad;
import org.checkerframework.checker.nullness.qual.NonNull;

@AutoLoad
//: the config.yml file from the plugin
public final class PluginConfigFile extends YamlConfigFile implements Prefixable {

    public PluginConfigFile(SpigotPlugin plugin, boolean def) {
        super(plugin.getDataFolder(), "config.yml", def);
    }

    public PluginConfigFile(String dir, boolean def) {
        super(dir, "config.yml", def);
    }

    @Override
    // returns the String that is set in plugin config.
    public @NonNull String getPrefix() {
        return readString("prefix") != null ? readString("prefix") : "";
    }

    @Override
    // sets the String value in plugin config on key 'prefix'
    public void setPrefix(String v) {
        writeString("prefix", v);
    }

}
