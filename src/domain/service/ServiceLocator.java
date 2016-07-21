package domain.service;

import DAO.BookDAO;
import DAO.CdDAO;
import DAO.CustomerDAO;
import DAO.DvdDAO;
import DAO.database.DBBookDAO;
import DAO.database.DBCdDAO;
import DAO.database.DBDvdDAO;
import DAO.properties.PropertiesBookDAO;
import DAO.properties.PropertiesCdDAO;
import DAO.properties.PropertiesCustomerDAO;
import DAO.properties.PropertiesDvdDAO;

/**
*
*@author diana.maftei[at]gmail.com
*/
public class ServiceLocator {
	
	private static final Persistence persistance = Persistence.PROPERTIES;
	
	public static CustomerDAO getCustomerDAO() {
		if(persistance == Persistence.PROPERTIES) {
			return new PropertiesCustomerDAO();
		}else if(persistance == Persistence.SERIALIZATION){
			return null;
		}
		return null;
	}
	
	public static CdDAO getCdDAO(){
		if(persistance == Persistence.PROPERTIES) {
			return new PropertiesCdDAO();
		}else if(persistance == Persistence.SERIALIZATION){
			return null;
		}
		return new DBCdDAO();
	}
	
	public static DvdDAO getDvdDAO(){
		if(persistance == Persistence.PROPERTIES) {
			return new PropertiesDvdDAO();
		}else if(persistance == Persistence.SERIALIZATION){
			return null;
		}
		return new DBDvdDAO();
	}
	
	public static BookDAO getBookDAO(){
		if(persistance == Persistence.PROPERTIES) {
			return new PropertiesBookDAO();
		}else if(persistance == Persistence.SERIALIZATION){
			return null;
		}
		return new DBBookDAO();
	}
	
		
	private enum Persistence{
		PROPERTIES, SERIALIZATION, DATABASE		
	} 

}
