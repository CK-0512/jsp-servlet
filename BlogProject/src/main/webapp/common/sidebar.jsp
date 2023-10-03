<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="sidebar justify-end h-full mx-8 px-4 border-2 rounded-xl border-green-200">
	<div class="border-b-2 my-1 border-green-200">
		<h2>${blogInformation.introduction }</h2>
		<h4>오늘의 방문자 수 : ${blogInformation.todayVisitant }</h4>
		<h6>총 방문자 수 : ${blogInformation.totVisitant }</h6>
	</div>
	<div class="border-b-2 pt-1 pb-2 border-green-200">
		<form action="Article?cmd=article_list">
			<select data-value="${search }" class="select select-accent select-sm w-28" name="search">
				<option value="title">제목</option>
				<option value="body">내용</option>
				<option value="title,body">제목 + 내용</option>
			</select>
			<input class="ml-2 input input-bordered input-accent input-sm w-64" type="text" name="key" placeholder="검색어를 입력해주세요" maxlength="20" value="${key }"/>
			<button class="ml-2 btn btn-accent btn-sm">검색</button>
		</form>
	</div>
	<ul class="py-2">
		<li><a href="/Article?cmd=article_list&boardId=1">일상(${totFreeArticle })</a></li>
		<li><a href="/Article?cmd=article_list&boardId=2">공부(${totStudyArticle })</a></li>
		<li><a href="/Article?cmd=article_list&boardId=3">기업 정보(${totCompanyArticle })</a></li>
		<li><a href="/Article?cmd=article_list&boardId=4">구인 정보(${totOfferArticle })</a></li>
		<c:if test="${rq.loginedMember.authLevel == 0 }">	
			<li><a href="/Article?cmd=article_list&boardId=5">일정표(${totScheduleArticle })</a></li>
		</c:if>
		<li><a href="/Article?cmd=article_list&boardId=6">질의 응답(${totQuestionArticle })</a></li>
	</ul>
</div>