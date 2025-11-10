// src/restaurant/domain/MenuItem.java
package restaurant.domain;

public class MenuItem {
    private final String name;
    private final String description;
    private final double price;
    private final String category; // now a String

    public MenuItem(String name, String category, double price) {
        this(name, category, price, "");
    }

    public MenuItem(String name, String category, double price, String description) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
    }



    public String getName() {
        return name;
    }

    public String getDescription() {
        return description.isEmpty() ? name : description;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name + " (" + category + ") - " + price;
    }
}
