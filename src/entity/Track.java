package entity;
/**
*
*@author Diana Maftei
*/
public class Track {
	String title;
	int duration;
	
	public Track(String title, int duration) {
		super();
		this.title = title;
		this.duration = duration;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Title: " + title + ", duration: " + duration;
	}
	
	
}
