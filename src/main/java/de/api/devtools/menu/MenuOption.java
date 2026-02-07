package de.api.devtools.menu;

import de.api.devtools.common.utils.itembuilder.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public final class MenuOption {

    public static void fillGlassPanes(Menu menu, GlassColor color) {
        for (int slot = 0; slot < menu.getSize(); slot++) {
            if (menu.getInventory().getItem(slot) != null) {
                continue;
            }

            final Inventory inventory = menu.getInventory();

            inventory.setItem(slot, new ItemBuilder(color.getMaterial(), 1, ChatColor.DARK_GRAY.toString(), ""));
        }
    }
}
