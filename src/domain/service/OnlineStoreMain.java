package domain.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import DAO.BookDAO;
import DAO.CdDAO;
import DAO.ClientDAO;
import DAO.DvdDAO;
import DAO.ProductDAO;
import domain.entities.Book;
import domain.entities.CD;
import domain.entities.Customer;
import domain.entities.DVD;
import domain.entities.ShoppingCart;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class OnlineStoreMain {
	public static ShoppingCart currentOrder;
	public static InputStream clientsDatabase;
	public static Properties clientsProperties;
	private static ArrayList<Customer> clients;
	public static ArrayList<DVD> dvds = new ArrayList<>();
	public static ArrayList<CD> cds = new ArrayList<>();
	public static ArrayList<Book> books = new ArrayList<>();

	public static ArrayList<Customer> getClients() {
		return clients;
	}

	public static void main(String[] args) throws Exception {
		loadDatabaseOfClients();
		loadDatabaseOfProducts();

		StoreService storeService = new StoreService();
		storeService.doLogin();

		do {
			storeService.doStoreService();
		} while (!OrderService.checkedOut);
	}

	private static void loadDatabaseOfClients() throws Exception {
		// read from file the database of existing clients
		clientsDatabase = new FileInputStream("clientsDatabase");
		clientsProperties = new Properties(){
		    @Override
		    public synchronized java.util.Enumeration<Object> keys() {
		        return java.util.Collections.enumeration(new java.util.TreeSet<Object>(super.keySet()));
		    }
		};
		clientsProperties.load(clientsDatabase);
		clients = new ClientDAO(clientsProperties).getClients();
	}

	private static void loadDatabaseOfProducts() throws Exception {
		// read from file the products
		
		ProductDAO<CD> cdDAO = new CdDAO();
		ProductDAO<DVD> dvdDAO = new DvdDAO();
		ProductDAO<Book> bookDAO = new BookDAO();
		
		cds = (ArrayList<CD>) cdDAO.getAllMedia();
		dvds = (ArrayList<DVD>) dvdDAO.getAllMedia();
		books = (ArrayList<Book>) bookDAO.getAllMedia();
		
	}

}
