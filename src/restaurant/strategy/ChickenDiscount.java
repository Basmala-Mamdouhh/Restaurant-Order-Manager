package restaurant.strategy;
import restaurant.domain.MenuItem;

public class ChickenDiscount implements DiscountStrategy {

    @Override
    public double applyDiscount(MenuItem item) {
        return item.getPrice() * 0.5;
    }
}
