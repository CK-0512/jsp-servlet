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
	alert("가입 완료");
	location.href="/Index/index.jsp";
</script>		
<%	
	}else{
%>
<script>
	alert("오류");
	history.back();
</script>
<%
	}
%>
</body>
</html>