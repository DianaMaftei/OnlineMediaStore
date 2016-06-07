package Service;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import entity.Order;

public class OnlineMedia {
	public static DataFunctionProperties dataFunctionProperties;

	public static void main(String[] args) throws Exception {
		InputStream file = new FileInputStream("media-store2");
		Properties properties = new Properties();		
		properties.load(file);		
		dataFunctionProperties = new DataFunctionProperties(properties);

		StoreService storeService = new StoreService();
		
		Order currentOrder = new Order(storeService.displayMainMenu());
		int productType = storeService.displayItems();
		int productNumber = storeService.getItemNo();
		
		OrderService orderService = new OrderService();
		
		orderService.addItemToCart(productType, productNumber);
		
		
	}

}
