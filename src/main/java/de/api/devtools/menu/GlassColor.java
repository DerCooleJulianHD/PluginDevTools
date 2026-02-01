package de.api.devtools.menu;

import org.bukkit.Material;

public enum GlassColor {
    BLACK(Material.BLACK_STAINED_GLASS),
    RED(Material.RED_STAINED_GLASS_PANE),
    BLUE(Material.BLUE_STAINED_GLASS_PANE);

    private final Material material;

    GlassColor(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }
}
