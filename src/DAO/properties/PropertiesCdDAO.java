package DAO.properties;

import java.sql.ResultSet;
import java.util.ArrayList;

import DAO.CdDAO;
import domain.entities.Book;
import domain.entities.CD;
import domain.entities.Track;

/**
*
*@author diana.maftei[at]gmail.com
*/
public class PropertiesCdDAO extends PropertiesProductDAO<CD> implements CdDAO{

	@Override
	public String getPropertiesKeyName() {
		return "CD";
	}


	@Override
	public CD createProduct(int index) {
		CD cd = new CD();	
		String[] tracks = readProductAttribute("tracks", index).split("/");
		ArrayList<String> artists = new ArrayList<>();
		artists.add(readProductAttribute("artist", index));
		cd.setArtists(artists);
		ArrayList<Track> trackList = new ArrayList<>();
		for (int j = 0; j < tracks.length; j++) {
			String[] trackByIndex = tracks[j].split(":");
			trackList.add(new Track(trackByIndex[0], trackByIndex[1]));
		}
		cd.setTrackList(trackList);
		return cd;
	}


	@Override
	public CD createProduct(ResultSet rs) {
		//used by DBDAO
		return null;
	}



}
