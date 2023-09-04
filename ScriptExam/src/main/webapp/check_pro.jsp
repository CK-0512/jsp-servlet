<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	request.setCharacterEncoding("utf-8");
	String gender = request.getParameter("gender");
	String[] sport = request.getParameterValues("sport");
	String job = request.getParameter("job");
	
	out.print("성별 : " + gender);
	out.print("스포츠 : ");
	for(String s : sport) {
		out.print(s + ",");
	}
	out.print("직업 : " + job);

%>