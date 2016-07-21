package DAO.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.BookDAO;
import domain.entities.Book;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class DBBookDAO extends DBProductDAO<Book> implements BookDAO {

	@Override
	public String getPropertiesKeyName() {
		return "BOOK";
	}

	@Override
	public Book createProduct(ResultSet rs) {
		Book book = new Book();
		try {
			int noOfPages = rs.getInt("pages_no");
			String publishingHouse = rs.getString("publisher");
			book.setNoOfPages(noOfPages);
			book.setPublishingHouse(publishingHouse);
			int id = rs.getInt("product_id");
			book.setAuthors(getAuthors(id));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// private List<String> authors;

		return book;
	}

	@Override
	public Book createProduct(int index) {
		// used by PropertiesDAO
		return null;
	}
	
	private List<String> getAuthors(int id){
		List<String> authorNames = new ArrayList();
		Connection conn = DBUtil.getConnection();
		String allAuthors = String.format("select name from people ppl JOIN product_people pp ON ppl.person_id = pp.id_people where pp.id_product = %d;", id);
		try {
			PreparedStatement ps = conn.prepareStatement(allAuthors);
			ResultSet rs = ps.executeQuery(); 
			while(rs.next()){
				String name = rs.getString("name");
				authorNames.add(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		};

		return authorNames;
	}

}
