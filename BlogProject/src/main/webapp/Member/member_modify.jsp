<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Setting" />
<%@ include file="../common/menubar.jsp"%>

<section>
	<div class="flex">
		<div class="container mx-auto">
			<form action="/Member?cmd=member_modify_pro.do" method="POST">
				<div class="table-box-type-1">
					<table class="table">
						<colgroup>
							<col width="200" />
						</colgroup>
						<tbody>
							<tr>
								<th>가입일</th>
								<td>${member.regDate }</td>
							</tr>
							<tr>
								<th>로그인 아이디</th>
								<td>${member.userid }</td>
							</tr>
							<tr>
								<th>닉네임</th>
								<td><input class="input input-bordered input-accent w-96"
									type="text" name="nickname" placeholder="닉네임을 입력해주세요"
									value="${member.nickname }" /></td>
							</tr>
							<tr>
								<th>이메일</th>
								<td><input class="input input-bordered input-accent w-96"
									type="text" name="email" placeholder="이메일을 입력해주세요"
									value="${member.email }" /></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="mt-2 flex justify-between">
					<button class="btn btn-accent btn-sm" onclick="history.back();">뒤로가기</button>
					<div>
						<button class="btn btn-accent btn-sm">수정</button>
					</div>
				</div>
			</form>
		</div>
		<%@ include file="../common/sidebar.jsp"%>
	</div>
</section>

<%@ include file="../common/foot.jsp"%>