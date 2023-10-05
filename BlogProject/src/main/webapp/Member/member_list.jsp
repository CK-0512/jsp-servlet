<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Admin Page - MemberList" />
<%@ include file="../common/menubar.jsp"%>
<section>
	<div class="container mx-auto px-3 text-xl">
		<div class="mb-2 flex justify-between items-end">
			<div>
				<span>회원수 : ${totcount }명</span>
			</div>
			<form>
				<select data-value="${search }"
					class="select select-accent select-sm" name="search">
					<option value="loginId,name,nickname">전체</option>
					<option value="loginId">아이디</option>
					<option value="name">이름</option>
					<option value="nickname">닉네임</option>
				</select> <input class="ml-2 input input-bordered input-accent input-sm w-64"
					name="key" placeholder="검색어를 입력해주세요" maxlength="20"
					value="${key }" />
				<button class="ml-2 btn btn-accent btn-sm">검색</button>
			</form>
		</div>
		<c:choose>
			<c:when test="${membersCnt == 0 }">
				<div class="text-center py-2">조건에 일치하는 검색결과가 없습니다</div>
			</c:when>
			<c:otherwise>
				<div class="table-box-type-1">
					<table class="table w-full">
						<thead>
							<tr>
								<th><input type="checkbox" class="checkbox-all-member-id" /></th>
								<th>번호</th>
								<th>아이디</th>
								<th>닉네임</th>
								<th>이메일</th>
								<th>삭제여부</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="member" items="${list }">
								<tr class="hover">
									<c:choose>
										<c:when test="${member.delStatus != 1 }">
											<td><input type="checkbox" class="checkbox-member-id"
												value="${member.id }" /></td>
										</c:when>
										<c:otherwise>
											<td><input type="checkbox" class="checkbox-member-id"
												value="${member.id }" disabled /></td>
										</c:otherwise>
									</c:choose>
									<td>${member.id }</td>
									<td>${member.userid }</td>
									<td>${member.nickname }</td>
									<td>${member.email }</td>
									<td>${member.delStatus }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:otherwise>
		</c:choose>

		<script>
				$('.checkbox-all-member-id').change(function() {
					const allCheck = $(this);
					const allChecked = allCheck.prop('checked');
					$('.checkbox-member-id').prop('checked', allChecked);
					$('.checkbox-member-id:is(:disabled)').prop('checked', false);
				})
				
				$('.checkbox-member-id').change(function() {
					const checkboxMemberIdCount = $('.checkbox-member-id').length;
					const checkboxMemberIdCheckedCount = $('.checkbox-member-id:checked').length;
					const checkboxDisabledCount = $('.checkbox-member-id:is(:disabled)').length;
					const allChecked = (checkboxMemberIdCount - checkboxDisabledCount) == checkboxMemberIdCheckedCount;
					$('.checkbox-all-member-id').prop('checked', allChecked);
				})
			</script>

		<div class="mt-2 flex justify-end">
			<button class="btn btn-accent btn-sm btn-delete-selected-members">회원
				삭제</button>
		</div>

		<form action="/Member?cmd=member_delete_pro.do" method="POST"
			name="do-delete-members-form">
			<input type="hidden" name="ids" value="" />
			<input type="hidden" name="page" value="${page}">
		</form>

		<script>
				$('.btn-delete-selected-members').click(function() {
					const values = $('.checkbox-member-id:checked').map((index, el) => el.value).toArray();
					
					if (values.length == 0) {
						alert('선택한 회원이 없습니다');
						return;
					}
					if (confirm('선택한 회원을 삭제하시겠습니까?') == false) {						
						return;
					}
					$('input[name=ids]').val(values.join(','));
					$('form[name=do-delete-members-form]').submit();
				})
			</script>

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
<%@ include file="../common/foot.jsp"%>