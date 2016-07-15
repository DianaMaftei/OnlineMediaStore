package domain.service;

import java.util.ArrayList;

import DAO.properties.BookDAO;
import DAO.properties.CdDAO;
import DAO.properties.CustomerDAO;
import DAO.properties.DvdDAO;
import DAO.properties.ProductDAO;
import domain.entities.Book;
import domain.entities.CD;
import domain.entities.Customer;
import domain.entities.DVD;
import domain.entities.Product;
import domain.entities.ShoppingCart;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class OnlineStoreMain {
	public static ShoppingCart currentOrder = new ShoppingCart(null);
	private static ArrayList<Customer> customers;
	public static ArrayList<DVD> dvds = new ArrayList<>();
	public static ArrayList<CD> cds = new ArrayList<>();
	public static ArrayList<Book> books = new ArrayList<>();
	public static ArrayList<Product> products = new ArrayList<>();

	public static ArrayList<Customer> getCustomers() {
		return customers;
	}

	public static void main(String[] args) throws Exception {
		
		
		loadDatabaseOfCustomers();
		loadDatabaseOfProducts();

		StoreService storeService = new StoreService();

		storeService.doLogin();
		
		do {
			storeService.doStoreService();
		} while (!ShoppingCartService.checkedOut);
	}

	private static void loadDatabaseOfCustomers() throws Exception {
		customers = CustomerDAO.getInstance().getCustomers();
	}
	
	private static void loadDatabaseOfProducts() throws Exception {
		ProductDAO<CD> cdDAO = new CdDAO();
		ProductDAO<DVD> dvdDAO = new DvdDAO();
		ProductDAO<Book> bookDAO = new BookDAO();

		cds = (ArrayList<CD>) cdDAO.getAllMedia();
		dvds = (ArrayList<DVD>) dvdDAO.getAllMedia();
		books = (ArrayList<Book>) bookDAO.getAllMedia();
		
		products.addAll(cds);
		products.addAll(books);
		products.addAll(dvds);

	}

}
