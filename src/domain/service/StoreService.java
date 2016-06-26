package domain.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import domain.entities.ShoppingCart;
import domain.entities.Product;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class StoreService {
	public static int currentProductList;
	private static Scanner userInput = new Scanner(System.in);
	private static OrderService orderService = new OrderService();

	// TODO - keep logic, but move user interaction to ClientMenu - all display
	// and all getInputs

	private int getUserOption() {
		String userOption = userInput.next();
		if (userOption != null && userOption.length() > 0) {
			String digits = "";
			for (Character c : userOption.toCharArray()) {
				if (Character.isDigit(c)) {
					digits += c;
				}
			}
			if (digits != null && digits.length() > 0 && (digits.length() == userOption.length())) {
				return Integer.parseInt(digits);
			}
		}
		System.out.println("Please enter a number.");
		return -1;
	}

	private void displayLoginMenu() {
		System.out.println("Welcome to our online store!");
		System.out.println("Would you like to: \n" + "\t1. Login\n" + "\t2. Register\n" + "\t3. Continue as guest");
	}

	// TODO be able to return from login in case user changes their mind

	private boolean selectLoginOption(int userOption) {
		switch (userOption) {
		case 1:
			new Login().doLogin();
			OnlineStoreMain.currentOrder = new ShoppingCart(Login.getCurrentUser().getName());
			break;
		case 2:
			// register a new user and log them in automatically
			new Login().loginRegisteredUser(new Register().registerClient());
			OnlineStoreMain.currentOrder = new ShoppingCart(Login.getCurrentUser().getName());
			break;
		case 3:
			OnlineStoreMain.currentOrder = new ShoppingCart("guest");
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
		do {
			displayLoginMenu();
			int currentUserOption = getUserOption();
			loggedIn = selectLoginOption(currentUserOption);
		} while (!loggedIn);
	}

	public void doStoreService() {
		showProductsMenu();
		int commandOption = getUserOption();
		doUserCommand(commandOption);
	}

	private void showProductsMenu() {
		System.out.println(
				"1. Buy a DVD. \n" + "2. Buy a CD.\n" + "3. Buy a book.\n4. Remove item from cart. \n5. Checkout.");
	}

	private void sortItems(ArrayList<Product> list) {
		System.out.println("Do you want to sort by title or by price?" + "\n \t 1. By title" + "\n \t By price");
		int sortOption = getUserOption();
		if (sortOption == 1) {
			sortByTitle(list);
		} else if (sortOption == 2) {
			sortByPrice(list);
		}

	}

	private void sortByTitle(ArrayList<Product> list) {
		Collections.sort(list, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return o1.getTitle().compareTo(o2.getTitle());
			}

		});
	}

	private void sortByPrice(ArrayList<Product> list) {
		Collections.sort(list, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return (int) (o1.getPrice() - o2.getPrice());
			}

		});
	}

	// TODO eliminate the switch - find a better solution - OOP
	private void doUserCommand(int commandOption) {
		currentProductList = commandOption;
		switch (commandOption) {
		case 1:
			listItemsInStock(OnlineStoreMain.dvds);
			orderService.addItemToCart(OnlineStoreMain.dvds, getItemToRentOrPurchase("purchase"),
					getQuantity("purchase"));
			break;
		case 2:
			listItemsInStock(OnlineStoreMain.cds);
			orderService.addItemToCart(OnlineStoreMain.cds, getItemToRentOrPurchase("purchase"),
					getQuantity("purchase"));
			break;
		case 3:
			listItemsInStock(OnlineStoreMain.books);
			orderService.addItemToCart(OnlineStoreMain.books, getItemToRentOrPurchase("purchase"),
					getQuantity("purchase"));
			break;
		case 4:
			if (OnlineStoreMain.currentOrder.getOrderLines().size() == 0) {
				System.out.println("You haven't purchased anything yet.");
				return;
			}
			orderService.removeItemFromCart(getItemToRemoveFromCart(), getQuantityOfItemsToRemoveFromCart());
			break;
		case 5:
			checkoutOptions();
			break;
		default:
			System.out.println("This is not a valid option.");
			break;
		}

	}

	private void checkoutOptions() {
		if (Login.getCurrentUser() == null) {
			System.out.println("You must login or register with a new account in order to complete your purchase. "
					+ "\nDo you want to Login (L), Register (R) or Continue browsing (C)?");
			String choice = userInput.next();
			if ("L".equalsIgnoreCase(choice)) {
				new Login().doLogin();
			} else if ("R".equalsIgnoreCase(choice)) {
				new Login().loginRegisteredUser(new Register().registerClient());
				System.out.println("Welcome " + Login.getCurrentUser().getName() + "!");
				ShoppingCart tempOrder = new ShoppingCart(Login.getCurrentUser().getUserID());
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

	private int getItemToRentOrPurchase(String rentOrPurchase) {
		System.out.printf("Which item would you like to %s?", rentOrPurchase);
		System.out.println();
		return getUserOption();
	}

	private int getQuantity(String rentOrPurchase) {
		System.out.printf("How many items do you wish to %s ? The maximum is 100 items.", rentOrPurchase);
		System.out.println();
		int quantity;
		do {
			quantity = getUserOption();
			if (quantity > 100 || quantity < 0) {
				System.out.println("Please type a valid number.");
			}
		} while (quantity > 100 || quantity < 0);
		return quantity;
	}

	// TODO find a better solution - this is BAD

	private int getItemToRemoveFromCart() {
		System.out.println("Which item do you want to remove from your cart?");
		orderService.showItemsInCart();
		System.out.println();
		System.out.println("Press 0 if you've changed your mind.");
		return getUserOption();
	}

	private int getQuantityOfItemsToRemoveFromCart() {
		System.out.println("How many items do you wish to remove?");
		return getUserOption();
	}

	private void listItemsInStock(ArrayList<? extends Product> list) {
		int index = 1;
		for (Product item : list) {
			System.out.println(index);
			System.out.println(item);
			index++;
		}
	}

}
