package restaurant.strategy;

import restaurant.domain.Order;

public class CreditPayment implements PaymentStrategy{
    private String cardNumber;
    public CreditPayment(String cardNumber) { this.cardNumber = cardNumber; }

    @Override
    public void pay(Order order) {
        System.out.println("Paid " + order.getSubTotal() + " using Credit Card: " + cardNumber);
    }

}
