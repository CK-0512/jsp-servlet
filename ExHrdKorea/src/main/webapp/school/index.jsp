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
	#contents > span {padding : 10px;}
	#fotter {background-color: blue; text-align: center; padding: 10px;}
</style>
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
			<h2>과정평가형 자격 CBQ</h2>
			<br>
			<span>국가직무능력표준(NCS:National Competency Standards)으로 설계된
				교육*훈련과정을 체계적으로 이수하고 내외부 평가를 거쳐 취득하는 국가기술자격입니다.</span> <br><br> <span>산업현장
				중심의 교육평가로 더 커지는 능력!</span> <br><br> <span>알고 있는 것에 할 수 있는 것을 더하는</span> <br><br>
			<span>과정평가형 자격은</span> <br><br> <span>현장 중심형 인재육성을 지원 합니다.</span>
		</div>
		<div id="fotter">
			<span>HRDKOREA Copyright@2016 All rights reserve. Human
				Resources Development Serivce of Korea</span>
		</div>
	</section>
</body>
</html>