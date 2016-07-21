package DAO;

import java.util.ArrayList;

import domain.entities.Customer;
import domain.entities.ShoppingCart;

/**
*
*@author diana.maftei[at]gmail.com
*/
public interface CustomerDAO {

	ArrayList<Customer> getCustomers();

	void updateCustomerLoyaltyPoints();

	void updateDatabaseWithNewUser(String fullName, String userID, String password);

	void setCustomerHistory(ShoppingCart currentShoppingCart);

	ArrayList<ShoppingCart> getCustomerHistory();

}