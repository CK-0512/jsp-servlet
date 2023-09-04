<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
	List list = new ArrayList();
	list.add("김학생");
	list.add("이학생");
	list.add("박학생");
	list.add("강학생");
	list.add("홍학생");
	// 내장객체
	out.print("<h1>안녕하세요</h1>");
	out.print("이름 : " + list.get(0) + "<br>");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>이름을 출력합니다</h1>
	<% 
		for(int i = 0; i < list.size(); i++) {
	%>
		이름 : <%= list.get(i) %> <br>
	<% 
		}
	%>
</body>
</html>