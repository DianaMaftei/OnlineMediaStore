package DAO;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import domain.entities.Customer;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class CustomerDAO {

	public Properties customersProperties = new Properties();
	public InputStream customersDatabase;

	public CustomerDAO() {
		try {
			customersDatabase = new FileInputStream("customersDatabase");
			customersProperties = new Properties(){
				//order customers in properties file
			    @Override
			    public synchronized java.util.Enumeration<Object> keys() {
			        return java.util.Collections.enumeration(new java.util.TreeSet<Object>(super.keySet()));
			    }
			};
			customersProperties.load(customersDatabase);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getNumberOfCustomers() {
		int index = 1;
		while (true) {
			String name = customersProperties.getProperty(String.format("customer[%d].name", index));
			if (name == null) {
				return index;
			}
			index++;
		}
	}

	public ArrayList<Customer> getCustomers() {
		ArrayList<Customer> customerList = new ArrayList<>();
		for (int i = 1; i < getNumberOfCustomers(); i++) {
			String name = customersProperties.getProperty("customer[" + i + "].name");
			String userID = customersProperties.getProperty("customer[" + i + "].userID");
			String password = customersProperties.getProperty("customer[" + i + "].password");
			Customer customer = new Customer(name, userID, password);
			customerList.add(customer);
		}
		return customerList;
	}

}
