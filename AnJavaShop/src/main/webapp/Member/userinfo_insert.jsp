<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="">
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Register</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' href='register.css'>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css'>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>
<!--header section starts-->
<header class="header">
    <a href="/Index/index.jsp" class="logo"><i class='bx bxs-shopping-bags'></i>JAVAANJAVA</a>
<script>
	let validLoginId = '';

	function send(){
		if(register.userid.value==""){
			alert("아이디를 입력해주세요");
			register.userid.focus();
			return;
		}else if(!checkEngNum(register.userid.value)){
			alert("아이디는 영문, 숫자만 입력가능합니다");
			register.userid.focus();
			return;
		}
		if(register.userid.value != validLoginId.value) {
			alert("해당 아이디는 중복된 아이디입니다");
			register.userid.value = '';
			register.userid.focus();
			return;
		}
		if(register.password.value==""){
			alert("비밀번호를 입력해주세요");
			register.password.focus();
			return;
		}else if(!checkEngNum(register.password.value)){
			alert("비밀번호는 영문, 숫자만 입력가능합니다");
			register.password.focus();
			return;
		}
		if(register.password.value!=register.repassword.value){
			alert("비밀번호를 확인해주세요");
			register.repassword.focus();
			return;
		}
		if(register.username.value==""){
			alert("이름을 입력해주세요");
			register.username.focus();
			return;
		}
		if(register.address.value==""){
			alert("주소를 입력해주세요");
			register.address.focus();
			return;
		}
		if(register.detailaddress.value==""){
			alert("상세주소를 입력해주세요");
			register.detailaddress.focus();
			return;
		}
		if(!register.agree.checked){
			alert("회원가입에 동의 해주세요");
			return;
		}
		register.submit();
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

	function loginIdDupChk(el) {
	    let loginIdChkMsg = document.getElementById('loginIdChkMsg');

	    while (loginIdChkMsg.firstChild) {
	        loginIdChkMsg.removeChild(loginIdChkMsg.firstChild);
	    }

	    el.value = el.value.trim();

	    let xhr = new XMLHttpRequest();
	    xhr.open('GET', 'loginIdDupChk.do?userId=' + el.value, true);

	    xhr.onreadystatechange = function () {
	        if (xhr.readyState === 4 && xhr.status === 200) {
	            let data = JSON.parse(xhr.responseText);
	            if (data.success) {
	                let message = document.createElement('span');
	                message.textContent = '해당 아이디는 사용 가능한 아이디입니다.';
	                loginIdChkMsg.appendChild(message);
	                validLoginId = document.getElementsByName('userid')[0];
	            } else {
	                let message = document.createElement('span');
	                message.textContent = '해당 아이디는 중복된 아이디입니다.';
	                loginIdChkMsg.appendChild(message);
	                validLoginId = '';
	            }
	        }
	    };

	    xhr.send();
	}

	
	function passCheck(){
		var correctColor1 = "#009000";
		var wrongColor1 = "#ff0000"
		
		if(register.password.value==""){
			configMsg1.style.color = wrongColor1;
			configMsg1.innerHTML = "비밀번호를 입력해주세요";
		} else {
			configMsg1.innerHTML = '';
		}
		if(register.repassword.value==""){
			configMsg2.style.color = wrongColor1;
			configMsg2.innerHTML = "비밀번호를 입력해주세요";
		}
		if(register.repassword.value) {
			if(register.password.value==register.repassword.value){
				configMsg2.style.color = correctColor1;
				configMsg2.innerHTML = "비밀번호 일치";
			}else{
				configMsg2.style.color = wrongColor1;
				configMsg2.innerHTML = "비밀번호 불일치";
			}
		}
	}
    
	function zipcode(){
		url="zipcode.jsp"
		window.open(url,"zipcode","width=410,height=410")
	}
</script>
</header>

<div class="wrapper">
    <div class="form-box register">
        <h1>Registration</h1>
        <form name="register" method="post" action="/Member/userinfo_insert.do" >
            <div class="input-box">
                <span class="icon"><i class='bx bxs-user' ></i></span>
                <input name="userid" type="text" placeholder="UserId" value="" required onblur="loginIdDupChk(this)" >
                <div id="loginIdChkMsg"></div>   
            </div>
            <div class="input-box">
                <span class="icon"><i class='bx bxs-lock-alt'></i></span>
                <input name="password" type="password" placeholder="Password" value="" required onblur="passCheck(this)">
                <div id="configMsg1"></div>
            </div>
            <div class="input-box">
                <span class="icon"><i class='bx bxs-lock'></i></span>
                <input name="repassword" type="password" placeholder="Re Password" value="" required onblur="passCheck(this)">
                <div id="configMsg2"></div>
            </div> 
            <div class="input-box">
                <span class="icon"><i class='bx bxs-user-badge' ></i></span>
                <input name="username" type="text" placeholder="UserName" value="" required>   
            </div>
            <div class="input-box">
                <span class="icon"><i class='bx bxs-envelope' ></i></span>
                <a href="javascript:zipcode()"><input name="zipcode"  placeholder="Zipcode" value="" required readonly></a>   
            </div>
            <div class="input-box">
                <span class="icon"><i class='bx bxs-building' ></i></span>
                <input name="address" type="text" placeholder="Address" value="" required>   
            </div>
            <div class="input-box">
                <span class="icon"><i class='bx bxs-building-house' ></i></span>
                <input name="detailaddress" type="text" placeholder="Detail Address" value="" required>   
            </div>
            <div class="remember-forgot">
                <label><input type="checkbox" name="agree">Agree to register</label>
            </div>
                <button type="button" class="btn" onclick="send()">Sign up</button>
            <div class="register-link">
            <p>Already have an account?
                <a href="/Member/userlogin.do">Login</a>
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