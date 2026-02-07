package de.api.devtools.common.config;

import de.api.devtools.common.utils.Validate;
import de.api.devtools.common.plugin.MinecraftPlugin;
import de.api.devtools.common.utils.FileManager;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

//: base class for files to work with: especially config files
public abstract class Document {

    protected final MinecraftPlugin plugin;
    protected final Type type;
    protected final File dir, file;
    private final boolean defResource;

    public Document(MinecraftPlugin plugin, Type type, File dir, String fileName, boolean defResource) {
        this.plugin = plugin;
        this.dir = dir;
        this.type = type;
        this.file = new File(dir, fileName);
        this.defResource = defResource;
        Validate.isTrue(isTypeOf(type), "file must be correct type.");
    }

    // creates the directory if it doesn't exist and the file in it.
    public final void createFiles() {
        try {
            FileManager.mkdirIfNotExists(dir);

            if (defResource) {
                if (!exists()) plugin.saveResource(file.getName(), true);
                return;
            }

            if (!FileManager.isFileExist(file)) file.createNewFile();
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Unable to create: " + file.getName(), ex);
        }
    }

    // returns true if config file does end with 'filename{".ending"}'
    private boolean hasEnding(String[] v) {
        final String fileName = file.getName();

        if (v.length == 0)
            return false;

        if (v.length < 2)
            return fileName.endsWith(v[0]);

        for (String ending : v) {
            if (fileName.endsWith(ending))
                return true;
        }

        return false;
    }

    public final boolean isTypeOf(Type type) {
        return hasEnding(type.ending());
    }

    // returns true when 'getDir()' does not return null and file is exist.
    public final boolean exists() {
        return dir != null && dir.exists() && file.exists();
    }

    // the folder the config file is in.
    public final File getDir() {
        return dir;
    }

    // returns the config file.
    public final File getFile() {
        return file;
    }

    public final MinecraftPlugin getPlugin() {
        return plugin;
    }

    public final Type getType() {
        return type;
    }

    public final boolean isDefaultResource() {
        return defResource;
    }

    //: which type of file our file has to be
    public enum Type {

        JSON(new String[]{".json"}),
        YAML(new String[]{".yml", ".yaml"}),
        TOML(new String[]{".toml"}),
        PROPERTIES(new String[]{".properties"}),
        XML(new String[]{".xml"}),
        TXT(new String[]{".txt"});

        private final String[] values;

        Type(String[] values) {
            this.values = values;
        }

        public String[] ending() {
            return values;
        }
    }
}


