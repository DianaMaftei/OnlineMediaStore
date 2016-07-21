package domain.entities;

import java.util.List;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class DVD extends Product implements IPlayable {

	private String seasonNo;
	private String director;
	private List<String> actors;

	public DVD() {
		super();
	}

	public String getSeasonNo() {
		return seasonNo;
	}

	public void setSeasonNo(String seasonNo) {
		this.seasonNo = seasonNo;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public List<String> getActors() {
		return actors;
	}

	private String getAuthorsAsString() {
		String listOfActors = "";
		for (int i = 0; i < actors.size() - 1; i++) {
			listOfActors += actors.get(i) + ", ";
		}
		listOfActors += actors.get(actors.size() - 1);
		return listOfActors;
	}

	public void setActors(List<String> actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "Title: " + getTitle() + "\nPrice: " + getPrice() + "\nGenre: " + getGenre() + "\nActors: "
				+ getAuthorsAsString() + "\nDescription: " + getDescription() + "\nSeason Number: " + seasonNo
				+ "\nDirector: " + director + "\n";
	}

	@Override
	public void play() {
		System.out.println("DVD is playing.");
	}

}
