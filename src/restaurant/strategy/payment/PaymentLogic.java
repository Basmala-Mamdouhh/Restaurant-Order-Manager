package restaurant.strategy.payment;


import restaurant.Core.Order;

public class PaymentLogic {
    private PaymentStrategy startegy;
    public PaymentLogic(PaymentStrategy strategy) {
        this.startegy = strategy;
    }
    public void pay(Order order, double totalAmount) {
        startegy.pay(order, totalAmount);
    }
}
