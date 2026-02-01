package de.api.devtools.menu.inventory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MenuInventory implements InventoryHolder, MenuQuantity {

    protected final Inventory inventory;
    protected final Set<Integer> draggable = new HashSet<>();
    protected final Map<Integer, ItemStack> items = new HashMap<>();
    protected final int size;
    protected final String title;

    public MenuInventory(int size, String title) {
        this.size = size;
        this.title = title;
        this.inventory = Bukkit.createInventory(this, size, title);
    }

    @Override
    public boolean isActionAllowed(int slot) {
        return draggable.contains(slot);
    }

    @Override
    public void setDraggable(int slot) {
        draggable.add(slot);
    }

    @Override
    public @NonNull Set<Integer> getDraggable() {
        return draggable;
    }

    @Override
    public @Nullable ItemStack getItemAt(int slot) {
        return inventory.getItem(slot);
    }

    @Override
    public void setItem(int slot, ItemStack item) {
        inventory.setItem(slot, item);
        items.put(slot, item);
    }

    @Override
    public @NonNull Map<Integer, ItemStack> getItems() {
        return items;
    }

    @Override
    public @Nonnull Inventory getInventory() {
        return inventory;
    }

    public @Nonnull String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }
}
