package DAO;

import java.util.ArrayList;
import java.util.Properties;

import domain.entities.Customer;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class ClientDAO {

	Properties properties;

	public ClientDAO(Properties properties) {
		this.properties = properties;
	}

	public int getNumberOfClients() {
		int index = 1;
		while (true) {
			String name = properties.getProperty(String.format("client%d.name", index));
			if (name == null) {
				return index;
			}
			index++;
		}
	}

	public ArrayList<Customer> getClients() {
		ArrayList<Customer> clientList = new ArrayList<>();
		for (int i = 1; i < getNumberOfClients(); i++) {
			String name = properties.getProperty("client" + i + ".name");
			String userID = properties.getProperty("client" + i + ".userID");
			String password = properties.getProperty("client" + i + ".password");
			Customer client = new Customer(name, userID, password);
			clientList.add(client);
		}
		return clientList;
	}

}
