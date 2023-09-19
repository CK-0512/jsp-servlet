package shop.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import shop.util.DBManager;

public class MemberDAO {
	private MemberDAO() {}
	
	public static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//회원 가입
	public int MemberInsert(MemberDTO dto) {
		int row = 0;
		String query = "insert into tbl_members values(tbl_members_seq_idx.nextval,?,?,?,?,?,?)";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getUserid());
			pstmt.setString(3, dto.getPass());
			pstmt.setString(4, dto.getZipcode());
			pstmt.setString(5, dto.getAddress());
			pstmt.setString(6, dto.getDe_address());
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
	
	//로그인
	public int MemberLogin(String userid, String passwd)  {
		int row=0;
		String query = "select pass from tbl_members where userid = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,userid);		
			rs = pstmt.executeQuery();
			
			if(rs.next()){ //아이디가 존재하는 경우
				String dbpass = rs.getString("pass");
				if(dbpass.equals(passwd)){  //아이디 비밀번호가 일치하는 경우
					row = 1;
				}else{ //비밀번호가 다른 경우
					row = 0;
				}
			}else{  //아이디가 없는 경우
				row = -1;
			}
		} catch(Exception e)	{
			e.printStackTrace();
		} finally	{
			DBManager.close(conn, pstmt,rs);
		}
		return row;
	}
	
	//회원정보 검색해서 가져오기
	public MemberDTO MemberSelect(String username)  {
		MemberDTO dto = new MemberDTO();
		String query = "select * from tbl_members where userid = ?";
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,username);		
			rs = pstmt.executeQuery();
			if(rs.next()){
				dto.setUserid(rs.getString("userid"));
				dto.setUsername(rs.getString("username"));
				dto.setPass(rs.getString("pass"));
				dto.setZipcode(rs.getString("zipcode"));	
				dto.setAddress(rs.getString("address"));	
				dto.setDe_address(rs.getString("de_address"));
			}
		} catch(Exception e)	{
			e.printStackTrace();
		} finally	{
			DBManager.close(conn, pstmt,rs);
		}
		return dto;
	}

	public boolean isAlreadyExistsUserId(String userId) {
		boolean isExists = false;
		String query = "select count(*) from tbl_members where userid = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);		
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getInt(1) != 0) {
					isExists = true;
				}
			}
		} catch(Exception e)	{
			e.printStackTrace();
		} finally	{
			DBManager.close(conn, pstmt,rs);
		}
		return isExists;
	}
}
