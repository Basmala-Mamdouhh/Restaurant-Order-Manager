package restaurant.Core;

import restaurant.strategy.discounts.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    private static final AtomicInteger idCounter = new AtomicInteger(100);
    private final int orderID;
    private final List<restaurant.Core.IMenuItem> items = new ArrayList<>();
    private final String orderType; // e.g., "DINE_IN", "DELIVERY"

    public Order(String orderType) {
        this.orderType = orderType;
        this.orderID = idCounter.getAndIncrement();
    }

    public void addItem(restaurant.Core.IMenuItem item) {
        if (item != null) items.add(item);
    }

    public void removeItem(restaurant.Core.IMenuItem item) {
        items.remove(item);
    }

    public List<restaurant.Core.IMenuItem> getItems() {
        return new ArrayList<>(items);
    }

    public String getOrderType() {
        return orderType;
    }

    public int getOrderID() {
        return orderID;
    }
    public double calculateTotalAfterDiscounts() {
        double total = 0;

        for (IMenuItem item : items) {
            IDiscountStrategy strategy;

            switch (item.getCategory().toUpperCase()) {
                case "MEAT" -> strategy = new MeatDiscount();
                case "CHICKEN" -> strategy = new ChickenDiscount();
                case "PIZZA" -> strategy = new PizzaDiscount();
                default -> strategy = IMenuItem::getPrice; // no discount
            }

            DiscountLogic context = new DiscountLogic(strategy);
            total += context.applyDiscount(item);
        }

        return total;
    }


}
