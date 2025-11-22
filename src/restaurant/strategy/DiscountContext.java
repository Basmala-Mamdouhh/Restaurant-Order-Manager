package restaurant.strategy;
import restaurant.domain.MenuItem;

public class DiscountContext {
    public double applyDiscount(MenuItem item) {
        String category = item.getCategory().toUpperCase();
        double price = item.getPrice();
        return switch (category) {
            case "MEAT" -> price * 0.80;
            case "PIZZA" -> price * 0.90;
            case "CHICKEN" -> price * 0.85;
            default -> price;
        };
    }
}