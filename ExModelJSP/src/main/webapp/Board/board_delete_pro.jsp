<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.board.*" %>

<%
	int idx = Integer.parseInt(request.getParameter("idx"));
	int nowPage = Integer.parseInt(request.getParameter("page"));
	BoardDAO dao = BoardDAO.getInstance();
	int row = dao.boardDelete(idx);
	
	if (row == 1) {
%>
	<script>
		alert("삭제 성공");
		window.opener.location.replace("board_list.jsp?page=" + nowPage);
		window.close();
	</script>
<% 
	} else {
%>
	<script>
		alert("오류");
		history.back();
	</script>
<%
	}
%>