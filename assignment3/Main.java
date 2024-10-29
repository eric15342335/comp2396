import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String inputLine = "";

		VendingMachine v = new VendingMachine();

		// Adds products to the vending machine
		v.addProduct(new Product("Cocacola", 4, 1)); // Price: $4, Quantity: 1
		v.addProduct(new Product("Pepsi", 5, 3)); // Price: $5, Quantity: 3

		System.out.println("Welcome to COMP2396B Assignment 3 - Vending Machine");

		// Reads user inputs continuously
		while (true) {
			inputLine = input.readLine();

			// Split the input line
			String[] cmdParts = inputLine.split(" ");

			Command cmdObj = null;

			if (cmdParts[0].equalsIgnoreCase("Exit")) {
				break;
			} else if (cmdParts[0].equalsIgnoreCase("Check")) {
				cmdObj = new CmdCheckProductInfo();
			} else if (cmdParts[0].equalsIgnoreCase("Insert")) {
				cmdObj = new CmdInsertCoin();
			} else if (cmdParts[0].equalsIgnoreCase("Reject")) {
				cmdObj = new CmdRejectCoins();
			} else if (cmdParts[0].equalsIgnoreCase("Buy")) {
				cmdObj = new CmdPurchase();
			} else {
				System.out.println("Unknown user command.");
			}

			if (cmdObj != null) {
				System.out.println(cmdObj.execute(v, cmdParts));
			}

			inputLine = "";
		}

		System.out.println("Bye");
	}
}
