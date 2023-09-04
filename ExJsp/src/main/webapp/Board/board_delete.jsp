<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head><title>방명록 삭제</title>
 <link rel="stylesheet" type="text/css" href="/stylesheet.css">
 
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
	ResultSet rs = null;
	int row = 0;
	try {
		Class.forName(myDriver);
		conn = DriverManager.getConnection(myURL, myID, myPass);
		pstmt = conn.prepareStatement("select pass from tbl_board where idx=?");
		pstmt.setInt(1, idx);
		rs = pstmt.executeQuery();
		rs.next();
	} catch (Exception e) {
		e.printStackTrace();
	}
%>

<script>
	function board_del(idx) {
	  const userEnteredPassword = del.pass.value; 
	  const storedPassword = '<%=rs.getString("pass") %>';
	  
	  if (userEnteredPassword === storedPassword) {
	    const shouldDelete = confirm("정말 삭제하시겠습니까?");
	    if (shouldDelete) {
	    	del.action = "board_del_pro.jsp?idx=" + idx;
	    	del.submit();
	    } else {
	      	return false;
	    }
	  } else {
		  alert("비밀번호 오류");
	      return false;
	  }
	}
</script>
 </head>
 <body>
 <form method="post" name="del">
   <table border="0" cellpadding="0" cellspacing="0" width="300" align="center">
     <tr>
       <td height="50">
       <img src="./img/bullet-05.gif"><b><font size="3" color="red">잠깐 !!</font></b></td></tr>
     <tr>
       <td valign="middle" height="30">
       <font size="2" face="돋움">게시물은 작성하신 분만 삭제할 수 있습니다.<br>
       글작성시 입력한 비밀번호를 입력해 주세요...</font></td></tr>
     <tr>
       <td valign="middle" height="40">
       <font size="2" face="돋움">
       비밀번호 <input type="password" name="pass" size="8"></font>
       <input type="submit" value="삭제" onClick="board_del(<%=idx %>);">
       <input type="button" value="닫기" onClick="self.close()"></td></tr>
   </table>
   </form>
 </body>
 </html>
