<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.member.*" %>
<%
	MemberDTO member = (MemberDTO)session.getAttribute("user");


%>
<html>
<head>
<title>회원수정</title>
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
	//아이디 중북검사
	function id_check(){
		url="/Member/userinfo_idcheck.do";
		window.open(url,"ID 중복검사","width=350 height=250");
	}
/*	
	//우편번호 검색
	function post_check(){
		url="post_check.do";
		window.open(url,"우편번호검색","width=350 height=250 scrollbars=yes");		
	}
*/	
	//입력폼 검사
	function send(){
		var tel = "";
		var zip = "";
		var form = document.member;
		
		if(form.name.value==""){
			alert("회원 성명을 입력하세요.");
			form.name.focus();
			return;
		}

		if(form.userid.value==""){
			alert("회원ID를 입력하세요.");
			form.userid.focus();
			return;
		}else if(form.userid.value.length < 5){
			alert("회원ID는 5자 이상 입력하셔야 합니다.");
			form.userid.focus();
			return;
		}else if(!a_or_d(form.userid.value)){
			alert("회원ID는 영문이나 숫자로 입력하셔야 합니다.");
			form.userid.focus();
			return;
		}
		
		if(form.passwd.value==""){
			alert("비밀번호를 입력하세요.");
			form.passwd.focus();
			return;
		}else if(form.passwd.value.length < 5){
			alert("비밀번호는 4자 이상 입력하셔야 합니다.");
			form.passwd.focus();
			return;
		}else if(!a_or_d(form.passwd.value)){
			alert("비밀번호는 영문이나 숫자로 입력하셔야 합니다.");
			form.passwd.focus();
			return;
		}
			
		if(form.passwd.value != form.repasswd.value){
			alert("비밀번호와 비밀번호확인의 값이 서로 같지 않습니다.");
			form.repasswd.focus();
			return;
		}

	//전화번호 체크
		var tel = form.tel.value
		if(tel==""){
			alert("Tel Check");
			form.tel.focus();
			return
		}else if(!tel_check(tel)){
			alert("전화번호형식오류 ");
		    form.tel.focus();
		    return ;
		}
		
		//이메일 유효성 검사
		var email = form.email.value;
		if(email==""){
			alert("Email 입력하세요");
			form.email.focus();
			return;
		}
		
/*		else if(!email_check(email)){
			alert("Email 형식오류 ");
		    form.email.focus();
		    return;
		}
*/
		//관심분야 1개 이상 체크
		var sw=0;
		for(var s=0;s<form.fa.length;s++){
			if(form.fa[s].checked){
				sw=1;
				break;
			}
		}
		
		if(sw==0){
			alert("관심분야는 한개이상 선택");
			return;
		}

		// 직업선택
		if(form.job.selectedIndex ==0 ){
			alert("직업을 선택하세요.");
			form.job.focus();
			return;
		}
		form.submit();
	}

	//입력시 사용된 문자검사(영문자, 숫자 이외의 다른 문자 사용 여부 검사)
	function a_or_d(str){
		lower_str = str.toLowerCase();
		
		for(i=0; i<lower_str.length; i++){
			ch = lower_str.charAt(i);
			if(((ch < 'a') || (ch > 'z')) && ((ch < '0') ||(ch > '9')))
				return 0;
		}
		return 1;
	}	
	//전화번호의 유효성 검사
	function tel_check(str){
		/*
		   전화번호 유효성 체크
		   010-xxxx-xxxx 또는  010xxxxxxxx
		  성공이면 true 실패이면 false
		*/
		str=str.split('-').join('');
		var phone=/^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;
		return phone.test(str);
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
  <td width="80%" valign="top">&nbsp;<img src="/Member/img/title1.gif" ><br>    
	<form name="member" method="post" action="/Member/userinfo_modify.do">
	<table border=0 cellpadding=0 cellspacing=0 border=0 width=730 valign=top>
		<tr><td align=center><br>                            
			<table cellpadding=0 cellspacing=0 border=0 width=650 align=center>       
				<tr>
					<td bgcolor="#7AAAD5">            
						<table cellpadding=0 cellspacing=0 border=0 width=100%>
							<tr bgcolor=#7AAAD5>
								<td align=left BORDER="0" HSPACE="0" VSPACE="0"><img src="/Member/img/u_b02.gif"></td>
								<td align=center bgcolor="#7AAAD5"><FONT COLOR="#FFFFFF"><b>사용자정보수정&nbsp;</b><font color=black>(</font><font color=red>&nbsp;*&nbsp;</font><font color=black>표시항목은 반드시 입력하십시요.)</font></FONT></td>
								<td align=right BORDER="0" HSPACE="0" VSPACE="0"><img src="/Member/img/u_b03.gif"></td>
							</tr>
						</table>
						<table cellpadding=3 cellspacing=1 border=0 width=100%>
							<tr>
								<td width=110 bgcolor=#EFF4F8>&nbsp;회원 성명<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<input type=text name=name size=16 maxlength=20 value="<%= member.getName() %>">성명은 빈칸없이 입력하세요.
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;회원 ID<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<table cellspacing=0 cellpadding=0>
										<tr>
											<td align=absmiddle>
												<input type=text name=userid size=12 maxlength=16 value="<%= member.getUserid() %>" style="width:120">
											</td>
											<td><a href="javascript:id_check()"><img src="Member/img/u_bt01.gif" hspace=2 border=0 name=img1  align=absmiddle></a>
                   										5~16자 이내의 영문이나 숫자만 가능합니다.
                  							</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;비밀번호<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<input type=password name=passwd size=8 maxlength=12 style="width:80">
											6~12자 이내의 영문이나 숫자만 가능합니다.
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;비밀번호확인<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE><input type=password name=repasswd size=8 maxlength=12 value="" style="width:80">비밀번호 확인을 위해서 비밀번호를 한번 더 입력해주세요. </td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;주소구분<font color=red>&nbsp;</font></td>
								<TD BGCOLOR=WHITE>
									<input type=radio name=gubun value="직장" <% if(member.getGubun().equals("직장")){ %> checked <% } %>>직장&nbsp;&nbsp;
									<input type=radio name=gubun value="자택" <% if(member.getGubun().equals("자택")){ %> checked <% } %>>자택 
								</td>
							</tr>
							
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;우편번호<font color=red>&nbsp;</font></td>
								<TD BGCOLOR=WHITE>
									<table cellspacing=0 cellpadding=0>
										<tr>
											<td><input type=text name=zip size=6 readonly maxlength=6 ></td>
                  							<td><a href="javascript:post_check()"><img src="/Member/img/u_bt07.gif" hspace=2 border=0 name=img2 align=absmiddle></a></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;주소<font color=red>&nbsp;</font></td>
								<TD BGCOLOR=WHITE>
									<input type=text name=addr1 size=50 maxlength=100 value="">
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;나머지 주소<font color=red>&nbsp;</font></td>
								<TD BGCOLOR=WHITE>
									<input type=text name=addr2 size=50 maxlength=100 value="">
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;전화번호<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<input type=text name=tel size=13 maxlength=13 value="<%= member.getTel() %>">
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;E-mail<font color=red>&nbsp;*</font>
								</td>
								<td bgcolor=WHITE valign=middle>
									<input type=text name=email size=30 maxlength=30 value="<%= member.getEmail() %>">
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;관심분야
                					<font color=red>&nbsp;</font>
								</td>
								<td bgcolor=WHITE valign=middle> 
									<input type="checkbox" name="fa" value="건강" <% if(member.getFavorite().contains("건강")){ %> checked <% } %>>건강 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;직업<font color=red>&nbsp;</font></td>
								<TD BGCOLOR=WHITE>
									<select name=job class="formbox">
 										<option value="0">선택하세요 ---
 										<option value="회사원" <% if(member.getJob().equals("회사원")) { %> selected <% } %>>회사원
 										<option value="연구전문직">연구전문직
 										<option value="교수학생">교수학생
 										<option value="일반자영업">일반자영업
 										<option value="공무원">공무원
 										<option value="의료인">의료인
 										<option value="법조인">법조인
 										<option value="종교,언론,에술인">종교.언론/예술인
 										<option value="농,축,수산,광업인">농/축/수산/광업인
 										<option value="주부">주부
 										<option value="무직">무직
 										<option value="기타">기타
									</select>
								</td>                
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;자기소개<font color=red>&nbsp;</font></td>
								<TD BGCOLOR=WHITE>
									<textarea cols=65 rows=5 name="intro"></textarea>
								</td>
							</tr>
						</table>
						<table cellpadding=0 cellspacing=0 border=0 width=100%>
							<tr bgcolor=#7AAAD5>
								<td valign=bottom>
									<img src="/Member/img/u_b04.gif" align=left hspace=0 vspace=0 border=0>
								</td>
								<td align=center></td>
								<td valign=bottom>
									<img src="/Member/img/u_b05.gif" align=right hspace=0 vspace=0 border=0>
								</td>
							</tr>
							<tr bgcolor=#ffffff>
								<td colspan=3 align=center>
									<a href="javascript:send()"><img src="/Member/img/u_bt06.gif" vspace=3 border=0 name=img3></a>
									<a href="javascript:history.back()"><img src="/Member/img/u_bt05.gif" border=0 hspace=10 vspace=3 name=img4></a>
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
