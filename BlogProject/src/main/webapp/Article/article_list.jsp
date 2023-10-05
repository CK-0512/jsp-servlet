<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${boardName } 게시판" />
<%@ include file="../common/menubar.jsp" %>

<section>
	<div class="flex-1 border-2 rounded-md border-gray-400 p-2 m-4">
		<c:if test="${boardName == null }">
			<div><h2>전체글(${list.size() })</h2></div>
		</c:if>
		<c:if test="${boardName != null }">
			<div><h2>${boardName }(${list.size() })</h2></div>
		</c:if>
		<c:forEach var="article" items="${list }">
			<div class="flex justify-between px-4 pt-4 pb-3 border-4 rounded-3xl border-blue-100 my-3">
				<div>
					<c:if test="${boardName == null }">
						<span class="border-r-2 pr-2 py-5 border-blue-100 font-bold">${article.boardName }</span>
					</c:if>
					<c:if test="${boardName != null }">
						<span class="border-r-2 pr-2 py-5 border-blue-100 font-bold">${boardName }</span>
					</c:if>
					<span>조회수 : ${article.hitCnt }</span>
				</div>
				<a href="/Article?cmd=article_view&id=${article.id }&page=${page }" class="text-lg">${article.title }</a>
				<span>작성일 : ${article.regDate }</span>
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
		<div align="center">
	        <table width="700" border="0" cellspacing="0" cellpadding="5">
	          <tr>&nbsp;</tr><tr>
	             <td colspan="5">        
	                <div align="center">${pageSkip}</div>
				  </td>
				 </tr>
			</table>
		</div>
	</div>
	<%@ include file="../common/sidebar.jsp" %>
</section>

<%@ include file="../common/foot.jsp" %>