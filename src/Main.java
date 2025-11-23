import restaurant.Core.IMenuItem;
import restaurant.Core.Order;
import restaurant.MenuFactory.IMenuFactory;
import restaurant.MenuFactory.KidsMenuFactory;
import restaurant.MenuFactory.VegetarianFactory;
import restaurant.MenuFactory.NonVegetarianFactory;
import restaurant.OrderNotification.KitchenNotifier;
import restaurant.OrderNotification.WaiterNotifier;
import restaurant.WorkflowFacade.RestaurantFacade;
import restaurant.strategy.payment.CashPayment;
import restaurant.strategy.payment.CreditPayment;
import restaurant.strategy.payment.MobileWalletPayment;
import restaurant.strategy.payment.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RestaurantFacade facade = new RestaurantFacade();

        // Observers
        KitchenNotifier kitchen = new KitchenNotifier();
        WaiterNotifier waiter = new WaiterNotifier();
        facade.registerObserver(kitchen);
        facade.registerObserver(waiter);

        // Menu factories
        IMenuFactory vegFactory = new VegetarianFactory();
        IMenuFactory nonVegFactory = new NonVegetarianFactory();
        IMenuFactory kidsFactory = new KidsMenuFactory();

        System.out.println("Welcome to the Restaurant Ordering System!\n");

        boolean moreOrders = true;
        int orderCount = 1;

        while (moreOrders) {

            System.out.println("\n==============");
            System.out.println("Order #" + orderCount);
            System.out.println("==============");

            List<IMenuItem> orderItems = new ArrayList<>();

            // 1. CHOOSE MENU TYPE
            IMenuFactory chosenFactory = null;
            while (chosenFactory == null) {
                System.out.println("Choose menu type:");
                System.out.println("1 = Vegetarian");
                System.out.println("2 = Non-Vegetarian");
                System.out.println("3 = Kids Menu");

                String input = sc.nextLine().trim();

                switch (input) {
                    case "1" -> chosenFactory = vegFactory;
                    case "2" -> chosenFactory = nonVegFactory;
                    case "3" -> chosenFactory = kidsFactory;
                    default -> System.out.println("Invalid choice, try again.\n");
                }
            }

            // Display menu
            facade.displayMenu(chosenFactory);


            // 2. ADD ITEMS
            while (true) {
                System.out.println("\nEnter item name to add (or 'done' to finish):");
                String itemName = sc.nextLine().trim().toLowerCase();

                if (itemName.isEmpty()) {
                    System.out.println("Please enter a valid item name.");
                    continue;
                }

                if (itemName.equals("done"))
                    break;

                IMenuItem item = chosenFactory.createItem(itemName);
                if (item == null) {
                    System.out.println("Item not found in the selected menu. Try again.");
                    continue;
                }

                // Add-ons
                List<String> addons = new ArrayList<>();

                System.out.println("Add extra cheese? (yes/no):");
                if (sc.nextLine().trim().equalsIgnoreCase("yes"))
                    addons.add("cheese");

                System.out.println("Add extra sauces? (yes/no):");
                if (sc.nextLine().trim().equalsIgnoreCase("yes"))
                    addons.add("sauces");

                System.out.println("Add extra toppings? (yes/no):");
                if (sc.nextLine().trim().equalsIgnoreCase("yes"))
                    addons.add("toppings");

                item = facade.addExtra(item, addons);
                orderItems.add(item);

                System.out.println("Item added!");
            }

            // Case: user added NO items
            if (orderItems.isEmpty()) {
                System.out.println("\n⚠ No items were added to this order.");
                System.out.println("Do you want to place another order? (yes/no):");
                moreOrders = sc.nextLine().trim().equalsIgnoreCase("yes");
                orderCount++;
                continue;
            }


            // 3. GET ORDER TYPE
            String orderType = "";
            while (true) {
                System.out.println("\nEnter order type (DINE_IN, DELIVERY, TAKEAWAY):");
                orderType = sc.nextLine().trim().toUpperCase();

                if (orderType.equals("DINE_IN") ||
                        orderType.equals("DELIVERY") ||
                        orderType.equals("TAKEAWAY"))
                    break;

                System.out.println("Invalid order type. Try again.");
            }

            // Create order
            Order order = facade.createOrder(orderType, orderItems);

            System.out.println("\nOrder created successfully! Order ID: " + order.getOrderID());


            // 4. CONFIRM OR CANCEL

            int proceedChoice = 0;
            while (true) {
                System.out.println("1 = Proceed to payment");
                System.out.println("2 = Cancel order");
                String input = sc.nextLine().trim();

                if (input.equals("1") || input.equals("2")) {
                    proceedChoice = Integer.parseInt(input);
                    break;
                }
                System.out.println("Invalid input. Try again.");
            }

            if (proceedChoice == 2) {
                facade.cancelOrder(order);
                System.out.println("\nOrder cancelled.");
                System.out.println("Do you want to place another order? (yes/no):");
                moreOrders = sc.nextLine().trim().equalsIgnoreCase("yes");
                orderCount++;
                continue;
            }


            // 5. PAYMENT STRATEGY

            PaymentStrategy payment = null;
            while (payment == null) {
                System.out.println("\nChoose payment method:");
                System.out.println("1 = Cash");
                System.out.println("2 = Credit Card");
                System.out.println("3 = Mobile Wallet");

                String input = sc.nextLine().trim();

                switch (input) {
                    case "1" -> payment = new CashPayment();
                    case "2" -> {
                        System.out.println("Enter credit card number:");
                        String card = sc.nextLine().trim();
                        payment = new CreditPayment(card);
                    }
                    case "3" -> {
                        System.out.println("Enter wallet number:");
                        String wallet = sc.nextLine().trim();
                        payment = new MobileWalletPayment(wallet);
                    }
                    default -> System.out.println("Invalid payment method, try again.");
                }
            }


            // 6. PROCESS ORDER WORKFLOW
            facade.processOrderWorkflow(orderType, orderItems, payment);

            // 7. NEW ORDER?
            System.out.println("\nDo you want to place another order? (yes/no):");
            moreOrders = sc.nextLine().trim().equalsIgnoreCase("yes");

            orderCount++;
        }

        System.out.println("\nThank you for visiting! ;)");
        sc.close();
    }
}
