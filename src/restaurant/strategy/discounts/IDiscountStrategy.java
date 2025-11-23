package restaurant.strategy.discounts;
import restaurant.Core.IMenuItem;
import restaurant.Core.MenuItem;

public interface IDiscountStrategy {
    public double applyDiscount(IMenuItem item);
}
