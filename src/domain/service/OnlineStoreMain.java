package domain.service;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import domain.entities.Client;
import domain.entities.Order;
import productDAO.DAO;
/**
*
*@author diana.maftei[at]gmail.com
*/
public class OnlineStoreMain {
	public static Order currentOrder;
	public static DAO dataFunctionProperties;
	private static ArrayList<Client> clients;

	public static ArrayList<Client> getClients() {
		return clients;
	}

	public static void main(String[] args) throws Exception {
		//create the database of existing clients
		clients = new ArrayList<>();
		initClients();
		
		//read from file the products
		InputStream file = new FileInputStream("media-store2");
		Properties properties = new Properties();		
		properties.load(file);		
		dataFunctionProperties = new DAO(properties);
		initProductDatabase();
		
		StoreService storeService = new StoreService();
		storeService.doLogin();		
		
		do{
			storeService.doStoreService();
		}while(!OrderService.checkedOut );
	}
	
	//TODO make in data.. a get all products method
	private static void initProductDatabase(){
		OnlineStoreMain.dataFunctionProperties.getAllDVDs();
		OnlineStoreMain.dataFunctionProperties.getAllCDs();
		OnlineStoreMain.dataFunctionProperties.getAllBooks();
	}
	
	
	//TODO add client data in properties file
	private static void initClients(){
		Client diana = new Client("Maftei Diana", "dianaM", "0000");
		clients.add(diana);
		Client anca = new Client("Stefanescu Anca", "ancaS", "1111");
		clients.add(anca);
	}

}
