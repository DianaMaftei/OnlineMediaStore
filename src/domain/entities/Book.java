package domain.entities;
import java.util.ArrayList;
/**
*
*@author diana.maftei[at]gmail.com
*/
public class Book extends Product{
	
	private ArrayList<String> authors;
	private int noOfPages;
	private String publishingHouse;
	
	public Book() {
		super();
	}
	
	public ArrayList<String> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<String> authors) {
		this.authors = authors;
	}

	public int getNoOfPages() {
		return noOfPages;
	}

	public void setNoOfPages(int noOfPages) {
		this.noOfPages = noOfPages;
	}

	public String getPublishingHouse() {
		return publishingHouse;
	}

	public void setPublishingHouse(String publishingHouse) {
		this.publishingHouse = publishingHouse;
	}

	@Override
	public String toString() {
		return "Title: " + getTitle() + "\nPrice: " + getPrice() + "\nGenre: " + getGenre() + "\nDescription: " + getDescription() + "\nAuthors: " + authors + "\nNumber of Pages: " + noOfPages + "\nPublishing House: " + publishingHouse + "\n";
	}

	
	
	
}
