package Service;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Media;
/**
*
*@author Diana Maftei
*/
public class OrderService {
	public static boolean checkedOut;
	private static Scanner userInput = new Scanner(System.in);

	public void addItem(ArrayList<?> mediaList, int productNumber) {
		OnlineStoreMain.currentOrder.getClientCart().add((Media) mediaList.get(productNumber - 1));
		System.out.println(
				"The item " + ((Media) mediaList.get(productNumber - 1)).getTitle() + " has been added to your cart.");
		OnlineStoreMain.currentOrder.setTotalCost(
				OnlineStoreMain.currentOrder.getTotalCost() + ((Media) mediaList.get(productNumber - 1)).getPrice());
		System.out.printf("Your total cost is: %.2f. \n", OnlineStoreMain.currentOrder.getTotalCost());
	}

	public void removeItemFromCart(int itemToRemove) {
		Media productChosen = OnlineStoreMain.currentOrder.getClientCart().get(itemToRemove - 1);
		OnlineStoreMain.currentOrder.getClientCart().remove(itemToRemove - 1);
		System.out.println("The item " + productChosen.getTitle() + "has been removed from your cart.");
	}
	
	public void showItemsInCart(){
		int i = 1;
		for (Media item : OnlineStoreMain.currentOrder.getClientCart()){
			System.out.println(i + ". " + item.getTitle() + " - " + item.getPrice());
			i++;
		}
	}

	public void checkOut() {
		//TODO check if user is loggedIn with an actual account or if still using guest acc
		System.out.printf("Your total is: %.2f. \n", OnlineStoreMain.currentOrder.getTotalCost());
		System.out.println("~~linked to secure payment option~~");
		System.out.println("Thank you for your purchase.");
		checkedOut = true;
	}
}
