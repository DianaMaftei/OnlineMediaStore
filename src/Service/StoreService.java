package Service;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Client;
import entity.Media;
/**
*
*@author Diana Maftei
*/
public class StoreService {
	public static int currentProductList;
	private static Scanner userInput = new Scanner(System.in);

	private int getUserOption() {
		int userOption = userInput.nextInt();
		return userOption;
	}

	private void displayLoginMenu() {
		System.out.println("Welcome to our online store!");
		System.out
				.println("Would you like to: \n" + "\t 1. Login\n" + "\t 2. Register\n" + "\t 3. Continue as guest\n");
	}

	private void selectLoginOption(int userOption) {
		switch (userOption) {
		case 1:
			new Login().doLogin();
			break;
		case 2:
			// register a new user and log them in automatically
			new Login().loginRegisteredUser(new Register().registerClient());
			break;
		case 3:
			//create a temp guest account and login with it
			Client guestUser = new Client("guest", "guest", "0000");
			Login.setCurrentUser(guestUser);
			break;
		default:
			System.err.println("Invalid option.");
			return;
		}
		System.out.println("Welcome " + Login.getCurrentUser().getName() + "!");
	}

	public void doLogin() {
		displayLoginMenu();
		int currentUserOption = getUserOption();
		selectLoginOption(currentUserOption);
	}

	public void displayItemsMenu() {
		showProductsMenu();
		int listToDisplay = getUserOption();
		displayItems(listToDisplay);
	}

	private void showProductsMenu() {
		System.out.println("To see our collection of DVDs, press 1.\n" + "To see our collection of CDs, press 2.\n"
				+ "To see our collection of books, press 3.");
	}

	public void displayItems(int userOption) {
		currentProductList = userOption;
		switch (userOption) {
		case 1:
			listItemsInStock(OnlineStoreMain.dataFunctionProperties.dvds);
			break;
		case 2:
			listItemsInStock(OnlineStoreMain.dataFunctionProperties.cds);
			break;
		case 3:
			listItemsInStock(OnlineStoreMain.dataFunctionProperties.books);
			break;
		default:
			System.err.println("This is not a valid option.");
			break;
		}
	}
	
	private void listItemsInStock(ArrayList<? extends Media> list){
		int index = 1;
		for(Media item : list){
			System.out.println(index);
			System.out.println(item);
			index++;
		}
	}

}
