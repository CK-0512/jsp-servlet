<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../common/head.jsp" %>

<script>
	function Theme_toggle() {
		const theme = localStorage.getItem("theme") ?? "light";
		
		if (theme == 'light') {
			localStorage.setItem("theme", "dark");
		} else {
			localStorage.setItem("theme", "light");
		}
		
		location.reload();
	}
	
	function Theme_applyTo(themeName) {
		$('html').attr('data-theme', themeName);
	}
	
	function Theme_init() {
		const theme = localStorage.getItem("theme") ?? "light";
		Theme_applyTo(theme);
	}
	
	Theme_init();
</script>

</head>
<body>

	<div class="navbar bg-base-300 fixed rounded-3xl top-0 z-10">
		<div class="navbar-start">
			<div class="dropdown">
				<label tabindex="0" class="btn btn-ghost btn-circle"> <svg
						xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none"
						viewBox="0 0 24 24" stroke="currentColor">
					<path stroke-linecap="round" stroke-linejoin="round"
							stroke-width="2" d="M4 6h16M4 12h16M4 18h7" /></svg>
				</label>
				<ul tabindex="0"
					class="menu menu-sm dropdown-content mt-3 z-[1] p-2 shadow bg-base-100 rounded-box w-28">
					<li><a href="/Article?cmd=article_list&boardId=1.do">일상</a></li>
					<li><a href="/Article?cmd=article_list&boardId=2.do">공부</a></li>
					<li><a href="/Article?cmd=article_list&boardId=3.do">기업 정보</a></li>
					<li><a href="/Article?cmd=article_list&boardId=4.do">구인 정보</a></li>
					<li><a href="/Article?cmd=article_list&boardId=5.do">일정표</a></li>
					<li><a href="/Article?cmd=article_list&boardId=6.do">질의 응답</a></li>
				</ul>
			</div>
		</div>
		<div class="navbar-center">
			<a href="/usr/home/main" class="btn btn-active normal-case text-bold text-5xl italic mb-2">C K A</a>
		</div>
		<div class="navbar-end">
			<div>
				<a class="h-full px-3 theme-toggle flex items-center" href="javascript:Theme_toggle();">
					<span><i class="fa-regular fa-sun"></i></span>
					<span><i class="fa-solid fa-sun"></i></span>
				</a>
			</div>
			<c:if test="${rq.getLoginedMemberId() != 0}">
				<div class="dropdown dropdown-end">
					<label tabindex="0" class="btn btn-ghost btn-circle mr-1">
						<div class="indicator">
							<svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none"
								viewBox="0 0 24 24" stroke="currentColor">
								<path stroke-linecap="round" stroke-linejoin="round"
									stroke-width="2"
									d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9" /></svg>
							<span class="badge badge-xs badge-primary indicator-item">${rq.memberNotices.size() }</span>
						</div>
					</label>
					<div tabindex="0"
						class="mt-3 z-[1] card card-compact dropdown-content w-96 bg-base-100 shadow text-center">
						<div class="card-body">
							<c:if test="${rq.memberNotices.size() == 0 }">
								<span class="font-semibold text-base">신규 알림이 없습니다.</span>
							</c:if>
							<c:if test="${rq.memberNotices.size() != 0 }">
								<span class="font-semibold text-base">신규 알림이 ${rq.memberNotices.size() }개 있습니다.</span>
								<c:forEach var="memberNotice" items="${rq.memberNotices }">
									<div class="card-actions border-2">
										<a href="${rq.memberNotices.noticeUrl }">${rq.memberNotices.message }</a>
									</div>
								</c:forEach>
							</c:if>
						</div>
					</div>
				</div>
			</c:if>
			<div class="dropdown dropdown-end">
				<label tabindex="0" class="btn btn-ghost btn-circle avatar">
					<div class="w-10 rounded-full">
						<c:if test="${rq.getLoginedMemberId() == 0}">
							<i class="fa-regular fa-user text-3xl"></i>
						</c:if>
						<c:if test="${rq.getLoginedMemberId() != 0}">
							<i class="fa-solid fa-user text-3xl"></i>
						</c:if>
					</div>
				</label>
				<ul tabindex="0"
					class="menu menu-sm dropdown-content mt-3 z-[1] p-2 shadow bg-base-100 rounded-box w-22">
					<c:if test="${rq.getLoginedMemberId() == 0}">
						<li><a href="/Member?cmd=member_login.do">로그인</a></li>
						<li><a href="/Member?cmd=member_join.do">회원가입</a></li>
					</c:if>
					<c:if test="${rq.getLoginedMemberId() != 0}">
						<c:choose>
							<c:when test="${rq.getLoginedMember().authLevel != 3 }">
								<li><a class="items-center" href="/Member?cmd=member_detail.do">내정보</a></li>
								<li><a href="/Member?cmd=member_modify.do">정보수정</a></li>
							</c:when>
							<c:otherwise>
								<li><a class="items-center" href="/Member?cmd=member_list.do">회원관리</a></li>
								<li><a class="items-center" href="/BlogInformation?cmd=information_modify.do">블로그수정</a></li>
							</c:otherwise>
						</c:choose>
						<li><a href="/Member?cmd=member_logout_pro.do">로그아웃</a></li>
					</c:if>	
				</ul>
			</div>
		</div>
	</div>