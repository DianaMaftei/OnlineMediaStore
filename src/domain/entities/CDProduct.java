package domain.entities;
import java.util.ArrayList;
/**
*
*@author diana.maftei[at]gmail.com
*/
public class CDProduct extends Product implements IPlayable{
	
	private String artist;
	private ArrayList<Track> trackList;

	public CDProduct() {
		super();
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
		return "Title: " + getTitle() + "\nPrice: " + getPrice() + "\nGenre: " + getGenre() + "\nDescription: " + getDescription() + "\nArtist: " + artist + "\nTracks: " + getTrackList() + "\n";
	}

	@Override
	public void play() {
		System.out.println("CD is playing.");
	}
	
	

	
}
