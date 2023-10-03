<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="CK's BLOG" />
<%@ include file="../common/menubar.jsp" %>

<section>
	<div class="flex-1 border-2 rounded-md border-gray-400 p-2 m-4">
		<div><h2>전체글(${list.size() })</h2></div>
		<c:forEach var="article" items="${list }">
			<div class="flex justify-between p-4 border-4 rounded-3xl border-blue-100 my-3">
				<span>작성일 : ${article.regDate }</span>
				<a href="/Article?cmd=article_view&id=${article.id }&page=${page }" class="self-start">${article.title }</a>
				<span>조회수 : ${article.hitCnt }</span>
			</div>
		</c:forEach>
	</div>
	<%@ include file="../common/sidebar.jsp" %>
</section>

<%@ include file="../common/foot.jsp" %>