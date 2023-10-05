<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="CK's BLOG" />
<%@ include file="../common/menubar.jsp" %>
<%@ include file="../common/toastUIEditorLib.jsp" %>


<section class="mt-24">
	<section class="flex-1">
		<section>
		<div class="container mx-auto pb-5">
			<div class="table-box-type-1">
				<table class="table">
					<colgroup>
						<col width="200" />
					</colgroup>
					<tbody>
						<tr>
							<th>블로그 생성일</th>
							<td>${blog.regDate }</td>
						</tr>
						<tr>
							<th>마지막 글 작성일</th>
							<td>${blog.updateDate }</td>
						</tr>
						<tr>
							<th>소개글</th>
							<td>
								<div class="toast-ui-viewer">
  									<script type="text/x-template">${blog.introduction }</script>
  								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="mt-2">
				<c:if test="${rq.loginedMember.authLevel == 0 }">
					<a class="btn btn-accent btn-sm" href="/BlogInformation?cmd=blog_modify">수정</a>
				</c:if>
			</div>
		</div>
	</section>
	</section>
	<%@ include file="../common/sidebar.jsp" %>
</section>

<%@ include file="../common/foot.jsp" %>