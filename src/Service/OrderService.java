package Service;

import java.util.ArrayList;
import entity.Media;
import entity.Order;
import entity.OrderLine;

/**
 *
 * @author Diana Maftei
 */
public class OrderService {
	public static boolean checkedOut;

	public void addItemToCart(ArrayList<?> mediaList, int[] itemToAdd) {
		// index 0 is the product index and index 1 is the quantity to remove
		if ((itemToAdd[0] > 0 && itemToAdd[0] <= mediaList.size())) {
			OrderLine newOrderLine = new OrderLine(((Media) mediaList.get(itemToAdd[0] - 1)), itemToAdd[1]);
			for(int i = 0; i < OnlineStoreMain.currentOrder.getOrderLines().size(); i++){
				if (OnlineStoreMain.currentOrder.getOrderLines().get(i).equals(newOrderLine)){
					if(OnlineStoreMain.currentOrder.getOrderLines().get(i).getQuantity() + itemToAdd[1] > 100){
						System.out.println("You cannot purchase more than 100 items. The total quantity of this item has been updated accordingly.");
						System.out.printf("Your total cost is: %.2f. \n", OnlineStoreMain.currentOrder.getTotalCost());
						OnlineStoreMain.currentOrder.getOrderLines().get(i).setQuantity(100);
						return;
					}
					OnlineStoreMain.currentOrder.getOrderLines().get(i).setQuantity(OnlineStoreMain.currentOrder.getOrderLines().get(i).getQuantity() + itemToAdd[1]);
					System.out.println("The total quantity of this item has been updated.");
					System.out.printf("Your total cost is: %.2f. \n", OnlineStoreMain.currentOrder.getTotalCost());
					return;
				}
			}
			OnlineStoreMain.currentOrder.getOrderLines().add(newOrderLine);
			System.out.println("The item " + newOrderLine.getProduct().getTitle() + " has been added to your cart.");
			System.out.printf("Your total cost is: %.2f. \n", OnlineStoreMain.currentOrder.getTotalCost());
		} else {
			System.out.println("Invalid option.");
		}
	}

	public void removeItemFromCart(int[] itemToRemove) {
		// index 0 is the product index and index 1 is the quantity to remove
		if (itemToRemove[0] == 0) {
			return;
		}
		if (itemToRemove[0] > 0 && itemToRemove[0] <= OnlineStoreMain.currentOrder.getOrderLines().size()) {
			OrderLine productChosen = OnlineStoreMain.currentOrder.getOrderLines().get(itemToRemove[0] - 1);
			if (itemToRemove[1] >= productChosen.getQuantity()) {
				OnlineStoreMain.currentOrder.getOrderLines().remove(productChosen);
				System.out.println("Removed product " + productChosen.getProduct().getTitle() + " from your cart.");
			} else {
				productChosen.setQuantity((productChosen.getQuantity() - itemToRemove[1]));
				System.out.println("Removed " + itemToRemove + " product(s) " + productChosen.getProduct().getTitle()
						+ " from your cart.");
			}
		} else {
			System.out.println("Invalid option.");
		}
	}

	public void showItemsInCart() {
		int i = 1;
		for (OrderLine line : OnlineStoreMain.currentOrder.getOrderLines()) {
			System.out.println(i + ". " + line);
			i++;
		}
	}

	public void checkOut() {

		// TODO check if user is loggedIn with an actual account or if still
		// using guest acc
		System.out.printf("Your total is: %.2f. \n", OnlineStoreMain.currentOrder.getTotalCost());
		System.out.println("~~linked to secure payment option~~");
		System.out.println("Thank you for your purchase.");
		checkedOut = true;
	}
}
