package DAO.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class DBUtilThreadLocal {

	private static ThreadLocal<Connection> myConnection = new ThreadLocal();

	public static void startConnection() {

		try {
			myConnection.set(DriverManager.getConnection("jdbc:mysql://localhost:3306/media_store", "root", "1234"));
			myConnection.get().setAutoCommit(false);

		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to database.", e);
		}
	}

	public static Connection getConnection() {
		return myConnection.get();
	}

	public static void commit() {
		try {
			myConnection.get().commit();
			myConnection.remove();
		} catch (SQLException e) {
			throw new RuntimeException("Cannot commit to database.", e);
		}
	}

	public static void rollback() {
		try {
			myConnection.get().rollback();
			myConnection.remove();
		} catch (SQLException e) {
			throw new RuntimeException("Cannot rollback the changes.", e);
		}
	}

}
