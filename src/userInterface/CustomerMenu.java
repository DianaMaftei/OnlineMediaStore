package userInterface;

import java.util.Scanner;

import domain.service.OnlineStoreMain;
import domain.service.StoreService;

/**
*
*@author diana.maftei[at]gmail.com
*/
public class CustomerMenu {

	public int getUserOption() {
		Scanner userInput = new Scanner(System.in);
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
		System.out.println("Invalid number.");
		return -1;
	}

	public void displayLoginMenu() {
		System.out.println("Welcome to our online store!");
		System.out.println("Would you like to: \n" + "\t1. Login\n" + "\t2. Register\n" + "\t3. Continue as guest");
	}

	public void showProductsMenu() {
		System.out.println("\t1. View DVD library. \n\t" + "2. View CD library.\n"
				+ "\t3. View book library.\n\t4. Remove item from cart. \n\t5. Checkout.");
	}

	public int getItemToRemoveFromCart() {
		System.out.println("Which item do you want to remove from your cart?");
		StoreService.shoppingCartService.showItemsInCart();
		System.out.println();
		int option;
		do {
			System.out.println("Press 0 if you've changed your mind.");
			option = getUserOption();
			if (option < 0 || option > OnlineStoreMain.currentOrder.getOrderLines().size()) {
				System.out.println("Invalid option.");
			}
		} while (option < 0 || option > OnlineStoreMain.currentOrder.getOrderLines().size());
		return option;
	}

	public int getQuantityOfItemToAddToCart(String rentOrPurchase) {
		System.out.printf("How many items do you wish to %s ? The maximum is 100 items.", rentOrPurchase);
		System.out.println();
		int quantity;
		do {
			quantity = getUserOption();
			if (quantity > 100 || quantity <= 0) {
				System.out.println("Please type a valid number.");
			}
		} while (quantity > 100 || quantity <= 0);
		return quantity;
	}

	public int getQuantityOfItemsToRemoveFromCart() {
		System.out.println("How many items do you wish to remove?");
		return getUserOption();
	}

	public int getItemToRentOrPurchase(String rentOrPurchase) {
		System.out.printf("Which item would you like to %s?", rentOrPurchase);
		System.out.println();
		return getUserOption();
	}

}
