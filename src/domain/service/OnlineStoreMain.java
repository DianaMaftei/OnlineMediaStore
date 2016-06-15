package domain.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import domain.entities.Book;
import domain.entities.CD;
import domain.entities.Client;
import domain.entities.DVD;
import domain.entities.Order;
import productDAO.BookDAO;
import productDAO.CdDAO;
import productDAO.DvdDAO;
import productDAO.ProductDAO;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class OnlineStoreMain {
	public static Order currentOrder;
	public static InputStream clientsDatabase;
	public static Properties clientsProperties;
	private static ArrayList<Client> clients;
	public static ArrayList<DVD> dvds = new ArrayList<>();
	public static ArrayList<CD> cds = new ArrayList<>();
	public static ArrayList<Book> books = new ArrayList<>();

	public static ArrayList<Client> getClients() {
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
		clientsProperties = new Properties();
		clientsProperties.load(clientsDatabase);
		clients = new ClientDAO(clientsProperties).getClients();
	}

	private static void loadDatabaseOfProducts() throws Exception {
		// read from file the products
		
		ProductDAO cdDAO = new CdDAO();
		ProductDAO dvdDAO = new DvdDAO();
		ProductDAO bookDAO = new BookDAO();
		
		cds = (ArrayList<CD>) cdDAO.getAllMedia();
		dvds = (ArrayList<DVD>) dvdDAO.getAllMedia();
		books = (ArrayList<Book>) bookDAO.getAllMedia();
		
	}

}
