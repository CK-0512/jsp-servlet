<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String name = request.getParameter("name");
	String id = request.getParameter("id");
	String pw = request.getParameter("password");
	String email = request.getParameter("email") + "@" + request.getParameter("domain");
	String phoneNumber = request.getParameter("phone-first") + request.getParameter("phone-second") + request.getParameter("phone-last");
	String gender = request.getParameter("gender");
	String hobby[] = request.getParameterValues("hobby");
	String job = request.getParameter("job");

	out.print("이름 : " + name + "<br>");
	out.print("아이디 : " + id + "<br>");
	out.print("비번 : " + pw + "<br>");
	out.print("이메일 : " + email + "<br>");
	out.print("번호 : " + phoneNumber + "<br>");
	out.print("성별 : " + gender + "<br>");
	out.print("취미 : ");
	for(String h : hobby) {
		out.print(h + " ");
	}
	out.print("<br>");
	out.print("직업 : " + job + "<br>");
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