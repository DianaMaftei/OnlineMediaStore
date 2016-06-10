package entity;

import java.util.ArrayList;
/**
*
*@author Diana Maftei
*/
public class Client {
	private String name;
	private String userID;
	private String password;
	private ArrayList<Order> clientOrders;
	
	public Client(String name, String userID, String password) {
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
	
	public ArrayList<Order> getClientOrders() {
		return clientOrders;
	}

	@Override
	public String toString() {
		return "Name: " + name + ", Client ID: " + userID + ", Password: " + password + "]";
	}
	
	
	
	
}
