package restaurant.observer;
import restaurant.domain.Order;

public class WaiterNotifier implements OrderObserver{
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
