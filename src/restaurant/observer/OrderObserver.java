package restaurant.observer;
import restaurant.domain.Order;

public interface OrderObserver {
    void notifyOrderCreated(Order order);
    void notifyOrderCancelled(Order order);
}
