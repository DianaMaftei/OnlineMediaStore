package Service;

import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;

import entity.BookProduct;
import entity.CDProduct;
import entity.DVDProduct;
import entity.Genre;
import entity.Track;

public class DataFunctionProperties {
	
	Properties properties;
	StringTokenizer stk;
	
	public DataFunctionProperties(Properties properties){
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
			System.out.println(idx);
			System.out.println(dvd);
			idx++;

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
			for(int i = 0; i < tracks.length; i++){
				stk = new StringTokenizer(tracks[i], ":");
				String songName = stk.nextToken();
				int duration = Integer.parseInt(stk.nextToken());
				trackList.add(new Track(songName,duration));
			}
			cd.setTrackList(trackList);
			System.out.println(idx);
			System.out.println(cd);
			idx++;

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
			for(int i = 0; i < authors.length; i++){
				authorsList.add(authors[i]);
			}
			
			book.setAuthors(authorsList);
			book.setNoOfPages(Integer.valueOf(noOfPages));
			book.setPublishingHouse(publishingHouse);
			System.out.println(idx);
			System.out.println(book);
			idx++;

		}

	}
}
