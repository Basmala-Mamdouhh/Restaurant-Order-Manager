package restaurant.observer;
import restaurant.domain.Order;

public class KitchenNotifier implements OrderObserver {

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
