package de.api.devtools.menu.inventory;

import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public interface Viewable {

    @Nullable Player getViewer();

    void setViewer(Player player);

    default boolean isViewing(Player player) {
        return getViewer() != null && getViewer().equals(player);
    }
}
