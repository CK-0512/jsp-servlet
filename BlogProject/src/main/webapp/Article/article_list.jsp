<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${boardName } 게시판" />
<%@ include file="../common/menubar.jsp" %>

<section>
	<div class="flex-1 border-2 rounded-md border-gray-400 p-2 m-4">
		<div><h2>${boardName }(${list.size() })</h2></div>
		<c:forEach var="article" items="${list }">
			<div class="flex justify-between p-4 border-4 rounded-3xl border-blue-100 my-3">
				<span>작성일 : ${article.regDate }</span>
				<a href="/Article?cmd=article_view&id=${article.id }&page=${page }" class="text-xl font-semibold">${article.title }</a>
				<span>조회수 : ${article.hitCnt }</span>
			</div>
		</c:forEach>
			<c:if test="${rq.loginedMember.authLevel != 0 }">
				<div class="mt-2 flex justify-end">
					<a class="btn btn-accent btn-sm" href="/Article?cmd=article_write&page=${page }">질문글 작성</a>
				</div>
			</c:if>
			<c:if test="${rq.loginedMember.authLevel == 0 }">
				<div class="mt-2 flex justify-end">
					<a class="btn btn-accent btn-sm" href="/Article?cmd=article_write&page=${page }">글 작성</a>
				</div>
			</c:if>	
		</div>
	<%@ include file="../common/sidebar.jsp" %>
</section>

<%@ include file="../common/foot.jsp" %>