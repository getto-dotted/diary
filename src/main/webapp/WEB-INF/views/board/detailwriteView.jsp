<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    
    .pop-layer .pop-container {
	  padding: 20px 25px;
	}
	
	.pop-layer p.ctxt {
	  color: #666;
	  line-height: 25px;
	}
	
	.pop-layer .btn-r {
	  width: 100%;
	  margin: 10px 0 20px;
	  padding-top: 10px;
	  border-top: 1px solid #DDD;
	  text-align: right;
	}
	
	.pop-layer {
	  display: none;
	  position: absolute;
	  top: 50%;
	  left: 50%;
	  width: 410px;
	  height: auto;
	  background-color: #fff;
	  border: 5px solid #3571B5;
	  z-index: 10;
	}
	
	.dim-layer {
	  display: none;
	  position: fixed;
	  _position: absolute;
	  top: 0;
	  left: 0;
	  width: 100%;
	  height: 100%;
	  z-index: 100;
	}
	
	.dim-layer .dimBg {
	  position: absolute;
	  top: 0;
	  left: 0;
	  width: 100%;
	  height: 100%;
	  background: #000;
	  opacity: .5;
	  filter: alpha(opacity=50);
	}
	
	.dim-layer .pop-layer {
	  display: block;
	}
	
	a.btn-layerClose {
	  display: inline-block;
	  height: 25px;
	  padding: 0 14px 0;
	  border: 1px solid #304a8a;
	  background-color: #3f5a9d;
	  font-size: 13px;
	  color: #fff;
	  line-height: 25px;
	}
	
	a.btn-layerClose:hover {
	  border: 1px solid #091940;
	  background-color: #1f326a;
	  color: #fff;
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
   	$(function() {
	    $(".inputs").keyup (function () {
	        var charLimit = $(this).attr("maxlength");
	        if (this.value.length >= charLimit) {
	        	  $( document ).ready(function() {//좌측 레이어로 로딩시 해당하는 옵션?
	        	    	$('#content2').prop("disabled", false);
	        	  }); 
	            $("#content2").focus();
	            return false;
	        }
	    });
	    
	    $('#loginbtn').click(function(){
	        var $href = $(this).attr('href');
	        layer_popup($href);
	    });
	    $('#datepicker').datepicker({
	    	format: "yyyy-mm-dd"
	    });
	    
	});
    
  	//캡쳐 url 담기
    function imgSrcMake(){
    	html2canvas($("#alertalert").get(0)).then(function(canvas) {
    	imgSource = $("#imgSrc").val(canvas.toDataURL("image/png"));
    	});
    	return imgSource;
    }    
    
  	//데이터 등록
   	function sumcontent(){
   		
		var imgSourceChk = imgSrcMake();

		if(imgSourceChk != ""){
   			var contentValue = $('#inputs1').val();
   			var bno = $('#bno').val();
   						
   			if(contentValue == ""){//텍스트에리어에 테스트가 적혀있어서 작동하지 않는다.
   				alert("내용을 적어주세요.");
   				return false;
   			}
   			
   			//ajax 데이터 통신
   			$.ajax({ 
   				type: 'POST',
   				url : 'update',
   				data: {
					   "content" 	 : contentValue	
					   "bno" : bno
   				},
   				success : function(data) { 
   					console.log(data);
   				} 
   				/* error: function(jqXHR, textStatus, errorThrown) {
   					console.log(jqXHR.responseText);
   				} */ 
   			});
		}
   	}
  	
  	//목록 선택하면 불러오기
   	function detailwriteView(bno){
   		//ajax 데이터 통신
		$.ajax({ 
			type: 'POST',
			url : 'detailwriteView',
			data: {
				   "bno"    	 : bno	   
			},
			success : function(data) { 
				console.log(data);
			} 
			/* error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			} */ 
		});
   	}
  	
   	function layer_popup(el){
   		var $el = $(el);
        
        var isDim = $el.prev().hasClass('dimBg');   //dimmed 레이어를 감지하기 위한 boolean 변수
		
        isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();

        var $elWidth = ~~($el.outerWidth()),
            $elHeight = ~~($el.outerHeight()),
            docWidth = $(document).width(),
            docHeight = $(document).height();

        // 화면의 중앙에 레이어를 띄운다.
        if ($elHeight < docHeight || $elWidth < docWidth) {
            $el.css({
                marginTop: -$elHeight /2,
                marginLeft: -$elWidth/2
            })
        } else {
            $el.css({top: 0, left: 0});
        }

        $el.find('#btn-layerClose').click(function(){
            isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
            return false;
        });

        $('.layer .dimBg').click(function(){
            $('.dim-layer').fadeOut();
            return false;
        });
    }
   	
   	function login(){
   		var idchk = $("#inputEmail3").val();
   		var passchk = $("#inputPassword3").val();

   		if(idchk==''){
   			alert('ID를 입력해주세요');
   			return false;
   		}
   		if(passchk==''){
   			alert('비밀번호를 입력해주세요');
   			return false;
   		}
   		
   		$.ajax({ 
			type: 'POST',
			url : 'login',
			data: {
				   "member_id"    	 : idchk,
				   "password"		: passchk
			},
			success : function(data) { 
				console.log(data);
				location.reload();
				/* ajax를 썼는데 새로고침을 해야할까...? */
			} 
			/* error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText);
			} */ 
		});
   	}
</script>
	<body>
		<div class="leftroot">
			<div class="alert alert-warning" role="alert">
				<section id = "container">
					<table>
					<c:choose>
					<c:when test="${empty sessionScope.username }">
						<h3>로그인필요</h3>
					</c:when>
					
					<c:otherwise>
								<c:forEach items="${list}" var = "list">
							<tr>
								<td>
									<input id="bno" type="hidden" value="${list.bno}"/>
									<a href="detailwriteView?bno=${list.bno}">
									<img onclick="detailwriteView(${list.bno});" style="cursor:hand" 
									src="${pageContext.request.contextPath}/resources/image/${list.filepath}" width="100%" height="30%"/></a>
									<hr />
								</td>
							</tr>
						</c:forEach>			
					</c:otherwise>
				</c:choose>	
						
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
				<c:choose>
					<c:when test="${empty sessionScope.username }">
						<a href="#layer2" id="loginbtn" class="btn btn-primary" >로그인</a>
					</c:when>
					
					<c:otherwise>
						<a href="logout" id="loginbtn" class="btn btn-primary" >로그아웃</a>					
					</c:otherwise>
				</c:choose>	
					
				</div>
				<div class="dim-layer">
				    <div class="dimBg"></div>
				    <div id="layer2" class="pop-layer">
				        <div class="pop-container">
				            <div class="pop-conts">
				                <!--로그인 화면 //-->
				                <form action="login" >
									<div class="form-group">
										<label for="ID" class="col-sm-2 control-label">ID</label>
										<div class="col-sm-10">
										<input type="text" class="form-control" id="inputEmail3" placeholder="아이디">
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
										<div class="col-sm-10">
										<input type="password" class="form-control" id="inputPassword3" placeholder="비밀번호">
										</div>
									</div>
								</form>
								<c:forEach items="${name}" var = "name">
								<input type="text" value="${name }"/>
								</c:forEach>
				                <div class="btn-r">
				                	<a href="#" id="btn-singup" class="btn btn-primary" onClick="location.href='signUp'">회원가입</a>
				                	<a href="#" class="btn btn-primary" onclick="login()">로그인</a>
				                    <a href="#" id="btn-layerClose" class="btn btn-primary">닫기</a>
				                </div>
				                <!--// content-->
				            </div>
				        </div>
				    </div>
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
										<div id="styledatepicker">
										<c:forEach items="${list2}" var = "list2">
												<input type="text" value="${list2.write_date }" readonly/>
												<input type="hidden" id="bno" name="bno" value="${list2.bno }"/>
												</c:forEach> 
											<!-- <input id="datepicker" width="120"/> 
												수정시에는 날짜선택불가
											-->
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<textarea id="inputs1" class="form-control"  cols = "140" rows = "10" name="content1" maxlength="700"><c:forEach items="${list2}" var = "list2">
												${list2.content }
												</c:forEach> </textarea>
									</td>
									
								</tr>
								
									<input type="hidden" id="writer" name="writer" value="${sessionScope.userid }"/>
								<tr>
									<td>
									</td>
									<td align="right">		
									<c:choose>
										<c:when test="${empty sessionScope.username }"></c:when>					
										<c:otherwise>
											<button class="btn btn-primary" type="button" onclick="sumcontent()">작성</button>					
										</c:otherwise>
									</c:choose>															
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