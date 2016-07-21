package domain.service;

import java.util.ArrayList;

import domain.entities.Book;
import domain.entities.CD;
import domain.entities.DVD;
import domain.entities.Product;
import domain.entities.ShoppingCart;
import userInterface.CustomerMenu;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class StoreService {
	public static int currentProductList;
	public ProductService productService = new ProductService();
	public CustomerMenu customerMenu = new CustomerMenu();

	public static ShoppingCartService shoppingCartService = new ShoppingCartService();

	private boolean selectLoginOption(int userOption) {
		switch (userOption) {
		case 1:
			new Login().doLogin();
			if (Login.getCurrentCustomer() == null) {
				return false;
			}
			OnlineStoreMain.currentOrder = new ShoppingCart(
					Login.getCurrentCustomer().getCustomerID());
			break;
		case 2:
			// register a new user and log them in automatically
			new Login().loginRegisteredUser(new Register().registerCustomer());
			if (Login.getCurrentCustomer() == null) {
				return false;
			}
			OnlineStoreMain.currentOrder = new ShoppingCart(
					Login.getCurrentCustomer().getCustomerID());
			break;
		case 3:
			new Login().loginGuest();
			OnlineStoreMain.currentOrder = new ShoppingCart(
					Login.getCurrentCustomer().getCustomerID());
			System.out.println("Welcome guest!");
			return true;
		default:
			System.out.println("Invalid option. Please choose one of the above.");
			return false;
		}
		System.out.println("Welcome " + Login.getCurrentCustomer().getName() + "!");
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
			productLibraryMenu((ArrayList<DVD>) ServiceLocator.getDvdDAO().getAllProducts());
			break;
		case 2:
			productLibraryMenu((ArrayList<CD>) ServiceLocator.getCdDAO().getAllProducts());
			break;
		case 3:
			productLibraryMenu((ArrayList<Book>) ServiceLocator.getBookDAO().getAllProducts());
			break;
		case 4:
			if (OnlineStoreMain.currentOrder.getItemsOrdered().size() == 0) {
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
		productService.listItemsInStock(productList);
		do {
			System.out.println("\t1. Sort by title. \n" + "\t2. Sort by price. \n" + "\t3. Purchase product. \n"
					+ "\t4. Rent product. \n\t5. Return to products menu.");
			option = customerMenu.getUserOption();
			switch (option) {
			case 1:
				productService.listItemsInStock(productService.sortByTitle(productList));
				break;
			case 2:
				productService.listItemsInStock(productService.sortByPrice(productList));
				break;
			case 3:
				if ("guest".equals(OnlineStoreMain.currentOrder.getCustomerID())) {
					System.out.println("Login with a valid account to benefit from discounts.");
				}
				purchaseItem(productList, "purchase");
				break;
			case 4:
				purchaseItem(productList, "rental");
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
		if ("guest".equals(Login.getCurrentCustomer().getCustomerID())) {
			int choice = customerMenu.getCheckoutOption();
			if (1 == choice) {
				new Login().doLogin();
			} else if (2 == choice) {
				new Login().loginRegisteredUser(new Register().registerCustomer());
				System.out.println("Welcome " + Login.getCurrentCustomer().getName() + "!");
				ShoppingCart tempOrder = new ShoppingCart(
						Login.getCurrentCustomer().getCustomerID());
				tempOrder.setCustomerOrders(OnlineStoreMain.currentOrder.getItemsOrdered());
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

	private void purchaseItem(ArrayList<? extends Product> list, String rentalOrPurchase) {
		int itemToGet = customerMenu.getItemToRentOrPurchase();
		int quantityToGet;
		int daysRented;
		if ((itemToGet < 0 || itemToGet > list.size())) {
			System.out.println("Invalid option.");
		} else {
			if ("rental".equals(rentalOrPurchase)) {
				daysRented = customerMenu.getNumberOfDaysToRent();
				shoppingCartService.addItemToCart(list, itemToGet, daysRented, 0, rentalOrPurchase);
				return;
			}
			quantityToGet = customerMenu.getQuantityOfItemToAddToCart();
			shoppingCartService.addItemToCart(list, itemToGet, 0, quantityToGet, rentalOrPurchase);
		}
	}

}
