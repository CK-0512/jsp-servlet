package model.boardeditor;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.util.DBManager;

public class BoardEditorDAO {
	//싱글톤
	private BoardEditorDAO() {}
	private static BoardEditorDAO instance = new BoardEditorDAO();//자신 객체 생성
	public static BoardEditorDAO getInstance() {
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
		String query="select count(*) from tbl_gallery2";
		
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
		String query="select count(*) from tbl_gallery2 where " + search + " like ? ";
		
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
	public List<BoardEditorDTO> boardList() {
		//리턴타입
		List<BoardEditorDTO> list = new ArrayList<>();
		//쿼리
		String query="select * from tbl_gallery2 order by regdate desc";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			BoardEditorDTO board = null;
			while(rs.next()) {
				board = new BoardEditorDTO();
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
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
	public List<BoardEditorDTO> boardList(String search, String key) {
		List<BoardEditorDTO> list = new ArrayList<>();// 리턴타입
		String query="select * from tbl_gallery2 where " + search + " like ? order by idx desc";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + key + "%");
			rs = pstmt.executeQuery();
			BoardEditorDTO board = null;
			while(rs.next()) {
				board = new BoardEditorDTO();
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
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
	public List<BoardEditorDTO> boardList(int startpage, int endpage) {
		List<BoardEditorDTO> list = new ArrayList<>();// 리턴타입
		String query="select X.* from (select rownum as rnum, A.* from " + 
				"(select * from tbl_gallery2 order by idx desc) A " + 
				" where rownum <= ?) X where X.rnum >= ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, endpage);
			pstmt.setInt(2, startpage);
			
			rs = pstmt.executeQuery();
			
			BoardEditorDTO board = null;
			while(rs.next()) {
				board = new BoardEditorDTO();
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
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
	public List<BoardEditorDTO> boardList(String search, String key, int startpage, int endpage) {
		List<BoardEditorDTO> list = new ArrayList<>();// 리턴타입
		String query="select X.* from (select rownum as rnum, A.* from " + 
				"(select * from tbl_gallery2 order by idx desc) A " + 
				" where " + search + " like ? and rownum <= ?) X where " + search + " like ?  and X.rnum >= ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + key + "%");
			pstmt.setInt(2, endpage);
			pstmt.setString(3, "%" + key + "%");
			pstmt.setInt(4, startpage);
			
			rs = pstmt.executeQuery();
			
			BoardEditorDTO board = null;
			while(rs.next()) {
				board = new BoardEditorDTO();
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
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
	public int boardWrite(BoardEditorDTO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//리터타입
		int row=0;
		//query
		String sql="insert into tbl_gallery2(idx,name,pass,subject,contents) "
				+ "values(tbl_board_seq_idx.nextval, ? ,?, ?, ?)";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getName());
			pstmt.setString(2, board.getPass());
			pstmt.setString(3, board.getSubject());
			
			StringReader sr = new StringReader(board.getContents());
		    pstmt.setCharacterStream(4, sr, board.getContents().length());

			
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
		String sql="update tbl_gallery2 set readcnt=readcnt+1 where idx=?";
		
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
	public BoardEditorDTO boardSelect(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//리터타입
		BoardEditorDTO board = new BoardEditorDTO();
		//query
		String sql="select * from tbl_gallery2 where idx=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
				board.setPass(rs.getString("pass"));
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
	public int boardModify(BoardEditorDTO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//리터타입
		int row = 0;
		//query
		String sql="update tbl_gallery2 set subject=?, contents=? "
				+ " where idx=? and pass=?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getSubject());
			
			StringReader sr = new StringReader(board.getContents());
		    pstmt.setCharacterStream(2, sr, board.getContents().length());

			//pstmt.setString(3, board.getContents());
			pstmt.setInt(3, board.getIdx());
			pstmt.setString(4, board.getPass());

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
		String sql="delete from tbl_gallery2 where idx=? and pass=?";
		
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
