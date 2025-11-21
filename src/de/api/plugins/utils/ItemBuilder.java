package de.api.plugins.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemBuilder {

    protected final Map<Enchantment, Integer> enchantments = new HashMap<>();
    protected ItemStack itemStack;
    protected Material material;
    // subid of material
    protected int id;
    protected String name;
    protected String displayName;
    protected boolean unbreakable = false;
    protected ItemMeta meta;

    protected ItemBuilder(Material material, int id, int amount) {
        this.itemStack = new ItemStack(material, amount, (byte) id);

        // from ItemStack of trooper class
        this.meta = itemStack.getItemMeta();

        this.material = material;
        this.id = id;
        this.displayName = meta.getDisplayName();
    }

    public static ItemBuilder of(Material material, int id, int amount) {
        return new ItemBuilder(material, id, amount);
    }

    public final ItemBuilder setDisplayName(String displayName) {
        this.displayName = displayName;

        if (meta != null)
            meta.setDisplayName(displayName);

        return this;
    }

    public final ItemBuilder setLore(List<String> lore) {
        if (meta != null) meta.setLore(lore);
        return this;
    }

    public final ItemBuilder setLore(String... lore) {
        if (meta != null) {
            final List<String> list = Arrays.stream(lore).toList();

            if (list.isEmpty())
                return this;

            return setLore(list);
        }

        return this;
    }

    public final ItemBuilder setUnbreakable(boolean b) {
        this.unbreakable = b;

        if (meta != null)
            meta.spigot().setUnbreakable(b);

        return this;
    }

    public final ItemBuilder addEnchant(Enchantment enchantment, int lvl) {
        this.enchantments.put(enchantment, lvl);

        if (meta != null)
            meta.addEnchant(enchantment, lvl, true);

        return this;
    }

    public final ItemBuilder removeEnchant(Enchantment enchantment) {
        this.enchantments.remove(enchantment);

        if (meta != null)
            meta.removeEnchant(enchantment);

        return this;
    }

    public final ItemBuilder removeEnchants() {
        if (meta != null)
            meta.getEnchants().keySet().forEach(this::removeEnchant);

        this.enchantments.clear();
        return this;
    }

    public final Material getMaterial() {
        return material;
    }

    public final ItemBuilder setMaterial(Material material) {
        this.material = material;
        itemStack.setType(material);
        return this;
    }

    public final int getId() {
        return id;
    }

    public final ItemBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public final Map<Enchantment, Integer> getEnchantments() {
        return enchantments;
    }

    public final ItemBuilder setMeta(ItemMeta meta) {
        this.meta = meta;
        return this;
    }

    public final ItemStack build() {
        if (meta == null) return itemStack;
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public final String getName() {
        return name;
    }

    public final ItemBuilder setName(String name) {
        this.name = ChatColor.stripColor(name);
        return this;
    }

    public final String getIDName() {
        return name;
    }

    public boolean hasName(String name) {
        return (this.name != null && (!this.name.isEmpty())) && name.equals(this.name);
    }

    public boolean hasIDName(String name) {
        return hasName(name);
    }

    public final ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
