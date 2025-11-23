package restaurant.OrderNotification;

import restaurant.Core.Order;
import java.util.ArrayList;
import java.util.List;
/**
 * OrderNotifier is the Concrete Subject in the Observer Pattern.
 * This class maintains a list of observers (Kitchen, Waiter, etc.)
 * and notifies them whenever an event occurs, such as:
 *  - A new order being created
 *  - An existing order being cancelled
 * It decouples the order logic from the notification logic,
 * making the system flexible and easy to extend.
 */
public class OrderNotifier implements IOrderSubject {

    private final List<IOrderObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(IOrderObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IOrderObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyOrderCreated(Order order) {
        for (IOrderObserver observer : observers) {
            observer.notifyOrderCreated(order);
        }
    }

    @Override
    public void notifyOrderCancelled(Order order) {
        for (IOrderObserver observer : observers) {
            observer.notifyOrderCancelled(order);
        }
    }
}
