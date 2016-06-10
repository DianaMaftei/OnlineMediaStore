package Service;

import java.util.ArrayList;
import java.util.Properties;
import entity.BookProduct;
import entity.CDProduct;
import entity.DVDProduct;
import entity.Genre;
import entity.Track;
/**
*
*@author Diana Maftei
*/
public class DataFunctionProperties {
	
	public static ArrayList<DVDProduct> dvds = new ArrayList<>();
	public static ArrayList<CDProduct> cds = new ArrayList<>();
	public static ArrayList<BookProduct> books = new ArrayList<>();

	Properties properties;

	public DataFunctionProperties(Properties properties) {
		this.properties = properties;
	}

	public void getAllDVDs() {
		int idx = 1;
		while (true) {
			String title = properties.getProperty("dvd" + idx + ".title");
			String price = properties.getProperty("dvd" + idx + ".price");
			String genre = properties.getProperty("dvd" + idx + ".genre");
			String description = properties.getProperty("dvd" + idx + ".description");
			String seasonNo = properties.getProperty("dvd" + idx + ".seasonNo");
			String director = properties.getProperty("dvd" + idx + ".director");

			if (title == null) {
				break;
			}

			DVDProduct dvd = new DVDProduct();
			dvd.setTitle(title);
			dvd.setPrice(Double.valueOf(price));
			dvd.setGenre(Genre.valueOf(genre));
			dvd.setDescription(description);
			dvd.setSeasonNo(seasonNo);
			dvd.setDirector(director);
			idx++;
			dvds.add(dvd);
		}

	}

	public void getAllCDs() {
		int idx = 1;
		while (true) {
			String title = properties.getProperty("cd" + idx + ".title");

			if (title == null) {
				break;
			}

			String price = properties.getProperty("cd" + idx + ".price");
			String genre = properties.getProperty("cd" + idx + ".genre");
			String description = properties.getProperty("cd" + idx + ".description");
			String artist = properties.getProperty("cd" + idx + ".artist");
			String[] tracks = properties.getProperty("cd" + idx + ".tracks").split("/");

			CDProduct cd = new CDProduct();
			cd.setTitle(title);
			cd.setPrice(Double.valueOf(price));
			cd.setGenre(Genre.valueOf(genre));
			cd.setDescription(description);
			cd.setArtist(artist);

			ArrayList<Track> trackList = new ArrayList<>();
			// populate the trackList with new Tracks parsed from the file,
			// delimited by :
			for (int i = 0; i < tracks.length; i++) {
				String[] trackByIndex = tracks[i].split(":");
				trackList.add(new Track(trackByIndex[0], Integer.parseInt(trackByIndex[1])));
			}
			cd.setTrackList(trackList);
			idx++;
			cds.add(cd);
		}

	}

	public void getAllBooks() {
		int idx = 1;
		while (true) {
			String title = properties.getProperty("book" + idx + ".title");

			if (title == null) {
				break;
			}

			String price = properties.getProperty("book" + idx + ".price");
			String genre = properties.getProperty("book" + idx + ".genre");
			String description = properties.getProperty("book" + idx + ".description");
			String[] authors = properties.getProperty("book" + idx + ".authors").split("/");
			String noOfPages = properties.getProperty("book" + idx + ".noOfPages");
			String publishingHouse = properties.getProperty("book" + idx + ".publishingHouse");

			BookProduct book = new BookProduct();
			book.setTitle(title);
			book.setPrice(Double.valueOf(price));
			book.setGenre(Genre.valueOf(genre));
			book.setDescription(description);

			ArrayList<String> authorsList = new ArrayList<>();
			// populate the authorsList with names of authors parsed from the
			// file
			for (int i = 0; i < authors.length; i++) {
				authorsList.add(authors[i]);
			}
			book.setAuthors(authorsList);
			book.setNoOfPages(Integer.valueOf(noOfPages));
			book.setPublishingHouse(publishingHouse);
			idx++;
			books.add(book);
		}

	}
}
