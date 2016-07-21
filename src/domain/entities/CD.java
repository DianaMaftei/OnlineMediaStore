package domain.entities;

import java.util.List;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class CD extends Product implements IPlayable {

	private List<String> artists;
	private List<Track> trackList;

	public CD() {
		super();
	}

	public List<String> getArtists() {
		return artists;
	}

	private String getArtistsAsString() {
		String listOfArtists = "";
		for (int i = 0; i < artists.size() - 1; i++) {
			listOfArtists += artists.get(i) + ", ";
		}
		listOfArtists += artists.get(artists.size() - 1);
		return listOfArtists;
	}

	public void setArtists(List<String> artists) {
		this.artists = artists;
	}

	public List<Track> getTrackList() {
		return trackList;
	}

	public void setTrackList(List<Track> trackList) {
		this.trackList = trackList;
	}

	@Override
	public String toString() {
		return "Title: " + getTitle() + "\nPrice: " + getPrice() + "\nGenre: " + getGenre() + "\nDescription: "
				+ getDescription() + "\nArtist: " + getArtistsAsString() + "\nTracks: " + getTrackList() + "\n";
	}

	@Override
	public void play() {
		System.out.println("CD is playing.");
	}

}
