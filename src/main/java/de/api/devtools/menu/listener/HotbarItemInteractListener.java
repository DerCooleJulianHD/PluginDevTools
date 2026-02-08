package de.api.devtools.menu.listener;

import de.api.devtools.menu.item.HotbarItem;
import de.api.devtools.menu.utils.HotbarItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class HotbarItemInteractListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        HotbarItems.getRegistered().values().forEach(item -> {
            player.getInventory().setItem(item.getSlot(), item);
        });
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.hasItem())
            return;

        if (event.getAction() != Action.RIGHT_CLICK_AIR)
            return;

        final HotbarItem item = HotbarItems.get(event.getItem());

        if (item == null)
            return;

        item.getAction().accept(event.getPlayer());
    }
}
