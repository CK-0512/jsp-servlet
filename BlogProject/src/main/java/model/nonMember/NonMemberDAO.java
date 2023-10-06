package model.nonMember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.member.MemberDTO;
import model.member.MemberDTO;
import model.util.DBManager;

public class NonMemberDAO {
	private NonMemberDAO() {
	}

	private static NonMemberDAO instance = new NonMemberDAO();

	public static NonMemberDAO getInstance() {
		return instance;
	}

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void insertNon(NonMemberDTO non) {
		
		conn = null;
		pstmt = null;
		String query = "insert into non_member(nickname,pass,type,articleId) values (?, ?, ?, ?)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, non.getNickname());
			pstmt.setString(2, non.getPass());
			pstmt.setInt(3, non.getType());
			pstmt.setInt(4, non.getArticleId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public NonMemberDTO NonMemberSelect(int articleId) {

		conn = null;
		pstmt = null;
		rs = null;
		NonMemberDTO nonMember = new NonMemberDTO();
		String query = "select N.* from non_member N inner join article A on N.articleId = A.id where A.id = ? and N.type = 1";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				nonMember.setArticleId(articleId);
				nonMember.setId(rs.getInt("id"));
				nonMember.setNickname(rs.getString("nickname"));
				nonMember.setPass(rs.getString("pass"));
				nonMember.setRegDate(rs.getString("regDate"));
				nonMember.setType(rs.getInt("type"));
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return nonMember;
	}

	public void nonMemberModify(int articleId, String nickname) {

		conn = null;
		pstmt = null;
		String query = "update non_member set nickname = ? where articleId = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleId);
			pstmt.setString(2, nickname);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
}
