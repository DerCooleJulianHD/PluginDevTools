package de.api.plugins.config;

import de.api.plugins.plugin.SpigotPlugin;
import de.api.plugins.utils.Loadable;
import org.apache.commons.lang3.Validate;

import java.io.File;

public abstract class Document implements Loadable {

    protected final SpigotPlugin plugin = SpigotPlugin.getInstance();
    protected final DocumentType type;
    protected final File dir, file;

    private boolean loaded = false;

    public Document(DocumentType type, File dir, String fileName, boolean loadOnInit) {
        this.dir = dir;
        this.type = type;
        this.file = new File(dir, fileName);
        Validate.isTrue(isTypeOf(type), "file must be correct type.");
        if (loadOnInit) load();
    }

    public Document(DocumentType type, String dir, String fileName, boolean loadOnInit) {
        this(type, new File(dir), fileName, loadOnInit);
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
    public final File getDir() {
        return dir;
    }

    // returns the config file.
    public final File getFile() {
        return file;
    }
}


