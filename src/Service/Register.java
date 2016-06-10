package Service;

import java.util.Scanner;

import entity.Client;
/**
*
*@author Diana Maftei
*/
public class Register {
	private Scanner userInput = new Scanner(System.in);
	private String fullName;
	private String userID;
	private String password;
	
	public Client registerClient(){
		getRegisterInfo();
		Client newClient = new Client(fullName, userID, password);
		OnlineStoreMain.getClients().add(newClient);
		return newClient;
	}

	private void getRegisterInfo() {
		System.out.println("Type your full name, your userID and the password for your new account.");
		fullName = userInput.nextLine();
		do {
			userID = userInput.next();
		} while (doesUserExist(userID));
		password = userInput.next();
	}

	private boolean doesUserExist(String userID) {
		for (Client user : OnlineStoreMain.getClients()) {
			if (userID.equalsIgnoreCase(user.getUserID())) {
				System.out.println("This userID already exists. Please choose another.");
				return true;
			}
		}
		return false;
	}

}
