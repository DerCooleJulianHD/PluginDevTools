package de.api.devtools.config;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;

//: type of document to read and write values in keys from a config file
public class YamlConfigFile extends Document {

    private final FileConfiguration config = new YamlConfiguration();

    public YamlConfigFile(File dir, String fileName, boolean def) {
        super(DocumentType.YAML, dir, fileName, def);
    }

    public YamlConfigFile(String dir, String fileName, boolean def) {
        this(new File(dir), fileName, def);
    }

    @Override
    public final void load() {
        try {
            if (config == null)
                return;

            if (!exists())
                createFiles();

            config.load(file);
            setLoaded(true);
        } catch (InvalidConfigurationException | IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Unable to load: " + file.getName(), e);
        }
    }

    // writes an object to a Yaml-Configuration.
    public final void write(String k, Object v) {
        if (!isLoaded())
            load();

        config.set(k, v);
        save();
    }

    @Nullable
    // returns an Object from YamlConfiguration
    public final Object read(String k) {
        if (!file.exists())
            return null;

        if (!isLoaded())
            load();

        return config.get(k);
    }

    // saves all the data, this is called for you when calling the 'write()' method.
    public final void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Unable to save: " + file.getName(), e);
        }
    }

    // reads a keyed location from section with sub paths.
    public final Location readLocation(String k) {
        if (config.get(k) instanceof Location)
            return readSerializedLocation(k);

        final Location location = readBlockLocation(k);

        if (isSet(k + ".yaw") && isSet(k + ".pitch")) {
            location.setYaw(readFloat(k + ".yaw"));
            location.setPitch(readFloat(k + ".pitch"));
        }

        return location;
    }

    public final Location readBlockLocation(String k) {
        final String worldName = readString(k + ".world");
        World world = Bukkit.getWorld(worldName);

        if (world == null) world = Bukkit.createWorld(WorldCreator.name(worldName));

        final double x = readDouble(k + ".x");
        final double y = readDouble(k + ".y");
        final double z = readDouble(k + ".z");

        return new Location(world, x, y, z);
    }

    public final void writeLocation(String k, Location location) {
        writeString(k + ".world", location.getWorld().getName());
        writeDouble(k + ".x", location.getX());
        writeDouble(k + ".y", location.getX());
        writeDouble(k + ".z", location.getX());
        writeFloat(k + ".yaw", location.getYaw());
        writeFloat(k + ".pitch", location.getPitch());
    }

    public final void writeBlockLocation(String k, Block block) {
        final Location location = block.getLocation();

        writeString(k + ".world", block.getWorld().getName());
        writeDouble(k + ".x", location.getX());
        writeDouble(k + ".y", location.getX());
        writeDouble(k + ".z", location.getX());
    }

    // writes a String to YamlConfiguration
    public final void writeString(String k, String v) {
        this.write(k, v);
    }

    // writes an Integer to YamlConfiguration
    public final void writeInt(String k, int v) {
        this.write(k, v);
    }

    // writes a Double to YamlConfiguration
    public final void writeDouble(String k, double v) {
        this.write(k, v);
    }

    // writes a Float to YamlConfiguration
    public final void writeFloat(String k, float v) {
        this.write(k, v);
    }

    // writes a Boolean to YamlConfiguration
    public final void writeBoolean(String k, boolean v) {
        this.write(k, v);
    }

    // writes a Short to YamlConfiguration
    public final void writeShort(String k, short v) {
        this.write(k, v);
    }

    // writes a Long to YamlConfiguration
    public final void writeLong(String k, long v) {
        this.write(k, v);
    }

    // writes a Byte to YamlConfiguration
    public final void writeByte(String k, byte v) {
        this.write(k, v);
    }

    // writes a List to YamlConfiguration
    public final void writeList(String k, List<?> v) {
        this.write(k, v);
    }

    // writes an ItemStack to YamlConfiguration
    public final void writeItem(String k, ItemStack v) {
        this.write(k, v);
    }

    // writes a Color to YamlConfiguration
    public final void writeColor(String k, Color v) {
        this.write(k, v);
    }

    // writes a ChatColor to YamlConfiguration
    public final void writeChatColor(String k, ChatColor v) {
        this.writeString(k, v.name());
    }

    // writes a Character to YamlConfiguration
    public final void writeCharacter(String k, char v) {
        this.write(k, v);
    }

    // writes a Location to YamlConfiguration
    public final void writeSerializedLocation(String k, Location v) {
        this.write(k, v);
    }

    // returns a String
    public final String readString(String k) {
        return (String) read(k);
    }

    // returns an Integer
    public final int readInt(String k) {
        return (int) Objects.requireNonNull(read(k));
    }

    // returns a Double
    public final double readDouble(String k) {
        return (double) Objects.requireNonNull(read(k));
    }

    // returns a Float
    public final float readFloat(String k) {
        return (float) Objects.requireNonNull(read(k));
    }

    // returns a Boolean
    public final boolean readBoolean(String k) {
        return (boolean) Objects.requireNonNull(read(k));
    }

    // returns a Short
    public final short readShort(String k) {
        return (short) Objects.requireNonNull(read(k));
    }

    // returns a Long
    public final long readLong(String k) {
        return (long) Objects.requireNonNull(read(k));
    }

    // returns a Byte
    public final byte readByte(String k) {
        return (byte) Objects.requireNonNull(read(k));
    }

    // returns a List
    public final List<?> readList(String k) {
        return (List<?>) read(k);
    }

    // returns an ItemStack
    public final ItemStack readItem(String k) {
        return (ItemStack) read(k);
    }

    // returns a Color
    public final Color readColor(String k) {
        return (Color) read(k);
    }

    // returns a ChatColor
    public final ChatColor readChatColor(String k) {
        return ChatColor.valueOf(readString(k).toUpperCase());
    }

    @Deprecated(since = "1.4.2")
    // returns a Serialized Location from bukkit.
    public final Location readSerializedLocation(String k) {
        return (Location) read(k);
    }

    // returns all Keys
    public final Set<String> keySet(boolean deep) {
        return config.getKeys(deep);
    }

    // returns true when config contains path.
    public final boolean isSet(String k) {
        return config.isSet(k);
    }

    // returns true if the config file is empty, which means config doesn't contain any data.
    public final boolean isEmpty() {
        return keySet(false).isEmpty();
    }

    // returns a section
    public final ConfigurationSection getConfigurationSection(String name) {
        return config.getConfigurationSection(name);
    }

    // creates a section
    public final void createSection(String name) {
        config.createSection(name);
    }

    public final FileConfiguration getConfig() {
        return config;
    }

    @Override
    public void setDefaults() {}
}
