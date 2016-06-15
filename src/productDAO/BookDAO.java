package productDAO;

import java.util.ArrayList;

import domain.entities.Book;

/**
*
*@author diana.maftei[at]gmail.com
*/
public class BookDAO extends ProductDAO<Book>{

	@Override
	protected String getPropertiesKeyName() {
		return "Book";
	}

	@Override
	protected Book createMedia(int index) {
		Book book = new Book();
		String[] authors = readMediaAttribute("authors", index).split("/");
		ArrayList<String> authorsList = new ArrayList<>();
		for (int j = 0; j < authors.length; j++) {
			authorsList.add(authors[j]);
		}
		book.setAuthors(authorsList);		
		book.setNoOfPages(Integer.valueOf(readMediaAttribute("noOfPages", index)));
		book.setPublishingHouse(readMediaAttribute("publishingHouse", index));
		return book;
	}



}
