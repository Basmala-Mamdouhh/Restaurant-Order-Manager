package restaurant.strategy;
import restaurant.domain.MenuItem;

public interface DiscountStrategy {
    public double applyDiscount(MenuItem item);
}
