package restaurant.strategy;
import restaurant.domain.Order;

public interface PaymentStrategy {
    public void pay(Order order);
}
