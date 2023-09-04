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
	String subject = request.getParameter("subject");
	String contents = request.getParameter("contents");

	Connection conn = null;
	PreparedStatement pstmt = null;
	int row = 0;
	try {
		Class.forName(myDriver);
		conn = DriverManager.getConnection(myURL, myID, myPass);
		pstmt = conn.prepareStatement("update tbl_board set subject=?, contents=? where idx=?");
		pstmt.setString(1, subject);
		pstmt.setString(2, contents);
		pstmt.setInt(3, idx);
		
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
		alert("수정되었습니다");
		location.replace("board_view.jsp?idx=<%=idx %>"); //history.back()안먹힘
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