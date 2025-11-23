package restaurant.strategy.payment;

import restaurant.Core.Order;

public class MobileWalletPayment implements PaymentStrategy {
    private String walletNumber;
    public MobileWalletPayment(String walletNumber) { this.walletNumber = walletNumber; }

    @Override
    public void pay(Order order, double total) {
        System.out.println("Paid " + total + " using Mobile Wallet: " + walletNumber);
    }
}
