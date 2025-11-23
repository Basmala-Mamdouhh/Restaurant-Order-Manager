package restaurant.strategy.discounts;

import restaurant.Core.IMenuItem;

public class DiscountLogic {

    private final IDiscountStrategy strategy;

    public DiscountLogic(IDiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public double applyDiscount(IMenuItem item) {
        return strategy.applyDiscount(item);
    }
}
