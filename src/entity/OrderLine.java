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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if(((OrderLine)obj).getProduct().equals(this.getProduct())){
			return true;
		}
		return false;
	}


	
}
