package entity;
import java.util.ArrayList;

public class BookProduct extends Media{
	
	private ArrayList<String> authors;
	private int noOfPages;
	private String publishingHouse;
	
	public BookProduct() {
		super();
	}
	
	public BookProduct(String title, double price, Genre genre, String description, ArrayList<String> authors, int noOfPages, String publishingHouse) {
		super(title, price, genre, description);
		this.authors = authors;
		this.noOfPages = noOfPages;
		this.publishingHouse = publishingHouse;
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
		return "Title: " + getTitle() + "\nPrice: " + getPrice() + "\nGenre: " + getGenre() + "\nDescription: " + getDescription() + "\nAuthors: " + getAuthors() + "\nNumber of Pages: " + noOfPages + "\nPublishing House: " + publishingHouse + "\n" + 
				"---------------------------";
	}

	
	
	
}
