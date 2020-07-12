package caffeinateme;

import java.util.ArrayList;
import java.util.List;

public class CoffeeOrdersClient {

    List<Order> orders = new ArrayList<>();

    public OrderReceipt placeOrders(long customerId, int quantity, String product) {
        Order order = new Order(customerId, quantity, product);
        orders.add(order);
        return order.getReceipt();
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }
}
