package DAO;

import java.sql.ResultSet;

import domain.entities.Book;

/**
*
*@author diana.maftei[at]gmail.com
*/
public interface BookDAO extends ProductDAO<Book>{

	String getPropertiesKeyName();

	Book createProduct(int index);
	Book createProduct(ResultSet rs);

}