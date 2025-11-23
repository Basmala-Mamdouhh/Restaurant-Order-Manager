package restaurant.OrderNotification;
import restaurant.Core.Order;
/**
 * IOrderSubject defines the Subject role in the Observer Pattern.
 * It represents any object that can be observed for order-related events.
 * Observers (e.g., Waiter, Kitchen) register themselves here to receive updates.
 * Responsibilities:
 *  - Allow observers to subscribe/unsubscribe.
 *  - Notify all observers when an order is created or cancelled.
 */
public interface IOrderSubject {
    void addObserver(IOrderObserver observer);
    void removeObserver(IOrderObserver observer);

    void notifyOrderCreated(Order order);
    void notifyOrderCancelled(Order order);
}
