package restaurant.domain;

import restaurant.strategy.DiscountContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    private static final AtomicInteger idCounter = new AtomicInteger(100);
    private final int orderID;
    private final List<restaurant.domain.MenuItem> items = new ArrayList<>();
    private final String orderType; // e.g., "DINE_IN", "DELIVERY"

    public Order(String orderType) {
        this.orderType = orderType;
        this.orderID = idCounter.getAndIncrement();
    }

    public void addItem(restaurant.domain.MenuItem item) {
        if (item != null) items.add(item);
    }

    public void removeItem(restaurant.domain.MenuItem item) {
        items.remove(item);
    }

    public double getSubTotal() {
        return items.stream().mapToDouble(restaurant.domain.MenuItem::getPrice).sum();
    }

    public List<restaurant.domain.MenuItem> getItems() {
        return new ArrayList<>(items);
    }

    public String getOrderType() {
        return orderType;
    }

    public int getOrderID() {
        return orderID;
    }
    public double calculateTotalAfterDiscounts() {
        DiscountContext context = new DiscountContext();

        double total = 0;
        for (MenuItem item : items) {
            total += context.applyDiscount(item);
        }

        return total;
    }

}
