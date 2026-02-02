package de.api.devtools;

import de.api.devtools.item.Clickable;
import de.api.devtools.item.HotbarItem;
import de.api.devtools.item.Icon;
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
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public final class MinecraftDevToolPlugin extends JavaPlugin {

    private static Set<HotbarItem> hotbarItems;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("&8[&aPluginDevTools&8] " + ChatColor.GREEN + "Successfully Enabled :D");

        final PluginManager manager = getServer().getPluginManager();
        hotbarItems = new HashSet<>();
        manager.registerEvents(new ItemClickListener(), this);
    }

    public static void registerHotbarItem(HotbarItem item) {
        hotbarItems.add(item);
    }

    @Nullable
    public static HotbarItem getHotbarItem(ItemStack itemStack) {
        for (HotbarItem hotbarItem : hotbarItems) {
            if (itemStack.equals(hotbarItem.getItemStack())) {
                return hotbarItem;
            }
        }

        return null;
    }

    private static final class ItemClickListener implements Listener {

        @EventHandler
        public void onInteract(PlayerInteractEvent event) {
            if (!event.hasItem()) return;

            final Player player = event.getPlayer();
            final ItemStack item = event.getItem();

            if (item == null) return;
            if (event.getAction() != Action.RIGHT_CLICK_AIR) return;

            final HotbarItem hotbarItem = getHotbarItem(item);
            if (hotbarItem == null) return;
            hotbarItem.getAction().accept(player);
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

            if (!menu.isActionAllowed(event.getSlot())) {
                event.setResult(Event.Result.DENY);
            }

            final Icon icon = menu.getItemAt(event.getSlot());

            if (icon == null) return;
            if (!(icon instanceof Clickable clickable)) return;
            if (clickType != clickable.getClickType()) return;
            clickable.getAction().accept((Player) event.getWhoClicked());
            if (!menu.getKeepOpen()) event.getWhoClicked().closeInventory();
        }
    }
}
