package Service;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Media;

public class OrderService {
	public static boolean checkedOut;
	private static Scanner userInput = new Scanner(System.in);

	public void doOrderService() {
		displayOrderMenu();
		doUserOption(getUserOption());
	}

	private int getUserOption() {
		int userOption = userInput.nextInt();
		return userOption;
	}

	private void displayOrderMenu() {
		System.out.println(
				"1. Add DVD to cart \n2. Add CD to cart \n3. Add book to cart \n4. Remove item from cart \n5. Checkout");
	}

	private void doUserOption(int userOption) {
		switch (userOption) {
		case 1:
		case 2:
		case 3:
			addItemToCart(userOption);
			break;
		case 4:
			removeItemFromCart();
			break;
		case 5:
			checkOut();
			break;
		default:
			System.err.println("Invalid option.");
			break;
		}
	}

	private void addItemToCart(int productType) {
		System.out.println("Which item would you like to purchase?");
		int productNumber = getUserOption();
		switch (productType) {
		case 1:
			addItem(DataFunctionProperties.dvds, productNumber);
			break;
		case 2:
			addItem(DataFunctionProperties.cds, productNumber);
			break;
		case 3:
			addItem(DataFunctionProperties.books, productNumber);
			break;
		default:
			System.err.println("Invalid option.");
		}
	}

	private void addItem(ArrayList<?> mediaList, int productNumber) {
		OnlineStoreMain.currentOrder.getClientCart().add((Media) mediaList.get(productNumber - 1));
		System.out.println(
				"The item " + ((Media) mediaList.get(productNumber - 1)).getTitle() + " has been added to your cart.");
		OnlineStoreMain.currentOrder.setTotalCost(
				OnlineStoreMain.currentOrder.getTotalCost() + ((Media) mediaList.get(productNumber - 1)).getPrice());
		System.out.println("Your total cost is: " + OnlineStoreMain.currentOrder.getTotalCost());
	}

	private void removeItemFromCart() {
		System.out.println("Which item do you want to remove from your cart?");
		OnlineStoreMain.currentOrder.getClientCart().remove(getUserOption());
	}

	private void checkOut() {
		//TODO check if user is loggedIn with an actual account or if still using guest acc
		System.out.println("Total: " + OnlineStoreMain.currentOrder.getTotalCost());
		System.out.println("~~linked to secure payment option~~");
		System.out.println("Thank you for your purchase.");
		checkedOut = true;
	}
}
