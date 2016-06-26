package domain.service;

import java.util.Scanner;

import domain.entities.Customer;
/**
*
*@author diana.maftei[at]gmail.com
*/
public class Login {
	private Scanner userInput = new Scanner(System.in);
	private String userID;
	private String password;
	private static Customer currentUser;

	public static Customer getCurrentUser() {
		return currentUser;
	}

	public void doLogin() {
		boolean loggedIn;
		do {
			getUserInfo();
			if ("0".equals(userID)){
				return;
			}
			loggedIn = loginUser(userID, password);
		} while (!loggedIn);
	}
	
	private void getUserInfo() {
		System.out.println("Type your userID and password to login. \nType 0 to return to the previous menu.");
		userID = userInput.next();
		if ("0".equals(userID)){
			return;
		}
		password = userInput.next();
	}
	
	private boolean loginUser(String userID, String password) {
		for (Customer user : OnlineStoreMain.getCustomers()) {
			if (userID.equalsIgnoreCase(user.getUserID())) {
				if (password.equalsIgnoreCase(user.getPassword())) {
					System.out.println("You've successfully logged in!");
					currentUser = user;
					return true;
				}
				System.out.println("Invalid password. Please retry!");
				return false;
			}
		}
		System.out.println("The userID is invalid.");
		return false;
	}

	public void loginRegisteredUser(Customer newClient) {
		currentUser = newClient;
	}

}
