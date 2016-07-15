package domain.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

import domain.entities.Product.AgeCategory;
import domain.entities.Product.PriceCategory;
import domain.service.CustomerService;
import domain.service.Login;
import domain.service.OnlineStoreMain;
import domain.service.CustomerService.PaymentCategory;
import domain.service.CustomerService.RenterFrequencyCategory;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class CartItem {
	private Product product;
	private int quantity;
	private String typeOfTransaction;
	private int daysRented;

	public CartItem(Product product, int quantity, String typeOfTransaction) {
		super();
		this.typeOfTransaction = typeOfTransaction;
		this.product = product;
		this.quantity = quantity;
	}

	public BigDecimal getPrice(String typeOfTransaction) {
		if ("purchase".equals(typeOfTransaction)) {
			return BigDecimal.valueOf(product.getPrice()).multiply(BigDecimal.valueOf(quantity));
		}
		return getRentalPrice();
	}

	private BigDecimal getRentalPricePerDay() {
		BigDecimal productBasePrice = BigDecimal.valueOf(product.getPrice());
		if (product instanceof DVD) {
			return (productBasePrice.multiply(new BigDecimal(0.1)));
		} else if (this.getProduct() instanceof CD) {
			return (productBasePrice.multiply(new BigDecimal(0.5)));
		} else {
			// books
			return (productBasePrice.multiply(new BigDecimal(0.02)));
		}
	}

	private BigDecimal getRentalPrice() {
		BigDecimal discountByProduct = applyDiscountByPriceCategory();
		BigDecimal discountByRenterFrequency = applyDiscountByRenterFrequencyCategory(discountByProduct);
		BigDecimal finalPrice = applyDiscountByPaymentCategory(discountByRenterFrequency);
		finalPrice = finalPrice.setScale(2, RoundingMode.HALF_UP);
		return finalPrice;
	}

	private BigDecimal applyDiscountByPriceCategory() {
		BigDecimal regularPrice = getRentalPricePerDay();
		BigDecimal discountedPrice = new BigDecimal(0);

		for (int i = 0; i < daysRented; i++) {
			if (AgeCategory.CHILDREN.equals(product.getAgeCategory())) {
				if (i > 0 && i < 3) {
					discountedPrice = discountedPrice.add(regularPrice);
				} else if (i >= 3) {
					discountedPrice = discountedPrice
							.add(regularPrice.subtract(regularPrice.multiply(new BigDecimal(0.75))));
				}

			} else if (PriceCategory.NEW_RELEASE.equals(product.getPriceCategory())) {
				if (i >= 1) {
					discountedPrice = discountedPrice.subtract(regularPrice.multiply(new BigDecimal(0.5)));
				}
			} else if (PriceCategory.INFREQUENTLY_RENTED.equals(product.getPriceCategory())) {
				discountedPrice = discountedPrice
						.add(regularPrice.subtract(regularPrice.multiply(new BigDecimal(0.9))));
			}
		}
		return discountedPrice;

	}

	private BigDecimal applyDiscountByRenterFrequencyCategory(BigDecimal discount) {
		RenterFrequencyCategory renterFC = CustomerService.getInstance().getFrequencyCategory();
		BigDecimal totalSum = (BigDecimal.valueOf(daysRented).multiply(getRentalPricePerDay()).subtract(discount));
		if (RenterFrequencyCategory.NEW_RENTAL_CUSTOMER.equals(renterFC)) {
			return totalSum.subtract(totalSum.multiply(new BigDecimal(0.05)));

		} else if (RenterFrequencyCategory.GOLD.equals(renterFC)) {
			return totalSum.subtract(totalSum.multiply(new BigDecimal(0.01)));

		} else if (RenterFrequencyCategory.PLATINUM.equals(renterFC)) {
			return totalSum.subtract(totalSum.multiply(new BigDecimal(0.03)));
		}
		return totalSum;

	}

	private BigDecimal applyDiscountByPaymentCategory(BigDecimal finalPrice) {
		if ("guest".equals(OnlineStoreMain.currentOrder.getCustomerID())) {
			return finalPrice;
		}
		PaymentCategory renterPC = Login.getCurrentCustomer().getPaymentCategory();
		if (PaymentCategory.PREPAYER.equals(renterPC)) {
			finalPrice = finalPrice.subtract(finalPrice.multiply(new BigDecimal(0.01)));
		}
		return finalPrice;
	}

	public String getTypeOfTransaction() {
		return typeOfTransaction;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public void setDaysRented(int daysRented) {
		this.daysRented = daysRented;
	}

	@Override
	public String toString() {
		if ("rental".equals(this.typeOfTransaction)) {
			return "Rental" + " - " + product.getTitle() + ", Days rented: " + daysRented + ", Price: $"
					+ String.format("%.2f", getPrice(this.typeOfTransaction));
		} else {
			return "Purchase" + " - " + product.getTitle() + ", Quantity: " + quantity + ", Price: $"
					+ String.format("%.2f", getPrice(this.typeOfTransaction));
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (((CartItem) obj).getProduct().equals(this.getProduct())) {
			return true;
		}
		return false;
	}

}
