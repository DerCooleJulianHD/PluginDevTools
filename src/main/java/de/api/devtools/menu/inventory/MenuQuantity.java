package de.api.devtools.menu.inventory;

import javax.annotation.Nonnull;
import java.util.Set;

public interface MenuQuantity {

   boolean isActionAllowed(int slot);

   void setDraggable(int slot);

   @Nonnull Set<Integer> getDraggable();

   boolean getKeepOpen();

   void setKeepOpen(boolean b);
}
