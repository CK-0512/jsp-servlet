<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${article.title }" />
<%@ include file="../common/menubar.jsp" %>
<%@ include file="../common/toastUIEditorLib.jsp" %>


<section class="mt-24">
	<section class="flex-1">
		<section>
		<div class="container mx-auto pb-5 border-bottom-line">
			<div class="table-box-type-1">
				<table class="table">
					<colgroup>
						<col width="200" />
					</colgroup>
					<tbody>
						<tr>
							<th>작성일</th>
							<td>${article.regDate }</td>
						</tr>
						<tr>
							<th>수정일</th>
							<td>${article.updateDate }</td>
						</tr>
						<tr>
							<th>조회수</th>
							<td><span id="articleDetail_increaseHitCnt">${article.hitCnt }</span></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>${article.writerName }</td>
						</tr>
						<tr>
							<th>제목</th>
							<td>${article.title }</td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
								<div class="toast-ui-viewer">
  									<script type="text/x-template">${article.body }</script>
  								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="mt-2">
				<button class="btn btn-accent btn-sm" onclick="history.back();">뒤로가기</button>
				
				<c:choose>
					<c:when test="${article.memberId == rq.loginedMemberId || rq.loginedMember.authLevel == 0}">
						<a class="btn btn-accent btn-sm" href="/Article?cmd=article_modify&id=${article.id}&page=${page}">수정</a>
						<a class="btn btn-accent btn-sm" href="/Article?cmd=article_delete_pro&id=${article.id }&page=${page}" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
					</c:when>
					<c:when test="${article.memberType == 2 && rq.loginedMemberId == 0}">
						<a class="btn btn-accent btn-sm" href="/Article?cmd=article_non&id=${article.id}&page=${page}&btnType=1">수정</a>
						<a class="btn btn-accent btn-sm" href="/Article?cmd=article_non&id=${article.id}&page=${page}&btnType=2">삭제</a>
					</c:when>
				</c:choose>
			</div>
		</div>
	</section>
	
	<script>
		originalId = null;
		originalForm = null;
		
		function replyModify_getForm(page, replyId, i) {
			
			if (originalForm != null) {
				replyModify_cancle(originalId);
			}
			
			$.post('/Reply?cmd=reply_modify', {
				id : replyId,
				page : page
			}, function(data){
				let replyContent = $('#' + i);
				
				originalId = i;
				originalForm = replyContent.html();
				
				let addHtml = `
					<form action="/Reply?cmd=reply_modify_pro" method="POST">
						<input type="hidden" name="id" value="\${data.id}"/>
						<input type="hidden" name="page" value="\${data.page}"/>
						<div class="mt-4 border border-gray-400 rounded-lg text-base p-4">
							<div class="mb-2"><span>${rq.loginedMember.nickname }</span></div>
							<textarea class="textarea textarea-accent w-full" name="body" placeholder="댓글을 남겨보세요">\${data.body}</textarea>
							<div class="mt-1 flex justify-end">
								<a class="btn btn-accent btn-sm mr-2" onclick="replyModify_cancle(\${i});">취소</a>
								<button class="btn btn-accent btn-sm">수정</button>
							</div>
						</div>
					</form>
					`;
				
				replyContent.empty().html('');
				replyContent.append(addHtml);
				
			}, 'json')
		}
		
		function replyModify_cancle(i) {
			let replyContent = $('#' + i);
			replyContent.html(originalForm);
			
			originalId = null;
			originalForm = null;
		}
		
	</script>
	
	<section class="my-5 text-xl">
		<div class="container mx-auto px-3">
			<h2>댓글</h2>
			
			<c:forEach var="reply" items="${replies }" varStatus="status">
				<div id="${status.count }" class="text-base py-4 pl-16 border-bottom-line">
					<div class="flex justify-between items-end">
						<div class="font-semibold"><span>${reply.writerName }</span></div>		
						<c:choose>
							<c:when test="${reply.memberId == rq.loginedMemberId || rq.loginedMember.authLevel == 0}">
								<div class="dropdown">
								    <button class="btn btn-circle btn-ghost btn-sm mr-6">
								    	<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" class="inline-block w-5 h-5 stroke-current"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 12h.01M12 12h.01M19 12h.01M6 12a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0z"></path></svg>
								    </button>
								    <ul tabindex="0" class="menu menu-sm dropdown-content mt-3 z-[1] p-2 shadow bg-base-100 rounded-box w-20">
								        <c:if test="${reply.memberType == 1}">
								        	<li><a onclick="replyModify_getForm(${page }, ${reply.id }, ${status.count });">수정</a></li>
								        </c:if>
								        <li><a href="/Reply?cmd=reply_delete_pro&id=${reply.id }&page=${page }" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a></li>
							        </ul>
							  	</div>
					  		</c:when>
					  		<c:when test="${(reply.memberType == 2 && rq.loginedMemberId == 0) || rq.loginedMember.authLevel == 0}">
								<button class="btn btn-square" id="showPasswordButton">
								  <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" /></svg>
								</button>
								<form id="passwordInputContainer" action="/Reply?cmd=reply_delete_non_pro" method="post" style="display: none;">
									<input type="hidden" name="page" value="${page}"/>
									<input type="hidden" name="id" value="${reply.id }"/>
							        <div>
							        	<input class="input input-bordered input-accent w-3/4" name="pass" type="password" id="passwordInput" placeholder="비밀번호를 입력하세요">
							        	<button class="btn btn-accent btn-sm" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</button>
							        </div>
							    </form>
						  	</c:when>
						</c:choose>
					</div>
					
					<script>
						document.getElementById("showPasswordButton").addEventListener("click", function () {
						    // 입력란을 보이게 하기
						    document.getElementById("passwordInputContainer").style.display = "block";
						    document.getElementById("showPasswordButton").style.display = "none";
						});
					</script>
					
					<div class="my-1 text-lg pl-2"><span>${reply.getForPrintBody() }</span></div>
					<div class="text-xs text-gray-400"><span>${reply.updateDate }</span></div>
				</div>
			</c:forEach>
			
			<c:if test="${rq.loginedMemberId != 0 }">
				<form action="/Reply?cmd=reply_write_pro" method="POST">
					<input type="hidden" name="page" value="${page}"/>
					<input type="hidden" name="articleId" value="${article.id }"/>
					<div class="mt-4 border border-gray-400 rounded-lg text-base p-4">
						<div class="mb-2"><span>${rq.loginedMember.nickname }</span></div>
						<textarea class="textarea textarea-accent w-full" name="body" placeholder="댓글을 남겨보세요"></textarea>
						<div class="mt-1 flex justify-end"><button class="btn btn-accent btn-sm">등록</button></div>
					</div>
				</form>
			</c:if>
			<c:if test="${rq.loginedMemberId == 0 }">
				<form action="/Reply?cmd=reply_write_non_pro" method="POST">
					<input type="hidden" name="page" value="${page}"/>
					<input type="hidden" name="articleId" value="${article.id }"/>
					<div class="mt-4 border border-gray-400 rounded-lg text-base p-4">
						<table class="table mb-2">
							<tr>
								<th>닉네임</th>
								<td><input class="input input-bordered input-accent w-full" type="text" name="nickname" placeholder="닉네임을 입력해주세요" value="ㅇㅇ"/></td>
								<th>비밀번호</th>
								<td><input class="input input-bordered input-accent w-full" type="text" name="pass" placeholder="비밀번호를 입력해주세요"/></td>
							</tr>
						</table>
						<textarea class="textarea textarea-accent w-full" name="body" placeholder="댓글을 남겨보세요"></textarea>
						<div class="mt-1 flex justify-end"><button class="btn btn-accent btn-sm">등록</button></div>
					</div>
				</form>
			</c:if>
		</div>
	</section>
	</section>
	<%@ include file="../common/sidebar.jsp" %>
</section>

<%@ include file="../common/foot.jsp" %>