package entity;

import java.util.ArrayList;
import java.util.Date;
/**
*
*@author Diana Maftei
*/
public class Order {
	private String clientName;
	private Date issueDate;
	private ArrayList<Media> clientCart;
	private double totalCost;
	
	
	public Order(String clientName) {
		this.clientName = clientName;
		this.issueDate = new Date();
		this.clientCart = new ArrayList<>();
		this.totalCost = 0.0;
	}


	public String getClientName() {
		return clientName;
	}


	public void setClientName(String clientName) {
		this.clientName = clientName;
	}


	public Date getIssueDate() {
		return issueDate;
	}


	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}


	public ArrayList<Media> getClientCart() {
		return clientCart;
	}


	public void setClientCart(ArrayList<Media> clientBasket) {
		this.clientCart = clientBasket;
	}

	public double getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}


	@Override
	public String toString() {
		return "Client Name: " + clientName + ", Issue Date: " + issueDate + ", Client Cart: " + clientCart;
	}
	
}
