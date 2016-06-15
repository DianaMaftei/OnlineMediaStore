package domain.entities;

/**
*
*@author diana.maftei[at]gmail.com
*/
public class Track implements IPlayable{
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

	@Override
	public void play() {
		System.out.println("Track is playing.");
		
	}
	
	
}
