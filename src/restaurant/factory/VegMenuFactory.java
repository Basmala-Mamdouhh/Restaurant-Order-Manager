package restaurant.factory;

import restaurant.domain.MenuItem;

import java.util.Arrays;
import java.util.List;

public class VegMenuFactory implements IMenuFactory {

    @Override
    public List<MenuItem> createMenu() {
        return Arrays.asList(
                new MenuItem("Veg Pizza", "PIZZA", 10.0),
                new MenuItem("Veg Burger", "BURGER", 9.0)
        );
    }

    @Override
    public MenuItem createItem(String itemKey) {
        if (itemKey == null) return null;

        switch (itemKey.trim().toLowerCase()) {
            case "pizza":
                return new MenuItem("Veg Pizza", "PIZZA", 10.0);
            case "italian":
                return new MenuItem("Veg Italian Pizza", "PIZZA", 11.0);
            case "eastern":
                return new MenuItem("Veg Eastern Pizza", "PIZZA", 10.5);
            case "burger":
                return new MenuItem("Veg Burger", "BURGER", 9.0);
            case "classic":
                return new MenuItem("Veg Classic Burger", "BURGER", 9.5);
            default:
                return null; // throw Exception
        }
    }
}
