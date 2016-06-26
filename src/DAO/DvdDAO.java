package DAO;

import domain.entities.DVD;

/**
*
*@author diana.maftei[at]gmail.com
*/
public class DvdDAO extends ProductDAO<DVD>{

	@Override
	protected String getPropertiesKeyName() {
		return "DVD";
	}

	@Override
	protected DVD createMedia(int index) {
		DVD dvd = new DVD();
		dvd.setSeasonNo(readMediaAttribute("seasonNo", index));
		dvd.setDirector(readMediaAttribute("director", index));
		return dvd;
	}


}
