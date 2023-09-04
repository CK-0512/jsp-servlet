<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function send(){
		if(login.userid.value.trim() == "") {
			alert("아이디를 입력하세요");
			login.userid.focus();
			return;
		}
		if(!login.password.value.trim()) {
			alert("비밀번호를 입력하세요");
			login.password.focus();
			return;
		}
		
		login.submit();
	}
</script>
</head>
<body>
	<form name="login" method="post" action="login_pro.jsp">
		<table width="300" border="1">
			<tr>
				<td colspan="2">로그인 폼</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userid"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="2">
					<!-- <input type="submit" value="로그인" onClick="send()">
						 <input type="reset" value="취소">
					-->
					<input type="button" value="등록" onClick="send()">
					<input type="button" value="취소" onClick="reset()">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>