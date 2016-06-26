package domain.entities;

import java.util.ArrayList;
/**
*
*@author diana.maftei[at]gmail.com
*/
public class Customer {
	private String name;
	private String userID;
	private String password;
	private ArrayList<ShoppingCart> clientOrders;
	
	public Customer(String name, String userID, String password) {
		this.name = name;
		this.userID = userID;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getUserID() {
		return userID;
	}
	
	public ArrayList<ShoppingCart> getClientOrders() {
		return clientOrders;
	}

	@Override
	public String toString() {
		return "Name: " + name + ", Client ID: " + userID + ", Password: " + password + "]";
	}
	
	
	
	
}
