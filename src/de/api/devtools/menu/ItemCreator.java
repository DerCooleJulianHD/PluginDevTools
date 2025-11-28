package de.api.devtools.menu;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public final class ItemCreator {

    private static ItemStack item;
    private static ItemMeta meta;

    public static ItemCreator of(Material material, int amount) {
        return of(material, 0, amount);
    }

    public static ItemCreator of(Material material, int id, int amount) {
        // creating the bukkit itemstack.
        item = new ItemStack(material, amount, (byte) id);
        // getting the itemmeta on that itemstack
        meta = item.getItemMeta();

        return new ItemCreator();
    }

    public ItemCreator glow() {
        meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemCreator lore(String... lore) {
        meta.setLore(Arrays.stream(lore).toList());
        return this;
    }

    public ItemCreator name(String displayName) {
        meta.setDisplayName(displayName);
        return this;
    }

    public ItemStack make() {
        item.setItemMeta(meta);
        return item;
    }
}
