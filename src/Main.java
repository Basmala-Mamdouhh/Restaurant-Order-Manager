import restaurant.Core.IMenuItem;
import restaurant.Core.MenuItem;
import restaurant.Core.Order;
import restaurant.MenuFactory.IMenuFactory;
import restaurant.MenuFactory.KidsMenuFactory;
import restaurant.MenuFactory.VegetarianFactory;
import restaurant.MenuFactory.NonVegetarianFactory;
import restaurant.WorkflowFacad.RestaurantFacade;
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
        IMenuFactory vegFactory = new VegetarianFactory();
        IMenuFactory nonVegFactory = new NonVegetarianFactory();
        IMenuFactory kidsFactory = new KidsMenuFactory();

        System.out.println("Welcome to the Restaurant Ordering System!\n");

        boolean moreOrders = true;
        int orderCount = 1;

        while (moreOrders) {
            System.out.println("\nOrder #" + orderCount);
            List<IMenuItem> orderItems = new ArrayList<>();

            // Choose menu type
            System.out.println("Choose menu type: 1=Vegetarian, 2=Non-Vegetarian, 3=Kids Menu");
            int menuChoice = sc.nextInt();
            sc.nextLine(); // consume newline

            IMenuFactory chosenFactory;
            switch(menuChoice) {
                case 1 -> chosenFactory = vegFactory;
                case 2 -> chosenFactory = nonVegFactory;
                case 3 -> chosenFactory = kidsFactory;
                default -> {
                    System.out.println("Invalid choice. Defaulting to Vegetarian menu.");
                    chosenFactory = vegFactory;
                }
            }
            facade.displayMenu(chosenFactory);

            boolean addingItems = true;
            while (addingItems) {
                System.out.println("Enter item name to add (or 'done' to finish):");
                String itemName = sc.nextLine().trim().toLowerCase();
                if (itemName.equals("done")) break;

                IMenuItem item = chosenFactory.createItem(itemName);
                if (item == null) {
                    System.out.println("Item not found. Try again.");
                    continue;
                }

                // Ask for add-ons
                List<String> addons = new ArrayList<>();
                System.out.println("Add extra cheese? (yes/no):");
                if (sc.nextLine().trim().equalsIgnoreCase("yes")) addons.add("cheese");

                System.out.println("Add extra sauces? (yes/no):");
                if (sc.nextLine().trim().equalsIgnoreCase("yes")) addons.add("sauces");

                System.out.println("Add extra toppings? (yes/no):");
                if (sc.nextLine().trim().equalsIgnoreCase("yes")) addons.add("toppings");

                item = facade.addExtra(item, addons);
                orderItems.add(item);
            }

            // Order type
            System.out.println("Enter order type (DINE_IN, DELIVERY, TAKEAWAY):");
            String orderType = sc.nextLine().trim().toUpperCase();

            Order order = facade.createOrder(orderType, orderItems);

            // Choose payment method
            System.out.println("Choose payment method: 1=Cash, 2=Credit, 3=Mobile Wallet");
            int payChoice = sc.nextInt();
            sc.nextLine(); // consume newline
            PaymentStrategy payment;
            switch (payChoice) {
                case 2 -> {
                    System.out.println("Enter credit card number:");
                    String card = sc.nextLine();
                    payment = new CreditPayment(card);
                }
                case 3 -> {
                    System.out.println("Enter wallet number:");
                    String wallet = sc.nextLine();
                    payment = new MobileWalletPayment(wallet);
                }
                default -> payment = new CashPayment();
            }

            // Pay
            facade.payOrder(order, payment);

            System.out.println("\nDo you want to place another order? (yes/no):");
            moreOrders = sc.nextLine().trim().equalsIgnoreCase("yes");
            orderCount++;
        }

        System.out.println("Thank you for visiting!");
        sc.close();
    }
}
