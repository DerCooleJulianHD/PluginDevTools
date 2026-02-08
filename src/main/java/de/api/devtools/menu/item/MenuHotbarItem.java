package de.api.devtools.menu.item;

import de.api.devtools.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class MenuHotbarItem extends HotbarItem {

    private final Menu menu;

    public MenuHotbarItem(Menu menu, int slot, Material material, int id, int amount, String name, String... lore) {
        super(slot, material, id, amount, name, lore);
        this.menu = menu;
    }

    public MenuHotbarItem(Menu menu, int slot, Material material, int amount, String name, String... lore) {
        super(slot, material, amount, name, lore);
        this.menu = menu;
    }

    @Override
    public Consumer<Player> getAction() {
        return player -> {
            if (menu != null)
                menu.open(player);
        };
    }
}
