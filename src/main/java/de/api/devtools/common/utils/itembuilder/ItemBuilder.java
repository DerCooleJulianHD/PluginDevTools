package de.api.devtools.common.utils.itembuilder;

import de.api.devtools.common.utils.TextUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Objects;

public class ItemBuilder extends ItemStack {

    private final ItemMeta meta;

    public ItemBuilder(Material material, int id, int amount, String name, String... lore) {
        super(material, amount, (byte) id); // creating the bukkit itemstack.
        this.meta = Objects.requireNonNull(getItemMeta()); // getting the itemmeta of this itemstack.
        if (name != null) meta.setDisplayName(TextUtil.colorize(name)); // setting the displayname
        if (lore != null) meta.setLore(Arrays.stream(lore).toList()); // setting the lore.
    }

    public ItemBuilder(Material material, int amount, String name, String... lore) {
        this(material, 0, amount, name, lore);
    }

    public final ItemMeta getMeta() {
        return meta;
    }
}
