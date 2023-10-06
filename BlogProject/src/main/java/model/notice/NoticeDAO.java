package model.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.article.ArticleDTO;
import model.util.DBManager;

public class NoticeDAO {
	private NoticeDAO() {
	}

	private static NoticeDAO instance = new NoticeDAO();

	public static NoticeDAO getInstance() {
		return instance;
	}

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void sendNotice(NoticeDTO notice) {

		String query = "insert into notice (type, articleId, memberId, url) values (?, ?, ?, ?)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, notice.getType());
			pstmt.setInt(2, notice.getArticleId());
			pstmt.setInt(3, notice.getMemberId());
			pstmt.setString(4, notice.getUrl());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public int noticeCount(int loginedMemberId) {
		// 리턴타입
		int row = 0;
		// 쿼리
		String query = "select count(*) from notice where memberId = ? and checkStatus = 0";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, loginedMemberId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				row = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}

	public List<NoticeDTO> noticeList(int startpage, int endpage, int loginedMemberId) {
		List<NoticeDTO> list = new ArrayList<>();// 리턴타입
		String query = "select X.* from (select rownum as rnum, A.* from "
				+ "(select * from notice where memberId = ? order by id desc) A " + " where rownum <= ?) X where X.rnum >= ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, loginedMemberId);
			pstmt.setInt(2, endpage);
			pstmt.setInt(3, startpage);

			rs = pstmt.executeQuery();

			NoticeDTO notice = null;
			while (rs.next()) {
				notice = new NoticeDTO();
				notice.setArticleId(rs.getInt("articleId"));
				notice.setCheckStatus(rs.getInt("checkStatus"));
				notice.setId(rs.getInt("id"));
				notice.setMemberId(rs.getInt("memberId"));
				notice.setRegDate(rs.getString("regDate"));
				notice.setType(rs.getInt("type"));
				notice.setUpdateDate(rs.getString("updateDate"));
				notice.setUrl(rs.getString("url"));

				list.add(notice);
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
				e.printStackTrace();
			}
		}
		return list;
	}

	public NoticeDTO noticeSelect(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 리터타입
		NoticeDTO notice = new NoticeDTO();
		// query
		String sql = "select * from notice where id=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				notice.setArticleId(rs.getInt("articleId"));
				notice.setCheckStatus(rs.getInt("checkStatus"));
				notice.setId(rs.getInt("id"));
				notice.setMemberId(rs.getInt("memberId"));
				notice.setRegDate(rs.getString("regDate"));
				notice.setType(rs.getInt("type"));
				notice.setUpdateDate(rs.getString("updateDate"));
				notice.setUrl(rs.getString("url"));
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
		return notice;
	}

	public void readNotice(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// query
		String sql = "update notice set checkStatus = 1 where id = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			pstmt.executeUpdate();

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
		// return row;
	}

}
