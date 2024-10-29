package tutorial4.part2;

/**
 * Television class, which is a subclass of the ElectronicsProduct class. It should have an
 * extra attribute that records it’s warranty period (in months).
 */
public class Television extends ElectronicsProduct {
    private int warrantyPeriod;

    public Television(String productId, String name, double price, int warrantyPeriod) {
        super(productId, name, price);
        this.warrantyPeriod = warrantyPeriod;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void extendWarranty(int months) {
        warrantyPeriod += months;
    }
}
