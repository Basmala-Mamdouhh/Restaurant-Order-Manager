package restaurant.OrderNotification;
import restaurant.Core.Order;
/**
 * OrderObserver defines the Observer role in the Observer Pattern.
 * Any class that needs to be notified when a new order is created
 * or cancelled must implement this interface.
 * Examples of observers:
 *  - KitchenNotifier
 *  - WaiterNotifier
 * Each observer decides how to react to the notification.
 */
public interface IOrderObserver {
    void notifyOrderCreated(Order order);
    void notifyOrderCancelled(Order order);
}
