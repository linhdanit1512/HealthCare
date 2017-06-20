package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnect {
	public static void main(String[] args) {
		System.out.println(getConnection());
	}

	static Connection connection = null;

	public static Connection getConnection() {
		if (connection == null) {
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String url = "jdbc:sqlserver://healthcare21617.database.windows.net:1433;database=healthcare";
			String user = "healthcare@healthcare21617";
			String pass = "Cnpm123456";
//			String url="9366.us-imm-sql2.000webhost.io:3306/database=id1982529_healthcare";
//			String user = "id1982529_root";
//			String pass = "123456";
			try {
				Class.forName(driver);
				connection = DriverManager.getConnection(url, user, pass);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	public static void close() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
