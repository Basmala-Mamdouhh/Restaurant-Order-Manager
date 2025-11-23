package restaurant.CustomAddOns;
import restaurant.Core.IMenuItem;

/**
 * Adds extra toppings to a menu item.
 */
public class ExtraToppings extends MenuDecorator {

    public ExtraToppings(IMenuItem baseItem) {
        super(baseItem);
    }

    @Override
    public double getPrice() {
        return baseItem.getPrice() + 2.0;
    }

    @Override
    public String getDescription() {
        return baseItem.getDescription() + " + Extra Toppings";
    }
}
