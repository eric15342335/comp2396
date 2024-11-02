public class CmdCheckProductInfo implements Command {
    @Override
    public String execute(VendingMachine v, String[] cmdParts) {
        // sample output:
        //Pepsi: Price = 5, Quantity = 3.
        String productName = cmdParts[1];
        Product product = v.getProduct(productName);
        if (product != null) {
            return product.getName() + ": Price = " + product.getPrice() + ", Quantity = " + product.getQuantity() + ".";
        } else {
            //not required for the assignment
            return "Product not found!";
        }
    }
}