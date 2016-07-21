package DAO;

import java.util.List;

import domain.entities.Product;

/**
*
*@author diana.maftei[at]gmail.com
*/
public interface ProductDAO<T extends Product> {

	List<T> getAllProducts();

}