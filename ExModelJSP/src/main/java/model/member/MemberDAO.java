package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.util.DBManager;

public class MemberDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private MemberDAO() {};
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public List<MemberDTO> memberList() {
		List<MemberDTO> list = new ArrayList<>();
		String query = "select * from tbl_member";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setUserId(rs.getString("userid"));
				dto.setName(rs.getString("name"));
				dto.setTel(rs.getString("tel"));
				dto.setFirst_time(rs.getString("first_time"));
				dto.setLast_time(rs.getString("last_time"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public List<MemberDTO> memberList(String search, String key) {
		List<MemberDTO> list = new ArrayList<>();
		String query = "select * from tbl_member where " + search + " like ?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + key + "%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setUserId(rs.getString("userid"));
				dto.setName(rs.getString("name"));
				dto.setTel(rs.getString("tel"));
				dto.setFirst_time(rs.getString("first_time"));
				dto.setLast_time(rs.getString("last_time"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public int isUsed(String user_id) {
		int row = 0;
		String query = "select count(*) from tbl_member where userid = ?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}

	public int userInsert(MemberDTO dto) {
		int row = 0;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("insert into tbl_member(name, userid, passwd, gubun, zip, addr1, addr2, tel, email, favorite, job, intro)" +
					" values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getUserId());
			pstmt.setString(3, dto.getPasswd());
			pstmt.setString(4, dto.getGubun());
			pstmt.setString(5, dto.getZip());
			pstmt.setString(6, dto.getAdd1());
			pstmt.setString(7, dto.getAdd2());
			pstmt.setString(8, dto.getTel());
			pstmt.setString(9, dto.getEmail());
			pstmt.setString(10, dto.getFavorite());
			pstmt.setString(11, dto.getJob());
			pstmt.setString(12, dto.getIntro());
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
	
	public MemberDTO userInfo(String userid) {
		MemberDTO dto = new MemberDTO();
		String query = "select * from tbl_member where userid = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setName(rs.getString("name"));
				dto.setUserId(rs.getString("userid"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setGubun(rs.getString("gubun"));
				dto.setZip(rs.getString("zip"));
				dto.setAdd1(rs.getString("addr1"));
				dto.setAdd2(rs.getString("addr2"));
				dto.setTel(rs.getString("tel"));
				dto.setEmail(rs.getString("email"));
				dto.setFavorite(rs.getString("favorite"));
				dto.setJob(rs.getString("job"));
				dto.setIntro(rs.getString("intro"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return dto;
	}
	
	public int userModify(String userid, MemberDTO dto) {
		int row = 0;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("update tbl_member"
					+ " set gubun = ?, zip = ?, addr1 = ?, addr2 = ?, tel = ?, email = ?, favorite = ?, job = ?, intro = ?"
					+ " where userid = ?");
			pstmt.setString(1, dto.getGubun());
			pstmt.setString(2, dto.getZip());
			pstmt.setString(3, dto.getAdd1());
			pstmt.setString(4, dto.getAdd2());
			pstmt.setString(5, dto.getTel());
			pstmt.setString(6, dto.getEmail());
			pstmt.setString(7, dto.getFavorite());
			pstmt.setString(8, dto.getJob());
			pstmt.setString(9, dto.getIntro());
			pstmt.setString(10, userid);
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
}
