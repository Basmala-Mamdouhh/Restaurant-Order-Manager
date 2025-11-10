package restaurant.factory;
import restaurant.domain.MenuItem;
import java.util.Arrays;
import java.util.List;

public class KidsMenuFactory implements IMenuFactory{
    @Override
    public List<MenuItem> createMenu() {
        return Arrays.asList(
                new MenuItem("Small Pizza", "PIZZA", 10.0),
                new MenuItem("Small Burger", "Burger", 9.0)
        );
    }

    @Override
    public MenuItem createItem(String itemKey) {
        if (itemKey == null) return null;
        switch (itemKey.trim().toLowerCase()) {
            case "pizza":
            case "small chicken pizza":
                return new MenuItem("Small Chicken Pizza", "PIZZA", 10.0);
            case "burger":
            case "small beef burger":
                return new MenuItem("Small Beef Burger", "Burger", 9.0);
            default:
                return null; // or throw IllegalArgumentException("Unknown itemKey: " + itemKey)
        }
    }
}
