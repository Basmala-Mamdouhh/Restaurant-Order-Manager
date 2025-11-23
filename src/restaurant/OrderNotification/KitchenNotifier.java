package restaurant.OrderNotification;
import restaurant.Core.Order;
/**
 * KitchenNotifier is a Concrete Observer.
 * The kitchen needs to be notified whenever:
 *  - A new order is created (so they can start preparing it)
 *  - An order is cancelled (so they can stop preparing it)
 * This class reacts to the notifications by printing kitchen-specific messages.
 */
public class KitchenNotifier implements IOrderObserver {

    @Override
    public void notifyOrderCreated(Order order) {
        System.out.println("Kitchen notified: New order " + order.getOrderID());
        order.getItems().forEach(i ->
                System.out.println("  - " + i.getDescription())
        );
    }

    @Override
    public void notifyOrderCancelled(Order order) {
        System.out.println("Kitchen notified: Order cancelled " + order.getOrderID());
    }

}
