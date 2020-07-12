package caffeinateme.entities;

import caffeinateme.enums.Urgency;

import java.util.Objects;

public class Order {
    private final long customerId;
    private final int quantity;
    private final String product;
    private int etaInMinutes;

    public Order(long customerId, int quantity, String product) {

        this.customerId = customerId;
        this.quantity = quantity;
        this.product = product;
    }

    public long getCustomerId() {
        return customerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProduct() {
        return product;
    }

    public static Order matching(OrderReceipt orderReceipt) {
        return new Order(orderReceipt.getCustomerId(), orderReceipt.getQuantity(), orderReceipt.getProduct());
    }

    public OrderReceipt getReceipt() {
        return new OrderReceipt(customerId, quantity, product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return customerId == order.customerId &&
                quantity == order.quantity &&
                Objects.equals(product, order.product);
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerId=" + customerId +
                ", quantity=" + quantity +
                ", product='" + product + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, quantity, product);
    }

    public void updateETATo(int etaInMinutes) {
        this.etaInMinutes = etaInMinutes;
    }

    public Urgency getUrgency() {
        if (etaInMinutes < 5) return Urgency.Urgent;
        if (etaInMinutes <= 10) return Urgency.High;
        return Urgency.Normal;
    }
}