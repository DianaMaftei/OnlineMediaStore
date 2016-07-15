package DAO.properties;

import java.util.ArrayList;

import domain.entities.CD;
import domain.entities.Track;

/**
*
*@author diana.maftei[at]gmail.com
*/
public class CdDAO extends ProductDAO<CD>{

	@Override
	protected String getPropertiesKeyName() {
		return "CD";
	}

	@Override
	protected CD createMedia(int index) {
		CD cd = new CD();	
		String[] tracks = readMediaAttribute("tracks", index).split("/");
		cd.setArtist(readMediaAttribute("artist", index));
		ArrayList<Track> trackList = new ArrayList<>();
		for (int j = 0; j < tracks.length; j++) {
			String[] trackByIndex = tracks[j].split(":");
			trackList.add(new Track(trackByIndex[0], Integer.parseInt(trackByIndex[1])));
		}
		cd.setTrackList(trackList);
		return cd;
	}



}
