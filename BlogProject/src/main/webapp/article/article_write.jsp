<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="write" />
<%@ include file="../common/head.jsp" %>
<%@ include file="../common/toastUIEditorLib.jsp" %>

	<section class="mt-8">
		<div class="container mx-auto">
			<form action="/Article?cmd=article_write_pro.do" method="POST" onsubmit="submitForm(this); return false;">
				<input type="hidden" name="body" />
				<div class="table-box-type-1">
					 <c:if test="${rq.loginedMember.authLevel == 3 }">
						<div class="w-full max-w-xs mb-2">
							<span class="text-gray-700 font-bold text-sm ml-4 mr-2">게시판 선택</span>
							<select data-value="${boadrdId }" name="boardId" class="select select-accent select-bordered">
								<option value="1">일상</option>
							    <option value="2">공부</option>
								<option value="3">기업 정보</option>
								<option value="4">구인 정보</option>
								<option value="5">일정표</option>
								<option value="6">질의 응답</option>
							</select>
						</div>
					</c:if>
					<table class="table">
						<colgroup>
							<col width="200" />
						</colgroup>
						<tbody>
							<tr>
								<th>제목</th>
								<td><input class="input input-bordered input-accent w-full" type="text" name="title" placeholder="제목을 입력해주세요" /></td>
							</tr>
							<tr>
								<th>내용</th>
								<td>
									<div class="toast-ui-editor">
								    	<script type="text/x-template"></script>
								    </div>
								</td>
							</tr>
							<tr>
								<td colspan="2"><button class="btn btn-accent btn-sm">작성</button></td>
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