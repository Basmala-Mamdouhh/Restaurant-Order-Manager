package restaurant.observer;

import restaurant.domain.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderNotifier {
    private List<OrderObserver> observers = new ArrayList<>();
    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }
    public void notifyOrderCreated(Order order) {
        for (OrderObserver observer : observers) {
            observer.notifyOrderCreated(order);
        }
    }
    public void notifyOrderCancelled(Order order) {
        for (OrderObserver observer : observers) {
            observer.notifyOrderCancelled(order);
        }
    }
}
