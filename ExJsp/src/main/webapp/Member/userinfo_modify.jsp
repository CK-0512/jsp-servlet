<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*" %>

<%
	String userid = request.getParameter("userid");

	String myDriver= "oracle.jdbc.driver.OracleDriver"; 
	String myURL = "jdbc:oracle:thin:@localhost:1521:xe";
	String myID = "track2_12";
	String myPass = "1234";	
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String query ="select * from tbl_member where userid = ?";
	try {
		Class.forName(myDriver);
		conn = DriverManager.getConnection(myURL, myID, myPass);
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, userid);
		rs = pstmt.executeQuery();
		rs.next();
	} catch (Exception e) {
		e.printStackTrace();
	}
%>

<html>
<head>
<title>회원등록</title>
<STYLE TYPE="text/css">
<!--
body { font-family: 돋움, Verdana; font-size: 9pt}
td   { font-family: 돋움, Verdana; font-size: 9pt; text-decoration: none; color: #000000; BACKGROUND-POSITION: left top; BACKGROUND-REPEAT: no-repeat;}
-->
.formbox {
	BACKGROUND-COLOR: #F0F0F0; FONT-FAMILY: "Verdana", "Arial", "Helvetica", "돋움"; FONT-SIZE:9pt
} 
--->
</STYLE>

<script>
	function id_check(){
		var url = "id_check.jsp";
		window.open(url, "id_check", "x=500, y=500, width=500, heigth=300");
	}
	
	function post_check(){
		var url = "post_check.jsp";
		window.open(url, "post_check", "x=500, y=500, width=500, heigth=300");
	}
	
	function send(){
		let join = document.getElementsByName("join")[0];
		
		
		let name = join.name;
		if (!name.value.trim().length) {
			alert("이름을 입력해주세요");
			name.focus();
			return false;
		}
		
		let userid = join.userid;
		if (!userid.value.trim().length) {
			alert("아이디를 입력해주세요");
			userid.focus();
			return false;
		}
		
		let repasswd = join.repasswd;
		if (!repasswd.value.trim().length) {
			alert("비밀번호 확인을 입력해주세요");
			repasswd.focus();
			return false;
		}
		if (repasswd.value.trim() != (<%= rs.getString("passwd") %>)) {
		    alert("비밀번호 오류");
		    repasswd.focus();
		    return false;
		}
		
		let tel = join.tel;
		if (!tel.value.trim().length) {
			alert("전화번호를 입력해주세요");
			tel.focus();
			return false;
		}
		let email = join.email;
		if (!email.value.trim().length) {
			alert("이메일을 입력해주세요");
			email.focus();
			return false;
		}
		
		join.action="userinfo_modify_pro.jsp";
		join.submit();
	}
	
</script>

</head>

<body bgcolor="#FFFFFF" LEFTMARGIN=0  TOPMARGIN=0 >
 
 <!-- 탑 메뉴 영역 삽입-->


<table border="0" width="800">
<tr>
  <td width="20%"  bgcolor="#ecf1ef" valign="top" style="padding-left:0;">
	
	<!--로그인 영역 삽입-->

  </td>
  <td width="80%" valign="top">&nbsp;<img src="./img/title1.gif" ><br>    
	<form method="post" name="join">
	<table border=0 cellpadding=0 cellspacing=0 border=0 width=730 valign=top>
		<tr><td align=center><br>                            
			<table cellpadding=0 cellspacing=0 border=0 width=650 align=center>       
				<tr>
					<td bgcolor="#7AAAD5">            
						<table cellpadding=0 cellspacing=0 border=0 width=100%>
							<tr bgcolor=#7AAAD5>
								<td align=left BORDER="0" HSPACE="0" VSPACE="0"><img src="./img/u_b02.gif"></td>
								<td align=center bgcolor="#7AAAD5"><FONT COLOR="#FFFFFF"><b>사용자정보 수정&nbsp;</b><font color=black>(</font><font color=red>&nbsp;*&nbsp;</font><font color=black>표시항목은 반드시 입력하십시요.)</font></FONT></td>
								<td align=right BORDER="0" HSPACE="0" VSPACE="0"><img src="./img/u_b03.gif"></td>
							</tr>
						</table>
						<table cellpadding=3 cellspacing=1 border=0 width=100%>
							<tr>
								<td width=110 bgcolor=#EFF4F8>&nbsp;회원 성명<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<input type=text name=name size=16 maxlength=20 value="<%= rs.getString("name") %>" readonly="readonly">성명은 빈칸없이 입력하세요.
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;회원 ID<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<table cellspacing=0 cellpadding=0>
										<tr>
											<td align=absmiddle>
												<input type=text name=userid size=12 maxlength=16 readonly value="<%= rs.getString("userid") %>" style="width:120">
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;비밀번호확인<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE><input type=password name=repasswd size=8 maxlength=12 value="" style="width:80">비밀번호 확인을 위해서 비밀번호를 입력해주세요. </td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;주소구분<font color=red>&nbsp;</font></td>
								<TD BGCOLOR=WHITE>
									<input type=radio name=gubun value="직장" <% if(rs.getString("gubun").equals("직장")){ %> checked="checked"<% } %>>직장&nbsp;&nbsp;
									<input type=radio name=gubun value="자택" <% if(rs.getString("gubun").equals("자택")){ %> checked="checked"<% } %>>자택 
								</td>
							</tr>
							
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;우편번호<font color=red>&nbsp;</font></td>
								<TD BGCOLOR=WHITE>
									<table cellspacing=0 cellpadding=0>
										<tr>
											<td><input type=text name=zip size=6 maxlength=6 ></td>
                  							<td><a href="javascript:post_check()"><img src="./img/u_bt07.gif" hspace=2 border=0 name=img2 align=absmiddle></a></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;주소<font color=red>&nbsp;</font></td>
								<TD BGCOLOR=WHITE>
									<input type=text name=addr1 size=50 maxlength=100 value="<%= rs.getString("addr1") %>">
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;나머지 주소<font color=red>&nbsp;</font></td>
								<TD BGCOLOR=WHITE>
									<input type=text name=addr2 size=50 maxlength=100 value="<%= rs.getString("addr2") %>">
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;전화번호<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<input type=text name=tel size=13 maxlength=13 value="<%= rs.getString("tel") %>">
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;E-mail<font color=red>&nbsp;*</font>
								</td>
								<td bgcolor=WHITE valign=middle>
									<input type=text name=email size=30 maxlength=30 value="<%= rs.getString("email") %>">
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;관심분야
                					<font color=red>&nbsp;</font>
								</td>
								<% if(rs.getString("favorite") == null) {%> 
									<td bgcolor=WHITE valign=middle> 
										<input type="checkbox" name="fa" value="건강">건강 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" name="fa" value="문화예술">문화예술 &nbsp;&nbsp;
										<input type="checkbox" name="fa" value="경제">경제 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" name="fa" value="연예오락">연예오락 &nbsp;
										<input type="checkbox" name="fa" value="뉴스">뉴스
										<br><input type="checkbox" name="fa" value="여행레져">여행레져 &nbsp;&nbsp;
										<input type="checkbox" name="fa" value="생활">생활 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" name="fa" value="스포츠">스포츠 &nbsp;&nbsp;
										<input type="checkbox" name="fa" value="교육">교육 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" name="fa" value="컴퓨터">컴퓨터 &nbsp;&nbsp;
										<input type="checkbox" name="fa" value="학문">학문 &nbsp;&nbsp;
									</td>
								<% } else {%>
									<td bgcolor=WHITE valign=middle> 
										<input type="checkbox" name="fa" value="건강" <% if(rs.getString("favorite").contains("건강")){ %> checked="checked" <% } %>>건강 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" name="fa" value="문화예술" <% if(rs.getString("favorite").contains("문화예술")){ %> checked="checked" <% } %>>문화예술 &nbsp;&nbsp;
										<input type="checkbox" name="fa" value="경제" <% if(rs.getString("favorite").contains("경제")){ %> checked="checked" <% } %>>경제 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" name="fa" value="연예오락" <% if(rs.getString("favorite").contains("연예오락")){ %> checked="checked" <% } %>>연예오락 &nbsp;
										<input type="checkbox" name="fa" value="뉴스" <% if(rs.getString("favorite").contains("뉴스")){ %> checked="checked" <% } %>>뉴스
										<br><input type="checkbox" name="fa" value="여행레져" <% if(rs.getString("favorite").contains("여행레져")){ %> checked="checked" <% } %>>여행레져 &nbsp;&nbsp;
										<input type="checkbox" name="fa" value="생활" <% if(rs.getString("favorite").contains("생활")){ %> checked="checked" <% } %>>생활 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" name="fa" value="스포츠" <% if(rs.getString("favorite").contains("스포츠")){ %> checked="checked" <% } %>>스포츠 &nbsp;&nbsp;
										<input type="checkbox" name="fa" value="교육" <% if(rs.getString("favorite").contains("교육")){ %> checked="checked" <% } %>>교육 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" name="fa" value="컴퓨터" <% if(rs.getString("favorite").contains("컴퓨터")){ %> checked="checked" <% } %>>컴퓨터 &nbsp;&nbsp;
										<input type="checkbox" name="fa" value="학문" <% if(rs.getString("favorite").contains("학문")){ %> checked="checked" <% } %>>학문 &nbsp;&nbsp;
									</td>
								<% } %>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;직업<font color=red>&nbsp;</font></td>
								<TD BGCOLOR=WHITE>
									<select name=job class="formbox">
 										<option value="0">선택하세요 ---
 										<option value="회사원" <% if(rs.getString("job").equals("회사원")) { %> selected="selected" <% } %>>회사원
 										<option value="연구전문직" <% if(rs.getString("job").equals("연구전문직")) { %> selected="selected" <% } %>>연구전문직
 										<option value="교수학생" <% if(rs.getString("job").equals("교수학생")) { %> selected="selected" <% } %>>교수학생
 										<option value="일반자영업" <% if(rs.getString("job").equals("일반자영업")) { %> selected="selected" <% } %>>일반자영업
 										<option value="공무원" <% if(rs.getString("job").equals("공무원")) { %> selected="selected" <% } %>>공무원
 										<option value="의료인" <% if(rs.getString("job").equals("의료인")) { %> selected="selected" <% } %>>의료인
 										<option value="법조인" <% if(rs.getString("job").equals("법조인")) { %> selected="selected" <% } %>>법조인
 										<option value="종교,언론,에술인" <% if(rs.getString("job").equals("종교,언론,에술인")) { %> selected="selected" <% } %>>종교.언론/예술인
 										<option value="농,축,수산,광업인" <% if(rs.getString("job").equals("농,축,수산,광업인")) { %> selected="selected" <% } %>>농/축/수산/광업인
 										<option value="주부" <% if(rs.getString("job").equals("주부")) { %> selected="selected" <% } %>>주부
 										<option value="무직" <% if(rs.getString("job").equals("무직")) { %> selected="selected" <% } %>>무직
 										<option value="기타" <% if(rs.getString("job").equals("기타")) { %> selected="selected" <% } %>>기타
									</select>
								</td>                
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;자기소개<font color=red>&nbsp;</font></td>
								<TD BGCOLOR=WHITE>
									<textarea cols=65 rows=5 name="intro"><%= rs.getString("intro") %></textarea>
								</td>
							</tr>
						</table>
						<table cellpadding=0 cellspacing=0 border=0 width=100%>
							<tr bgcolor=#7AAAD5>
								<td valign=bottom>
									<img src="./img/u_b04.gif" align=left hspace=0 vspace=0 border=0>
								</td>
								<td align=center></td>
								<td valign=bottom>
									<img src="./img/u_b05.gif" align=right hspace=0 vspace=0 border=0>
								</td>
							</tr>
							<tr bgcolor=#ffffff>
								<td colspan=3 align=center>
									<a href="javascript:send()"><img src="./img/u_bt06.gif" vspace=3 border=0 name=img3></a>
									<a href="#" onClick="join.reset()"><img src="./img/u_bt05.gif" border=0 hspace=10 vspace=3 name=img4></a>
								</td>
							 </tr>
						</table> 
					</td>
				</tr>
			</td>
		</tr>
	</table>
	</form>
	</td>
</tr>
</table>

 <!-- copyright 영역 삽입-->
  

</body>
</html>
