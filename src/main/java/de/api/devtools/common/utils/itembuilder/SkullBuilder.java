package de.api.devtools.common.utils.itembuilder;

import de.api.devtools.common.utils.TextUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.Objects;

public class SkullBuilder extends ItemStack {

    private final SkullMeta meta;

    public SkullBuilder(int amount, String name, String... lore) {
        super(Material.PLAYER_HEAD, amount); // creating the skull.
        this.meta = Objects.requireNonNull((SkullMeta) getItemMeta()); // getting the itemmeta of this itemstack.
        if (name != null) meta.setDisplayName(TextUtil.colorize(name)); // setting the displayname
        if (lore != null) meta.setLore(Arrays.stream(lore).toList()); // setting the lore.
    }

    public SkullBuilder(Player owner, int amount, String name, String... lore) {
        this(amount, name, lore);
        this.setOwner(owner.getName());
    }

    public final SkullMeta getMeta() {
        return meta;
    }

    public final void setOwner(String playername) {
        meta.setOwner(playername);
    }
}
