package restaurant.strategy.discounts;
import restaurant.Core.IMenuItem;
import restaurant.Core.MenuItem;

public class PizzaDiscount implements IDiscountStrategy {

    @Override
    public double applyDiscount(IMenuItem item) {
        return item.getPrice() * 0.90;
    }
}
