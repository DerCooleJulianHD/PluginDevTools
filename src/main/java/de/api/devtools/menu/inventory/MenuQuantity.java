package de.api.devtools.menu.inventory;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Set;

public interface MenuQuantity {

   boolean isActionAllowed(int slot);

   void setDraggable(int slot);

   @NonNull Set<Integer> getDraggable();

   boolean getKeepOpen();

   void setKeepOpen(boolean b);
}
