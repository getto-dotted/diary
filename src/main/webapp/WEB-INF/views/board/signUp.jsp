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
	$(document).ready(function(){//회원가입 화면 로딩시 아이디와 비밀번호 체크를 위한 함수 호출
		
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
	//핸드폰 번호는 최대 11자리 maxlength 만큼만 입력 가능, type="nuber"에서는 maxlength 속성이 적용되지 않는다고 한다.
	function maxLengthCheck(object){
	    if (object.value.length > 11){
	        object.value = object.value.slice(0, 11);
	    }
	};
	function frmchk(f){//자바스크립트를 이용한 유효성 체크 항목을 required 속성을 사용해 상당부분 없앴다.
		var chk = document.getElementById('appendlabelpassword').innerHTML;//비밀번호 입력후 출력 문구를 가져온다.
		var chkword ='<br><span style="color: blue;">비밀번호 확인 완료</span>';//정상 비밀번호일 경우의 출력문구. 태그까지 가져온다.
		
		if(chk!=chkword){
			alert("비밀번호를 확인하세요");
			f.password1.focus();
			return false;
		}		
		f.action="save";
		f.method="post";
	};
</script>	
<body>
	<div class="alert alert-primary" role="alert">
		<div id="headertitle">
			<header>
				<h1> 회원가입 </h1>
			</header>
		</div>
		<form role="form" onsubmit="frmchk(this)" method="post" enctype="multipart/form-data">
		<!-- enctype을 지정해야 파일 업로드 가능 enctype="multipart/form-data"-->
			<div id="member_form1">
				<div class="form-group">
					<label id="appendlabelid" for="ID">아이디<br /><span style="color:blue;">아이디에는 특수문자를 사용할 수 없습니다.</span></label>
					<input type="text" id="member_id" class="form-control" name="member_id" 
					required oninvalid="this.setCustomValidity('아이디를 입력해 주세요')" oninput = "setCustomValidity('')">
				</div>
				<div class="form-group">
					<label for="PASSWORD">비밀번호 <br /><span style="color:blue;">10~15자리의 영문,숫자 조합이어야 합니다.</span></label>					
					<input type="password" id="password" class="form-control" name="member_password"  >
				</div>
				<div class="form-group">
					<label id="appendlabelpassword" for="PASSWORD">비밀번호 재확인</label>
					<label id="appendlabelpassword2" for="PASSWORD"></label>
					<input type="password" id="password1"class="form-control" name="member_password1" 
					required oninvalid="this.setCustomValidity('비밀번호를 입력해 주세요')" oninput = "setCustomValidity('')">
				</div>
				<div class="form-group">
					<label for="NAME">이름</label>
					<input type="text" class="form-control" name="member_name" 
					required oninvalid="this.setCustomValidity('이름을 입력해 주세요')" oninput = "setCustomValidity('')">
				</div>
				<div class="form-group">
					<label for="GENDER">성별</label>
					<select class="form-control" name="member_gender" required="required"><!-- 출력 메시지 조정이 어렵다  -->
						<option value="">성별</option>
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
					<input type="email" class="form-control" name="member_email" placeholder="이메일을 입력하세요" >
				</div>
				<div class="form-group">
					<label for="PHONE">핸드폰</label>
					<input type="number" class="form-control" name="member_phone" oninput="maxLengthCheck(this)" placeholder="'-' 없이 입력해주세요."
					required="required" oninvalid="this.setCustomValidity('핸드폰 번호를 입력해주세요.')" onkeyup="setCustomValidity('')"/><!-- type="number"는 onkeyup을 사용한다. -->
				</div>
				<div class="form-group">
					<label for="ADDRESS">주소</label>
					<input type="text" class="form-control" name="member_address" >
				</div>
				<div class="form-group">
					<label for="Pic">프로필 사진</label>
					<input type="file" class="form-control" name="profilePic" >
				</div>
				<button class="btn btn-primary" id="regbtn" type="submit">가입하기</button>
			</div>
		</form>
	</div>
</body>
</html>