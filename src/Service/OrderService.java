package Service;

import java.util.ArrayList;

import entity.Media;

public class OrderService {
	
	public void addItemToCart(int productType, int productNumber){
		switch(productType){
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
			System.out.println("Invalid");
		}
	}
	
	private void addItem(ArrayList<?> mediaList, int productNumber){
		OnlineMedia.currentOrder.getClientCart().add((Media) mediaList.get(productNumber-1));
		System.out.println("The item " + ((Media) mediaList.get(productNumber-1)).getTitle() + " has been added to your cart.");
		OnlineMedia.currentOrder.setTotalCost(OnlineMedia.currentOrder.getTotalCost() + ((Media) mediaList.get(productNumber-1)).getPrice());
		System.out.println("Your total cost is: " + OnlineMedia.currentOrder.getTotalCost());
	}
	
	public void removeItemFromCart(int productType, int productNumber){
		
	}
}
