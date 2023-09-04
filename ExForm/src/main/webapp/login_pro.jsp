<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//request : 요청처리
	String userid = request.getParameter("userid");
	String pass = request.getParameter("userpw");
	
	out.print("아이디 : " + userid + "<br>");
	out.print("비밀번호 : " + pass + "<br>");

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	아이디 : <%= userid %><br>
	비밀번호 : <%= pass %><br>
</body>
</html>