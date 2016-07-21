package domain.service;

import java.util.ArrayList;

import DAO.database.DBUtil;
import DAO.properties.PropertiesCustomerDAO;
import domain.entities.Customer;
import domain.entities.Product;
import domain.entities.ShoppingCart;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class OnlineStoreMain {
	public static ShoppingCart currentOrder = new ShoppingCart(null);
	private static ArrayList<Customer> customers;
	public static ArrayList<Product> products = new ArrayList<>();

	public static ArrayList<Customer> getCustomers() {
		return customers;
	}

	public static void main(String[] args) throws Exception {	
		
		DBUtil.createConnection();
		
		loadDatabaseOfCustomers();

		StoreService storeService = new StoreService();

		storeService.doLogin();
		
		do {
			storeService.doStoreService();
		} while (!ShoppingCartService.checkedOut);
	}

	private static void loadDatabaseOfCustomers() throws Exception {
		customers = PropertiesCustomerDAO.getInstance().getCustomers();
	}
	
	
	
	
}
