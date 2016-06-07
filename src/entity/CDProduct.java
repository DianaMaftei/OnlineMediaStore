package entity;
import java.util.ArrayList;

public class CDProduct extends Media {
	
	private String artist;
	private ArrayList<Track> trackList;

	public CDProduct() {
		super();
	}

	public CDProduct(String title, double price, Genre genre, String description, String artist, ArrayList<Track> trackList) {
		super(title, price, genre, description);
		this.artist = artist;
		this.trackList = trackList;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public ArrayList<Track> getTrackList() {
		return trackList;
	}

	public void setTrackList(ArrayList<Track> trackList) {
		this.trackList = trackList;
	}

	@Override
	public String toString() {
		return "Title: " + getTitle() + "\nPrice: " + getPrice() + "\nGenre: " + getGenre() + "\nDescription: " + getDescription() + "\nArtist: " + artist + "\nTracks: " + "sooo many tracks" + "\n" +
				"---------------------------";
	}
	
	

	
}
