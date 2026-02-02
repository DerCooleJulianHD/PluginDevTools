package de.api.devtools.item;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public final class HotbarItem {

    private final int slot;
    private final ItemStack itemStack;

    private final Consumer<Player> action;

    public HotbarItem(int slot, ItemStack itemStack, @Nonnull Consumer<Player> action) {
        this.slot = slot;
        this.itemStack = itemStack;
        this.action = action;
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public @Nonnull Consumer<Player> getAction() {
        return action;
    }
}
