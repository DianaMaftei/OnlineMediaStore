package entity;

import java.util.ArrayList;

public class Order {
	private String clientName;
	private String issueDate;
	private ArrayList<Media> clientCart;
	
	
	public Order(String clientName) {
		this.clientName = clientName;
		this.issueDate = "now";
		this.clientCart = new ArrayList<>();
	}


	public String getClientName() {
		return clientName;
	}


	public void setClientName(String clientName) {
		this.clientName = clientName;
	}


	public String getIssueDate() {
		return issueDate;
	}


	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}


	public ArrayList<Media> getClientCart() {
		return clientCart;
	}


	public void setClientCart(ArrayList<Media> clientBasket) {
		this.clientCart = clientBasket;
	}
	
	
	
	
}
