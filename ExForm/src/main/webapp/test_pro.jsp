<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("utf-8");

	String gender = request.getParameter("gender");
	String fd[] = request.getParameterValues("fd");//배열
	String job = request.getParameter("job");
	String intro = request.getParameter("intro");
	
	out.print("성별 : " + gender + "<br>");
	for(int i=0; i <fd. length; i++) {
		out.print(fd[i] + ",");
	}
	out.print("<br>");
	out.print("직업 : " + job + "<br>");
	out.print("소개 : " + intro + "<br>");

%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>