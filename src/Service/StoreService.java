package Service;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Client;
import entity.Media;

/**
 *
 * @author Diana Maftei
 */
public class StoreService {
	public static int currentProductList;
	private static Scanner userInput = new Scanner(System.in);
	private static OrderService orderService = new OrderService();

	private int getUserOption() {
		try {
			int input = Integer.parseInt(userInput.next());
			return input;
		} catch (NumberFormatException n) {
			System.out.println("Please enter a number.");
			return -1;
		}
	}

	private void displayLoginMenu() {
		System.out.println("Welcome to our online store!");
		System.out.println("Would you like to: \n" + "\t1. Login\n" + "\t2. Register\n" + "\t3. Continue as guest");
	}

	private boolean selectLoginOption(int userOption) {
		switch (userOption) {
		case 1:
			new Login().doLogin();
			break;
		case 2:
			// register a new user and log them in automatically
			new Login().loginRegisteredUser(new Register().registerClient());
			break;
		case 3:
			// create a temp guest account and login with it
			Client guestUser = new Client("guest", "guest", "0000");
			Login.setCurrentUser(guestUser);
			break;
		default:
			System.out.println("Invalid option. Please choose one of the above.");
			return false;
		}
		System.out.println("Welcome " + Login.getCurrentUser().getName() + "!");
		return true;
	}

	public void doLogin() {
		boolean loggedIn;
		;
		do {
			displayLoginMenu();
			int currentUserOption = getUserOption();
			loggedIn = selectLoginOption(currentUserOption);
		} while (!loggedIn);
	}

	public void itemsMenu() {
		showProductsMenu();
		int commandOption = getUserOption();
		doUserCommand(commandOption);
	}

	private void showProductsMenu() {
		System.out.println(
				"1. Buy a DVD. \n" + "2. Buy a CD.\n" + "3. Buy a book.\n4. Remove item from cart. \n5. Checkout.");
	}

	private void doUserCommand(int commandOption) {
		currentProductList = commandOption;
		switch (commandOption) {
		case 1:
			listItemsInStock(OnlineStoreMain.dataFunctionProperties.dvds);
			orderService.addItem(DataFunctionProperties.dvds, getItemToPurchase());
			break;
		case 2:
			listItemsInStock(OnlineStoreMain.dataFunctionProperties.cds);
			orderService.addItem(DataFunctionProperties.cds, getItemToPurchase());
			break;
		case 3:
			listItemsInStock(OnlineStoreMain.dataFunctionProperties.books);
			orderService.addItem(DataFunctionProperties.books, getItemToPurchase());
			break;
		case 4:
			System.out.println("Which item do you want to remove from your cart?");
			orderService.showItemsInCart();
			orderService.removeItemFromCart(getUserOption());
			break;
		case 5:
			orderService.checkOut();
			break;
		default:
			System.out.println("This is not a valid option.");
			break;
		}

	}

	private int getItemToPurchase() {
		System.out.println("Which item would you like to purchase?");
		return getUserOption();
	}

	private void listItemsInStock(ArrayList<? extends Media> list) {
		int index = 1;
		for (Media item : list) {
			System.out.println(index);
			System.out.println(item);
			index++;
		}
	}

}
