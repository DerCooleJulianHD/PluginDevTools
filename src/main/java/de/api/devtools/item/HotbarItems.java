package de.api.devtools.item;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public final class HotbarItems {

    private static final Map<ItemStack, Clickable> items = new HashMap<>();

    public static void registerHotbarItem(Clickable clickable) {
        items.put(clickable, clickable);
    }

    public static void removeHotbarItem(Clickable clickable) {
        items.remove(clickable);
    }

    public static Clickable getItem(ItemStack itemStack) {
        return items.get(itemStack);
    }

    public static boolean isHotbarItem(ItemStack itemStack) {
        return items.containsKey(itemStack);
    }
}
