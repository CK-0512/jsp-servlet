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
	#header > h1 {color: white; font-size: 1.5rem;}
	#menubar {background-color: #BCA9F5; display: flex; padding: 5px;}
	#menubar > li > a {display: block; padding: 10px; color: purple; cursor: pointer; margin-left: 10px;}
	#contents {background-color: #E6E6E6; padding: 20px 0 40px 10px;}
	#contents > h2 {text-align: center;}
	#contents  table {position: relative; width: 40%; left: 30%;}
	th, td {border: 1px solid black;}
	table tr:last-child {text-align: center;}
	#footer {text-align: center; background-color: blue; padding: 10px;}
</style>
<script>
	function send() {
		if(!regist.bun.value.trim()) {
			alert("대출번호를 입력해주세요.");
			regist.bun.focus();
			return;
		}
		if(!regist.name.value.trim()) {
			alert("이름을 입력해주세요.");
			regist.name.focus();
			return;
		}
		if(!regist.tel.value.trim()) {
			alert("전화번호를 입력해주세요.");
			regist.tel.focus();
			return;
		}
		let gender = regist.gender;
		let check = false;
		gender.forEach(radiobox => {
			if(radiobox.checked) {
				check = true;
			}
		})
		if(!check) {
			alert("성별을 선택해주세요.");
			return;
		}
		if(!regist.code.value.trim()) {
			alert("도서코드를 입력해주세요.");
			regist.code.focus();
			return;
		}
		if(!regist.date.value.trim()) {
			alert("대출일자를 입력해주세요.");
			regist.date.focus();
			return;
		}
		regist.action = "regist_pro.jsp";
		regist.submit();
	}
	
	function re() {
		alert("모든정보를 지우고 다시 입력합니다.");
		regist.reset();
	}
</script>
<body>
	<section>
		<div id="header">
			<h1>(과정평가형 기사)도서 관리 프로그램 ver 2.0</h1>
		</div>
		<ul id="menubar">
			<li><a href="regist.jsp">도서대출</a></li>
			<li><a>도서대출현황조회</a></li>
			<li><a>도서반납</a></li>
			<li><a>도서등록</a></li>
			<li><a>도서별대출현황조회</a></li>
			<li><a>홈으로</a></li>
		</ul>
		<div id="contents">
			<h2>과정평가형 자격 CBQ</h2>
			<br>
			<form method="post" name="regist">
				<table>
					<tr>
						<th>대출번호</th>
						<td><input name="bun"/> 정수 4자리(1001)</td>
					</tr>
					<tr>
						<th>고객성명</th>
						<td><input name="name"/></td>
					</tr>
					<tr>
						<th>고객전화</th>
						<td><input name="tel"/></td>
					</tr>
					<tr>
						<th>회원성</th>
						<td>
							<input type="radio" name="gender" value="M"/>남자
							<input type="radio" name="gender" value="F"/>여자
						</td>
					</tr>
					<tr>
						<th>도서코드</th>
						<td><input name="code"/></td>
					</tr>
					<tr>
						<th>대출일자</th>
						<td><input name="date"/> 예)20200207</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" onClick="send()" value="등록하기"/>
							<input type="button" onClick="re()" value="다시쓰기"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="footer">
			<span>HRDKOREA Copyright@2016 All rights reserve. Human
				Resources Development Serivce of Korea</span>
		</div>
	</section>
</body>
</html>