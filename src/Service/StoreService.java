package Service;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Media;
import entity.Order;

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
			OnlineStoreMain.currentOrder = new Order(Login.getCurrentUser().getName());
			break;
		case 2:
			// register a new user and log them in automatically
			new Login().loginRegisteredUser(new Register().registerClient());
			OnlineStoreMain.currentOrder = new Order(Login.getCurrentUser().getName());
			break;
		case 3:
			OnlineStoreMain.currentOrder = new Order("guest");
			System.out.println("Welcome guest!");
			return true;
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
			listItemsInStock(DataFunctionProperties.dvds);
			orderService.addItemToCart(DataFunctionProperties.dvds, getItemToPurchase());
			break;
		case 2:
			listItemsInStock(DataFunctionProperties.cds);
			orderService.addItemToCart(DataFunctionProperties.cds, getItemToPurchase());
			break;
		case 3:
			listItemsInStock(DataFunctionProperties.books);
			orderService.addItemToCart(DataFunctionProperties.books, getItemToPurchase());
			break;
		case 4:
			if (OnlineStoreMain.currentOrder.getOrderLines().size() == 0){
				System.out.println("You haven't purchased anything yet.");
				return;
			}
			orderService.removeItemFromCart(getItemToRemoveFromCart());
			break;
		case 5:
			checkoutOptions();
			break;
		default:
			System.out.println("This is not a valid option.");
			break;
		}

	}
	
	private void checkoutOptions(){
		if (Login.getCurrentUser() == null) {
			System.out.println(
					"You must login or register with a new account in order to complete your purchase. "
					+ "\nDo you want to Login (L), Register (R) or Continue browsing (C)?");
			String choice = userInput.next();
			if ("L".equalsIgnoreCase(choice)) {
				new Login().doLogin();
			} else if ("R".equalsIgnoreCase(choice)) {
				new Login().loginRegisteredUser(new Register().registerClient());
				System.out.println("Welcome " + Login.getCurrentUser().getName() + "!");
				Order tempOrder = new Order(Login.getCurrentUser().getUserID());
				tempOrder.setOrderLines(OnlineStoreMain.currentOrder.getOrderLines());
				OnlineStoreMain.currentOrder = tempOrder;
				return;
			} else if ("C".equalsIgnoreCase(choice)) {
				return;
			} else {
				System.out.println("Invalid option.");
			}
		} else {
			orderService.checkOut();
		}
	}

	private int[] getItemToPurchase() {
		System.out.println("Which item would you like to purchase?");
		int item = getUserOption();
		System.out.println("How many items do you wish to purchase? The maximum is 100 items.");
		int quantity;
		do{
			quantity = getUserOption();
			if(quantity > 100 || quantity < 0){
				System.out.println("Please type a valid number.");
			}
		}while(quantity > 100 || quantity < 0);

		return new int [] {item, quantity};
	}
	
	private int[] getItemToRemoveFromCart(){
		System.out.println("Which item do you want to remove from your cart?");
		orderService.showItemsInCart();
		System.out.println("Press 0 if you've changed your mind.");
		int item = getUserOption();
		if(item == 0){
			return new int []{0, 0};
		}
		System.out.println("How many items do you wish to remove?");
		int quantity = getUserOption();
		return new int [] {item, quantity};
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
