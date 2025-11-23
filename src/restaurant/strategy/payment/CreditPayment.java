package restaurant.strategy.payment;

import restaurant.Core.Order;

public class CreditPayment implements PaymentStrategy {
    private String cardNumber;
    public CreditPayment(String cardNumber) { this.cardNumber = cardNumber; }

    @Override
    public void pay(Order order, double total) {
        System.out.println("Paid " + total + " using Credit Card: " + cardNumber);
    }
}
