public class Product {

	private String name;
	private int price;
	private int quantity;

	public Product(String name, int price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	/* You may add other properties and methods */

	// getters
	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	// setters
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	} 
}
