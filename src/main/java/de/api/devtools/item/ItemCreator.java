package de.api.devtools.item;

import de.api.devtools.utils.TextUtil;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class ItemCreator implements ConfigurationSerializable {

    @Nonnull private final ItemStack item;
    @Nonnull private final ItemMeta meta;

    public ItemCreator(@Nonnull ItemStack itemStack, @Nonnull ItemMeta meta) {
        this.item = itemStack;
        this.meta = meta;
    }

    public ItemCreator(@Nonnull ItemStack itemStack) {
        this(itemStack, Objects.requireNonNull(itemStack.getItemMeta()));
    }

    public static ItemCreator of(Material material, int id, int amount, @Nullable String name, @Nullable String... lore) {
        // creating the bukkit itemstack.
        final ItemCreator creator = new ItemCreator(new ItemStack(material, amount, (byte) id));
        final ItemMeta meta = creator.getMeta();

        if (name != null) meta.setDisplayName(TextUtil.colorize(name));
        if (lore != null) meta.setLore(Arrays.stream(lore).toList());

        return creator;
    }

    public static ItemCreator of(@Nonnull Material material, int amount, @Nullable String name, @Nullable String... lore) {
        return of(material, 0, amount, name, lore);
    }

    public static ItemCreator ofSkull(int amount, @Nullable String name, @Nullable String... lore) {
        return of(Material.PLAYER_HEAD, amount, name, lore);
    }

    public ItemCreator setLore(@Nonnull String... lore) {
        meta.setLore(Arrays.stream(lore).toList());
        return this;
    }

    public ItemCreator glow() {
        meta.addEnchant(Enchantment.INFINITY, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemStack make() {
        item.setItemMeta(meta);
        return item;
    }

    @Nonnull public ItemStack getItem() {
        return item;
    }

    @Nonnull public ItemMeta getMeta() {
        return meta;
    }

    @Override
    public @Nonnull Map<String, Object> serialize() {
        final Map<String, Object> data = new HashMap<>();
        data.put("item", item);
        return data;
    }

    public static ItemCreator deserialize(@Nonnull Map<String, Object> data) {
        final ItemStack itemStack = (ItemStack) data.get("item");
        return new ItemCreator(itemStack);
    }
}
