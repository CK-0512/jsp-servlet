package model.pds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.board.BoardDAO;
import model.board.BoardDTO;
import model.util.DBManager;

public class PdsDAO {
	//싱글톤
	private PdsDAO() {}
	private static PdsDAO instance = new PdsDAO();//자신 객체 생성
	public static PdsDAO getInstance() {
		return instance;
	}
	
	//DB관련 
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//게시판 글 총수 계산
	public int pdsCount() {
		//리턴타입
		int row=0;
		//쿼리
		String query="select count(*) from tbl_pds";
		
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

	public List<PdsDTO> pdsList() {
		//리턴타입
		List<PdsDTO> list = new ArrayList<>();
		//쿼리
		String query="select * from tbl_pds order by regdate desc";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			PdsDTO pds = null;
			while(rs.next()) {
				pds = new PdsDTO();
				pds.setIdx(rs.getInt("idx"));
				pds.setName(rs.getString("name"));
				pds.setEmail(rs.getString("email"));
				pds.setSubject(rs.getString("subject"));
				pds.setRegdate(rs.getString("regdate"));
				pds.setReadcnt(rs.getInt("readcnt"));
				pds.setFilename(rs.getString("filename"));
			
				list.add(pds);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	
	//글 등록
	public int pdsWrite(PdsDTO pds) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//리터타입
		int row=0;
		//query
		String sql="insert into tbl_pds(idx,name,pass,email,subject,contents,filename) "
				+ "values(tbl_pds_seq_idx.nextval, ? ,?, ?, ?, ?,?)";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pds.getName());
			pstmt.setString(2, pds.getPass());
			pstmt.setString(3, pds.getEmail());
			pstmt.setString(4, pds.getSubject());
			pstmt.setString(5, pds.getContents());
			pstmt.setString(6, pds.getFilename());
			
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
