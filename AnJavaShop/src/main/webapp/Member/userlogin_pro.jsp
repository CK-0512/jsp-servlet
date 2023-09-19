<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int row = (int)request.getAttribute("row");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	if(row==1){
%>
<script>
	alert("로그인성공");
	location.href="/Index/index.jsp";
</script>
<%
	}else if(row==0){
%>
<script>
	alert("비밀번호가 일치하지않습니다");
	history.back();
</script>
<%
	}else{
%>
<script>
	alert("아이디가 존재하지않습니다");
	history.back();
</script>
<%
	}
%>
</body>
</html>