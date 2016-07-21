package DAO.database;

import java.util.ArrayList;

import DAO.CustomerDAO;
import domain.entities.Customer;
import domain.entities.ShoppingCart;

/**
*
*@author diana.maftei[at]gmail.com
*/
public class DBCustomers implements CustomerDAO{

	@Override
	public ArrayList<Customer> getCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCustomerLoyaltyPoints() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDatabaseWithNewUser(String fullName, String userID, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCustomerHistory(ShoppingCart currentShoppingCart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<ShoppingCart> getCustomerHistory() {
		// TODO Auto-generated method stub
		return null;
	}

}
