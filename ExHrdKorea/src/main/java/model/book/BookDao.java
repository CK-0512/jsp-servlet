package model.book;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BookDao {
	private BookDao() {};
	private static BookDao instance = new BookDao();
	public static BookDao getInstance() {
		return instance;
	}
	
	public void doRegist(BookDto book) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String quert = ""
	}
}
