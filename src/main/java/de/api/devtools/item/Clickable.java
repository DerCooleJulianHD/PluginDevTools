package de.api.devtools.item;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class Clickable extends Icon {

    private final ClickType clickType;
    private final Consumer<Player> action;

    public Clickable(ItemCreator creator, @Nonnull ClickType clickType, @Nonnull Consumer<Player> action) {
        super(creator);
        this.action = action;
        this.clickType = clickType;
    }

    public @Nonnull final ClickType getClickType() {
        return clickType;
    }

    public final @Nonnull Consumer<Player> getAction() {
        return action;
    }
}
