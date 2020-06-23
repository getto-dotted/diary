<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resources/js/canvas/html2canvas.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/canvas/html2canvas.min.js"></script>
<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />

<head>
	<title>일기장</title>
</head>
<style type="text/css">
#inputs1 {
  background-attachment: local;
  background-image:
    linear-gradient(to right, white 10px, transparent 10px),
    linear-gradient(to left, white 10px, transparent 10px),
    repeating-linear-gradient(white, white 30px, #ccc 30px, #ccc 31px, white 31px);
  line-height: 31px;
  padding: 6px 8px;
}
#inputs2 {
  background-attachment: local;
  background-image:
    linear-gradient(to right, white 10px, transparent 10px),
    linear-gradient(to left, white 10px, transparent 10px),
    repeating-linear-gradient(white, white 30px, #ccc 30px, #ccc 31px, white 31px);
  line-height: 31px;
  padding: 6px 8px;
}
</style>	
<style>
    div.leftroot {
        width: 20%;
        height: 100%;
        float: left;
        box-sizing: border-box;
    }
    div.rightroot {
        width: 80%;
        height: 100%;
        float: right;
        box-sizing: border-box;
    }
	#styledatepicker {
		float: right;
	}
</style>
<script>    
   /*  //처음 시작 시 content2번 막음
    $( document ).ready(function() {
    	$('#content2').prop("disabled", true);
    });  */   	
   	//텍스트 넘기기
   	
   	$(function() {//로그인 버튼과 날짜선택 버튼 활성화 함수, jquery 준비 함수   
	    $('#datepicker').datepicker({
	    	format: "yyyy-mm-dd"
	    });	    
	});
    
  	/* ---------------------------------------------게시글 작성 관련 함수 --------------------------------------*/

    function imgSrcMake(){//화면캡쳐 url 담기
    	html2canvas($("#alertalert").get(0)).then(function(canvas) {
    	imgSource = $("#imgSrc").val(canvas.toDataURL("image/png"));
    	});
    	return imgSource;
    }    
    
   	function sumcontent(){//게시글작성 함수
   		
		var imgSourceChk = imgSrcMake();

		if(imgSourceChk != ""){
			//content 내용 합치기
   			var contentValue1 = $('#inputs1').val();
   			var contentValue2 = $('#inputs2').val();
   			var addString = '`\\';
   			var contentValue = contentValue1 + contentValue2;
   			var contentTotal = contentValue1 + addString+ contentValue2;
   			
   			if($("#datepicker").val() == ""){
   				alert("날짜를 입력하세요.");
   				return false
   			}
			
   			if(contentValue == ""){//기본으로 임시저장글이 들어가 있어서 작동하지 않을듯
   				alert("내용을 적어주세요.");
   				return false;
   			}
   			
   			//ajax 데이터 통신
   			$.ajax({ 
   				type: 'POST',
   				url : 'write',
   				data: {
   					   "title"    	 : $("#datepicker").val(),
   					   "content" 	 : contentTotal,
   					   "writer"  	 : $("#writer").val(),
   					   "filepathurl" : $("#imgSrc").val()				   
   				},
   				success : function(data) { 
   					alert('작성되었습니다.');
   					console.log(data);
   					location.reload();
   				},
   				error: function(jqXHR, textStatus, errorThrown) {
   					console.log(jqXHR.responseText);
   				}
   			});
		}
   	}  	
  	
   	function detailwriteView(bno){//목록 선택하면 상세보기 화면으로 이동
   		//ajax 데이터 통신
		$.ajax({ 
			type: 'POST',
			url : 'detailwriteView?bno='+bno,
			data: {
				   "bno"    	 : bno	   
			},
			success : function(data) { 
				console.log(data);
			} 
		});
   	}
   	
   	/* 로그인 함수없음 */
  	/* ---------------------------------------------본문 꾸미기 관련 함수 --------------------------------------*/
	function changeSize(){
   		var text1 =  document.getElementById("inputs1");
   		var text2 =  document.getElementById("inputs2");
   		var fontS = document.getElementById("font-size");   		
   		
   		text1.style.fontSize =  fontS.value+"px";
   		text2.style.fontSize =  fontS.value+"px"; 		

   	}
   	function changeColor(){   		
   		var text1 =  document.getElementById("inputs1");
   		var text2 =  document.getElementById("inputs2");
   		var fontC = document.getElementById("font-color");   		
   		
   		text1.style.color =  fontC.value;
   		text2.style.color =  fontC.value; 		

   	}
   	function changeFont(){   		
   		var text1 =  document.getElementById("inputs1");
   		var text2 =  document.getElementById("inputs2");
   		var font1 = document.getElementById("font1");   		
   		
   		text1.style.fontFamily =  font1.value;
   		text2.style.fontFamily =  font1.value;

   	}
