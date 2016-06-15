package domain.entities;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class Order {
	private String clientName;
	private Date issueDate;
	private ArrayList<OrderLine> orderLines;

	public ArrayList<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(ArrayList<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public Order(String clientName) {
		this.clientName = clientName;
		this.issueDate = new Date();
		this.orderLines = new ArrayList<OrderLine>();
	}

	public String getClientName() {
		return clientName;
	}

	public double getTotalCost() {
		double totalCost = 0;
		for (OrderLine line : orderLines) {
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
