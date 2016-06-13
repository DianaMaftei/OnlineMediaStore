package Service;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.Client;

/**
 *
 * @author Diana Maftei
 */
public class Register {
	private Scanner userInput = new Scanner(System.in);
	private String fullName;
	private String userID;
	private String password;
	private Pattern pr;
	private Matcher m;

	public Client registerClient() {
		getRegisterInfo();
		Client newClient = new Client(fullName, userID, password);
		OnlineStoreMain.getClients().add(newClient);
		return newClient;
	}

	private void getRegisterInfo() {
		System.out.println("Type your full name. Only letters, periods, hyphens, commas and apostrophes are allowed.");
		do {
			fullName = userInput.nextLine();
		} while (!isNameValid(fullName));
		System.out.println("Choose a one-word userID. Only letters, numbers and underscores are allowed.");
		do {
			userID = userInput.next();
		} while (doesUserExist(userID) || !isUserIDValid(userID));
		System.out.println("Choose a password. It must include at least one uppercase letter, a lowercase letter, a number and it must be between 6 and 10 characters long.");
		do {
			password = userInput.next();
		} while (!isPasswordValid(password));
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

	private boolean isNameValid(String name) {
		String pattern = "[A-z ,.'-]+";
		// Create a Pattern object
		pr = Pattern.compile(pattern);
		m = pr.matcher(name);
		if (m.matches()) {
			return true;
		} else {
			System.out.println("Please type a valid name.");
			return false;
		}
	}

	private boolean isUserIDValid(String userID) {
		String pattern = "\\w+";
		pr = Pattern.compile(pattern);
		m = pr.matcher(userID);
		if (m.matches()) {
			return true;
		} else {
			System.out.println("Please type a valid userID.");
			return false;
		}
	}
	
	private boolean isPasswordValid(String password){
		String pattern = "^(?=\\w*\\d)(?=\\w*[a-z])(?=\\w*[A-Z])\\w{6,10}$";
		pr = Pattern.compile(pattern);
		m = pr.matcher(password);
		if (m.matches()) {
			return true;
		} else {
			System.out.println("Please type a valid password.");
			return false;
		}
	}
}
