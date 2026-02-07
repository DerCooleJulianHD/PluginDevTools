package de.api.devtools.menu.item;

import de.api.devtools.common.utils.itembuilder.ItemBuilder;
import org.bukkit.Material;
import org.checkerframework.checker.nullness.qual.NonNull;

public class Icon extends ItemBuilder {

    public Icon(@NonNull Material material, int id, int amount, String name, String... lore) {
        super(material, id, amount, name, lore);
    }

    public Icon(@NonNull Material material, int amount, String name, String... lore) {
        super(material, amount, name, lore);
    }
}
