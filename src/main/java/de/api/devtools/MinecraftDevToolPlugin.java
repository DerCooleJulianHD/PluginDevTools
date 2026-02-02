package de.api.devtools;

import de.api.devtools.item.Clickable;
import de.api.devtools.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftDevToolPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("&8[&aPluginDevTools&8] " + ChatColor.GREEN + "Successfully Enabled :D");

        final PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new ItemClickListener(), this);
    }

    private static final class ItemClickListener implements Listener {

        @EventHandler
        public void onItemInteract(PlayerInteractEvent event) {
            if (!event.hasItem())
                return;

            final ItemStack item = event.getItem();
            final Player clicker = event.getPlayer();

            if (!(item instanceof Clickable clickable)) return;
            if (event.getAction() != Action.RIGHT_CLICK_AIR || event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

            clickable.getAction().accept(clicker);
        }

        @EventHandler
        public void onInventoryClick(InventoryClickEvent event) {
            if (event.getClickedInventory() == null) return;

            //condition to check if inventory was a menu.
            if (!(event.getClickedInventory().getHolder() instanceof Menu menu)) return;

            final Inventory inventory = menu.getInventory();
            final ItemStack item = event.getCurrentItem();
            final ClickType clickType = event.getClick();

            if (item == null) return;
            if (!(item instanceof Clickable clickable)) return;
            event.setResult(Event.Result.DENY);

            if (clickType != clickable.getClickType()) return;
            clickable.getAction().accept((Player) event.getWhoClicked());
            if (!menu.getKeepOpen()) event.getWhoClicked().closeInventory();
        }
    }
}
