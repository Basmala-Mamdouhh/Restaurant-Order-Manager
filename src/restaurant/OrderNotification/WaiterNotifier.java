package restaurant.OrderNotification;
import restaurant.Core.Order;
/**
 * WaiterNotifier is another Concrete Observer.
 * The waiter must also be informed when:
 *  - A new order is created (so they can serve or follow up)
 *  - An order is cancelled (so they stop monitoring it)
 * This class handles waiter-specific notifications.
 */
public class WaiterNotifier implements IOrderObserver {
    @Override
    public void notifyOrderCreated(Order order) {
        System.out.println("Waiter notified: New order " + order.getOrderID());
        order.getItems().forEach(i ->
                System.out.println("  - " + i.getDescription())
        );
    }

    @Override
    public void notifyOrderCancelled(Order order) {
        System.out.println("Waiter notified: Order cancelled " + order.getOrderID());

    }
}
