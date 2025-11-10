// src/restaurant/factory/NonVegMenuFactory.java
package restaurant.factory;

import java.util.Arrays;
import java.util.List;
import restaurant.domain.MenuItem;

public class NonVegMenuFactory implements restaurant.factory.IMenuFactory {

    @Override
    public List<MenuItem> createMenu() {
        return Arrays.asList(
                new MenuItem("Non Veg Pizza", "PIZZA", 10.0),
                new MenuItem("Non Veg Burger", "BURGER", 9.0)
        );
    }

    @Override
    public MenuItem createItem(String itemKey) {
        if (itemKey == null) return null;
        switch (itemKey.trim().toLowerCase()) {
            case "chicken pizza":
                return new MenuItem("Chicken Pizza", "PIZZA", 10.0);
            case "beef burger":
                return new MenuItem("Beef Burger", "BURGER", 9.0);
            default:
                return null; // or throw IllegalArgumentException("Unknown itemKey: " + itemKey)
        }
    }
}
