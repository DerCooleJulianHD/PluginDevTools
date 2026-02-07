package de.api.devtools.common.config.yaml;

import de.api.devtools.common.plugin.Prefixable;
import de.api.devtools.common.plugin.MinecraftPlugin;
import de.api.devtools.common.utils.load.AutoLoad;
import org.checkerframework.checker.nullness.qual.NonNull;

@AutoLoad
//: the config.yml file from the plugin
public final class PluginConfigFile extends YamlConfigFile implements Prefixable {

    public PluginConfigFile(@NonNull MinecraftPlugin plugin, boolean def) {
        super(plugin, plugin.getDataFolder(), "config.yml", def);
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
