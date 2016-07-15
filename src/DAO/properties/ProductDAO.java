package DAO.properties;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import domain.entities.Genre;
import domain.entities.Product;
import domain.entities.Product.AgeCategory;
import domain.entities.Product.PriceCategory;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public abstract class ProductDAO<T extends Product> {

	Properties productsProperties = new Properties();

	public ProductDAO() {
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

	protected abstract T createMedia(int index);

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

	public List<T> getAllMedia() {
		List<T> mediaList = new ArrayList<>();
		int noOfProduct = getNumberOfProducts(getPropertiesKeyName() + "%d.title");
		for (int i = 1; i < noOfProduct; i++) {
			T media = createMedia(i);
			fillCommonFields(media, i);
			mediaList.add(media);
		}
		return mediaList;
	}

	protected String readMediaAttribute(String attribute, int mediaIndex) {
		return productsProperties.getProperty(getPropertiesKeyName() + mediaIndex + "." + attribute);
	}

	private void fillCommonFields(Product product, int indexInPropertiesFile) {
		product.setTitle(readMediaAttribute("title", indexInPropertiesFile));
		product.setPrice(Double.valueOf(readMediaAttribute("price", indexInPropertiesFile)));
		product.setGenre(Genre.valueOf(readMediaAttribute("genre", indexInPropertiesFile)));
		product.setDescription(readMediaAttribute("description", indexInPropertiesFile));
		product.setPriceCategory(PriceCategory.valueOf(readMediaAttribute("priceCategory", indexInPropertiesFile)));
		product.setAgeCategory(AgeCategory.valueOf(readMediaAttribute("ageCategory", indexInPropertiesFile)));
	}

	
	
	public static void listItemsInStock(ArrayList<? extends Product> list) {
		int index = 1;
		for (Product item : list) {
			System.out.println(index);
			System.out.println(item);
			index++;
		}
	}
	

	public static ArrayList<? extends Product> sortByTitle(ArrayList<? extends Product> list) {
		Collections.sort(list, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return o1.getTitle().compareTo(o2.getTitle());
			}
		});
		return list;
	}

	public static ArrayList<? extends Product> sortByPrice(ArrayList<? extends Product> list) {
		Collections.sort(list, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return (int) (o1.getPrice() - o2.getPrice());
			}
		});
		return list;
	}

	
}
