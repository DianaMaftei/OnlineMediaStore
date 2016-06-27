package domain.service;

import java.util.ArrayList;

import DAO.ProductDAO;
import domain.entities.Product;
import domain.entities.ShoppingCart;
import userInterface.CustomerMenu;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class StoreService {
	public static int currentProductList;
	public ProductDAO productDAO;
	public CustomerMenu customerMenu = new CustomerMenu();

	public static ShoppingCartService shoppingCartService = new ShoppingCartService();

	// TODO be able to return from login in case user changes their mind

	private boolean selectLoginOption(int userOption) {
		switch (userOption) {
		case 1:
			new Login().doLogin();
			if(Login.getCurrentUser() == null){
				return false;
			}
			OnlineStoreMain.currentOrder = new ShoppingCart(Login.getCurrentUser().getName());
			break;
		case 2:
			// register a new user and log them in automatically
			new Login().loginRegisteredUser(new Register().registerCustomer());
			if(Login.getCurrentUser() == null){
				return false;
			}
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
			customerMenu.displayLoginMenu();
			int currentUserOption = customerMenu.getUserOption();
			loggedIn = selectLoginOption(currentUserOption);
		} while (!loggedIn);
	}

	public void doStoreService() {
		customerMenu.showProductsMenu();
		int commandOption = customerMenu.getUserOption();
		doUserCommand(commandOption);
	}

	// TODO eliminate the switch - find a better solution - OOP
	private void doUserCommand(int commandOption) {
		currentProductList = commandOption;
		switch (commandOption) {
		case 1:
			productLibraryMenu(OnlineStoreMain.dvds);
			break;
		case 2:
			productLibraryMenu(OnlineStoreMain.cds);
			break;
		case 3:
			productLibraryMenu(OnlineStoreMain.books);
			break;
		case 4:
			if (OnlineStoreMain.currentOrder.getCustomerOrders().size() == 0) {
				System.out.println("You haven't purchased anything yet.");
				return;
			}
			int option = customerMenu.getItemToRemoveFromCart();
			if (option == 0) {
				break;
			} else {
				shoppingCartService.removeItemFromCart(option, customerMenu.getQuantityOfItemsToRemoveFromCart());
			}
			break;
		case 5:
			checkoutOptions();
			break;
		default:
			System.out.println("This is not a valid option.");
			break;
		}
	}

	private void productLibraryMenu(ArrayList<? extends Product> productList) {
		int option;
		productDAO.listItemsInStock(productList);
		do {
			System.out.println(
					"\t1. Sort by title. \n\t2. Sort by price. \n\t3. Purchase product. \n\t4. Rent product. \n\t5. Return to products menu.");
			option = customerMenu.getUserOption();
			switch (option) {
			case 1:
				productDAO.listItemsInStock(productDAO.sortByTitle(productList));
				break;
			case 2:
				productDAO.listItemsInStock(productDAO.sortByPrice(productList));
				break;
			case 3:
				purchaseItem(productList, "purchase");
				break;
			case 4:
				purchaseItem(productList, "rent");
				break;
			case 5:
				break;
			default:
				System.out.println("Invalid option.");
				break;
			}
		} while (option != 5);
	}
	
	private void checkoutOptions() {
		if (Login.getCurrentUser() == null) {
			System.out.println("You must login or register with a new account in order to complete your purchase. "
					+ "\nDo you want to: \n\t1. Login \n\t2. Register \n\t3. Continue browsing?");
			int choice = customerMenu.getUserOption();
			if (1 ==choice) {
				new Login().doLogin();
			} else if (2 == choice) {
				new Login().loginRegisteredUser(new Register().registerCustomer());
				System.out.println("Welcome " + Login.getCurrentUser().getName() + "!");
				ShoppingCart tempOrder = new ShoppingCart(Login.getCurrentUser().getUserID());
				tempOrder.setCustomerOrders(OnlineStoreMain.currentOrder.getCustomerOrders());
				OnlineStoreMain.currentOrder = tempOrder;
				return;
			} else if (3 == choice) {
				return;
			} else {
				System.out.println("Invalid option.");
			}
		} else {
			shoppingCartService.checkOut();
		}
	}

	private void purchaseItem(ArrayList<? extends Product> list, String rentOrPurchase) {
		int itemToGet = customerMenu.getItemToRentOrPurchase(rentOrPurchase);
		int quantityToGet;
		if ((itemToGet < 0 || itemToGet > list.size())) {
			System.out.println("Invalid option.");
		} else {
			quantityToGet = customerMenu.getQuantityOfItemToAddToCart(rentOrPurchase);
			shoppingCartService.addItemToCart(list, itemToGet, quantityToGet, rentOrPurchase);
		}
	}

}
