package de.api.devtools.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public final class ItemCreator {

    private static ItemStack item;
    private static ItemMeta meta;

    public static ItemCreator of(Material material, int amount, String name, String... lore) {
        return of(material, 0, amount, name, lore);
    }

    public static ItemCreator of(Material material, int id, int amount, String name, String... lore) {
        // creating the bukkit itemstack.
        item = new ItemStack(material, amount, (byte) id);
        // getting the itemmeta on that itemstack
        meta = item.getItemMeta();
        meta.setDisplayName(TextUtil.colorize(name));
        meta.setLore(Arrays.stream(lore).toList());

        return new ItemCreator();
    }

    public ItemCreator glow() {
        meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemStack make() {
        item.setItemMeta(meta);
        return item;
    }
}
