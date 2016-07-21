package DAO.properties;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import DAO.ProductDAO;
import domain.entities.Genre;
import domain.entities.Product;
import domain.entities.Product.AgeCategory;
import domain.entities.Product.PriceCategory;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public abstract class PropertiesProductDAO<T extends Product> implements ProductDAO<T>{

	Properties productsProperties = new Properties();

	public PropertiesProductDAO() {
		InputStream productsDatabase;
		try {
			productsDatabase = new FileInputStream("productsDatabase");
			productsProperties = new Properties();
			productsProperties.load(productsDatabase);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected abstract String getPropertiesKeyName();

	protected abstract T createProduct(int index);

	/* (non-Javadoc)
	 * @see DAO.properties.ProductDAO#getNumberOfProducts(java.lang.String)
	 */

	public int getNumberOfProducts(String searchString) {
		int index = 1;
		while (true) {
			String title = productsProperties.getProperty(String.format(searchString, index));
			if (title == null) {
				return index;
			}
			index++;
		}
	}

	/* (non-Javadoc)
	 * @see DAO.properties.ProductDAO#getAllMedia()
	 */
	@Override
	public List<T> getAllProducts() {
		List<T> productList = new ArrayList<>();
		int noOfProduct = getNumberOfProducts(getPropertiesKeyName() + "%d.title");
		for (int i = 1; i < noOfProduct; i++) {
			T product = createProduct(i);
			fillCommonFields(product, i);
			productList.add(product);
		}
		return productList;
	}

	protected String readProductAttribute(String attribute, int mediaIndex) {
		return productsProperties.getProperty(getPropertiesKeyName() + mediaIndex + "." + attribute);
	}

	private void fillCommonFields(Product product, int indexInPropertiesFile) {
		product.setTitle(readProductAttribute("title", indexInPropertiesFile));
		product.setPrice(Double.valueOf(readProductAttribute("price", indexInPropertiesFile)));
		product.setGenre(Genre.valueOf(readProductAttribute("genre", indexInPropertiesFile)));
		product.setDescription(readProductAttribute("description", indexInPropertiesFile));
		product.setPriceCategory(PriceCategory.valueOf(readProductAttribute("priceCategory", indexInPropertiesFile)));
		product.setAgeCategory(AgeCategory.valueOf(readProductAttribute("ageCategory", indexInPropertiesFile)));
	}

	
}
