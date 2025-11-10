import restaurant.domain.MenuItem;
import restaurant.domain.Order;
import restaurant.factory.IMenuFactory;
import restaurant.factory.NonVegMenuFactory;

void main() {
    IMenuFactory nonVeg = new NonVegMenuFactory();
    IO.println("Non-Veg Menu:");
    nonVeg.createMenu().forEach(System.out::println);

    MenuItem item = nonVeg.createItem("pizza");
    Order order = new Order("DINE_IN");
    order.addItem(item);
    IO.println("Order ID: " + order.getOrderID());
    order.getItems().forEach(i -> IO.println(" - " + i));
    IO.println("Subtotal: " + order.getSubTotal());
}