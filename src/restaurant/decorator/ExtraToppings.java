package restaurant.decorator;

import restaurant.domain.IMenuItem;

public class ExtraToppings extends MenuDecorator{
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
