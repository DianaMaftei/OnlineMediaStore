package DAO.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author diana.maftei[at]gmail.com
 */
public class DBUtil {

	private static Connection connection;

	public static void createConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/media_store", "root", "1234");
		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect to database.", e);
		}
	}

	public static Connection getConnection() {
		return connection;
	}

	public static void commit() {
		try {
			connection.commit();
			connection = null;
		} catch (SQLException e) {
			throw new RuntimeException("Cannot commit to database.", e);
		}
	}

	public static void rollback() {
		try {
			connection.rollback();
			connection = null;
		} catch (SQLException e) {
			throw new RuntimeException("Cannot rollback the changes.", e);
		}
	}

}
