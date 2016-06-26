package domain.entities;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public abstract class Product {
	private String title;
	private double price;
	private Genre genre;
	private String description;

	public Product() {

	}

	public Product(String title, double price, Genre genre, String description) {
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
		return "Title: " + title + ", Price: " + price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Product))
			return false;
		if (((Product) obj).getTitle().equals(this.getTitle())) {
			if ((((Product) obj).getDescription().equals(this.getDescription()))) {
				if (((Product) obj).getPrice() == (this.getPrice())) {
					return true;
				}
			}
		}
		return false;
	}


}
