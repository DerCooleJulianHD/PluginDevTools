package de.api.devtools.item;

import org.bukkit.inventory.ItemStack;

public class Icon extends ItemStack {

    private final ItemCreator creator;

    public Icon(ItemCreator creator) {
        super(creator.make());
        this.creator = creator;
    }

    public ItemCreator getCreator() {
        return creator;
    }
}
