package restaurant.Core;
/**
 * MenuItem is the basic item that appears in the restaurant menu.
 * It is the Concrete Component in the Decorator Pattern.
 * Decorators can wrap this object to add extra behaviors (add-ons).
 */
public class MenuItem implements IMenuItem {
    private final String name;
    private final String description;
    private final double price;
    private final String category;

    public MenuItem(String name, String category, double price) {
        this(name, category, price, "");
    }

    public MenuItem(String name, String category, double price, String description) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() {
        return description.isEmpty() ? name : description;
    }

    @Override
    public double getPrice() { return price; }

    @Override
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return name + " (" + category + ") - " + price;
    }
}
