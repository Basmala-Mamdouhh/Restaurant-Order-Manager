package restaurant.strategy.payment;
import restaurant.Core.Order;

public interface PaymentStrategy {
    void pay(Order order, double totalAmount);
}