</script>
	<body>
		<div class="leftroot" id= "leftroot">
			<div class="alert alert-warning" role="alert">
				<section id = "container">
					<table>					
						<c:forEach items="${list}" var = "list">
						<tr>
							<td>
								<input id="bno" type="hidden" value="${list.bno}"/>
								<a href="detailwriteView?bno=${list.bno}">
								<img onclick="detailwriteView(${list.bno});" style="cursor:hand" 
								src="${pageContext.request.contextPath}/resources/image/${list.filepath}" 
								width="100%" height="30%"/>
								</a>
								<hr />
							</td>
						</tr>
						</c:forEach>
					</table>
				</section>
			</div>
		</div>
		<div class="rightroot">
			<input type="hidden" name="imgSrc" id="imgSrc" />
			<div id="alertalert" class="alert alert-primary" role="alert">
				<header>
					<h1> 일기장 </h1>
				</header>
				<div align="right">
					<img src="${pageContext.request.contextPath}/resources/image/${sessionScope.profilepic}" style="height: 100px" />
					<span>${sessionScope.username }님 반갑습니다.</span><br />
					<a href="memberinfo" id="loginbtn" class="btn btn-secondary" >회원정보 수정</a>					
					<a href="logout" id="loginbtn" class="btn btn-warning" >로그아웃</a>					
				</div>
				<hr />
				<hr />
				<section id="container">
					<form role="form">
						<table style="width: 100%">
							<tbody>
								<tr>
									<td>
										<label for="content">내용</label>
									</td>
									<td>
									<c:forEach items="${list2}" var = "list2">
												<c:set var="content1" value="${list2.content }"/>
												<input type="hidden" id="bno" name="bno" value="${list2.bno }"/>
												</c:forEach> 
										<div id="styledatepicker">
											<input id="datepicker" width="120"/>
										</div>
									</td>
								</tr>
								<tr>
									<td>									
										<textarea id="inputs1" class="form-control"  cols = "70" rows = "10" name="content1" maxlength="310">${fn:split(content1,'`\\')[0] }</textarea>
									</td>
									<td>										
										<textarea id="inputs2" class="form-control" cols = "70" rows = "10" name="content2" maxlength="310" >${fn:split(content1,'`\\')[1] }</textarea>
									</td>
								</tr>
								<tr><!-- 게시글 꾸미기 -->
									<td>									
										<select id="font-size" onchange="changeSize()" >
											<option value="15">15</option>
											<option value="25">25</option>
											<option value="35">35</option>
										</select>																		
										<select id="font-color" onchange="changeColor()" >
											<option value="red">빨강</option>
											<option value="blue">파랑</option>
											<option value="yellow">노랑</option>
										</select>																		
										<select id="font1" onchange="changeFont()" >
											<option value="Serif">돋움체</option>
											<option value="Arial">바탕체</option>
											<option value="Courier">고딕체</option>
										</select>
									<input type="hidden" id="writer" name="writer" value="${sessionScope.userid }"/>
									</td>
									<td align="right">											
										<button class="btn btn-primary" type="button" onclick="sumcontent()">작성</button>																		
									</td>
								</tr>			
							</tbody>			
						</table>
					</form>
				</section>
				<hr />
			</div>
		</div>
	</body>
</html>