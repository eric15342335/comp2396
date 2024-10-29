package tutorial4.part2;

/**
 * ElectronicsProduct class with attributes for product ID, name, and price.
 */
public class ElectronicsProduct {
    private final String productId;
    private final String name;
    private double price;

    public ElectronicsProduct(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public void applyDiscount(int discount) {
        price = price * (100 - discount) / 100;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
