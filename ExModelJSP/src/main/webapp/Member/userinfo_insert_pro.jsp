<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.member.*" %>

<%
	MemberDAO dao = MemberDAO.getInstance();
	MemberDTO dto = new MemberDTO();
	request.setCharacterEncoding("utf-8");
	dto.setName(request.getParameter("name"));
	dto.setUserId(request.getParameter("userid"));
	dto.setPasswd(request.getParameter("passwd"));
	dto.setGubun(request.getParameter("gubun"));
	dto.setZip(request.getParameter("zip"));
	dto.setAdd1(request.getParameter("addr1"));
	dto.setAdd2(request.getParameter("addr2"));
	dto.setTel(request.getParameter("tel"));
	dto.setEmail(request.getParameter("email"));
	String[] fas = request.getParameterValues("fa");
	dto.setJob(request.getParameter("job"));
	dto.setIntro(request.getParameter("intro"));
	String favorite = "";
	if(fas != null) {
		favorite = fas[0];
		for (int i = 1; i < fas.length; i++) {
			favorite = favorite + "," + fas[i];
		}
	}
	dto.setFavorite(favorite);
	
	int row = dao.userInsert(dto);
	
	if (row == 1) {
%>
	<script>
		alert("회원가입 성공");
		location.replace("userinfo_list.jsp");
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