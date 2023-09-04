package model.guest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
//	public static Connection getConnection() {
//		Connection conn = null;
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "track2_12", "1234");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return conn;
//	}
	
	private static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "track2_12", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// 메소드 정의
	public void dbTest() {
		Connection conn = getConnection();
		System.out.println("Conn : " + conn);
	}
	
	// 전체 게시글 카운트 메소드
	public int guestCount() {
		//Connection conn = null;
		//PreparedStatement pstmt = null;
		//ResultSet rs = null;
		int row = 0;// 리턴타입
		String query = "select count(*) from tbl_guest";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				row = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	// 전체 게시글 검색 반환(전체 목록)
	public List<GuestDTO> guestList(int pageStart, int pageEnd) {
		//Connection conn = null;
		//PreparedStatement pstmt = null;
		//ResultSet rs = null;
		List<GuestDTO> list = new ArrayList<>();// 리턴타입
		String query = "select idx, subject, name, regdate, readcnt"
				+ " from (select rownum rm, idx, subject, name, regdate, readcnt from tbl_guest where rownum <= ? order by idx desc)"
				+ " where rm >= ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(2, pageStart);
			pstmt.setInt(1, pageEnd);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GuestDTO dto = new GuestDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setReadcnt(rs.getInt("readCnt"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getString("regdate"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 검색 메소드
		public int guestCount(String search, String key) {
			//Connection conn = null;
			//PreparedStatement pstmt = null;
			//ResultSet rs = null;
			int row = 0;// 리턴타입
			String query = "select count(*) from tbl_guest where " + search + " like ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + key + "%");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					row = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return row;
		}
		
		// 게시글 검색 반환(검색 목록)
		public List<GuestDTO> guestList(String search, String key, int pageStart, int pageEnd) {
			//Connection conn = null;
			//PreparedStatement pstmt = null;
			//ResultSet rs = null;
			List<GuestDTO> list = new ArrayList<>();// 리턴타입
			String query = "select idx, subject, name, regdate, readcnt"
					+ " from (select rownum rm, idx, subject, name, regdate, readcnt from tbl_guest where rownum <= ? and " + search + " like ?)"
							+ " where rm >= ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(3, pageStart);
				pstmt.setString(2, "%" + key + "%");
				pstmt.setInt(1, pageEnd);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					GuestDTO dto = new GuestDTO();
					dto.setIdx(rs.getInt("idx"));
					dto.setName(rs.getString("name"));
					dto.setReadcnt(rs.getInt("readCnt"));
					dto.setSubject(rs.getString("subject"));
					dto.setRegdate(rs.getString("regdate"));
					
					list.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return list;
		}
	
	public void guestHits(int idx) {
		//Connection conn = null;
		//PreparedStatement pstmt = null;
		//ResultSet rs = null;
		String query = "update tbl_guest set readcnt = readcnt + 1 where idx = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public GuestDTO guestSelect(int idx) {
		//Connection conn = null;
		//PreparedStatement pstmt = null;
		//ResultSet rs = null;
		GuestDTO dto = null;// 리턴타입
		String query = "select * from tbl_guest where idx = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new GuestDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setName(rs.getString("name"));
				dto.setReadcnt(rs.getInt("readCnt"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setContents(rs.getString("contents"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	public int guestWrite(GuestDTO dto) {
		//Connection conn = null;
		//PreparedStatement pstmt = null;
		//ResultSet rs = null;
		int row = 0;// 리턴타입
		String query = "insert into tbl_guest(idx, name, subject, contents)"
				+ " values(tbl_guest_seq_idx.nextval, ?, ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContents());
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	public int guestDelete(int idx) {
		int row = 0;// 리턴타입
		String query = "delete from tbl_guest where idx = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, idx);
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	public int guestModify(GuestDTO dto) {
		int row = 0;// 리턴타입
		String query = "update tbl_guest set name = ?, subject = ?, contents = ? where idx = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContents());
			pstmt.setInt(4, dto.getIdx());
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
}
