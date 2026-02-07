package de.api.devtools.common.command;

import de.api.devtools.common.plugin.MinecraftPlugin;
import de.api.devtools.common.utils.Messages;
import de.api.devtools.common.utils.Permissible;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

//: base object for any type of command
public abstract class PluginCommand implements Permissible, ICommandExecutor {

    @NonNull protected final MinecraftPlugin plugin;

    @NonNull protected final String name;
    @Nullable protected String permission;

    protected final boolean requiresPlayer;

    protected PluginCommand(@NonNull MinecraftPlugin plugin, @NonNull final String name, @Nullable String permission, boolean requiresPlayer) {
        this.plugin = plugin;
        this.name = name; // name this command will be registered on
        this.permission = permission; // permission require to execute
        this.requiresPlayer = requiresPlayer; // when only players are allowed to execute
    }

    protected final boolean onCommandExecute(@NonNull CommandSender sender, @NonNull String [] args) {
        if (requiresPermission()) {
            if (!sender.hasPermission(Objects.requireNonNull(getPermission()))) {
                sender.sendMessage(Messages.COMMAND_NO_PERMISSION.getValue());
                return false;
            }
        }

        if (requiresPlayer() && (!(sender instanceof Player))) {
            sender.sendMessage(Messages.COMMAND_INVALID_SENDER.getValue());
            return false;
        }

        execute(sender, args);
        return true;
    }

    @Override
    public final @Nullable String getPermission() {
        return permission;
    }

    @Override
    public final void setPermission(@NonNull String value) {
        this.permission = value;
    }

    public final @NonNull MinecraftPlugin getPlugin() {
        return plugin;
    }

    // the name this command is registered on.
    public final @NonNull String getName() {
        return name;
    }

    public abstract @Nullable String getDescription();

    public final boolean requiresPlayer() {
        return requiresPlayer;
    }
}
