package de.api.devtools.menu.item;

import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;

public abstract class HotbarItem extends Clickable {

    private final int slot;

    public HotbarItem(int slot, Material material, int id, int amount, String name, String... lore) {
        super(material, id, amount, name, lore);
        this.slot = slot;
    }

    public HotbarItem(int slot, Material material, int amount, String name, String... lore) {
        super(material, amount, name, lore);
        this.slot = slot;
    }

    @Override
    public ClickType getClickType() {
        return ClickType.RIGHT;
    }

    public int getSlot() {
        return slot;
    }
}
