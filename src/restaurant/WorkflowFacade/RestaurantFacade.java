package restaurant.WorkflowFacade;

import restaurant.Core.IMenuItem;
import restaurant.Core.Order;
import restaurant.CustomAddOns.ExtraCheese;
import restaurant.CustomAddOns.ExtraSauces;
import restaurant.CustomAddOns.ExtraToppings;
import restaurant.MenuFactory.IMenuFactory;
import restaurant.OrderNotification.IOrderObserver;
import restaurant.OrderNotification.OrderNotifier;
import restaurant.strategy.discounts.*;
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

    private double applyCategoryDiscount(IMenuItem item) {

        IDiscountStrategy strategy = switch (item.getCategory().toUpperCase()) {
            case "PIZZA" -> new PizzaDiscount();
            case "MEAT" -> new MeatDiscount();
            case "CHICKEN" -> new ChickenDiscount();
            default -> null;
        };

        DiscountLogic logic = new DiscountLogic(strategy);
        return logic.applyDiscount(item);
    }

    // 4. Calculate total with discounts + tax
    public double calculateTotal(Order order) {
        double subtotal = 0;

        for (IMenuItem item : order.getItems()) {
            subtotal += applyCategoryDiscount(item);
        }

        // TAX by order type
        double tax = switch(order.getOrderType()) {
            case "DINE_IN" -> subtotal * 0.10;
            case "DELIVERY" -> subtotal * 0.20;
            default -> 0;   // TAKEAWAY no tax
        };

        return subtotal + tax;
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

    public void processOrderWorkflow(String orderType, List<IMenuItem> items,PaymentStrategy paymentStrategy) { //orchestrator
        // Step 1: Create order
        Order order = createOrder(orderType, items);

        // Calculate total with discount + correct tax
        double total = calculateTotal(order);

        // Step 2: Notify kitchen and waiter about the new order
        notifyOrder(order);
        
        // Step 3: Process payment
        PaymentLogic paymentLogic = new PaymentLogic(paymentStrategy);
        paymentLogic.pay(order, total);
    }
}
