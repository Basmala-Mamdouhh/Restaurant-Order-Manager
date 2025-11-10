package restaurant.strategy;

import restaurant.domain.Order;

public class MobilWalletPayment implements  PaymentStrategy {
    private String walletNumber;
    public MobilWalletPayment(String walletNumber) { this.walletNumber = walletNumber; }

    @Override
    public void pay(Order order) {
        System.out.println("Paid " + order.getSubTotal() + " using mobil wallet: " + walletNumber);
    }

}
