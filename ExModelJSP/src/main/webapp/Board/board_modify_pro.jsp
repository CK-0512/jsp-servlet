<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.board.*" %>

<%
	BoardDAO dao = BoardDAO.getInstance();
	BoardDTO dto = new BoardDTO();
	int nowPage = Integer.parseInt(request.getParameter("page"));
	dto.setIdx(Integer.parseInt(request.getParameter("idx")));
	dto.setEmail(request.getParameter("email"));
	dto.setSubject(request.getParameter("subject"));
	dto.setContents(request.getParameter("contents"));
	int row = dao.boardModify(dto);
	
	if (row == 1) {
%>
	<script>
		alert("수정 성공");
		location.replace("board_list.jsp?page=" + nowPage);
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