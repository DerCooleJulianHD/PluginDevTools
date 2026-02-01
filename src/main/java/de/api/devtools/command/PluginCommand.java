package de.api.devtools.command;

import de.api.devtools.plugin.SpigotPlugin;
import de.api.devtools.utils.Permissible;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public abstract class PluginCommand implements Permissible, ICommandExecutor {

    private static PluginCommand instance;

    protected final SpigotPlugin plugin = SpigotPlugin.getInstance();
    protected final String name;
    protected String permission;

    protected PluginCommand(final String name) {
        instance = this;
        this.name = name;
        final CommandPermissionInfo info = getClass().getDeclaredAnnotation(CommandPermissionInfo.class); // the annotation to read the permission required about this command
        this.permission = (info != null) ? info.value() : "";
    }

    @Override
    public final @NonNull String getPermission() {
        return permission;
    }

    @Override
    public final void setPermission(String value) {
        this.permission = value;
    }

    public final SpigotPlugin getPlugin() {
        return plugin;
    }

    // the name this command is registered on.
    public final String getName() {
        return name;
    }

    public abstract @Nullable String getDescription();

    public static PluginCommand getInstance() {
        return instance;
    }
}
