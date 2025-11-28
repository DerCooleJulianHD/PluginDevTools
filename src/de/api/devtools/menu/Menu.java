package de.api.devtools.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.HashMap;

import java.util.Map;

public abstract class Menu implements InventoryHolder {

    private Player viewer = null;

    private static final ItemStack NO_ITEM = null;

    private final Map<Integer, Icon> map = new HashMap<>();

    private Inventory inventory;

    protected int size;
    protected String title;

    public final void open(Player player, @Nullable Sound sound) {
        this.inventory = Bukkit.createInventory(this, size, title);
        setIcons();
        player.openInventory(inventory);

        if (sound != null)
            player.playSound(player.getLocation(), sound, 3, 0);

        setViewer(player);
    }

    private void setIcons() {
        if (inventory == null)
            return;

        map.values().forEach(icon -> {
            inventory.setItem(icon.getPosition().value(), icon.getItem());
        });
    }

    public final void setSize(int size) {
        if (size > 54 || size < 9)
            throw new IllegalStateException("size must be a number between 9 and 54");

        this.size = size;

        if (viewer != null)
            open(viewer, null);
    }

    public final void setEmptySlots(Material material) {
        final ItemStack FILL_ITEM = ItemCreator.of(
                material, 1)
                .name("")
                .make();

        for (int slot = 0; slot < inventory.getSize(); slot++) {
            // preventing from set into a slot where an icon is placed
           if (map.containsKey(slot))
               continue;

           inventory.setItem(slot, FILL_ITEM);
        }
    }

    @Override public Inventory getInventory() {
        return inventory;
    }

    public ItemStack getItemAt(int slot) {
        return NO_ITEM;
    }

    private void setViewer(Player viewer) {
        this.viewer = viewer;
    }

    public Player getViewer() {
        return viewer;
    }
}
