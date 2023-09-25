<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="sidebar">
	<div>
		<h2>${blogInformation.introduction }</h2>
		<h4>오늘의 방문자 수 : ${blogInformation.todayVisitant }</h4>
		<h6>총 방문자 수 : ${blogInformation.totVisitant }</h6>
	</div>
	<div>
		<form action="Article?cmd=article_list.do">
			<select data-value="${searchKeywordType }" class="select select-accent select-sm w-28" name="searchKeywordType">
				<option value="title">제목</option>
				<option value="body">내용</option>
				<option value="title,body">제목 + 내용</option>
			</select>
			<input class="ml-2 input input-bordered input-accent input-sm w-64" type="text" name="searchKeyword" placeholder="검색어를 입력해주세요" maxlength="20" value="${searchKeyword }"/>
			<button class="ml-2 btn btn-accent btn-sm">검색</button>
		</form>
	</div>
	<ul>
		<li><a href="/Article?cmd=article_list&boardId=1.do">일상(${totFreeArticle })</a></li>
		<li><a href="/Article?cmd=article_list&boardId=2.do">공부(${totStudyArticle })</a></li>
		<li><a href="/Article?cmd=article_list&boardId=3.do">기업 정보(${totCompanyArticle })</a></li>
		<li><a href="/Article?cmd=article_list&boardId=4.do">구인 정보(${totOfferArticle })</a></li>
		<li><a href="/Article?cmd=article_list&boardId=5.do">일정표(${totScheduleArticle })</a></li>
		<li><a href="/Article?cmd=article_list&boardId=6.do">질의 응답(${totQuestionArticle })</a></li>
	</ul>
</div>