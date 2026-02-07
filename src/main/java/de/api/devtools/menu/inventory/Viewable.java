package de.api.devtools.menu.inventory;

import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.Nullable;

public interface Viewable {

    Player getViewer();

    void setViewer(Player player);

    default boolean isViewing(Player player) {
        return getViewer() != null && getViewer().equals(player);
    }
}
