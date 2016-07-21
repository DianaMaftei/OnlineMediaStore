package domain.entities;

/**
*
*@author diana.maftei[at]gmail.com
*/
public class Track implements IPlayable{
	String title;
	String duration;
	
	public Track(){
		
	}
	
	public Track(String title, String duration) {
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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Title: " + title + ", duration: " + duration;
	}

	@Override
	public void play() {
		System.out.println("Track is playing.");
		
	}
	
	
}
