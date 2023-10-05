<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="알림 확인" />
<%@ include file="../common/menubar.jsp" %>

<section>
	<div class="flex-1 border-2 rounded-md border-gray-400 p-2 m-4">
		<div><h2>새로운 알림(${totcount })</h2></div>
		<c:forEach var="notice" items="${list }">
			<c:if test="${notice.checkStatus == 0}">
				<div class="flex justify-between px-4 pt-4 pb-3 border-4 rounded-3xl border-blue-100 my-3">
					<span>알림 번호 : ${notice.id}</span>
					<c:if test="${notice.type == 2}">
						<a href="/Notice?cmd=notice_view&id=${notice.id}" class="text-lg">새 댓글이 달렸습니다!</a>
					</c:if>
					<c:if test="${notice.type == 1}">
						<a href="/Notice?cmd=notice_view&id=${notice.id}" class="text-lg">새 질문글이 작성되었습니다.</a>
					</c:if>
					<span>등록일 : ${notice.regDate }</span>
				</div>
			</c:if>
			<c:if test="${notice.checkStatus == 1}">
				<div class="flex justify-between px-4 pt-4 pb-3 border-4 rounded-3xl border-blue-100 my-3 bg-gray-300">
					<span>알림 번호 : ${notice.id}</span>
					<c:if test="${notice.type == 2}">
						<a href="/Notice?cmd=notice_view&id=${notice.id}" class="text-lg">새 댓글이 달렸습니다!</a>
					</c:if>
					<c:if test="${notice.type == 1}">
						<a href="/Notice?cmd=notice_view&id=${notice.id}" class="text-lg">새 질문글이 작성되었습니다.</a>
					</c:if>
					<span>등록일 : ${notice.regDate }</span>
				</div>
			</c:if>
		</c:forEach>
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