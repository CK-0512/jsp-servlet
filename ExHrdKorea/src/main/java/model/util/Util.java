package model.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "track2_12", "1234");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
