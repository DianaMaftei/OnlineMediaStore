package domain.service;

import java.math.BigDecimal;

import domain.entities.CartItem;
import domain.entities.Customer;
import domain.entities.ShoppingCart;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class CustomerService {

	private static CustomerService instance = new CustomerService();

	private CustomerService() {

	}

	public static CustomerService getInstance() {
		return instance;
	}


	public RenterFrequencyCategory getFrequencyCategory() {
		if ("guest".equals(OnlineStoreMain.currentOrder.getCustomerID())) {
			return RenterFrequencyCategory.GUEST;
		}else if (PaymentCategory.BAD_PAYER.equals(Login.getCurrentCustomer().getPaymentCategory())) {
			return RenterFrequencyCategory.REGULAR;
		} else if (Login.getCurrentCustomer().getLoyaltyPoints() == 0) {
			return RenterFrequencyCategory.NEW_RENTAL_CUSTOMER;
		} else if (Login.getCurrentCustomer().getLoyaltyPoints() < 10) {
			return RenterFrequencyCategory.REGULAR;
		} else if (Login.getCurrentCustomer().getLoyaltyPoints() > 10 && Login.getCurrentCustomer().getLoyaltyPoints() < 100) {
			return RenterFrequencyCategory.GOLD;
		} else {
			return RenterFrequencyCategory.PLATINUM;
		}

	}

	public int calculateLoyaltyPointsAfterNewPurchase(ShoppingCart shopCart) {
		if (PaymentCategory.BAD_PAYER.equals(Login.getCurrentCustomer().getPaymentCategory())) {
			return 0;
		}
		int loyaltyPoints = Login.getCurrentCustomer().getLoyaltyPoints();
		for (CartItem c : shopCart.getItemsOrdered()) {
			if ("rental".equals(c.getTypeOfTransaction())) {
				if ("NEW_RELEASE".equals(c.getProduct().getPriceCategory()) && c.getDaysRented() > 1) {
					loyaltyPoints += 2;
				} else if ("INFREQUENTLY_RENTED".equals(c.getProduct().getPriceCategory())) {
					loyaltyPoints += 2;
				} else if ("CHILDREN".equals(c.getProduct().getAgeCategory()) && c.getDaysRented() > 4) {
					loyaltyPoints += 3;
				} else {
					loyaltyPoints += 1;
				}
			}
		}

		if (shopCart.getTotalCost().compareTo(new BigDecimal(10)) > 0) {
			loyaltyPoints++;
		}
		if (shopCart.getTotalCost().compareTo(new BigDecimal(30)) > 0) {
			loyaltyPoints++;
		}
		return loyaltyPoints;
	}

	public enum PaymentCategory {
		PREPAYER, REGULAR, BAD_PAYER
	}

	public enum RenterFrequencyCategory {
		GUEST, NEW_RENTAL_CUSTOMER, REGULAR, GOLD, PLATINUM
	}
}
