package caffeinateme.entities;

public class ProductPrice {
    private final String product;
    private final double price;

    public String getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ProductPrices{" +
                "product='" + product + '\'' +
                ", price=" + price +
                '}';
    }

    public ProductPrice(String product, double price) {
        this.product = product;
        this.price = price;
    }

}
