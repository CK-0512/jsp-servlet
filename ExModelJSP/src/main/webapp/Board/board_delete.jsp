<%@ page import="model.board.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
	int idx = Integer.parseInt(request.getParameter("idx"));
	int nowPage = Integer.parseInt(request.getParameter("page"));	
	BoardDAO dao = BoardDAO.getInstance();
	BoardDTO dto = dao.boardView(idx);
%>

<html>
<head><title>방명록 삭제</title>
 <link rel="stylesheet" type="text/css" href="/stylesheet.css">
 
 <script>
 	function del(idx, nowPage) {
 		const pass = delete_form.pass.value;
 		const originPass = delete_form.originPass.value;
 		
 		if (pass == originPass) {
 			if (confirm("정말 삭제하시겠습니까?")) {
 				delete_form.action = "board_delete_pro.jsp?idx=" + idx + "&page=" + nowPage;
 	 	 		delete_form.submit();
 			} else {
 				return false;
 			}
 		} else {
 			alert("비밀번호 오류");
 			return false;
 		}
 	}
 </script>
 </head>
 <body>
 <form method="post" name="delete_form">
 	<input type="hidden" name="originPass" value="<%=dto.getPass() %>">
   <table border="0" cellpadding="0" cellspacing="0" width="300" align="center">
     <tr>
       <td height="50">
       <img src="./img/bullet-05.gif"><b><font size="3" color="red">잠깐 !!</font></b></td></tr>
     <tr>
       <td valign="middle" height="30">
       <font size="2" face="돋움">게시물은 작성하신 분만 삭제할 수 있습니다.<br>
       글작성시 입력한 비밀번호를 입력해 주세요...</font></td></tr>
     <tr>
       <td valign="middle" height="40">
       <font size="2" face="돋움">
       비밀번호 <input type="password" name="pass" size="8"></font>
       <input type="submit" value="삭제" onClick="del(<%=idx %>, <%=nowPage %>)">
       <input type="button" value="닫기" onClick="window.close()"></td></tr>
   </table>
   </form>
 </body>
 </html>
