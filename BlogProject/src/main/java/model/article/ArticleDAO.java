package model.article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.util.DBManager;

public class ArticleDAO {
	// 싱글톤
	private ArticleDAO() {
	}

	private static ArticleDAO instance = new ArticleDAO();// 자신 객체 생성

	public static ArticleDAO getInstance() {
		return instance;
	}

	// DB관련
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 게시판 글 총수 계산
	public int articleCount() {
		// 리턴타입
		int row = 0;
		// 쿼리
		String query = "select count(*) from article";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
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

	public int articleCount(int boardId) {
		// 리턴타입
		int row = 0;
		// 쿼리
		String query = "select count(*) from article where boardId = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardId);
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

	// 게시판 글 총수 계산(검색조건포함)
	public int articleCount(String search, String key) {
		// 리턴타입
		int row = 0;
		// 쿼리
		// String query="select count(*) from article where " + search + " like '%" +
		// key + "%'";
		String query = "select count(*) from article where " + search + " like ? ";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + key + "%");
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

	public int articleCount(String search, String key, int boardId) {
		// 리턴타입
		int row = 0;
		// 쿼리
		// String query="select count(*) from article where " + search + " like '%" +
		// key + "%'";
		String query = "select count(*) from article where boardId = ? and " + search + " like ? ";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardId);
			pstmt.setString(2, "%" + key + "%");
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

	// 게시판 전체목록
	public List<ArticleDTO> articleList() {
		// 리턴타입
		List<ArticleDTO> list = new ArrayList<>();
		// 쿼리
		String query = "select * from article order by regdate desc";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			ArticleDTO article = null;
			while (rs.next()) {
				article = new ArticleDTO();
				article.setId(rs.getInt("id"));
				article.setMemberId(rs.getInt("memberId"));
				article.setMemberType(rs.getInt("memberType"));
				article.setTitle(rs.getString("title"));
				article.setBoardId(rs.getInt("boardId"));
				article.setRegDate(rs.getString("regDate"));
				article.setHitCnt(rs.getInt("hitCnt"));

				list.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	// 전체목록- 페이지처리 추가
	public List<ArticleDTO> articleList(int startpage, int endpage) {
		List<ArticleDTO> list = new ArrayList<>();// 리턴타입
		String query = "select X.* from (select rownum as rnum, A.* from "
				+ "(select * from article order by id desc) A " + " where rownum <= ?) X where X.rnum >= ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, endpage);
			pstmt.setInt(2, startpage);

			rs = pstmt.executeQuery();

			ArticleDTO article = null;
			while (rs.next()) {
				article = new ArticleDTO();
				article.setId(rs.getInt("id"));
				article.setMemberId(rs.getInt("memberId"));
				article.setMemberType(rs.getInt("memberType"));
				article.setTitle(rs.getString("title"));
				article.setBoardId(rs.getInt("boardId"));
				article.setRegDate(rs.getString("regDate"));
				article.setHitCnt(rs.getInt("hitCnt"));

				list.add(article);
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
	
	public List<ArticleDTO> articleList(int startpage, int endpage, int boardId) {
		List<ArticleDTO> list = new ArrayList<>();// 리턴타입
		String query = "select X.* from (select rownum as rnum, A.* from "
				+ "(select * from article where boardId = ? order by id desc) A " + " where rownum <= ?) X where X.rnum >= ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardId);
			pstmt.setInt(2, endpage);
			pstmt.setInt(3, startpage);

			rs = pstmt.executeQuery();

			ArticleDTO article = null;
			while (rs.next()) {
				article = new ArticleDTO();
				article.setId(rs.getInt("id"));
				article.setMemberId(rs.getInt("memberId"));
				article.setMemberType(rs.getInt("memberType"));
				article.setTitle(rs.getString("title"));
				article.setBoardId(rs.getInt("boardId"));
				article.setRegDate(rs.getString("regDate"));
				article.setHitCnt(rs.getInt("hitCnt"));

				list.add(article);
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

	// 전체목록- 검색 + 페이지처리 추가
	public List<ArticleDTO> articleList(String search, String key, int startpage, int endpage) {
		List<ArticleDTO> list = new ArrayList<>();// 리턴타입
		String query = "select X.* from (select rownum as rnum, A.* from "
				+ "(select * from article order by id desc) A " + " where " + search
				+ " like ? and rownum <= ?) X where " + search + " like ?  and X.rnum >= ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + key + "%");
			pstmt.setInt(2, endpage);
			pstmt.setString(3, "%" + key + "%");
			pstmt.setInt(4, startpage);

			rs = pstmt.executeQuery();

			ArticleDTO article = null;
			while (rs.next()) {
				article = new ArticleDTO();
				article.setId(rs.getInt("id"));
				article.setMemberId(rs.getInt("memberId"));
				article.setMemberType(rs.getInt("memberType"));
				article.setTitle(rs.getString("title"));
				article.setBoardId(rs.getInt("boardId"));
				article.setRegDate(rs.getString("regDate"));
				article.setHitCnt(rs.getInt("hitCnt"));

				list.add(article);
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

	public List<ArticleDTO> articleList(String search, String key, int startpage, int endpage, int boardId) {
		List<ArticleDTO> list = new ArrayList<>();// 리턴타입
		String query = "select X.* from (select rownum as rnum, A.* from "
				+ "(select * from article where boardId = ? order by id desc) A " + " where " + search
				+ " like ? and rownum <= ?) X where " + search + " like ?  and X.rnum >= ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardId);
			pstmt.setString(2, "%" + key + "%");
			pstmt.setInt(3, endpage);
			pstmt.setString(4, "%" + key + "%");
			pstmt.setInt(5, startpage);

			rs = pstmt.executeQuery();

			ArticleDTO article = null;
			while (rs.next()) {
				article = new ArticleDTO();
				article.setId(rs.getInt("id"));
				article.setMemberId(rs.getInt("memberId"));
				article.setMemberType(rs.getInt("memberType"));
				article.setTitle(rs.getString("title"));
				article.setBoardId(rs.getInt("boardId"));
				article.setRegDate(rs.getString("regDate"));
				article.setHitCnt(rs.getInt("hitCnt"));

				list.add(article);
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

	// 글 등록
	public int articleWrite(ArticleDTO article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 리터타입
		int row = 0;
		// query
		String sql = "insert into article(title,body,boardId,memberId,memberType) " + "values(? ,?, ?, ?, ?)";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getBody());
			pstmt.setInt(3, article.getBoardId());
			pstmt.setInt(4, article.getMemberId());
			pstmt.setInt(5, article.getMemberType());

			row = pstmt.executeUpdate();

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
		return row;
	}

	// 특정글 조회 증가
	public void articleHits(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// query
		String sql = "update article set hitcnt=hitcnt+1 where id=?";

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

	// 특정글 검색(view)
	public ArticleDTO articleSelect(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 리터타입
		ArticleDTO article = new ArticleDTO();
		// query
		String sql = "select A.*, M.nickname as writerName from article A inner join member M on a.memberid = M.id where A.id=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				article.setId(rs.getInt("id"));
				article.setMemberId(rs.getInt("memberId"));
				article.setWriterName(rs.getString("writerName"));
				article.setMemberType(rs.getInt("memberType"));
				article.setTitle(rs.getString("title"));
				article.setBoardId(rs.getInt("boardId"));
				article.setRegDate(rs.getString("regDate"));
				article.setUpdateDate(rs.getString("updateDate"));
				article.setHitCnt(rs.getInt("hitCnt"));
				article.setBody(rs.getString("body"));
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
		return article;
	}

	// 수정
	public int articleModify(ArticleDTO article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 리터타입
		int row = 0;
		// query
		String sql = "update article set title=?, body=?, updateDate=sysdate " + " where id=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getBody());
			pstmt.setInt(3, article.getId());

			row = pstmt.executeUpdate();

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
		return row;
	}

	public int articleDelete(int id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 리터타입
		int row = 0;
		// query
		String sql = "delete from article where id=?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			row = pstmt.executeUpdate();

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
		return row;
	}

	public int lastArticleId() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int articleId = 0;
		String sql = "SELECT id FROM article WHERE ROWNUM <= 1 ORDER BY regDate DESC";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				articleId = rs.getInt(1);
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
		return articleId;
	}

}
