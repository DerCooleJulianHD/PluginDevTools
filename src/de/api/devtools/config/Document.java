package de.api.devtools.config;

import de.api.devtools.utils.functionals.AutoLoad;
import de.api.devtools.plugin.SpigotPlugin;
import de.api.devtools.utils.FileManager;
import de.api.devtools.utils.functionals.Loadable;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public abstract class Document implements Loadable {

    protected final SpigotPlugin plugin = SpigotPlugin.getInstance();
    protected final DocumentType type;
    @Nullable protected final File dir;
    protected final File file;

    private boolean loaded = false;

    public Document(DocumentType type, @Nullable File dir, String fileName) {
        this.dir = dir;
        this.type = type;
        this.file = new File(dir, fileName);

        Validate.isTrue(isTypeOf(type), "file must be correct type.");

        if (getClass().isAnnotationPresent(AutoLoad.class))
            load();
    }

    public Document(DocumentType type, @NotNull String dir, String fileName) {
        this(type, new File(dir), fileName);
    }

    // creates the directory if it doesn't exist and the file in it.
    public final void createFiles(boolean defaultResource) {
        if (dir != null && !dir.exists())
            FileManager.mkdirIfNotExists(dir);

        if (defaultResource)
            saveDefaultResource(file.getName());

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                plugin.getLogger().log(Level.SEVERE, "Unable to create: " + file.getName(), ex);
            }
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

    public final void saveDefaultResource(String fileName) {
        if (!exists()) plugin.saveResource(fileName, false);
    }

    @Override
    // returns true when config has been loaded.
    public final boolean isLoaded() {
        return loaded;
    }

    @Override
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


