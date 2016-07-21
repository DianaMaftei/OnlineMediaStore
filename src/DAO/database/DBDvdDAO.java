package DAO.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.DvdDAO;
import domain.entities.DVD;

/**
*
*@author diana.maftei[at]gmail.com
*/
public class DBDvdDAO extends DBProductDAO<DVD> implements DvdDAO{

	@Override
	public String getPropertiesKeyName() {
		return "DVD";
	}

	@Override
	public DVD createProduct(ResultSet rs) {
		DVD dvd = new DVD();
		try {
			String type = rs.getString("type");
			if ("DVD".equals(type)) {				
				String seasonNo = rs.getString("season_no");
				String director = rs.getString("director");
				int id = rs.getInt("product_id");
				List<String> actors = getActors(id);
				dvd.setActors(actors);
				dvd.setSeasonNo(seasonNo);
				dvd.setDirector(director);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dvd;
	}

	@Override
	public DVD createProduct(int index) {
		//used by PropertiesDAO
		return null;
	}
	
	private List<String> getActors(int id){
		List<String> actorsNames = new ArrayList();
		Connection conn = DBUtil.getConnection();
		String allActors = String.format("select name from people ppl JOIN product_people pp ON ppl.person_id = pp.id_people where pp.id_product = %d;", id);
		try {
			PreparedStatement ps = conn.prepareStatement(allActors);
			ResultSet rs = ps.executeQuery(); 
			while(rs.next()){
				String name = rs.getString("name");
				actorsNames.add(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		};

		return actorsNames;
	}

}
