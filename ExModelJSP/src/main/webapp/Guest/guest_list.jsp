<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.guest.*, model.util.*, java.util.*" %>

<%	
	request.setCharacterEncoding("utf-8");
	GuestDAO dao = new GuestDAO();
	
	String search = "";
	String key = "";
	String url = "guest_list.jsp";
	String addTag = "";
	int totCount = 0;
	List<GuestDTO> list = null;
	if(request.getParameter("key") != null){
		key = request.getParameter("key");
		search = request.getParameter("search");
		
		totCount = dao.guestCount(search, key);
	} else {
		totCount = dao.guestCount();
	}
	
	int nowPage = 1;
	int maxList = 10;
	int totPage = 1;
	if (totCount % maxList == 0) {
		totPage = totCount / maxList;
	} else {
		totPage = totCount / maxList + 1;
	}
	if (totPage == 0) totPage = 1;
	
	if(request.getParameter("page") != null) {
		nowPage = Integer.parseInt(request.getParameter("page"));
	}
	
	int pageStart = (nowPage - 1) * maxList + 1;
	int pageEnd = nowPage * maxList;
	int listCount = totCount - ((nowPage - 1) * maxList);//가상번호 출력용
	
	if(key.equals("")) {
		list = dao.guestList(pageStart, pageEnd);
	} else {
		list = dao.guestList(search, key, pageStart, pageEnd);
	}
	
	String pageSkip = "";
	if (key.equals("")) {
		pageSkip = PageIndex.pageList(nowPage, totPage, url, addTag);
	} else {
		pageSkip = PageIndex.pageListHan(nowPage, totPage, url, search, key);
	}
%>

<html>
<head><title>방명록 읽기</title>
<link rel="stylesheet" type="text/css" href="stylesheet.css">
<style type="text/css">
  a.list {text-decoration:none;color:black;font-size:10pt;}
</style>
<script>
	function guest_search(){
		if(guest.key.value==""){
			alert("검색어를 입력하세요");
			guest.key.focus();
			return;
		}
		guest.submit();
	}
</script>
</head>
<body bgcolor="#FFFFFF" topmargin="0" leftmargin="0">
<table border="0" width="800">
  <tr>
    <td width="20%" height="500" valign="top" bgcolor="#ecf1ef">
	<!-- 다음에 추가할 부분 --></td>
    <td width="80%" valign="top">	
    <br>
    <table border="0" cellspacing="1" width="100%" align="center">
      <tr>
        <td colspan="7" align="center" valign="center" height="20">
        <font size="4" face="돋움" color="blue">
        <img src="./img/bullet-01.gif"> <b>방 명 록</b></font></td></tr>
      <tr>
        <td colspan="5" align="right" valign="middle" height="20">
		<font size="2" face="고딕">전체 : <%= totCount %></b>건 - <%=nowPage %> Pages</font></td></tr>
 	   <tr bgcolor="e3e9ff">
 	      <td width="10%"align="center" height="20"><font face="돋움" size="2">번호</font></td>
 	      <td width="50%" align="center" height="20"><font face="돋움" size="2">제목</font></td>
 	      <td width="15%" align="center" height="20"><font face="돋움" size="2">글쓴이</font></td>
 	      <td width="15%" align="center" height="20"><font face="돋움" size="2">작성일</font></td>
 	      <td width="10%" align="center" height="20"><font face="돋움" size="2">조회수</font></td>
 	   </tr>

<%
	//글이 없는 경우
	if(list.size() == 0) {
		
%>
		<tr onMouseOver="style.backgroundColor='#D1EEEE'" onMouseOut="style.backgroundColor=''">
          <td colspan="5" align="center" height="25">
          	<font face="돋움" size="2" color="#000000">등록된 자료가 없습니다.</font>
          </td>
      	</tr>
<%
	}
%>

<%
	for(GuestDTO dto : list) {
%>

		<tr onMouseOver="style.backgroundColor='#D1EEEE'" onMouseOut="style.backgroundColor=''">
          <td align="center" height="25"><font face="돋움" size="2" color="#000000"><%=listCount-- %></font></td>
          <td align="left" height="20"><font face="돋움" size="2" color="#000000"><a href="guest_view.jsp?idx=<%=dto.getIdx() %>&page=<%=nowPage %>"><%=dto.getSubject() %></a></font></td>
          <td align="center" height="20"><font face="돋움" size="2">
                <a class="list" href="mailto:ein1027@nate.com"><%=dto.getName() %></a></font></td>
          <td align="center" height="20"><font face="돋움" size="2"><%=dto.getRegdate().substring(0, 10) %></font></td>
          <td align="center" height="20"><font face="돋움" size="2"><%=dto.getReadcnt() %></font></td>
      	</tr>

<%
	}
%>
		
     <div align="center">
        <table width="700" border="0" cellspacing="0" cellpadding="5">
          <tr>&nbsp;</tr><tr>
             <td colspan="5">        
                <div align="center"><%=pageSkip %></div>
			  </td>
			 </tr>
		</table>
		
		<table width="700">
		<tr>
			<td width="25%"> &nbsp;</td>
			<td width="50%" align="center">
				<table>
					<form name="guest" method="post">	
					<!-- 검색어를 이용하여 글제목, 작성자, 글내용 중에 하나를 입력 받아 처리하기 위한 부분 -->
						<tr>
							<td>
								<select name="search">
									<option value="subject" <% if(search.equals("subject")) { %> selected="selected" <% } %>>글제목</option>
									<option value="name" <% if(search.equals("name")) { %> selected="selected" <% } %>>작성자</option>
									<option value="contents" <% if(search.equals("contents")) { %> selected="selected" <% } %>>글내용</option>
								</select>
							</td>
							<td> <input type="text" size=20 name="key" value="<%=key %>"></td>
							<td> <a href="javascript:guest_search()"><img src="./img/search2.gif" border="0"></a></td>
						</tr>
					</form>
				</table>
			</td>
			<td width="25%" align="right">
			<a href="guest_write.jsp"><img src="./img/write.gif" border="0"></a>
			</td>
		</tr>
	</table>
		
		</div>
	</body>
	</html>

