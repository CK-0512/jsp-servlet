package model.blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.util.DBManager;

public class BlogDAO {
	// 싱글톤
	private BlogDAO() {
	}

	private static BlogDAO instance = new BlogDAO();// 자신 객체 생성

	public static BlogDAO getInstance() {
		return instance;
	}

	// DB관련
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public BlogDTO getBlogInformation() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 리터타입
		BlogDTO blog = new BlogDTO();
		// query
		String sql = "select * from blogInformation";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				blog.setRegDate(rs.getString("regDate"));
				blog.setUpdateDate(rs.getString("updateDate"));
				blog.setIntroduction(rs.getString("introduction"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return blog;
	}
	public int blogModify(String introduction) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 리터타입
		int row = 0;
		// query
		String sql = "update blogInformation set introduction=?, updateDate=sysdate";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, introduction);

			row = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return row;
	}
	
	public void updateBlog() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		// query
		String sql = "update article set updateDate=sysdate";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			row = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
	}
}
