import java.util.ArrayList;

public class CmdPurchase implements Command {
    @Override
    public String execute(VendingMachine v, String[] cmdParts) {
        String productName = cmdParts[1];
        Product product = v.getProduct(productName);
        if (product == null) {
            //not required for the assignment
            return "Product not found!";
        }
        if (product.getQuantity() <= 0) {
            return productName + " is out of stock!";
        }
        // not enough credit to buy
        int price = product.getPrice();
        int total = v.getTotalInsertedCoinsAmount();
        if (total < price) {
            //Not enough credit to buy Pepsi! Inserted $2 but needs $5
            return "Not enough credit to buy " + productName + "! Inserted $" + total + " but needs $" + price + ".";
        }
        v.deductProductQuantity(product);
        // charge the user and dump all coins remaining
        // using $10, $5, $2, $1 in order
        if (product.getPrice() == v.getTotalInsertedCoinsAmount()) {
            // sample output:
            //Dropped Pepsi. Paid $5. No change.
            v.dumpAllCoins();
            return "Dropped " + productName + ". Paid $" + total + ". No change.";
        }
        //Dropped Pepsi. Paid $16. Your change: $1, $10.
        int change = total - price;
        // assume infinite amount of coins from coin changer
        ArrayList<ArrayList<Integer>> coins_list = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 4; i++) {
            coins_list.add(new ArrayList<Integer>());
        }
        while (change > 0) {
            if (change >= 10) {
                coins_list.get(3).add(10);
                change -= 10;
            } else if (change >= 5) {
                coins_list.get(2).add(5);
                change -= 5;
            } else if (change >= 2) {
                coins_list.get(1).add(2);
                change -= 2;
            } else {
                coins_list.get(0).add(1);
                change -= 1;
            }
        }   
        String return_text = "Dropped " + productName + ". Paid $" + total + ". Your change: ";
        for (int i = 0; i < 4; i++) {
            for (Integer c : coins_list.get(i)) {
                return_text += "$" + c + ", ";
            }
        }
        // remove the last ", " and add "."
        return_text = return_text.substring(0, return_text.length() - 2) + ".";
        v.dumpAllCoins();
        return return_text;
    }
}
