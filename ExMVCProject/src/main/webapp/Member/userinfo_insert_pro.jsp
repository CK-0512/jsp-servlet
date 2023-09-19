<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int row = (int)request.getAttribute("row");
	if(row==1){
%>
	<script>
		alert("가입성공");
		location.href="userlogin_form.do";
	</script>
<%
	}else{	
%>	
	<script>
		alert("가입실패");
		history.back();
	</script>
<%
	}
%>