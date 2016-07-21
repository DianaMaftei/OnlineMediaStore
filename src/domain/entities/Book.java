package domain.entities;

import java.util.List;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class Book extends Product {

	private List<String> authors;
	private int noOfPages;
	private String publishingHouse;

	public Book() {
		super();
	}

	public List<String> getAuthors() {
		return authors;
	}

	private String getAuthorsAsString() {
		String listOfAuthors = "";
		for (int i = 0; i < authors.size() - 1; i++) {
			listOfAuthors += authors.get(i) + ", ";
		}
		listOfAuthors += authors.get(authors.size() - 1);
		return listOfAuthors;
	}

	public void setAuthors(List<String> authors) {
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
		return "Title: " + getTitle() + "\nPrice: " + getPrice() + "\nGenre: " + getGenre() + "\nDescription: "
				+ getDescription() + "\nAuthors: " + getAuthorsAsString() + "\nNumber of Pages: " + noOfPages
				+ "\nPublishing House: " + publishingHouse + "\n";
	}

}
