package DAO.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.ProductDAO;
import domain.entities.Genre;
import domain.entities.Product;
import domain.entities.Product.AgeCategory;
import domain.entities.Product.PriceCategory;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public abstract class DBProductDAO<T extends Product> implements ProductDAO<T> {
	private Connection connection = DBUtil.getConnection();

	protected abstract String getPropertiesKeyName();

	protected abstract T createProduct(ResultSet rs);

	@Override
	public List<T> getAllProducts() {
		List<T> productList = new ArrayList<>();
		String allProducts = "select product_id from products";
		PreparedStatement ps;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(allProducts);
			rs = ps.executeQuery();
			List<Integer> ids = getIDS(rs);
			for (Integer i : ids) {
				String productDetails = String.format("select * from products where product_id = %d;", i);
				ps = connection.prepareStatement(productDetails);
				rs = ps.executeQuery();
				if (rs.next()) {
					// TODO - filter by type
					String type = rs.getString("type");
					if (getPropertiesKeyName().equals(type)) {
						T product = createProduct(rs);
						fillCommonFields(product, rs);
						productList.add(product);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productList;
	}

	private List<Integer> getIDS(ResultSet rs) {
		List<Integer> listOfIds = new ArrayList<Integer>();
		try {
			while (rs.next()) {
				listOfIds.add(rs.getInt("product_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfIds;
	}

	private void fillCommonFields(Product product, ResultSet rs) {
		try {
			String title = rs.getString("title");
			double price = rs.getDouble("price");
			Genre genre = Genre.valueOf(rs.getString("genre"));
			String description = rs.getString("description");
			PriceCategory priceCategory = PriceCategory.valueOf(rs.getString("price_category"));
			AgeCategory ageCategory = AgeCategory.valueOf(rs.getString("age_category"));

			product.setTitle(title);
			product.setPrice(price);
			product.setGenre(genre);
			product.setDescription(description);
			product.setPriceCategory(priceCategory);
			product.setAgeCategory(ageCategory);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
