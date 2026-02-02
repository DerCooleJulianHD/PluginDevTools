package de.api.devtools.menu;

import de.api.devtools.item.Clickable;
import de.api.devtools.item.ItemCreator;
import de.api.devtools.menu.inventory.MenuInventory;
import de.api.devtools.menu.inventory.Rows;
import de.api.devtools.menu.inventory.Viewable;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class Menu extends MenuInventory implements Viewable {

    protected final @Nullable Menu parent;
    protected boolean keepOpen = false;

    protected Player viewer = null;

    public Menu(@Nullable Menu parent, @Nonnull Rows rows, @Nonnull String title) {
        super(rows.getSize(), title);
        this.parent = parent;
        if (parent != null) inventory.setItem(getSize() - 1, getButtonReturnBack());
    }

    public final void open(Player player) {
        setViewer(player);
        player.openInventory(getInventory());
    }

    @Override
    public @Nullable Player getViewer() {
        return viewer;
    }

    @Override
    public void setViewer(Player player) {
        this.viewer = player;
    }

    public final @Nullable Menu getParent() {
        return parent;
    }

    @Override
    public final boolean getKeepOpen() {
        return keepOpen;
    }

    @Override
    public void setKeepOpen(boolean b) {
        this.keepOpen = b;
    }

    public final Clickable getButtonReturnBack() {
        final ItemCreator item = ItemCreator.of(Material.SPRUCE_DOOR, 1, ChatColor.RED + "Return", ChatColor.GRAY + "Return to previous Menu.");
        return new Clickable(item, ClickType.LEFT, player -> {
            if (parent == null)
                return;

            parent.open(player);
        });
    }
}
