package restaurant.decorator;

import restaurant.domain.IMenuItem;

public class ExtraCheese extends MenuDecorator {

    public ExtraCheese(IMenuItem item) {
        super(item);
    }

    @Override
    public double getPrice() {
        return baseItem.getPrice() + 2.0;
    }

    @Override
    public String getDescription() {
        return baseItem.getDescription() + " + Extra Cheese";
    }
}
