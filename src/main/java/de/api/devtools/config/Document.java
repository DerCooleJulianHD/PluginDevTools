package de.api.devtools.config;

import de.api.devtools.common.Validate;
import de.api.devtools.plugin.SpigotPlugin;
import de.api.devtools.utils.FileManager;
import de.api.devtools.utils.Loadable;
import org.bukkit.Bukkit;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

//: base class for files to work with: especially config files
public abstract class Document implements Loadable {

    protected final SpigotPlugin plugin = SpigotPlugin.getInstance();
    protected final DocumentType type;
    protected final File dir, file;
    private final boolean defResource;
    private boolean loaded = false;

    public Document(DocumentType type, File dir, String fileName, boolean defResource) {
        this.dir = dir;
        this.type = type;
        this.file = new File(dir, fileName);
        this.defResource = defResource;

        Validate.isTrue(isTypeOf(type), "file must be correct type.");

        if (isAutoLoad() && !isLoaded()) load();
    }

    public abstract void setDefaults();

    // creates the directory if it doesn't exist and the file in it.
    public final void createFiles() {
        try {
            FileManager.mkdirIfNotExists(dir);

            if (defResource) {
                saveDefaultResource();
                return;
            }

            if (!FileManager.isFileExist(file)) file.createNewFile();

            if (isAutoLoad() && isLoaded())
                setDefaults();
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

    public final boolean isTypeOf(DocumentType type) {
        return hasEnding(type.ending());
    }

    public final void saveDefaultResource() {
        if (!exists()) plugin.saveResource(file.getName(), false);
    }

    @Override
    // returns true when config has been loaded.
    public final boolean isLoaded() {
        return loaded;
    }

    // sets if config has been loaded or not.
    public final void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    // returns true when 'getDir()' does not return null and file is exist.
    public final boolean exists() {
        return dir != null && dir.exists() && file.exists();
    }

    // the folder the config file is in.
    public final @Nullable File getDir() {
        return dir;
    }

    // returns the config file.
    public final File getFile() {
        return file;
    }
}


