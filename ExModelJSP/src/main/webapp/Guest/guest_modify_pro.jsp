<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.guest.*" %>

<%
	GuestDAO dao = new GuestDAO();
	GuestDTO dto = new GuestDTO();
	
//	String name = request.getParameter("name");
//	dto.setName(name);
	request.setCharacterEncoding("utf-8");
	int nowPage = Integer.parseInt(request.getParameter("page"));
	int idx = Integer.parseInt(request.getParameter("idx"));
	dto.setIdx(idx);
	dto.setName(request.getParameter("name"));
	dto.setSubject(request.getParameter("subject"));
	dto.setContents(request.getParameter("contents"));

	int row = dao.guestModify(dto);
	if(row == 1) {
		response.sendRedirect("guest_view.jsp?idx=" + idx + "&page=" + nowPage);
	} else {
%>
	<script>
		alert("오류");
		location.back();
	</script>
<%		
	}
%>