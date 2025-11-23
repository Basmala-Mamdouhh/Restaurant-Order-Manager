package restaurant.strategy.payment;
import restaurant.Core.Order;

public interface PaymentStrategy {
    public void pay(Order order, double totalAmount);
}
