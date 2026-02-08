package de.api.devtools.menu.utils;

import de.api.devtools.menu.item.HotbarItem;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public final class HotbarItems {

    private static final Map<ItemStack, HotbarItem> map = new HashMap<>();

    public static void registerHotbarItem(HotbarItem item) {
        map.put(item, item);
    }

    public static Map<ItemStack, HotbarItem> getRegistered() {
        return map;
    }

    public static HotbarItem get(ItemStack itemStack) {
        return map.get(itemStack);
    }

    public static void unregister(ItemStack itemStack) {
        map.remove(itemStack);
    }
}
