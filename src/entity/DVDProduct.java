package entity;
public class DVDProduct extends Media {
	
	private String seasonNo;
	private String director;
	
	public DVDProduct(){
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

	@Override
	public String toString() {
		return "Title: " + getTitle() + "\nPrice: " + getPrice() + "\nGenre: " + getGenre() + "\nDescription: " + getDescription() + "\nSeason Number: " + seasonNo + "\nDirector: " + director + "\n";
		
		
		
	} 
	
	
		
	
}
