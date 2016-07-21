package DAO.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import DAO.CustomerDAO;
import domain.entities.CartItem;
import domain.entities.Customer;
import domain.entities.Product;
import domain.entities.ShoppingCart;
import domain.service.CustomerService.PaymentCategory;
import domain.service.Login;
import domain.service.OnlineStoreMain;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class PropertiesCustomerDAO implements CustomerDAO {

	public Properties customersProperties = new Properties();
	public InputStream customersDatabase;

	private static CustomerDAO customerDao = new PropertiesCustomerDAO();

	public static CustomerDAO getInstance() {
		return customerDao;
	}

	public PropertiesCustomerDAO() {
		try (FileInputStream customersDatabase = new FileInputStream("customersDatabase")) {
			customersProperties = new Properties() {
				// order customers in properties file
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

	/* (non-Javadoc)
	 * @see DAO.properties.CustomerDAO#getNumberOfCustomers()
	 */
	private int getNumberOfCustomers() {
		int index = 1;
		while (true) {
			String name = customersProperties.getProperty(String.format("customer[%d].name", index));
			if (name == null) {
				return index;
			}
			index++;
		}
	}
	// TODO search in prop, only return current loggedin, if matching info

	/* (non-Javadoc)
	 * @see DAO.properties.CustomerDAO#getCustomers()
	 */
	@Override
	public ArrayList<Customer> getCustomers() {
		ArrayList<Customer> customerList = new ArrayList<>();
		for (int i = 1; i < getNumberOfCustomers(); i++) {
			String name = customersProperties.getProperty("customer[" + i + "].name");
			String userID = customersProperties.getProperty("customer[" + i + "].userID");
			String password = customersProperties.getProperty("customer[" + i + "].password");
			String paymentCategory = customersProperties.getProperty("customer[" + i + "].paymentCategory");
			String loyaltyPoints = customersProperties.getProperty("customer[" + i + "].loyaltyPoints");
			Customer customer = new Customer(name, userID, password);
			customer.setPaymentCategory(PaymentCategory.valueOf(paymentCategory));
			customer.setLoyaltyPoints(Integer.valueOf(loyaltyPoints));
			customerList.add(customer);
		}
		return customerList;
	}

	private int getCurrentCustomerIndex() {
		int currentCustomerIndex = 1;
		while (true) {
			if (customersProperties.getProperty("customer[" + currentCustomerIndex + "].userID")
					.equals(Login.getCurrentCustomer().getCustomerID())) {
				return currentCustomerIndex;
			}
			currentCustomerIndex++;
		}
	}

	/* (non-Javadoc)
	 * @see DAO.properties.CustomerDAO#updateCustomerLoyaltyPoints()
	 */
	@Override
	public void updateCustomerLoyaltyPoints() {
		Properties properties = customersProperties;
		int currentCustomerIndex = getCurrentCustomerIndex();
		try (FileInputStream customersDatabase = new FileInputStream("customersDatabase")) {
			properties.load(customersDatabase);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String loyaltyPoints = String.format("customer[%d].loyaltyPoints", currentCustomerIndex);
		int newLoyaltyPoints = Login.getCurrentCustomer().getLoyaltyPoints();
		properties.setProperty(loyaltyPoints, "" + newLoyaltyPoints);
		try (FileOutputStream out = new FileOutputStream("customersDatabase");) {
			properties.store(out, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see DAO.properties.CustomerDAO#updateDatabaseWithNewUser(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateDatabaseWithNewUser(String fullName, String userID, String password) {
		Properties properties = customersProperties;
		int currentCustomerIndex = getNumberOfCustomers();
		try (FileInputStream customersDatabase = new FileInputStream("customersDatabase")) {
			properties.load(customersDatabase);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		properties.setProperty(String.format("customer[%d].name", currentCustomerIndex), fullName);
		properties.setProperty(String.format("customer[%d].userID", currentCustomerIndex), userID);
		properties.setProperty(String.format("customer[%d].password", currentCustomerIndex), password);
		properties.setProperty(String.format("customer[%d].loyaltyPoints", currentCustomerIndex), "0");
		properties.setProperty(String.format("customer[%d].paymentCategory", currentCustomerIndex), "REGULAR");
		try (FileOutputStream out = new FileOutputStream("customersDatabase");) {
			properties.store(out, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see DAO.properties.CustomerDAO#setCustomerHistory(domain.entities.ShoppingCart)
	 */
	@Override
	public void setCustomerHistory(ShoppingCart currentShoppingCart) {
		Properties properties = customersProperties;
		int cartIndex = Login.getCurrentCustomer().getCustomerOrders().size() + 1;
		int currentCustomerIndex = getCurrentCustomerIndex();
		try (FileInputStream customersDatabase = new FileInputStream("customersDatabase")) {
			properties.load(customersDatabase);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		while (true) {
			String title = customersProperties.getProperty(
					"customer[" + getCurrentCustomerIndex() + "].cart[" + cartIndex + "].cartItem[1].title");
			if (title == null) {
				break;
			}
			cartIndex++;
		}
		for (int i = 0; i < currentShoppingCart.getItemsOrdered().size(); i++) {
			CartItem currentItem = currentShoppingCart.getItemsOrdered().get(i);
			
					// dd/MM/yyyy //hh:mm:ss

			properties.setProperty(
					String.format("customer[%d].cart[%d].cartItem[%d].title", currentCustomerIndex, cartIndex, i + 1),
					currentItem.getProduct().getTitle());
			properties.setProperty(String.format("customer[%d].cart[%d].cartItem[%d].quantity", currentCustomerIndex,
					cartIndex, i + 1), "" + currentItem.getQuantity());
			properties.setProperty(String.format("customer[%d].cart[%d].cartItem[%d].typeOfTransaction",
					currentCustomerIndex, cartIndex, i + 1), currentItem.getTypeOfTransaction());
			properties.setProperty(String.format("customer[%d].cart[%d].cartItem[%d].daysRented", currentCustomerIndex,
					cartIndex, i + 1), "" + currentItem.getDaysRented());
			
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			String date = df.format(currentShoppingCart.getIssueDate());
			
			properties.setProperty(String.format("customer[%d].cart[%d].dateIssued", currentCustomerIndex,
					cartIndex, i + 1), date);
		}

		try (FileOutputStream out = new FileOutputStream("customersDatabase")) {
			properties.store(out, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see DAO.properties.CustomerDAO#getCustomerHistory()
	 */
	@Override
	public ArrayList<ShoppingCart> getCustomerHistory() {
		ArrayList<ShoppingCart> history = new ArrayList<ShoppingCart>();
		int cartIndex = 1;
		while (true) {
			String title = customersProperties.getProperty(
					"customer[" + getCurrentCustomerIndex() + "].cart[" + cartIndex + "].cartItem[1].title");
			if (title == null) {
				return history;
			}
			history.add(getShoppingCart(getCurrentCustomerIndex(), cartIndex));
			cartIndex++;
		}
	}

	private ShoppingCart getShoppingCart(int currentCustomerIndex, int cartIndex) {
		ShoppingCart shoppingCart = new ShoppingCart(Login.getCurrentCustomer().getCustomerID());
		int itemIndex = 1;
		while (true) {
			String itemTitle = customersProperties.getProperty(
					"customer[" + currentCustomerIndex + "].cart[" + cartIndex + "].cartItem[" + itemIndex + "].title");
			if (itemTitle == null) {
				break;
			}
			shoppingCart.getItemsOrdered().add(getCartItem(currentCustomerIndex, cartIndex, itemIndex));
			
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			String dateString = customersProperties.getProperty(String.format("customer[%d].cart[%d].dateIssued", currentCustomerIndex,
					cartIndex));
			try {
				Date date = (Date)df.parse(dateString);
				shoppingCart.setIssueDate(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			itemIndex++;
		}
		return shoppingCart;
	}

	private CartItem getCartItem(int currentCustomerIndex, int cartIndex, int itemIndex) {
		CartItem cartItem = null;

		String itemTitle = customersProperties.getProperty(
				"customer[" + currentCustomerIndex + "].cart[" + cartIndex + "].cartItem[" + itemIndex + "].title");
		String quantity = customersProperties.getProperty(
				"customer[" + currentCustomerIndex + "].cart[" + cartIndex + "].cartItem[" + itemIndex + "].quantity");
		String typeOfTransaction = customersProperties.getProperty("customer[" + currentCustomerIndex + "].cart["
				+ cartIndex + "].cartItem[" + itemIndex + "].typeOfTransaction");
		String daysRented = customersProperties.getProperty("customer[" + currentCustomerIndex + "].cart[" + cartIndex
				+ "].cartItem[" + itemIndex + "].daysRented");

		Product product = null;
		for (Product p : OnlineStoreMain.products) {
			if (itemTitle.equals(p.getTitle())) {
				product = p;
				break;
			}
		}

		cartItem = new CartItem(product, Integer.valueOf(quantity), typeOfTransaction);
		if ("rental".equals(typeOfTransaction)) {
			cartItem.setDaysRented(Integer.valueOf(daysRented));
		}
		return cartItem;

	}

}
