package model.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.util.DBManager;

public class ReplyDAO {
	// 싱글톤
	private ReplyDAO() {
	}

	private static ReplyDAO instance = new ReplyDAO();// 자신 객체 생성

	public static ReplyDAO getInstance() {
		return instance;
	}

	// DB관련
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public List<ReplyDTO> getReplies(int id) {
		// 리턴타입
		List<ReplyDTO> list = new ArrayList<>();
		// 쿼리
		String query = "select R.*, M.nickname as writerName from reply R inner join member M on R.memberId = M.id where r.articleId = ? order by r.regdate desc";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			ReplyDTO reply = null;
			while (rs.next()) {
				reply = new ReplyDTO();
				reply.setId(rs.getInt("id"));
				reply.setMemberId(rs.getInt("memberId"));
				reply.setMemberType(rs.getInt("memberType"));
				reply.setWriterName(rs.getString("writerName"));
				reply.setBody(rs.getString("body"));
				reply.setRegDate(rs.getString("regDate"));
				reply.setUpdateDate(rs.getString("updateDate"));

				list.add(reply);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	public int replyWrite(ReplyDTO reply) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 리터타입
		int row = 0;
		// query
		String sql = "insert into reply(memberId,body,articleId,memberType) values(?, ?, ?, ?)";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(2, reply.getBody());
			pstmt.setInt(3, reply.getArticleId());
			pstmt.setInt(1, reply.getMemberId());
			pstmt.setInt(4, reply.getMemberType());

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

	public ReplyDTO getReplyById(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 리터타입
		ReplyDTO reply = new ReplyDTO();
		// query
		String sql = "select R.*, M.nickname as writerName from reply R inner join member M on R.memberId = M.id where r.id = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				reply.setId(rs.getInt("id"));
				reply.setMemberId(rs.getInt("memberId"));
				reply.setMemberType(rs.getInt("memberType"));
				reply.setWriterName(rs.getString("writerName"));
				reply.setRegDate(rs.getString("regDate"));
				reply.setUpdateDate(rs.getString("updateDate"));
				reply.setBody(rs.getString("body"));
				reply.setArticleId(rs.getInt("articleId"));
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
		return reply;
	}

	public int replyModify(int id, String body) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// 리터타입
		int row = 0;
		// query
		String sql = "update reply set body = ? where id = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(2, id);
			pstmt.setString(1, body);

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

	public int replyDelete(int id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		// 리터타입
		int row = 0;
		// query
		String sql = "delete from reply where id=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

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

}
