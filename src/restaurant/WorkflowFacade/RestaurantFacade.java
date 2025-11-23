package restaurant.WorkflowFacade;

import restaurant.Core.IMenuItem;
import restaurant.Core.Order;
import restaurant.CustomAddOns.ExtraCheese;
import restaurant.CustomAddOns.ExtraSauces;
import restaurant.CustomAddOns.ExtraToppings;
import restaurant.MenuFactory.IMenuFactory;
import restaurant.OrderNotification.IOrderObserver;
import restaurant.OrderNotification.OrderNotifier;
import restaurant.strategy.discounts.DiscountLogic;
import restaurant.strategy.discounts.PizzaDiscount;
import restaurant.strategy.payment.PaymentLogic;
import restaurant.strategy.payment.PaymentStrategy;

import java.util.List;

public class RestaurantFacade {

    private static final double TAX_PERCENTAGE = 0.10; // 10% tax
    private static final double DELIVERY_TAX = 0.20; // 20% tax
    private final OrderNotifier orderNotifier;

    public RestaurantFacade() {
        this.orderNotifier = new OrderNotifier();
    }

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

    // Notify observers about order creation
    public void notifyOrder(Order order) {
        orderNotifier.notifyOrderCreated(order);
    }

    // Notify observers about order cancellation
    public void notifyOrderCancelled(Order order) {
        orderNotifier.notifyOrderCancelled(order);
    }

    // Cancel an order and notify observers
    public void cancelOrder(Order order) {
        System.out.println("\n--- ORDER CANCELLED ---");
        System.out.println("Order ID: " + order.getOrderID() + " has been cancelled.");
        notifyOrderCancelled(order);
    }

    // Register observers (Kitchen, Waiter, etc.)
    public void registerObserver(IOrderObserver observer) {
        orderNotifier.addObserver(observer);
    }

    // Remove observer if needed
    public void unregisterObserver(IOrderObserver observer) {
        orderNotifier.removeObserver(observer);
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

        // Apply tax based on order type
        String orderType = order.getOrderType().toUpperCase();
        double tax = 0;
        if (orderType.equals("DINE_IN")) {
            tax = total * TAX_PERCENTAGE; // 10% tax for dine-in
        } else if (orderType.equals("DELIVERY")) {
            tax = total * DELIVERY_TAX; // 20% tax for delivery
        }
        // TAKEAWAY: no tax (tax remains 0)

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

    // Complete Ordering Workflow
    // This method orchestrates the complete ordering process:
    // 1. Create order with items and order type
    // 2. Apply discounts
    // 3. Calculate total with tax
    // 4. Notify kitchen and waiter
    // 5. Process payment and generate receipt
    public void processOrderWorkflow(String orderType, List<IMenuItem> items, 
                                     PaymentStrategy paymentStrategy) {
        // Step 1: Create order
        Order order = createOrder(orderType, items);
        
        // Step 2: Notify kitchen and waiter about the new order
        notifyOrder(order);
        
        // Step 3: Process payment and generate receipt
        // (calculateTotal is called inside payOrder)
        payOrder(order, paymentStrategy);
    }
}
