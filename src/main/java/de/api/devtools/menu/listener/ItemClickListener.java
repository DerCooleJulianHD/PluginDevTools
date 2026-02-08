package de.api.devtools.menu.listener;

import de.api.devtools.menu.item.Clickable;
import de.api.devtools.menu.item.Icon;
import de.api.devtools.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public final class ItemClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;

        //condition to check if inventory was a menu.
        if (!(event.getClickedInventory().getHolder() instanceof Menu menu)) return;

        final Inventory inventory = menu.getInventory();
        final ItemStack item = event.getCurrentItem();
        final ClickType clickType = event.getClick();

        if (item == null) return;
        if (!menu.isActionAllowed(event.getSlot())) event.setResult(Event.Result.DENY);

        final Icon icon = menu.getItemAt(event.getSlot());

        if (icon == null) return;
        if (!(icon instanceof Clickable clickable)) return;
        if (clickType != clickable.getClickType()) return;
        clickable.getAction().accept((Player) event.getWhoClicked());
        if (!menu.getKeepOpen()) event.getWhoClicked().closeInventory();
    }
}
