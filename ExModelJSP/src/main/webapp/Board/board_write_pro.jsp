<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.board.*" %>

<%
	BoardDAO dao = BoardDAO.getInstance();
	BoardDTO dto = new BoardDTO();
	int nowPage = Integer.parseInt(request.getParameter("page"));
	dto.setName(request.getParameter("name"));
	dto.setEmail(request.getParameter("email"));
	dto.setSubject(request.getParameter("subject"));
	dto.setContents(request.getParameter("contents"));
	dto.setPass(request.getParameter("pass"));
	int row = dao.boardWrite(dto);
	
	if (row == 1) {
%>
	<script>
		alert("등록 성공");
		location.replace("board_list.jsp?page=" + <%=nowPage %>);
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