package domain.service;

import java.util.ArrayList;

import DAO.properties.CustomerDAO;
import domain.entities.CartItem;
import domain.entities.Product;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class ShoppingCartService {
	public static boolean checkedOut;

	public void addItemToCart(ArrayList<? extends Product> productList, int itemToAdd, int daysRented,
			int quantityOfItems, String typeOfTransaction) {
		CartItem cartItem = new CartItem((productList.get(itemToAdd - 1)), quantityOfItems, typeOfTransaction);
		cartItem.setDaysRented(daysRented);
		int indexOfItemInCart = getIndexOfItemInCart(cartItem);
		if ("purchase".equals(typeOfTransaction) && indexOfItemInCart >= 0) {
			updateExistingCartItem(indexOfItemInCart, quantityOfItems);
		} else {
			addNewItem(cartItem, typeOfTransaction);
		}
	}

	private int getIndexOfItemInCart(CartItem cartItem) {
		for (int i = 0; i < OnlineStoreMain.currentOrder.getItemsOrdered().size(); i++) {
			if (OnlineStoreMain.currentOrder.getItemsOrdered().get(i).equals(cartItem)) {
				return i;
			}
		}
		return -1;
	}

	private void addNewItem(CartItem cartItem, String typeOfTransaction) {
		OnlineStoreMain.currentOrder.getItemsOrdered().add(cartItem);
		System.out.println("The item " + cartItem.getProduct().getTitle() + " has been added to your cart.");
		System.out.println("Cost for this item(s) is: " + cartItem.getPrice(typeOfTransaction));
		System.out.println("Your total cost is: " + OnlineStoreMain.currentOrder.getTotalCost());
		System.out.println();
	}

	private void updateExistingCartItem(int index, int quantityOfItems) {
		if (OnlineStoreMain.currentOrder.getItemsOrdered().get(index).getQuantity() + quantityOfItems > 100) {
			System.out.println(
					"You cannot purchase more than 100 items. The total quantity of this item has been updated accordingly.");
			System.out.println("Your total cost is: " + OnlineStoreMain.currentOrder.getTotalCost());
			OnlineStoreMain.currentOrder.getItemsOrdered().get(index).setQuantity(100);
			return;
		}
		OnlineStoreMain.currentOrder.getItemsOrdered().get(index).setQuantity(
				OnlineStoreMain.currentOrder.getItemsOrdered().get(index).getQuantity() + quantityOfItems);
		System.out.println("The total quantity of this item has been updated.");
		System.out.println("Your total cost is: " + OnlineStoreMain.currentOrder.getTotalCost());
		System.out.println();
		return;
	}

	public void removeItemFromCart(int itemToRemove, int quantityToRemove) {
		CartItem productChosen = OnlineStoreMain.currentOrder.getItemsOrdered().get(itemToRemove - 1);
		if (quantityToRemove > productChosen.getQuantity()) {
			System.out.println("Invalid quantity.");
		} else if (quantityToRemove == productChosen.getQuantity()) {
			OnlineStoreMain.currentOrder.getItemsOrdered().remove(productChosen);
			System.out.println("Removed product " + productChosen.getProduct().getTitle() + " from your cart.");
		} else {
			productChosen.setQuantity((productChosen.getQuantity() - quantityToRemove));
			System.out.println("Removed " + quantityToRemove + " product(s) " + productChosen.getProduct().getTitle()
					+ " from your cart.");
		}
		System.out.println();
	}

	public void showItemsInCart() {
		int i = 1;
		for (CartItem line : OnlineStoreMain.currentOrder.getItemsOrdered()) {
			System.out.println(i + ". " + line);
			i++;
		}
	}

	public void checkOut() {
		System.out.println(OnlineStoreMain.currentOrder);
		System.out.println("Your total is: " + OnlineStoreMain.currentOrder.getTotalCost());
		System.out.println("~~linked to secure payment option~~");
		CustomerDAO.getInstance().setCustomerHistory(OnlineStoreMain.currentOrder);
		Login.getCurrentCustomer().setLoyaltyPoints(CustomerService.getInstance().calculateLoyaltyPointsAfterNewPurchase(OnlineStoreMain.currentOrder));
		CustomerDAO.getInstance().updateCustomerLoyaltyPoints();
		System.out.println("Thank you for your purchase.");
		checkedOut = true;
	}
}
