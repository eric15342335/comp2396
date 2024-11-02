
import java.util.ArrayList;

public class VendingMachine {
	// ArrayList of Integers represents inserted coins in Coin Slot
	private ArrayList<Integer> insertedCoins;
	
	// ArrayList of Product represents inventories of products
	private ArrayList<Product> products;

	public VendingMachine() {
		insertedCoins = new ArrayList<Integer>();
		products = new ArrayList<Product>();
	}

	public void addProduct(Product p) {
		products.add(p);
	}
	
	public void insertCoin(Integer c) {
		insertedCoins.add(c);
	}
	
	/* You may add other properties and methods */

	// utility methods
	public void removeCoin(Integer c) {
		insertedCoins.remove(c);
	}

	public void dumpAllCoins() {
		insertedCoins.clear();
	}

	public void deductProductQuantity(Product p) {
		p.setQuantity(p.getQuantity() - 1);
	}

	// getters
	public ArrayList<Integer> getInsertedCoins() {
		return insertedCoins;
	}

	public ArrayList<ArrayList<Integer>> getGroupedInsertedCoins() {
		ArrayList<ArrayList<Integer>> coins_list = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 4; i++) {
			coins_list.add(new ArrayList<Integer>());
		}
		for (Integer c : insertedCoins) {
			if (c == 1) {
				coins_list.get(0).add(c);
			} else if (c == 2) {
				coins_list.get(1).add(c);
			} else if (c == 5) {
				coins_list.get(2).add(c);
			} else if (c == 10) {
				coins_list.get(3).add(c);
			}
		}
		return coins_list;
	}

	public int getTotalInsertedCoinsAmount() {
		int total = 0;
		for (Integer c : insertedCoins) {
			total += c;
		}
		return total;
	}

	public Product getProduct(String name) {
		for (Product p : products) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}
}
