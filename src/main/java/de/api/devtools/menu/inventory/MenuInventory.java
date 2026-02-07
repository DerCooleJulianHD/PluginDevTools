package de.api.devtools.menu.inventory;

import de.api.devtools.menu.item.Icon;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class MenuInventory implements InventoryHolder, MenuQuantity {

    protected final Inventory inventory;
    protected final Set<Integer> draggable = new HashSet<>();
    protected final Map<Integer, Icon> items = new HashMap<>();
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
    public Set<Integer> getDraggable() {
        return draggable;
    }

    public Icon getItemAt(int slot) {
        return items.get(slot);
    }

    public void setItem(int slot, Icon item) {
        inventory.setItem(slot, item);
        items.put(slot, item);
    }

    public Map<Integer, Icon> getItems() {
        return items;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }
}
