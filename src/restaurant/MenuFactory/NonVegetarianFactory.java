package restaurant.MenuFactory;

import restaurant.Core.MenuItem;
import java.util.Arrays;
import java.util.List;
import restaurant.Core.IMenuItem;

public class NonVegetarianFactory implements IMenuFactory {

    @Override
    public List<IMenuItem> createMenuList() {
        return Arrays.asList(
                new MenuItem("Chicken Pizza", "PIZZA", 12.0),
                new MenuItem("Classic Beef Burger", "MEAT", 11.0),
                new MenuItem("Classic Chicken Burger", "CHICKEN", 10.0)
        );
    }

    @Override
    public MenuItem createItem(String itemType) {
        if (itemType == null) return null;
        String lower = itemType.trim().toLowerCase();

        // Match full names or shortened versions
        if (lower.contains("chicken") && lower.contains("pizza")) {
            return new MenuItem("Chicken Pizza", "PIZZA", 12.0);
        }
        if (lower.contains("beef") && lower.contains("burger")) {
            return new MenuItem("Classic Beef Burger", "MEAT", 11.0);
        }
        if (lower.contains("chicken") && lower.contains("burger")) {
            return new MenuItem("Classic Chicken Burger", "CHICKEN", 10.0);
        }
        return null;
    }
}
