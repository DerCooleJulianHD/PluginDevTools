package de.api.devtools.menu.inventory;

import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Set;

public interface MenuQuantity {

   boolean isActionAllowed(int slot);

   void setDraggable(int slot);

   @Nonnull Set<Integer> getDraggable();

   @Nullable ItemStack getItemAt(int slot);

   void setItem(int slot, ItemStack item);

   @Nonnull Map<Integer, ItemStack> getItems();
}
