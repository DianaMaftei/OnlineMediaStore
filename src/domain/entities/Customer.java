package domain.entities;

import java.util.ArrayList;

import domain.service.CustomerService.PaymentCategory;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class Customer {
	private String name;
	private String customerID;
	private String password;
	private ArrayList<ShoppingCart> customerOrders;
	private PaymentCategory paymentCategory;
	private int loyaltyPoints = 0;

	public Customer(String name, String customerID, String password) {
		this.name = name;
		this.customerID = customerID;
		this.password = password;
		this.customerOrders = new ArrayList<ShoppingCart>();
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getCustomerID() {
		return customerID;
	}

	public ArrayList<ShoppingCart> getCustomerOrders() {
		return customerOrders;
	}

	public void setCustomerOrders(ArrayList<ShoppingCart> customerOrders) {
		this.customerOrders = customerOrders;
	}

	public void setPaymentCategory(PaymentCategory paymentCategory) {
		this.paymentCategory = paymentCategory;
	}

	public PaymentCategory getPaymentCategory() {
		return paymentCategory;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	@Override
	public String toString() {
		return "Name: " + name + ", Client ID: " + customerID + ", Payment Category: " + paymentCategory + ", Loyalty Points: " + loyaltyPoints;
	}

}
