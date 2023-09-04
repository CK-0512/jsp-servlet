<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userid = request.getParameter("userid");
	String password = request.getParameter("password");
	
	if(userid.equals("admin") && password.equals("1234")){
%>	
<script>
	alert("로그인 성공");
	location.href="index.jsp?idx=3&tc=30"
</script>	
<%
	} else {
%>
<script>
	alert("아이디 또는 비번오류");
	history.back();
</script>
<%		
	}
%>