package restaurant.CustomAddOns;

import restaurant.Core.IMenuItem;

/**
 * Adds extra sauces to a menu item.
 */
public class ExtraSauces extends MenuDecorator {

    public ExtraSauces(IMenuItem baseItem) {
        super(baseItem);
    }

    @Override
    public double getPrice() {
        return baseItem.getPrice() + 2.0;
    }

    @Override
    public String getDescription() {
        return baseItem.getDescription() + " + Extra Sauces";
    }
}

