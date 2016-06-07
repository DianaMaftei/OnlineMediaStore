package entity;
public class DVDProduct extends Media {
	
	private String seasonNo;
	private String director;
	
	public DVDProduct(){
		super();
	}

	public DVDProduct(String title, double price, Genre genre, String description, String seasonNo, String director) {
		super(title, price, genre, description);
		this.seasonNo = seasonNo;
		this.director = director;
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
		return "Title: " + getTitle() + "\nPrice: " + getPrice() + "\nGenre: " + getGenre() + "\nDescription: " + getDescription() + "\nSeason Number: " + seasonNo + "\nDirector: " + director + "\n" +
	"---------------------------";
		
		
		
	} 
	
	
		
	
}
