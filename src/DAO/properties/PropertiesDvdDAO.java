package DAO.properties;

import java.sql.ResultSet;

import DAO.DvdDAO;
import domain.entities.CD;
import domain.entities.DVD;

/**
*
*@author diana.maftei[at]gmail.com
*/
public class PropertiesDvdDAO extends PropertiesProductDAO<DVD> implements DvdDAO{

	@Override
	public String getPropertiesKeyName() {
		return "DVD";
	}

	@Override
	public DVD createProduct(int index) {
		DVD dvd = new DVD();
		dvd.setSeasonNo(readProductAttribute("seasonNo", index));
		dvd.setDirector(readProductAttribute("director", index));
		return dvd;
	}

	@Override
	public DVD createProduct(ResultSet rs) {
		//used by DBDAO
		return null;
	}


}
