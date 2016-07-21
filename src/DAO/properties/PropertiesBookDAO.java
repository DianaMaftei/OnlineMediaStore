package DAO.properties;

import java.sql.ResultSet;
import java.util.ArrayList;

import DAO.BookDAO;
import domain.entities.Book;

/**
*
*@author diana.maftei[at]gmail.com
*/
public class PropertiesBookDAO extends PropertiesProductDAO<Book> implements BookDAO{

	@Override
	public String getPropertiesKeyName() {
		return "Book";
	}

	@Override
	public Book createProduct(int index) {
		Book book = new Book();
		String[] authors = readProductAttribute("authors", index).split("/");
		ArrayList<String> authorsList = new ArrayList<>();
		for (int j = 0; j < authors.length; j++) {
			authorsList.add(authors[j]);
		}
		book.setAuthors(authorsList);		
		book.setNoOfPages(Integer.valueOf(readProductAttribute("noOfPages", index)));
		book.setPublishingHouse(readProductAttribute("publishingHouse", index));
		return book;
	}

	@Override
	public Book createProduct(ResultSet rs) {
		//used by DBDAO
		return null;
	}



}
