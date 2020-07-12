package caffeinateme.steps;

import caffeinateme.entities.Order;
import caffeinateme.entities.OrderReceipt;
import caffeinateme.entities.ProductCatalog;
import caffeinateme.entities.Receipt;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CoffeeOrdersClient {

    List<Order> orders = new ArrayList<>();

    @Steps(shared = true)
    ProductCatalog productCatalog;

    @Step("Place order for customer {0} for {1} x {2}")
    public OrderReceipt placeOrders(long customerId, int quantity, String product) {
        Order order = new Order(customerId, quantity, product);
        orders.add(order);
        return order.getReceipt();
    }

    @Step("Notify updated ETA for customer {0} who is {1} minutes away from the coffee shop")
    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    @Step
    public void updateCustomerEta(long customerId, int minutesAway) {
        orders.stream().filter(order -> order.getCustomerId() == customerId)
                .forEach(order -> order.updateETATo(minutesAway));
    }

    public Receipt getReceiptFor(long customerId) {
        double subtotal = orders.stream()
                .filter(order -> order.getCustomerId() == customerId)
                .mapToDouble(this::subtotalFor)
                .sum();
        double serviceFee = subtotal * 5 / 100;
        double total = subtotal + serviceFee;
        return new Receipt(roundedToDecimalPlaces(subtotal),
                           roundedToDecimalPlaces(serviceFee),
                           roundedToDecimalPlaces(total));
    }

    private double roundedToDecimalPlaces(double value) {
        return new BigDecimal(Double.toString(value)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private double subtotalFor(Order order) {
        return productCatalog.priceOf(order.getProduct()) * order.getQuantity();
    }
}
