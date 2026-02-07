package de.api.devtools.menu.inventory;

import java.util.Set;

public interface MenuQuantity {

   boolean isActionAllowed(int slot);

   void setDraggable(int slot);

   Set<Integer> getDraggable();

   boolean getKeepOpen();

   void setKeepOpen(boolean b);
}
