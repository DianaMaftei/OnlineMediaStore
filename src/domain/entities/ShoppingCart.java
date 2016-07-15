package domain.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class ShoppingCart {
	private String customerID;
	private Date issueDate;
	private ArrayList<CartItem> itemsOrdered;

	public ArrayList<CartItem> getItemsOrdered() {
		return itemsOrdered;
	}

	public void setCustomerOrders(ArrayList<CartItem> orderLines) {
		this.itemsOrdered = orderLines;
	}

	public ShoppingCart(String customerID) {
		this.customerID = customerID;
		this.issueDate = new Date();
		this.itemsOrdered = new ArrayList<CartItem>();
	}

	public String getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	public Date getIssueDate() {
		return issueDate;
	}
	
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public BigDecimal getTotalCost() {
		BigDecimal totalCost = new BigDecimal(0.0);
		for (CartItem cartItem : itemsOrdered) {
			totalCost = totalCost.add(cartItem.getPrice(cartItem.getTypeOfTransaction()));
		}
		totalCost= totalCost.setScale(2, RoundingMode.HALF_UP);
		return totalCost;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for(int i = 1; i <= itemsOrdered.size(); i++){
			sb.append(i + ". " + itemsOrdered.get(i-1) + "\n");
		}
		return "\nIssue Date: " + issueDate + "\nProducts:\n" + sb;
	}

}
