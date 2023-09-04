<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.*" %>
<% 
	String myDriver= "oracle.jdbc.driver.OracleDriver"; 
	String myURL = "jdbc:oracle:thin:@localhost:1521:xe";
	String myID = "track2_12";
	String myPass = "1234";
%>
<%
	request.setCharacterEncoding("utf-8");
	int idx = Integer.parseInt(request.getParameter("idx"));

	// db 연결
	Connection conn = null;
	PreparedStatement pstmt = null;
	int row = 0;
	try {
		Class.forName(myDriver);
		conn = DriverManager.getConnection(myURL, myID, myPass);
		pstmt = conn.prepareStatement("delete from tbl_guest where idx=?");
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
	
	if(row == 1) {
%>
	<script>
		alert("삭제되었습니다");
		location.replace("guest_list.jsp");
	</script>
<%
	} else {
%>

	<script>
		alert("잠시후 다시 시도해주세요");
		history.back();
	</script>
<%
	}
%>