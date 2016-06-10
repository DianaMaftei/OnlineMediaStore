package Service;

import java.util.Scanner;

import entity.Client;
/**
*
*@author Diana Maftei
*/
public class Login {
	private Scanner userInput = new Scanner(System.in);
	private String userID;
	private String password;
	private static Client currentUser;

	public static Client getCurrentUser() {
		return currentUser;
	}

	public void doLogin() {
		boolean loggedIn;
		do {
			displayLoginMenu();
			loggedIn = loginUser(userID, password);
		} while (!loggedIn);
	}
	
	private void displayLoginMenu() {
		System.out.println("Type your userID and password to login.");
		userID = userInput.next();
		password = userInput.next();
	}
	
	private boolean loginUser(String userID, String password) {
		for (Client user : OnlineStoreMain.getClients()) {
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

	public void loginRegisteredUser(Client newClient) {
		currentUser = newClient;
	}

}
