package restaurant.MenuFactory;

import restaurant.Core.IMenuItem;
import restaurant.Core.MenuItem;
import java.util.Arrays;
import java.util.List;

public class KidsMenuFactory implements IMenuFactory {

    @Override
    public List<IMenuItem> createMenuList() {
        return Arrays.asList(
                new MenuItem("Kids Pizza", "PIZZA", 8.0),
                new MenuItem("Kids Beef Burger", "MEAT", 7.0),
                new MenuItem("Kids Chicken Burger", "CHICKEN", 6.0)

        );
    }

    @Override
    public MenuItem createItem(String itemType) {
        if (itemType == null) return null;
        String lower = itemType.trim().toLowerCase();

        // Match full names or shortened versions
        if (lower.contains("pizza")) {
            return new MenuItem("Kids Pizza", "PIZZA", 8.0);
        }
        if (lower.contains("beef") && lower.contains("burger")) {
            return new MenuItem("Kids Beef Burger", "MEAT", 7.0);
        }
        if (lower.contains("chicken") && lower.contains("burger")) {
            return new MenuItem("Kids Chicken Burger", "CHICKEN", 6.0);
        }
        return null;
    }
}
