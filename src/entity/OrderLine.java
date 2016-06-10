package entity;

public class OrderLine {
	private Media product;
	private int quantity;

	public OrderLine(Media product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public double getPrice() {
		return product.getPrice() * quantity;
	}

	public Media getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}	

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product: " + product.getTitle() + ", Quantity: " + quantity + ", Price: " + product.getPrice();
	}

}
