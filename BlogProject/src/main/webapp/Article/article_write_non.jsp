<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="write" />
<%@ include file="../common/menubar.jsp" %>
<%@ include file="../common/toastUIEditorLib.jsp" %>

	<section class="mt-24">
		<div class="container mx-auto">
			<form action="/Article?cmd=article_write_non_pro" method="POST" onsubmit="submitForm(this); return false;">
				<input type="hidden" name="page" value="${page }"/>
				<input type="hidden" name="body" />
				<div class="table-box-type-1">
					<span class="text-gray-700 font-bold text-2xl">질문 등록</span>
					<table class="table">
						<colgroup>
							<col width="200" />
						</colgroup>
						<tbody>
							<tr>
								<th>닉네임</th>
								<td><input class="input input-bordered input-accent w-full" type="text" name="nickname" placeholder="닉네임을 입력해주세요" /></td>
								<th>비밀번호</th>
								<td><input class="input input-bordered input-accent w-full" type="text" name="pass" placeholder="비밀번호를 입력해주세요" /></td>
							</tr>
							<tr>
								<th>제목</th>
								<td colspan="3"><input class="input input-bordered input-accent w-full" type="text" name="title" placeholder="제목을 입력해주세요" /></td>
							</tr>
							<tr>
								<th>내용</th>
								<td colspan="3">
									<div class="toast-ui-editor">
								    	<script type="text/x-template"></script>
								    </div>
								</td>
							</tr>
							<tr>
								<td colspan="4"><button class="btn btn-accent btn-sm">작성</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			<div class="mt-2">
				<button class="btn btn-accent btn-sm" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
		<%@ include file="../common/sidebar.jsp" %>
	</section>
	
<%@ include file="../common/foot.jsp" %>