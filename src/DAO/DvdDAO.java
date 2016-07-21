package DAO;

import java.sql.ResultSet;

import domain.entities.DVD;

/**
*
*@author diana.maftei[at]gmail.com
*/
public interface DvdDAO extends ProductDAO<DVD>{

	String getPropertiesKeyName();

	DVD createProduct(ResultSet rs);
	DVD createProduct(int index);

}