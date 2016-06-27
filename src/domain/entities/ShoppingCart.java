package domain.entities;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class ShoppingCart {
	private String clientName;
	private Date issueDate;
	private ArrayList<CartItem> customerOrders;

	public ArrayList<CartItem> getCustomerOrders() {
		return customerOrders;
	}

	public void setCustomerOrders(ArrayList<CartItem> orderLines) {
		this.customerOrders = orderLines;
	}

	public ShoppingCart(String clientName) {
		this.clientName = clientName;
		this.issueDate = new Date();
		this.customerOrders = new ArrayList<CartItem>();
	}

	public String getClientName() {
		return clientName;
	}

	public double getTotalCost() {
		double totalCost = 0;
		for (CartItem cartItem : customerOrders) {
			totalCost += cartItem.getPrice(cartItem.getTypeOfTransaction());
		}
		return totalCost;
	}

	@Override
	public String toString() {
		return "Client Name: " + clientName + ", Issue Date: " + issueDate + ", Client Cart: " + customerOrders
				+ ", Total: " + getTotalCost();
	}

}
