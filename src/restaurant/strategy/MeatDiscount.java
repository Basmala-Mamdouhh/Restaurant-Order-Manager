package restaurant.strategy;
import restaurant.domain.MenuItem;

public class MeatDiscount implements DiscountStrategy {

    @Override
    public double applyDiscount(MenuItem item) {
        return item.getPrice() * 0.80;
    }
}
