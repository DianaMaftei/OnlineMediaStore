package domain.entities;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class CartItem {
	private Product product;
	private int quantity;
	private String typeOfTransaction;
	private int daysRented;

	public CartItem(Product product, int quantity, String typeOfTransaction) {
		super();
		this.typeOfTransaction = typeOfTransaction;
		this.product = product;
		this.quantity = quantity;
	}

	public double getPrice(String typeOfTransaction) {
		// TODO figure out for rent vs purchase
		// - 10% pe zi din pretul de vanzare la DVD-uri.
		//- 2% pe zi din pretul de vanzare la Books.
		//- 5% pe zi din pretul de vanzare la CD-uri.
		return product.getPrice() * quantity;
	}

	public String getTypeOfTransaction() {
		return typeOfTransaction;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getDaysRented() {
		return daysRented;
	}

	public void setDaysRented(int daysRented) {
		this.daysRented = daysRented;
	}

	@Override
	public String toString() {
		if ("rent".equals(this.typeOfTransaction)) {
			return typeOfTransaction + " - " + product.getTitle() + ", Quantity: " + quantity + ", Days rented: " + daysRented + ", Price: $"
					+ product.getPrice();
		} else {
			return typeOfTransaction + " - " + product.getTitle() + ", Quantity: " + quantity + ", Price: $"
					+ product.getPrice() + " per item";
		}
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
		if (((CartItem) obj).getProduct().equals(this.getProduct())) {
			return true;
		}
		return false;
	}

}
