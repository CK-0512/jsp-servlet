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
	section > ul > li > a {color:white; display:block; padding: 10px; margin-left: 15px; text-decoration: none;}
	section > .contents {background-color: gray; height: 100%; padding: 16px;}
	section > .contents > h2 {text-align: center;}
	section > .footer {text-align: center; background-color: blue; padding:15px;}
</style>
</head>
<body>
	<section>
		<div class="header">
			<h1>쇼핑몰 회원관리</h1>
		</div>
		<ul>
			<li><a href="regist.jsp">등록</a></li>
			<li><a>조회및수정</a></li>
			<li><a>조회</a></li>
			<li><a>홈</a></li>
		</ul>
		<div class="contents">
			<h2>과정평가형 자격 CBQ</h2>
			<br>
			<span>국가직무능력표준(NCS:National Competency Standards)으로 설계된
				교육*훈련과정을 체계적으로 이수하고 내외부 평가를 거쳐 취득하는 국가기술자격입니다.</span> <br><br> <span>산업현장
				중심의 교육평가로 더 커지는 능력!</span> <br><br> <span>알고 있는 것에 할 수 있는 것을 더하는</span> <br><br>
			<span>과정평가형 자격은</span> <br><br> <span>현장 중심형 인재육성을 지원 합니다.</span>
		</div>
		<div class="footer">
			<span>HRDKOREA Copyright@2016 All rights reserve. Human
				Resources Development Serivce of Korea</span>
		</div>
	</section>
</body>
</html>