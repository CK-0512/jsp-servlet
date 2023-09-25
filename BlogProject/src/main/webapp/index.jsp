<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="CK's BLOG" />
<%@ include file="../common/menubar.jsp" %>

<section class="flex">
	<div>
		<div><h2>전체글(${articles.size() })</h2></div>
		<c:forEach var="article" items="${articles }">
			<div class="flex justify-end">
				<a href="/Article?cmd=article_detail?id=${article.id }.do" class="self-start">${article.title }</a>
				<span>작성일 : ${article.regDate }</span>
				<span>조회수 : ${article.hitCount }</span>
			</div>
		</c:forEach>
	</div>
	<%@ include file="../common/sidebar.jsp" %>
</section>

<%@ include file="../common/foot.jsp" %>