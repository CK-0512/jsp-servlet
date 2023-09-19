<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		   <script language="javascript">
		   	alert("정상적으로 등록되었습니다.");  
		   	location.replace("pds_list.do?page=${page}");
   		   </script>
<%
	}else{
%>		
		    <script language="javascript">
     			alert("DB 점속오류.\n\n잠시후 다시 등록하세요");
     			history.back();
   			</script>
<%
	}
%>	
</body>
</html>