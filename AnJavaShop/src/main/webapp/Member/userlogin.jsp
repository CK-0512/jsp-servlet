<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="">
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Login</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' href='login.css'>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css'>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
<script>
	function send(){
		if(login.userid.value==""){
			alert("아이디를 입력해주세요");
			login.userid.focus();
			return;
		}else if(!checkEngNum(login.userid.value)){
			alert("아이디는 영문, 숫자만 입력가능합니다");
			login.userid.focus();
			return;
		}
		if(login.password.value==""){
			alert("비밀번호를 입력해주세요");
			login.password.focus();
			return;
		}else if(!checkEngNum(login.password.value)){
			alert("비밀번호는 영문, 숫자만 입력가능합니다");
			login.password.focus();
			return;
		}
		login.submit();
	}
	
	function checkEngNum(str){
		lower_str = str.toLowerCase();
		
		for(i=0; i<lower_str.length; i++){
			ch = lower_str.charAt(i);
			if(((ch < 'a') || (ch > 'z')) && ((ch < '0') || (ch > '9'))){
				return 0;
			}
			return 1;
		}
	}
</script>
</head>
<body>
<!--header section starts-->
<header class="header">
    <a href="/Index/index.jsp" class="logo"><i class='bx bxs-shopping-bags'></i>JAVAANJAVA</a>
</header>

<div class="wrapper">
    <div class="form-box login">
        <h1>Login</h1>
        <form name="login" method="post" action="/Member/userlogin.do" >
        
            <div class="input-box">
                <span class="icon"><i class='bx bxs-user' ></i></span>
                <input type="text" name ="userid" placeholder="Your Id" required>   
            </div>
            <div class="input-box">
                <span class="icon"><i class='bx bxs-lock-alt'></i></span>
                <input type="password" name ="password" placeholder="Your Password" required>
                
            </div> 
            <div class="remember-forgot">
                <label><input type="checkbox">Remember me</label>
                <a href="#">Forgot password?</a>
            </div>
                <button type="button" onclick="send()" class="btn">Login</button>
            <div class="register-link">
            <p>Don't have an account?
                <a href="/Member/userinfo_insert.do">Register</a>
            </p>

        </div>
    </form>
</div>
</div>
<!--header section ends-->
<!--custom js file lick-->
<script src='script.js'></script>
</body>
</html>
