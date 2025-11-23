package restaurant.WorkflowFacade;

import restaurant.Core.IMenuItem;
import restaurant.Core.Order;
import restaurant.CustomAddOns.ExtraCheese;
import restaurant.CustomAddOns.ExtraSauces;
import restaurant.CustomAddOns.ExtraToppings;
import restaurant.MenuFactory.IMenuFactory;
import restaurant.strategy.discounts.DiscountLogic;
import restaurant.strategy.discounts.PizzaDiscount;
import restaurant.strategy.payment.PaymentLogic;
import restaurant.strategy.payment.PaymentStrategy;

import java.util.List;

public class RestaurantFacade {

    private static final double TAX_PERCENTAGE = 0.10; // 10% tax
    private static final double DELIVERY_TAX = 0.20; // 20% tax

    // 1. Display menu
    public void displayMenu(IMenuFactory factory) {
        System.out.println("\n--- MENU ---");
        factory.createMenuList().forEach(System.out::println);
    }

    // 2. Place order
    public Order createOrder(String orderType, List<IMenuItem> items) {
        Order order = new Order(orderType);
        items.forEach(order::addItem);
        return order;
    }

    // 3. Add add-ons (fix: apply decorators correctly)
    public IMenuItem addExtra(IMenuItem item, List<String> addons) {
        IMenuItem decorated = item;
        for (String addon : addons) {
            switch (addon.toLowerCase()) {
                case "cheese" -> decorated = new ExtraCheese(decorated);
                case "sauces" -> decorated = new ExtraSauces(decorated);
                case "toppings" -> decorated = new ExtraToppings(decorated);
            }
        }
        return decorated;
    }

    // 4. Calculate total with discounts + tax
    public double calculateTotal(Order order) {
        double total = 0;
        for (IMenuItem item : order.getItems()) {
            DiscountLogic discountLogic = new DiscountLogic(new PizzaDiscount());
            double discountedPrice = discountLogic.applyDiscount(item);
            total += discountedPrice; // discount applied per item
        }
        double tax = total * TAX_PERCENTAGE;
        return total + tax;
    }

    // 5. Process payment (fix: print total with discounts + tax)
    public void payOrder(Order order, PaymentStrategy paymentStrategy) {
        double total = calculateTotal(order);
        System.out.println("\n--- RECEIPT ---");
        System.out.println("Order ID: " + order.getOrderID());
        System.out.println("Order Type: " + order.getOrderType());
        System.out.println("Items:");
        order.getItems().forEach(i ->
                System.out.println(" - " + i.getDescription() + " : $" + i.getPrice())
        );
        System.out.printf("Total after discounts + tax: $%.2f%n", total);

        // Use PaymentLogic
        PaymentLogic logic = new PaymentLogic(paymentStrategy);
        logic.pay(order, total);
    }
}
