package entity;

import java.util.ArrayList;
/**
*
*@author Diana Maftei
*/
public class Client {
	protected String name;
	protected String userID;
	protected String password;
	protected ArrayList<Order> clientOrders;
	
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

	@Override
	public String toString() {
		return "Name: " + name + ", Client ID: " + userID + ", Password: " + password + "]";
	}
	
	
	
	
}
