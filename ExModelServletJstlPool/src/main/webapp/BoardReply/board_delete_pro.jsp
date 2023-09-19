<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.boardreply.*" %>  

<%
	int row = (int)request.getAttribute("row");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		if(row==1){
	%>
			<script>
				alert("삭제되었습니다");
				window.opener.location.replace("/BoardReply/boardreply_list.do?page=${page}");
				self.close();
			</script>
	<%
		}else if(row==-1){
	%>		
			<script>
				alert("답변글이 있어 삭제할 수 없습니다");
				window.opener.location.replace("/BoardReply/boardreply_list.do");
				self.close();				
				</script>
	<%
		}else{	
	%>		
			<script>
				alert("비밀번호 오류 입니다");
				history.back();
				</script>
	<%
		}
	%>
</body>
</html>