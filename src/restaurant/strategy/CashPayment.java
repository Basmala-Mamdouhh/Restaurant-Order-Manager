package restaurant.strategy;

import restaurant.domain.Order;

public class CashPayment implements PaymentStrategy {
    @Override
    public void pay(Order order) {
        System.out.println("Paid " + order.getSubTotal() + " by cash.");
    }
}
