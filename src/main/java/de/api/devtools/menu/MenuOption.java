package de.api.devtools.menu;

import de.api.devtools.item.ItemCreator;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public final class MenuOption {

    public static void fillGlassPanes(Menu menu, GlassColor color) {
        for (int slot = 0; slot < menu.getSize(); slot++) {
            if (menu.getItemAt(slot) != null) {
                continue;
            }

            final Inventory inventory = menu.getInventory();

            inventory.setItem(slot, ItemCreator.of(color.getMaterial(), 1, ChatColor.DARK_GRAY.toString(), "").make());
        }
    }
}
