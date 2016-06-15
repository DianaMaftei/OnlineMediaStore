package productDAO;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.entities.Genre;
import domain.entities.Product;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public abstract class ProductDAO<T extends Product> {

	// TODO find a generalized way to get rid of separate DAOs

	Properties productsProperties = new Properties();;

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
	}

}
