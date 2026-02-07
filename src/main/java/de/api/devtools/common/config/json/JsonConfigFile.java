package de.api.devtools.common.config.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.api.devtools.common.config.Document;
import de.api.devtools.common.plugin.MinecraftPlugin;
import de.api.devtools.common.utils.Validate;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

@JsonProperties(/* by default */)
//: type of document where you can read and write JSON data
public class JsonConfigFile extends Document {

    @NonNull private final Gson gson;

    public JsonConfigFile(@NonNull MinecraftPlugin plugin, File dir, String fileName, boolean def) {
        super(plugin, Type.JSON, dir, fileName, def);

        final GsonBuilder builder = new GsonBuilder();
        final JsonProperties properties = Validate.nonNull(getClass().getDeclaredAnnotation(JsonProperties.class), getClass().getSimpleName() + "misses JsonProperties annotation!");

        if (properties.prettyPrinting()) builder.setPrettyPrinting();
        if (!properties.htmlEscaping()) builder.disableHtmlEscaping();
        if (!properties.innerClassSerialisation()) builder.disableInnerClassSerialization();

        this.gson = builder.create();
    }

    // writes an object to a Json-Configuration.
    public final void write(Object o) {
        if (!file.exists())
            return;

        if (!isLoaded())
            load();

        try {
            final FileWriter writer = new FileWriter(file);

            writer.write(gson.toJson(o));
            writer.close();
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Unable to write object to: " + file.getName(), e);
        }
    }

    @Nullable
    public final Object read(Class<?> classOfT) {
        if (!file.exists())
            return null;

        if (!isLoaded())
            return null;

        try {
            final FileReader reader = new FileReader(file);
            final Object o = gson.fromJson(reader, classOfT);
            reader.close();
            return o;
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Unable to read object from: " + file.getName(), e);
        }

        return null;
    }

    @Override
    public final void load() {
        try {
            if (!exists())
                createFiles();

            this.setLoaded(true);
        } catch (Exception ex) {
            plugin.getLogger().log(Level.SEVERE, "Unable to load: " + file.getName(), ex);
        }
    }
}
