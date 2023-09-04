<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.member.*" %>

<%
	MemberDAO dao = MemberDAO.getInstance();
	String user_id = request.getParameter("user_id");
	int row = dao.isUsed(user_id);
%>

<HTML>
<HEAD>
<TITLE>사용자의 아이디를 체크합니다.</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<STYLE TYPE="text/css">
<!--
body { font-family: 돋움, Verdana; font-size: 9pt}
td   { font-family: 돋움, Verdana; font-size: 9pt; text-decoration: none; color: #000000} 
--->
</STYLE>

<script>
	function idCheck() {
		if(!idChk.user_id.value.trim()) {
			alert("아이디를 입력해주세요");
			idChk.user_id.focus();
			return false;
		}
		
		idChk.submit();
	}
	
	function send(row) {
		if(row == 0) {
			opener.join.userid.value = document.getElementsByName("idChk")[0].user_id.value;
		}
		self.close();
	}
</script>

</HEAD>
<BODY bgcolor="#FFFFFF">
<TABLE CELLPADDING=0 CELLSPACING=0 BORDER=0 WIDTH=330>
  <TR BGCOLOR=#7AAAD5>
    <td align=left><img src="./img/u_b02.gif"></td>
    <td align=center><FONT COLOR="#FFFFFF"><b>아이디 중복 체크</FONT></td>
    <td align=right><img src="./img/u_b03.gif"></td>
  </tr>
</table>
<TABLE CELLPADDING=0 CELLSPACING=0 BORDER=0 WIDTH=330>
<TR BGCOLOR=#948DCF>
  <TD>
    <TABLE CELLPADDING=4 CELLSPACING=1 BORDER=0 WIDTH=330>
  	  <TR BGCOLOR="#FFFFFF">
        <TD ALIGN="center">
          <% 
          	if(user_id != null) {
          	if(row == 0) { 
          %>
          	<br><FONT FACE="굴림"><B>사용 가능합니다.</B></FONT><br>
         <BR><FONT COLOR="#483cae"><b><%=user_id %></b></FONT>는 아직 사용되지 않은 아이디입니다.
         <BR><FONT COLOR="#483cae"><b><%=user_id %></b></FONT>로 등록하셔도 됩니다.
         <%
         	} else {
         %>
         <br><font face="굴림"><b>죄송합니다</b></font><br>
    	<BR><FONT COLOR="#483cae"><b><%=user_id %></b></FONT>는 이미 사용 중인 아이디입니다<br>
    	다른 아이디를 사용하여 주십시오.
    	<% }} %>
    	<form name="idChk" method="post">
           <INPUT NAME=user_id type=text size=16 maxlength=16 <% if(user_id != null) { %> value="<%=user_id %> <% } %>">
      	   <input type=image src="./img/u_bt08.gif" border=0 vspace=0 onClick="idCheck()">
		</form>
        </TD>
      </TR>
    </TABLE>
 </TD>
</TR>
</TABLE>

<TABLE CELLPADDING=0 CELLSPACING=0 BORDER=0 WIDTH=330>
  <TR BGCOLOR=#7AAAD5>
    <td align=left><img src="./img/u_b04.gif"></td>
    <td align=right><img src="./img/u_b05.gif"></td>
  </tr>
  <tr>
    <td colspan=3 align=center>
      <a href="javascript:send(<%=row %>)"><img src="./img/u_bt13.gif" border=0 vspace=5></a>
    </td>
  </tr>
</table>
</BODY>
</HTML>