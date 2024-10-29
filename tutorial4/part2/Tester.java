package tutorial4.part2;

import java.io.*;
public class Tester {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader inData = new BufferedReader(isr);
        String c1_info[] = inData.readLine().split(" ");
        String c2_info[] = inData.readLine().split(" ");

        // Part 1: ElectronicsProduct object
        String productID1 = c1_info[0];
        String name1 = c1_info[1];
        double price1 = Double.valueOf(c1_info[2]);
        ElectronicsProduct product = new ElectronicsProduct(productID1, name1, price1);

        // Apply a discount and display the final price
        product.applyDiscount(10);
        System.out.println("Product ID: " + product.getProductId());
        System.out.println("Name: " + product.getName());
        System.out.println("Price after discount: $" + product.getPrice());
        System.out.println("--------------------");

        // Part 2: WashingMachine object
        String productID2 = c2_info[0];
        String name2 = c2_info[1];
        double price2 = Double.valueOf(c2_info[2]);
        int warrantyPeriod2 = Integer.valueOf(c2_info[3]);
        Television tv = new Television(productID2, name2, price2, warrantyPeriod2);

        // Apply a discount and display the final price
        tv.applyDiscount(15);
        System.out.println("Product ID: " + tv.getProductId());
        System.out.println("Name: " + tv.getName());
        System.out.println("Price after discount: $" + tv.getPrice());
        System.out.println("Warranty period: " + tv.getWarrantyPeriod() + " months");

        // Extend the warranty period and display the new warranty period
        tv.extendWarranty(12);
        System.out.println("Warranty period: " + tv.getWarrantyPeriod() + " months");
    }
}
