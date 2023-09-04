<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	int nowpage = (int)request.getAttribute("page");
	int row = (int)request.getAttribute("row");

	if(row==1){
%>
	<script>
		alert("등록성공");
		location.href="/Board/board_list.do?page="+<%=nowpage%>
	</script>
<%
	}else{
%>

	<script>
		alert("등록실패");
		history.back();
	</script>
<%
	}
%>	
	