package domain.service;

import java.util.ArrayList;

import domain.entities.CartItem;
import domain.entities.Product;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class OrderService {
	public static boolean checkedOut;

	public void addItemToCart(ArrayList<? extends Product> productList, int itemToAdd, int quantityOfItems) {
		CartItem newOrderLine = new CartItem((productList.get(itemToAdd - 1)), quantityOfItems);
		for (int i = 0; i < OnlineStoreMain.currentOrder.getOrderLines().size(); i++) {
			if (OnlineStoreMain.currentOrder.getOrderLines().get(i).equals(newOrderLine)) {
				if (OnlineStoreMain.currentOrder.getOrderLines().get(i).getQuantity() + quantityOfItems > 100) {
					System.out.println(
							"You cannot purchase more than 100 items. The total quantity of this item has been updated accordingly.");
					System.out.printf("Your total cost is: %.2f. \n", OnlineStoreMain.currentOrder.getTotalCost());
					OnlineStoreMain.currentOrder.getOrderLines().get(i).setQuantity(100);
					return;
				}
				OnlineStoreMain.currentOrder.getOrderLines().get(i).setQuantity(
						OnlineStoreMain.currentOrder.getOrderLines().get(i).getQuantity() + quantityOfItems);
				System.out.println("The total quantity of this item has been updated.");
				System.out.printf("Your total cost is: %.2f. \n", OnlineStoreMain.currentOrder.getTotalCost());
				return;
			}
		}
		OnlineStoreMain.currentOrder.getOrderLines().add(newOrderLine);
		System.out.println("The item " + newOrderLine.getProduct().getTitle() + " has been added to your cart.");
		System.out.printf("Your total cost is: %.2f. \n\n", OnlineStoreMain.currentOrder.getTotalCost());
	}

	public void removeItemFromCart(int itemToRemove, int quantityToRemove) {
		CartItem productChosen = OnlineStoreMain.currentOrder.getOrderLines().get(itemToRemove - 1);
		if (quantityToRemove >= productChosen.getQuantity()) {
			OnlineStoreMain.currentOrder.getOrderLines().remove(productChosen);
			System.out.println("Removed product " + productChosen.getProduct().getTitle() + " from your cart.");
		} else {
			productChosen.setQuantity((productChosen.getQuantity() - quantityToRemove));
			System.out.println("Removed " + quantityToRemove + " product(s) " + productChosen.getProduct().getTitle()
					+ " from your cart.");
		}

	}

	public void showItemsInCart() {
		int i = 1;
		for (CartItem line : OnlineStoreMain.currentOrder.getOrderLines()) {
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
