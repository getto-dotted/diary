<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resources/js/canvas/html2canvas.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/canvas/html2canvas.min.js"></script>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<style type="text/css">
	#headertitle{
		margin-top : 20px;
		margin-bottom : 20px;
		text-align: center;
	}
	#member_form1{
		margin-left: 40%;
		margin-right: 40%;
	}
	#member_birth1, #member_birth2, #member_birth3{
		text-align: center;
		display: inline;
		width: 25%;
	}
	input[type="number"]::-webkit-outer-spin-button, input[type="number"]::-webkit-inner-spin-button { 
		-webkit-appearance: none; -moz-appearance: none; appearance: none; 
	}
	#regbtn{
		width: 100%;
	}
</style>
<script type="text/javascript">
	//핸드폰 번호는 최대 11자리 maxlength 만큼만 입력 가능
	function maxLengthCheck(object){
	    if (object.value.length > object.maxLength){
	        object.value = object.value.slice(0, object.maxLength);
	    }
	};
	
	$(document).ready(function(){
		
		
		$("#member_id").bind("keyup",function(){
			chck = /[`/?<>,.;:~!@\#$%^&*\()\-=+_"']/gi;
			
			var temp = $("#member_id").val();
			if(chck.test(temp)){ //아이디에 특수문자가 포함되면 삭제하여 빈값으로 다시셋팅
				$("#member_id").val(temp.replace(chck,""));
			}
		});
		
		$("#password1").focusout(function(){
			var chkid = $("#member_id").val();
			var chkpassword = $("#password").val();
			var chkpassword1 = $("#password1").val();

			if(chkpassword1 != ""){
				if(!/^[a-zA-Z0-9]{10,15}$/.test(chkpassword1)){
					//숫자와 영문자 조합으로 10~15자리가 안될경우 경고문구를 append한다
					//포커스 아웃을 반복할경우 경고문구가 계속 누적된다. 앞선 문구를 제거해야 할듯
					$("#appendlabelpassword").empty();
					$("#appendlabelpassword2").empty();
					$("#appendlabelpassword").append('<br><span >비밀번호 재확인</span>');
					$("#appendlabelpassword2").append('<br><span style="color: red;">숫자와 영문자 조합으로 10~15자리를 사용해야 합니다.</span>');
					return false;
				}			
				else{
					$("#appendlabelpassword2").empty();
				}
				if(chkpassword1.search(chkid) > -1){//0이상일 경우 아이디가 비밀번호에 포함된 것
					$("#appendlabelpassword").empty();
					$("#appendlabelpassword2").empty();
					$("#appendlabelpassword").append('<br><span >비밀번호 재확인</span>');
					$("#appendlabelpassword2").append('<br><span style="color: red;">비밀번호에 아이디가 포함되었습니다.</span>');	
					return false;
				}
				else{
					$("#appendlabelpassword2").empty();
				}
				if(chkpassword != chkpassword1){//비밀번호 확인이 틀릴 경우
					$("#appendlabelpassword").empty();
					$("#appendlabelpassword2").empty();
					$("#appendlabelpassword").append('<br><span >비밀번호 재확인</span>');
					$("#appendlabelpassword2").append('<br><span style="color: red;">비밀번호를 다시 확인해 주세요.</span>');
					return false;
				}
				else{
					$("#appendlabelpassword2").empty();
						
				}
				if($("#appendlabelpassword2").val()==''){
					$("#appendlabelpassword").empty();
					$("#appendlabelpassword").append('<br><span style="color: blue;">비밀번호 확인 완료</span>');
				}
			}
			
		});
	});
</script>	
<body>
	<div class="alert alert-primary" role="alert">
		<div id="headertitle">
			<header>
				<h1> 회원가입 </h1>
			</header>
		</div>
		<form role="form" action="save" method="post">
			<div id="member_form1">
				<div class="form-group">
					<label id="appendlabelid" for="ID">아이디</label>
					<input type="text" id="member_id" class="form-control" name="member_id" >
				</div>
				<div class="form-group">
					<label for="PASSWORD">비밀번호</label>
					<input type="password" id="password" class="form-control" name="member_password"  >
				</div>
				<div class="form-group">
					<label id="appendlabelpassword" for="PASSWORD">비밀번호 재확인</label>
					<label id="appendlabelpassword2" for="PASSWORD"></label>
					<input type="password" id="password1"class="form-control" name="member_password1" >
				</div>
				<div class="form-group">
					<label for="NAME">이름</label>
					<input type="text" class="form-control" name="member_name" >
				</div>
				<div class="form-group">
					<label for="GENDER">성별</label>
					<select class="form-control" name="member_gender" onchange="check()">
						<option value="0">성별</option>
						<option value="1">남자</option>
						<option value="2">여자</option>
					</select>
				</div>
				<div class="form-group">
					<div>
					<label for="BIRTHDAY">생년월일</label>
					</div>
					<input type="number" class="form-control" id="member_birth1" name="member_birth1" maxlength="4" oninput="maxLengthCheck(this)" placeholder="YYYY">&nbsp;년&nbsp;
					<input type="number" class="form-control" id="member_birth2" name="member_birth2" maxlength="2" oninput="maxLengthCheck(this)" placeholder="MM">&nbsp;월&nbsp;
					<input type="number" class="form-control" id="member_birth3" name="member_birth3" maxlength="2" oninput="maxLengthCheck(this)" placeholder="DD">&nbsp;일&nbsp;
				</div>
				<div class="form-group">
					<label for="IputEmail">이메일 주소</label>
					<input type="email" class="form-control" name="member_email" placeholder="이메일을 입력하세요">
				</div>
				<div class="form-group">
					<label for="PHONE">핸드폰</label>
					<input type="number" class="form-control" name="member_phone" maxlength="11" oninput="maxLengthCheck(this)" placeholder="'-' 없이 입력해주세요.">
				</div>
				<div class="form-group">
					<label for="ADDRESS">주소</label>
					<input type="text" class="form-control" name="member_address" >
				</div>
				<button class="btn btn-primary" id="regbtn" type="submit" >가입하기</button>
			</div>
		</form>
	</div>
</body>
</html>