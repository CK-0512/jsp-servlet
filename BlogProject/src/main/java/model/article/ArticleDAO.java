package model.article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.util.DBManager;

public class ArticleDAO {
	//싱글톤
	private ArticleDAO() {}
	private static ArticleDAO instance = new ArticleDAO();//자신 객체 생성
	public static ArticleDAO getInstance() {
		return instance;
	}
	
	//DB관련 
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//게시판 글 총수 계산
	public int boardCount() {
		//리턴타입
		int row=0;
		//쿼리
		String query="select count(*) from tbl_board";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}

	//게시판 글 총수 계산(검색조건포함)
	public int boardCount(String search, String key) {
		//리턴타입
		int row=0;
		//쿼리
		//String query="select count(*) from tbl_board where " + search + " like '%" + key + "%'";
		String query="select count(*) from tbl_board where " + search + " like ? ";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + key + "%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}

	//게시판 전체목록
	public List<ArticleDTO> boardList() {
		//리턴타입
		List<ArticleDTO> list = new ArrayList<>();
		//쿼리
		String query="select * from tbl_board order by regdate desc";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			ArticleDTO board = null;
			while(rs.next()) {
				board = new ArticleDTO();
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
				board.setEmail(rs.getString("email"));
				board.setSubject(rs.getString("subject"));
				board.setRegdate(rs.getString("regdate"));
				board.setReadcnt(rs.getInt("readcnt"));
			
				list.add(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	// 전체목록- 검색기능추가
	public List<ArticleDTO> boardList(String search, String key) {
		List<ArticleDTO> list = new ArrayList<>();// 리턴타입
		String query="select * from tbl_board where " + search + " like ? order by idx desc";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + key + "%");
			rs = pstmt.executeQuery();
			ArticleDTO board = null;
			while(rs.next()) {
				board = new ArticleDTO();
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
				board.setEmail(rs.getString("email"));
				board.setSubject(rs.getString("subject"));
				board.setRegdate(rs.getString("regdate"));
				board.setReadcnt(rs.getInt("readcnt"));
			
				list.add(board);
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 전체목록- 페이지처리 추가
	public List<ArticleDTO> boardList(int startpage, int endpage) {
		List<ArticleDTO> list = new ArrayList<>();// 리턴타입
		String query="select X.* from (select rownum as rnum, A.* from " + 
				"(select * from tbl_board order by idx desc) A " + 
				" where rownum <= ?) X where X.rnum >= ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, endpage);
			pstmt.setInt(2, startpage);
			
			rs = pstmt.executeQuery();
			
			ArticleDTO board = null;
			while(rs.next()) {
				board = new ArticleDTO();
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
				board.setEmail(rs.getString("email"));
				board.setSubject(rs.getString("subject"));
				board.setRegdate(rs.getString("regdate"));
				board.setReadcnt(rs.getInt("readcnt"));
			
				list.add(board);
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 전체목록- 검색 + 페이지처리 추가
	public List<ArticleDTO> boardList(String search, String key, int startpage, int endpage) {
		List<ArticleDTO> list = new ArrayList<>();// 리턴타입
		String query="select X.* from (select rownum as rnum, A.* from " + 
				"(select * from tbl_board order by idx desc) A " + 
				" where " + search + " like ? and rownum <= ?) X where " + search + " like ?  and X.rnum >= ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + key + "%");
			pstmt.setInt(2, endpage);
			pstmt.setString(3, "%" + key + "%");
			pstmt.setInt(4, startpage);
			
			rs = pstmt.executeQuery();
			
			ArticleDTO board = null;
			while(rs.next()) {
				board = new ArticleDTO();
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
				board.setEmail(rs.getString("email"));
				board.setSubject(rs.getString("subject"));
				board.setRegdate(rs.getString("regdate"));
				board.setReadcnt(rs.getInt("readcnt"));
			
				list.add(board);
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//글 등록
	public int boardWrite(ArticleDTO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//리터타입
		int row=0;
		//query
		String sql="insert into tbl_board(idx,name,pass,email,subject,contents) "
				+ "values(tbl_board_seq_idx.nextval, ? ,?, ?, ?, ?)";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getName());
			pstmt.setString(2, board.getPass());
			pstmt.setString(3, board.getEmail());
			pstmt.setString(4, board.getSubject());
			pstmt.setString(5, board.getContents());
			
			row= pstmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			}catch(Exception e){}
		}
		return row;
	}

	//특정글 조회 증가
	public void boardHits(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//리터타입
		int row=0;
		//query
		String sql="update tbl_board set readcnt=readcnt+1 where idx=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			pstmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			}catch(Exception e){}
		}
		//return row;
	}


	// 특정글 검색(view)
	public ArticleDTO boardSelect(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//리터타입
		ArticleDTO board = new ArticleDTO();
		//query
		String sql="select * from tbl_board where idx=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
				board.setPass(rs.getString("pass"));
				board.setEmail(rs.getString("email"));
				board.setSubject(rs.getString("subject"));
				board.setContents(rs.getString("contents"));
				board.setRegdate(rs.getString("regdate"));
				board.setReadcnt(rs.getInt("readcnt"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			}catch(Exception e){}
		}
		return board;
	}
	
	//수정
	public int boardModify(ArticleDTO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//리터타입
		int row = 0;
		//query
		String sql="update tbl_board set email=?, subject=?, contents=? "
				+ " where idx=? and pass=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getEmail());
			pstmt.setString(2, board.getSubject());
			pstmt.setString(3, board.getContents());
			pstmt.setInt(4, board.getIdx());
			pstmt.setString(5, board.getPass());

			row= pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			}catch(Exception e){}
		}
		return row;
	}
	
	public int boardDelete(int idx, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//리터타입
		int row=0;
		//query
		String sql="delete from tbl_board where idx=? and pass=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, pass);
			
			row= pstmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();				
			}catch(Exception e){}
		}
		return row;
	}
	
}
