package domain.service;

import DAO.ProductDAO;
import domain.entities.Book;
import domain.entities.CD;
import domain.entities.DVD;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class Stock {

	public static void viewAllProducts() {
		viewAllCds();
		viewAllDvds();
		viewAllBooks();
	}

	public static void viewAllCds() {
		ProductDAO<CD> cDAO = ServiceLocator.getCdDAO();
		for(CD cd : cDAO.getAllProducts()){
			System.out.println(cd);
		}
	}

	public static void viewAllDvds() {
		ProductDAO<DVD> dDAO = ServiceLocator.getDvdDAO();
		for(DVD dvd : dDAO.getAllProducts()){
			System.out.println(dvd);
		}
	}

	public static void viewAllBooks() {
		ProductDAO<Book> bDAO = ServiceLocator.getBookDAO();
		for(Book book : bDAO.getAllProducts()){
			System.out.println(book);
		}
	}

}
