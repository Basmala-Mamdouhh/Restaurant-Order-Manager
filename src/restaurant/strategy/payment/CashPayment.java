package restaurant.strategy.payment;

import restaurant.Core.Order;

public class CashPayment implements PaymentStrategy {
    @Override
    public void pay(Order order, double total) {
        System.out.println("Paid " + total + " by cash.");
    }
}