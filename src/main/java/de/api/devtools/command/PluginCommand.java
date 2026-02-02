package de.api.devtools.command;

import de.api.devtools.plugin.SpigotPlugin;
import de.api.devtools.utils.Messages;
import de.api.devtools.utils.Permissible;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

//: base object for any type of command
public abstract class PluginCommand implements Permissible, ICommandExecutor {

    private static PluginCommand instance;

    protected final SpigotPlugin plugin = SpigotPlugin.getInstance();
    protected final String name;
    @Nullable protected String permission;

    protected final Class<? extends CommandSender> type;

    protected PluginCommand(@Nonnull final String name, @Nullable String permission, @Nonnull Class<? extends CommandSender> type) {
        instance = this; // static instance for outside access
        this.name = name; // name this command will be registered on
        this.permission = permission; // permission require to execute
        this.type = type; // instances who are allowed to execute
    }

    protected boolean onCommandExecute(@Nonnull CommandSender sender, @Nonnull String [] args) {
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
    public final void setPermission(@Nonnull String value) {
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

    public final boolean requiresPlayer() {
        return getType().equals(Player.class);
    }

    public final Class<? extends CommandSender> getType() {
        return type;
    }

    public static PluginCommand getInstance() {
        return instance;
    }
}
