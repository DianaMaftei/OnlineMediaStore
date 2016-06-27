package domain.service;

import java.util.ArrayList;

import domain.entities.CartItem;
import domain.entities.Product;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class ShoppingCartService {
	public static boolean checkedOut;

	public void addItemToCart(ArrayList<? extends Product> productList, int itemToAdd, int quantityOfItems, String typeOfTransaction) {
		CartItem cartItem = new CartItem((productList.get(itemToAdd - 1)), quantityOfItems, typeOfTransaction);
		int indexOfItemInCart = getIndexOfItemInCart(cartItem);
		if (indexOfItemInCart >= 0) {
			updateExistingCartItem(indexOfItemInCart, quantityOfItems);
		} else {
			addNewCartItem(cartItem);
		}
	}

	private int getIndexOfItemInCart(CartItem cartItem) {
		for (int i = 0; i < OnlineStoreMain.currentOrder.getCustomerOrders().size(); i++) {
			if (OnlineStoreMain.currentOrder.getCustomerOrders().get(i).equals(cartItem)) {
				return i;
			}
		}
		return -1;
	}

	private void addNewCartItem(CartItem cartItem) {
		OnlineStoreMain.currentOrder.getCustomerOrders().add(cartItem);
		System.out.println("The item " + cartItem.getProduct().getTitle() + " has been added to your cart.");
		System.out.printf("Your total cost is: %.2f. \n\n", OnlineStoreMain.currentOrder.getTotalCost());
	}

	private void updateExistingCartItem(int index, int quantityOfItems) {
		if (OnlineStoreMain.currentOrder.getCustomerOrders().get(index).getQuantity() + quantityOfItems > 100) {
			System.out.println(
					"You cannot purchase more than 100 items. The total quantity of this item has been updated accordingly.");
			System.out.printf("Your total cost is: %.2f. \n", OnlineStoreMain.currentOrder.getTotalCost());
			OnlineStoreMain.currentOrder.getCustomerOrders().get(index).setQuantity(100);
			return;
		}
		OnlineStoreMain.currentOrder.getCustomerOrders().get(index)
				.setQuantity(OnlineStoreMain.currentOrder.getCustomerOrders().get(index).getQuantity() + quantityOfItems);
		System.out.println("The total quantity of this item has been updated.");
		System.out.printf("Your total cost is: %.2f. \n", OnlineStoreMain.currentOrder.getTotalCost());
		return;
	}

	public void removeItemFromCart(int itemToRemove, int quantityToRemove) {
		CartItem productChosen = OnlineStoreMain.currentOrder.getCustomerOrders().get(itemToRemove - 1);
		if (quantityToRemove > productChosen.getQuantity()) {
			System.out.println("Invalid quantity.");}
		else if(quantityToRemove == productChosen.getQuantity()){
			OnlineStoreMain.currentOrder.getCustomerOrders().remove(productChosen);
			System.out.println("Removed product " + productChosen.getProduct().getTitle() + " from your cart.");
		} else {
			productChosen.setQuantity((productChosen.getQuantity() - quantityToRemove));
			System.out.println("Removed " + quantityToRemove + " product(s) " + productChosen.getProduct().getTitle()
					+ " from your cart.");
		}
	}

	public void showItemsInCart() {
		int i = 1;
		for (CartItem line : OnlineStoreMain.currentOrder.getCustomerOrders()) {
			System.out.println(i + ". " + line);
			i++;
		}
	}

	public void checkOut() {
		// TODO check if user is loggedIn
		System.out.printf("Your total is: %.2f. \n", OnlineStoreMain.currentOrder.getTotalCost());
		System.out.println("~~linked to secure payment option~~");
		System.out.println("Thank you for your purchase.");
		checkedOut = true;
	}
}
