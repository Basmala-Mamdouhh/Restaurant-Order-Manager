package restaurant.MenuFactory;

import restaurant.Core.MenuItem;
import java.util.Arrays;
import java.util.List;
import restaurant.Core.IMenuItem;

public class VegetarianFactory implements IMenuFactory {

    @Override
    public List<IMenuItem> createMenuList() {
        return Arrays.asList(
                new MenuItem("Italian Pizza", "PIZZA", 11.0),
                new MenuItem("Eastern Pizza", "PIZZA", 10.5)
        );
    }

    @Override
    public MenuItem createItem(String itemType) {
        String lower = itemType.trim().toLowerCase();

        // Match full names or shortened versions
        if (lower.contains("italian") && lower.contains("pizza")) {
            return new MenuItem("Italian Pizza", "PIZZA", 11.0);
        }
        if (lower.contains("eastern") && lower.contains("pizza")) {
            return new MenuItem("Eastern Pizza", "PIZZA", 10.5);
        }
        return null;
    }


}
