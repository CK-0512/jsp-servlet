<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Modify" />
<%@ include file="../common/menubar.jsp" %>
<%@ include file="../common/toastUIEditorLib.jsp" %>
<script>
function submitForm_blog(form){
	  
	  const editor = $(form).find('.toast-ui-editor').data('data-toast-editor');
	  const markdown = editor.getMarkdown().trim();
	  
	  if(markdown.length == 0){
	    alert('내용을 입력해주세요');
	    editor.focus();
	    return;
	  }
	  
	  form.body.value = markdown;
	  
	  form.submit();
	}
</script>

	<section class="mt-24">
		<div class="container mx-auto">
			<form action="/BlogInformation?cmd=blog_modify_pro" method="POST" onsubmit="submitForm_blog(this); return false;">
				<input type="hidden" name="body" />
				<div class="table-box-type-1">
					<table class="table">
						<colgroup>
							<col width="200" />
						</colgroup>
						<tbody>
							<tr>
								<th>내용</th>
								<td>
									<div class="toast-ui-editor">
								    	<script type="text/x-template">${blog.introduction }</script>
								    </div>
								</td>
							</tr>
							<tr>
								<td colspan="2"><button class="btn btn-accent btn-sm">수정</button></td>
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