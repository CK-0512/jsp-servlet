<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="sidebar justify-end h-full mx-8 px-4 border-2 rounded-xl border-green-200 w-1/5">
	<div class="border-b-2 my-1 border-green-200 text-center">
		<span class="text-3xl font-bold">환영합니다!</span>
		<br>
		<span class="text-sm">이 사이트는 정보정리를 위해 제작한 블로그입니다. 모든 정보는 공개되어 있으며 질의응답 게시판도 운용하고 있으니 많은 이용 바랍니다.</span>
	</div>
	<div class="border-b-2 pt-1 pb-2 border-green-200">
		<form action="Article?cmd=article_list">
			<select data-value="${search }" class="select select-accent select-sm w-28 ml-2 mb-1" name="search">
				<option value="title">제목</option>
				<option value="body">내용</option>
				<option value="title,body">제목 + 내용</option>
			</select>
			<input class="ml-2 input input-bordered input-accent input-sm w-52" type="text" name="key" placeholder="검색어를 입력해주세요" maxlength="20" value="${key }"/>
			<button class="ml-2 btn btn-accent btn-sm">검색</button>
		</form>
	</div>
	<ul class="py-2">
		<li><a href="/Article?cmd=article_list_all">전체글</a></li>
		<li><a href="/Article?cmd=article_list&boardId=1">일상</a></li>
		<li><a href="/Article?cmd=article_list&boardId=2">공부</a></li>
		<li><a href="/Article?cmd=article_list&boardId=3">기업 정보</a></li>
		<li><a href="/Article?cmd=article_list&boardId=4">구인 정보</a></li>
		<c:if test="${rq.loginedMember.authLevel == 0 }">	
			<li><a href="/Article?cmd=article_list&boardId=5">일정</a></li>
		</c:if>
		<li><a href="/Article?cmd=article_list&boardId=6">질의 응답</a></li>
	</ul>
</div>