<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.guest.*" %>

<%
	int idx = Integer.parseInt(request.getParameter("idx"));
	int nowPage = Integer.parseInt(request.getParameter("page"));

	GuestDAO dao = new GuestDAO();
	int row = dao.guestDelete(idx);
	
	if (row == 1) {
		response.sendRedirect("guest_list.jsp?page=" + nowPage);
	} else {
%>
	<script>
		alert("오류");
		history.back();
	</script>
<% 
	}
%>