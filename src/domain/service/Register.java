package domain.service;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DAO.properties.PropertiesCustomerDAO;
import domain.entities.Customer;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class Register {
	private Scanner userInput = new Scanner(System.in);
	private String fullName;
	private String userID;
	private String password;
	private Pattern pr;
	private Matcher m;

	public Customer registerCustomer() {
		getRegisterInfo();
		if ("0".equals(fullName)) {
			return null;
		}
		Customer newCustomer = new Customer(fullName, userID, password);
		OnlineStoreMain.getCustomers().add(newCustomer);
		PropertiesCustomerDAO.getInstance().updateDatabaseWithNewUser(fullName, userID, password);
		return newCustomer;
	}

	private void getRegisterInfo() {
		System.out.println(
				"Type your full name. Only letters, periods, hyphens, commas and apostrophes are allowed. \nType 0 to return to the previous menu.");
		do {
			fullName = userInput.nextLine();
			if ("0".equals(fullName)) {
				return;
			}
		} while (!isUserNameValid(fullName));
		System.out.println("Choose a one-word userID. Only letters, numbers and underscores are allowed.");
		do {
			userID = userInput.next();
		} while (doesUserExist(userID) || !isUserIDValid(userID));
		System.out.println(
				"Choose a password. It must include at least one uppercase letter, a lowercase letter, a number and it must be between 6 and 10 characters long.");
		do {
			password = userInput.next();
		} while (!isPasswordValid(password));
	}

	private boolean doesUserExist(String userID) {
		for (Customer customer : OnlineStoreMain.getCustomers()) {
			if (userID.equalsIgnoreCase(customer.getCustomerID())) {
				System.out.println("This userID already exists. Please choose another.");
				return true;
			}
		}
		return false;
	}

	private boolean isUserNameValid(String name) {
		// with RegEx
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
		// with RegEx
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

	private boolean isPasswordValid(String password) {
		// with RegEx
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
