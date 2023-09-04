<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	body, ul, li {color:inherit; list-style: none; margin: 0; padding: 0;}
	section > .header {background-color: blue; font-size:1.25rem; color: white; text-align: center; padding: 1px;}
	section > ul {background-color: purple; display: flex;}
	section > ul > li > a {color:white; display:block; padding: 10px; margin-left: 15px;}
	section > .contents {background-color: gray; height: 100%; padding: 16px;}
	section > .contents > h2 {text-align: center;}
	section > .contents table {left: 30%; position:relative;}
	th, td {border: 1px solid black;}
	section > .footer {text-align: center; background-color: blue; padding: 15px;}
</style>
<script>
	function send() {
		if(!regist.id.value.trim()) {
			alert("회원번호가 입력되지 않았습니다.");
			regist.id.focus();
			return;
		}
		if(!regist.name.value.trim()) {
			alert("회원성명이 입력되지 않았습니다.");
			regist.name.focus();
			return;
		}
		if(!regist.tel1.value.trim() || !regist.tel2.value.trim() || !regist.tel3.value.trim()) {
			alert("회원전화번호가 입력되지 않았습니다.");
			regist.tel1.focus();
			return;
		}
		
		let gender = regist.gender;
		let check = false;
		gender.forEach(radiobox => {
			if (radiobox.checked) {
				check = true;
			}
		})
		if(!check) {
			alert("회원성별이 선택되지 않았습니다.");
			gender.focus();
			return;
		}
		
		if(!regist.regDate.value.trim()) {
			alert("가입일자가 입력되지 않았습니다.");
			regist.regDate.focus();
			return;
		}
		
		if(!regist.grade.selectedIndex) {
			alert("고객등급이 선택되지 않았습니다.");
			regist.grade.focus();
			return;
		}
		if(!regist.city.selectedIndex) {
			alert("도시코드가 입력되지 않았습니다.");
			regist.city.focus();
			return;
		}
		
		alert("회원정보입력이 정상적으로 등록되었습니다.");
		regist.action = "regist_pro.jsp"
		regist.submit();
	}
	
	function re() {
		alert("입력한 모든 정보를 지우고 다시 입력합니다.");
		regist.reset();
	}
</script>
</head>
<body>
	<section>
		<div class="header">
			<h1>쇼핑몰 회원관리</h1>
		</div>
		<ul>
			<li><a>등록</a></li>
			<li><a>조회및수정</a></li>
			<li><a>조회</a></li>
			<li><a>홈</a></li>
		</ul>
		<div class="contents">
			<h2>회원등록</h2>
			<br>
			<form name="regist" method="post">
				<table>
					<tr>
						<th>회원번호(자동발생)</th>
						<td><input type="text" name="id"/></td>
					</tr>
					<tr>
						<th>회원성명</th>
						<td><input type="text" name="name"/></td>
					</tr>
					<tr>
						<th>회원전화</th>
						<td>
							<input type="text" name="tel1"/>-
							<input type="text" name="tel2"/>-
							<input type="text" name="tel3"/>
						</td>
					</tr>
					<tr>
						<th>회원성별</th>
						<td>
							<input type="radio" name="gender" value="남자"/>남자
							<input type="radio" name="gender" value="여자"/>여자	
						</td>
					</tr>
					<tr>
						<th>가입일자</th>
						<td><input type="text" name="regDate"/></td>
					</tr>
					<tr>
						<th>고객등급</th>
						<td>
							<select name="grade">
								<option value="A">vip</option>
								<option value="B">일반</option>
								<option value="C">직원</option>			
							</select>
						</td>
					</tr>
					<tr>
						<th>도시코드</th>
						<td>
							<select name="city">
								<option value="01">서울</option>
								<option value="02">경기</option>
								<option value="03">인천</option>			
								<option value="04">강원</option>
								<option value="05">충북</option>
								<option value="06">충남</option>		
								<option value="07">전북</option>
								<option value="08">전남</option>
								<option value="09">경북</option>		
								<option value="10">경남</option>
								<option value="11">제주</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="width: 100%; text-align: center;">
							<button onClick="send()">등록하기</button>
							<button onClick="re()">다시쓰기</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="footer">
			<span>HRDKOREA Copyright@2016 All rights reserve. Human
				Resources Development Serivce of Korea</span>
		</div>
	</section>
</body>
</html>