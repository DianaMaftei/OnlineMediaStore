package entity;

public abstract class Media {
	private String title;
	private double price;
	private Genre genre;
	private String description;
	
	public Media(){
		
	}
	
	public Media(String title, double price, Genre genre, String description) {
		this.title = title;
		this.price = price;
		this.genre = genre;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Title: " + title + ", Price: " + price + "]";
	}
	
}
