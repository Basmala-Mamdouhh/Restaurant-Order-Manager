package restaurant.strategy.payment;

import restaurant.Core.Order;

public class PaymentLogic {

    private final PaymentStrategy strategy;

    public PaymentLogic(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void pay(Order order, double totalAmount) {
        strategy.pay(order, totalAmount);
    }
}
