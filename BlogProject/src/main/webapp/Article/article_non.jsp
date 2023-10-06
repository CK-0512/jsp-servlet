<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="passChk" />
<%@ include file="../common/menubar.jsp" %>
<%@ include file="../common/toastUIEditorLib.jsp" %>

<script>
	function passChk(pass, type) {
		let chkPass = document.getElementsByName("pass")[0];
		if (chkPass.value != pass) {
			alert("비밀번호 오류");
			chkPass.value = '';
			chkPass.focus();
			return;
		}
		
		if (type === 1) {
			let modifyUrl = "/Article?cmd=article_modify_non&id=" + ${id} + "&page=" + ${page};
			location.href = modifyUrl;
		} else {
			if (confirm("정말 삭제하시겠습니까?")) {
				let deleteUrl = "/Article?cmd=article_delete_pro&id=" + ${id} + "&page=" + ${page};
				location.href = deleteUrl;
			} else {
				return;
			}
		}
	}
</script>

	<section class="mt-24">
		<div class="flex-grow flex justify-center items-center">
			<div class="w-1/3 h-2/3 border-2 border-purple-600">
				<h3 class="relative inset-x-1/4 ml-6 top-1/4 mb-2">비밀번호를 입력하세요.</h3>
				<div class="relative inset-x-1/4 top-1/4 -ml-4 mb-2">
					<input class="input input-bordered input-accent" type="password" name="pass"/>
				</div>
				<div class="relative inset-x-1/3 ml-2 top-1/4 ml-2 mb-8">
					<a class="btn btn-accent btn-sm" href="javascript:history.back()">취소</a>
					<a class="btn btn-accent btn-sm" onClick="passChk(${pass}, ${btnType})">확인</a>
				</div>
			</div>
		</div>
		<%@ include file="../common/sidebar.jsp" %>
	</section>
	
<%@ include file="../common/foot.jsp" %>