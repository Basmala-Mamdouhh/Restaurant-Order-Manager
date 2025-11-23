package restaurant.strategy.discounts;
import restaurant.Core.IMenuItem;
import restaurant.Core.MenuItem;

public class DiscountLogic {
    private IDiscountStrategy strategy;

    public DiscountLogic(IDiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public double applyDiscount(IMenuItem item) {
        return strategy.applyDiscount(item);
    }
}
