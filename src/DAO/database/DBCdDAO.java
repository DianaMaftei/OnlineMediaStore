package DAO.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.CdDAO;
import domain.entities.CD;
import domain.entities.Track;

/**
*
*@author diana.maftei[at]gmail.com
*/
public class DBCdDAO extends DBProductDAO<CD> implements CdDAO{

	@Override
	public String getPropertiesKeyName() {
		return "CD";
	}

	@Override
	public CD createProduct(ResultSet rs) {
		CD cd = new CD();
		try {
			String type = rs.getString("type");
			if ("CD".equals(type)) {
				int id = rs.getInt("product_id");
				List<String> artists = getArtists(id);
				cd.setArtists(artists);
				List<Track> trackList = getTracks(id);
				cd.setTrackList(trackList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cd;
	}

	@Override
	public CD createProduct(int index) {
		//used by PropertiesDAO
		return null;
	}
	
	private List<String> getArtists(int id){
		List<String> artistsNames = new ArrayList();
		Connection conn = DBUtil.getConnection();
		String allArtists = String.format("select name from people ppl JOIN product_people pp ON ppl.person_id = pp.id_people where pp.id_product = %d;", id);
		try {
			PreparedStatement ps = conn.prepareStatement(allArtists);
			ResultSet rs = ps.executeQuery(); 
			while(rs.next()){
				String name = rs.getString("name");
				artistsNames.add(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		};

		return artistsNames;
	}
	
	private List<Track> getTracks(int id){
		List<Track> tracksNames = new ArrayList();
		Connection conn = DBUtil.getConnection();
		String allTracks = String.format("select track_name, track_duration from tracks where id_track = %d;", id);
		try {
			PreparedStatement ps = conn.prepareStatement(allTracks);
			ResultSet rs = ps.executeQuery(); 
			while(rs.next()){
				Track track = new Track();
				String trackName = rs.getString("track_name");
				track.setTitle(trackName);
				String duration = rs.getString("track_duration");
				track.setDuration(duration);
				tracksNames.add(track);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		};

		return tracksNames;
	}

}
