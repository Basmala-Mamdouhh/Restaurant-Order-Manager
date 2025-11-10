package restaurant.decorator;

import restaurant.domain.IMenuItem;

public class ExtraSauces extends MenuDecorator{
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
