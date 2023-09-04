<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	body, ul, li {list-style: none; margin: 0; padding: 0;}
	a {text-decoration: none; color: inherit;}
	#header {background-color: blue; text-align: center; padding: 3px;}
	#header > h1 {height:100%; color: white; font-size: 2rem;}
	ul {background-color: purple; display: flex;}
	ul > li {padding : 10px; margin-left: 10px;}
	ul > li > a {display: block; color: white; cursor: pointer;}
	#contents {background-color: gray; padding: 3px; padding-bottom : 50px;}
	#contents > h2 {text-align: center; height: 100%}
	#table {position: relative; left: 30%; width: 40%}
	th, td {border: 1px solid black;}
	#button {text-align: center;}
	#fotter {background-color: blue; text-align: center; padding: 10px;}
</style>
<script>
	function send() {
		if(!join.hakbun.value.trim()) {
			alert("학번이 입력되지 않았습니다.");
			join.hakbun.focus();
			return false;
		}
		if(!join.name.value.trim()) {
			alert("이름이 입력되지 않았습니다.");
			join.name.focus();
			return false;
		}
		if(!join.tel1.value.trim() || !join.tel2.value.trim() || !join.tel3.value.trim()) {
			alert("전화번호가 입력되지 않았습니다.");
			join.tel1.focus();
			return false;
		}
		let gender = join.gender;
		let check = false;
		gender.forEach(radiobox => {
			if(radiobox.checked) {
				check = true;
			}
		})
		if (!check) {
			alert("성별이 선택되지 않았습니다.");
			gender.focus;
			return false;
		}
		
		if(!join.birth.value.trim()) {
			alert("생년월일이 입력되지 않았습니다.");
			join.birth.focus();
			return false;
		}
		if(!join.regdate.value.trim()) {
			alert("등록일자가 입력되지 않았습니다.");
			join.regdate.focus();
			return false;
		}
		
		join.submit();
	}
	
	function re() {
		alert("정보를 지우고 처음부터 다시 입력합니다.");
		join.reset();
		join.hakbun.focus();
	}
</script>
<body>
	<section>
		<div id="header">
			<h1>(과정평가형 정보처리산업기사)학생관리 프로그램 ver 1.0</h1>
		</div>
		<ul>
			<li><a href="join.jsp">학생정보등록</a></li>
			<li><a>학생정보조회</a></li>
			<li><a>성적정보등록</a></li>
			<li><a>학생성적조회</a></li>
			<li><a>학년성적조회</a></li>
			<li><a>홈으로</a></li>
		</ul>
		<div id="contents">
			<h2>학생 정보 등록</h2>
			<form method="post" name="join" action="join_pro.jsp">
				<table id="table">
					<tr>
						<th>학번</th>
						<td>
							<input name="hakbun"/>
							<span>예) 1101</span>
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>
							<input name="name"/>
						</td>
					</tr>
					<tr>
						<th>전화</th>
						<td>
							<input name="tel1" size=3/>-
							<input name="tel2" size=4/>-
							<input name="tel3" size=4/>
						</td>
					</tr>
					<tr>
						<th>성별</th>
						<td>
							<input type="radio" name="gender" value="M"/>남자
							<input type="radio" name="gender" value="F"/>여자
						</td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td>
							<input name="birth"/>
							<span>예) 19971107</span>
						</td>
					</tr>
					<tr>
						<th>등록일자</th>
						<td>
							<input name="regdate"/>
							<span>예) 20200207</span>
						</td>
					</tr>
					<tr id="button">
						<td colspan="2">
							<input type="button" onClick="send()" value="등록하기"/>
							<input type="button" onClick="re()" value="다시쓰기"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="fotter">
			<span>HRDKOREA Copyright@2016 All rights reserve. Human
				Resources Development Serivce of Korea</span>
		</div>
	</section>
</body>
</html>