package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.member.MemberDTO;
import model.member.MemberDTO;
import model.util.DBManager;

public class MemberDAO {
	private MemberDAO() {
	}

	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 회원정보 등록 메소드
	public int memberWrite(MemberDTO vo) {

		String query = "insert into member(nickname,userid,userPass,email) values (?, ?, ?, ?)";
		int row = 0;// 리턴타입
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getNickname());
			pstmt.setString(2, vo.getUserid());
			pstmt.setString(3, vo.getUserPass());
			pstmt.setString(4, vo.getEmail());
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}

	// ID 중복 검사 메소드
	public int memberIDcheck(String userid) {

		String query = "select count(*) as counter  from member where userid=?";
		int row = 0;// 리턴타입
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				row = rs.getInt(1); // row = rs.getInt("counter");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}

	// 회원 전체 리스트 메소드
	public List<MemberDTO> memberList() {

		String query = "select *  from member order by id";
		List<MemberDTO> list = new ArrayList<MemberDTO>();// 리턴타입
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			MemberDTO vo = null;
			while (rs.next()) {
				vo = new MemberDTO();
				vo.setId(rs.getInt("id"));
				vo.setNickname(rs.getString("nickname"));
				vo.setUserid(rs.getString("userid"));
				vo.setEmail(rs.getString("email"));
				vo.setAuthLevel(rs.getInt("authLevel"));
				vo.setDelStatus(rs.getInt("delStatus"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	// 로그인 체크
	public int memberLogin(String userid, String userPass) {

		String query = "select userPass from member where userId = ?";
		int row = 0;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 아이디가 존재하는 경우
				String dbpass = rs.getString("userPass");
				if (dbpass.equals(userPass)) {
					row = 1;
				} else { // 비밀번호가 다른 경우
					row = 0;
				}
			} else { // 아이디가 없는 경우
				row = -1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return row;
	}

	// 특정 ID 검색
	public MemberDTO memberSelect(int loginedMemberId) {

		String query = "";
		MemberDTO vo = new MemberDTO();
		try {
			conn = DBManager.getConnection();
			query = "select * from member where id = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, loginedMemberId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo.setId(rs.getInt("id"));
				vo.setUserid(rs.getString("userid"));
				vo.setNickname(rs.getString("nickname"));
				vo.setUserPass(rs.getString("userPass"));
				vo.setEmail(rs.getString("email"));
				vo.setAuthLevel(rs.getInt("authLevel"));
				vo.setDelStatus(rs.getInt("delStatus"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return vo;
	}

	// 회원정보 수정
	public int memberUpdate(MemberDTO vo) {

		String query = "";
		int row = 0;
		try {
			conn = DBManager.getConnection();
			query = "update member set nickname = ?, email = ?";
			query = query + " where id = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getNickname());
			pstmt.setString(2, vo.getEmail());
			pstmt.setInt(3, vo.getId());

			row = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}

	public int deleteMembers(List<Integer> memberIds) {
		String query = "";
		int row = 0;
		try {
			conn = DBManager.getConnection();
			query = "update member set delStatus = 1 where id = ?";
			for (int i : memberIds) {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, i);
				row += pstmt.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}

	public MemberDTO getMemberByEmail(String email) {

		String query = "";
		MemberDTO vo = new MemberDTO();
		try {
			conn = DBManager.getConnection();
			query = "select * from member where email = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo.setId(rs.getInt("id"));
				vo.setUserid(rs.getString("userid"));
				vo.setNickname(rs.getString("nickname"));
				vo.setUserPass(rs.getString("userPass"));
				vo.setEmail(rs.getString("email"));
				vo.setAuthLevel(rs.getInt("authLevel"));
				vo.setDelStatus(rs.getInt("delStatus"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return vo;
	}

	public MemberDTO getMemberPass(String userId, String email) {

		String query = "";
		MemberDTO vo = new MemberDTO();
		try {
			conn = DBManager.getConnection();
			query = "select * from member where userId = ? and email = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, userId);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo.setId(rs.getInt("id"));
				vo.setUserid(rs.getString("userid"));
				vo.setNickname(rs.getString("nickname"));
				vo.setUserPass(rs.getString("userPass"));
				vo.setEmail(rs.getString("email"));
				vo.setAuthLevel(rs.getInt("authLevel"));
				vo.setDelStatus(rs.getInt("delStatus"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return vo;
	}

	public MemberDTO getMember(String userid, String userPass) {

		String query = "";
		MemberDTO vo = new MemberDTO();
		try {
			conn = DBManager.getConnection();
			query = "select * from member where userId = ? and userPass = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, userid);
			pstmt.setString(2, userPass);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo.setId(rs.getInt("id"));
				vo.setUserid(rs.getString("userid"));
				vo.setNickname(rs.getString("nickname"));
				vo.setUserPass(rs.getString("userPass"));
				vo.setEmail(rs.getString("email"));
				vo.setAuthLevel(rs.getInt("authLevel"));
				vo.setDelStatus(rs.getInt("delStatus"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return vo;
	}

	public int modifyPass(int id, String userPass) {

		String query = "";
		int row = 0;
		try {
			conn = DBManager.getConnection();
			query = "update member set userPass";
			query = query + " where id = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, userPass);
			pstmt.setInt(2, id);

			row = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}

	public int memberCount(String search, String key) {
		// 리턴타입
		int row = 0;
		// 쿼리
		// String query="select count(*) from member where " + search + " like '%" + key
		// + "%'";
		String query = "select count(*) from member where " + search + " like ? ";

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

	public List<MemberDTO> memberList(int startpage, int endpage) {
		List<MemberDTO> list = new ArrayList<>();// 리턴타입
		String query="select X.* from (select rownum as rnum, A.* from " + 
				"(select * from member order by id desc) A " + 
				" where rownum <= ?) X where X.rnum >= ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, endpage);
			pstmt.setInt(2, startpage);
			
			rs = pstmt.executeQuery();
			
			MemberDTO vo = null;
			while (rs.next()) {
				vo = new MemberDTO();
				vo.setId(rs.getInt("id"));
				vo.setNickname(rs.getString("nickname"));
				vo.setUserid(rs.getString("userid"));
				vo.setEmail(rs.getString("email"));
				vo.setAuthLevel(rs.getInt("authLevel"));
				vo.setDelStatus(rs.getInt("delStatus"));

				list.add(vo);
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

	public List<MemberDTO> memberList(String search, String key, int startpage, int endpage) {
		List<MemberDTO> list = new ArrayList<>();// 리턴타입
		String query="select X.* from (select rownum as rnum, A.* from " + 
				"(select * from member order by id desc) A " + 
				" where " + search + " like ? and rownum <= ?) X where " + search + " like ?  and X.rnum >= ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + key + "%");
			pstmt.setInt(2, endpage);
			pstmt.setString(3, "%" + key + "%");
			pstmt.setInt(4, startpage);
			
			rs = pstmt.executeQuery();

			MemberDTO vo = null;
			while (rs.next()) {
				vo = new MemberDTO();
				vo.setId(rs.getInt("id"));
				vo.setNickname(rs.getString("nickname"));
				vo.setUserid(rs.getString("userid"));
				vo.setEmail(rs.getString("email"));
				vo.setAuthLevel(rs.getInt("authLevel"));
				vo.setDelStatus(rs.getInt("delStatus"));

				list.add(vo);
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

	public int memberCount() {
		// 리턴타입
		int row = 0;
		// 쿼리
		String query = "select count(*) from member";

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

	public boolean isAlreadyExistsUserId(String userId) {
		boolean isExists = false;
		String query = "select count(*) from member where userid = ?";

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
