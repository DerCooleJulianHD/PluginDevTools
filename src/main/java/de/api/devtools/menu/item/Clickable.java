package de.api.devtools.menu.item;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public abstract class Clickable extends Icon {

    public Clickable(@NonNull Material material, int id, int amount, String name, String... lore) {
        super(material, id, amount, name, lore);
    }

    public Clickable(@NonNull Material material, int amount, String name, String... lore) {
        this(material, 0, amount, name, lore);
    }

    @Nonnull public abstract ClickType getClickType();

    @Nonnull public abstract Consumer<Player> getAction();
}
