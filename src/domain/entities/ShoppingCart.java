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
	private ArrayList<CartItem> orderLines;

	public ArrayList<CartItem> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(ArrayList<CartItem> orderLines) {
		this.orderLines = orderLines;
	}

	public ShoppingCart(String clientName) {
		this.clientName = clientName;
		this.issueDate = new Date();
		this.orderLines = new ArrayList<CartItem>();
	}

	public String getClientName() {
		return clientName;
	}

	public double getTotalCost() {
		double totalCost = 0;
		for (CartItem line : orderLines) {
			totalCost += line.getPrice();
		}
		return totalCost;
	}

	@Override
	public String toString() {
		return "Client Name: " + clientName + ", Issue Date: " + issueDate + ", Client Cart: " + orderLines
				+ ", Total: " + getTotalCost();
	}

}
