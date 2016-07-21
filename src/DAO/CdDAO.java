package DAO;

import java.sql.ResultSet;

import domain.entities.CD;

/**
*
*@author diana.maftei[at]gmail.com
*/
public interface CdDAO extends ProductDAO<CD>{

	String getPropertiesKeyName();

	CD createProduct(int index);
	CD createProduct(ResultSet rs);

}