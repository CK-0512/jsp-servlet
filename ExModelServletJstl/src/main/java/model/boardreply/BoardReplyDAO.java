package model.boardreply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.util.DBManager;

public class BoardReplyDAO {
	private BoardReplyDAO() {}
	private static BoardReplyDAO instance = new BoardReplyDAO();
    
	public static BoardReplyDAO getInstance() {
		return instance;
	}

	//게시글 총수
	public int boardCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;

		String query="select count(*) from tbl_boardreply";
				
		int row=0;
		try {
			conn = DBManager.getConnection();
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			if(rs.next()){
				row=rs.getInt(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		return row;
	}
	
	//게시글 총수(검색포함)
	public int boardCount(String search, String key) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;

		String query="select count(*) from tbl_boardreply where "+ search + " like ? ";
				
		int row=0;
		try {
			conn = DBManager.getConnection();
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, "%" + key + "%");
			rs=pstmt.executeQuery();
			if(rs.next()){
				row=rs.getInt(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		return row;
	}
	
	//게시글 전체 목록(검색 X, 페이지 X)
	public List<BoardReplyDTO> boardList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		//리턴타입
		List<BoardReplyDTO> list = new ArrayList<>();
		//쿼리
		String query="select * from tbl_boardreply order by parent desc, depth asc ";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			BoardReplyDTO board = null;
			while(rs.next()) {
				board = new BoardReplyDTO();
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
				board.setEmail(rs.getString("email"));
				board.setSubject(rs.getString("subject"));
				board.setRegdate(rs.getString("regdate"));
				board.setReadcnt(rs.getInt("readcnt"));
				board.setIndent(rs.getInt("indent"));
			
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
	public List<BoardReplyDTO> boardList(String search, String key) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
	
		List<BoardReplyDTO> list = new ArrayList<>();// 리턴타입
		String query="select * from tbl_boardreply where " + search + " like ? order by parent desc, depth asc";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + key + "%");
			rs = pstmt.executeQuery();
			BoardReplyDTO board = null;
			while(rs.next()) {
				board = new BoardReplyDTO();
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
				board.setEmail(rs.getString("email"));
				board.setSubject(rs.getString("subject"));
				board.setRegdate(rs.getString("regdate"));
				board.setReadcnt(rs.getInt("readcnt"));
				board.setIndent(rs.getInt("indent"));
			
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
	
	//게시글 전체 목록(검색 X, 페이지 O)
	public List<BoardReplyDTO> boardList(int pagestart, int endpage) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		//리턴타입
		List<BoardReplyDTO> list = new ArrayList<>();
		//쿼리
		String query="select X.* from (select rownum as rnum, A.* from " + 
			     " (select * from tbl_boardreply order by parent desc, depth asc) A " + 
			     "	where rownum <= ?) X where X.rnum > ?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			BoardReplyDTO board = null;
			while(rs.next()) {
				board = new BoardReplyDTO();
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
				board.setEmail(rs.getString("email"));
				board.setSubject(rs.getString("subject"));
				board.setRegdate(rs.getString("regdate"));
				board.setReadcnt(rs.getInt("readcnt"));
				board.setIndent(rs.getInt("indent"));
			
				list.add(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	//게시글 전체 목록(검색 O, 페이지 O)
	public List<BoardReplyDTO> boardList(String search, String key, int pagestart, int endpage) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		//리턴타입
		List<BoardReplyDTO> list = new ArrayList<>();
		//쿼리
		String query="select X.* from (select rownum as rnum, A.* from " + 
				"(select * from tbl_board order by parent desc, depth asc) A " + 
				" where " + search + " like ? and rownum <= ?) X where " + search + " like ?  and X.rnum >= ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + key + "%");
			pstmt.setInt(2, endpage);
			pstmt.setString(3, "%" + key + "%");
			pstmt.setInt(4, pagestart);

			BoardReplyDTO board = null;
			while(rs.next()) {
				board = new BoardReplyDTO();
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
				board.setEmail(rs.getString("email"));
				board.setSubject(rs.getString("subject"));
				board.setRegdate(rs.getString("regdate"));
				board.setReadcnt(rs.getInt("readcnt"));
				board.setIndent(rs.getInt("indent"));
			
				list.add(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	// 뷰
	public BoardReplyDTO boardSelect(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		//리턴타입
		BoardReplyDTO board = new BoardReplyDTO();
		//쿼리
		String query="select * from tbl_boardreply where idx = ?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
				board.setEmail(rs.getString("email"));
				board.setSubject(rs.getString("subject"));
				board.setContents(rs.getString("contents"));
				board.setRegdate(rs.getString("regdate"));
				board.setReadcnt(rs.getInt("readcnt"));
				board.setParent(rs.getInt("parent"));
				board.setRealparent(rs.getInt("realparent"));
				board.setDepth(rs.getInt("depth"));
				board.setIndent(rs.getInt("indent"));
			
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return board;
	}
	
	//글등록
	public int boardWrite(BoardReplyDTO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int parent = board.getParent();
		int indent = board.getIndent();
		int depth = board.getDepth();
		int idx=0;
		
		int row=0;
		String query="select tbl_boardreply_seq_idx.nextval from dual";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if(rs.next())
				idx = rs.getInt(1);
			
			if(parent != 0) {//답변글일 경우
				query="update tbl_boardreply set depth=depth+1 where parent = ? and depth > ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, parent);
				pstmt.setInt(2, depth);
				pstmt.executeUpdate();
				
				board.setDepth(depth + 1);
				board.setIndent(indent + 1);
			}else {//새 글일 경우
				board.setParent(idx);
				depth=0;
				indent=0;
			}
			
			board.setIdx(idx);		
			query = "insert into tbl_boardreply(idx,name,email,subject,contents,pass,"
					+ "parent,realparent,depth,indent) "
					+ "values (?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, board.getIdx());
			pstmt.setString(2, board.getName());
			pstmt.setString(3, board.getEmail());
			pstmt.setString(4, board.getSubject());
			pstmt.setString(5, board.getContents());
			pstmt.setString(6, board.getPass());
			pstmt.setInt(7, board.getParent());
			pstmt.setInt(8, board.getRealparent());
			pstmt.setInt(9, board.getDepth());
			pstmt.setInt(10, board.getIndent());

			row = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}

	// 답변글 유무 체크 
	public int replySearch(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;

		String query = "select count(realparent) as sNum from tbl_boardreply where realparent = ?";
		int row=0;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		return row;
	}

	// 특정 글 삭제 처리
	public int boardDelete(int idx, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;

		String query="delete from tbl_boardreply where idx = ? and pass = ?";
		int row=0;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			pstmt.setString(2, pass);
			row = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		return row;
	}

	
}
