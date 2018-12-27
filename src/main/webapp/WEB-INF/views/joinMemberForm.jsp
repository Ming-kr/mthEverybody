<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>자취생을 위한 모두의 떨이</title>
  <link rel="shortcut icon" type="image⁄x-icon" href="${pageContext.request.contextPath}/resources/img/logo/image2.png">
  <!-- Bootstrap core CSS -->
  <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script> 
  <!-- Custom styles for this template -->
  <link href="${pageContext.request.contextPath}/resources/css/shop-homepage.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/main/joinmemberform.css" rel="stylesheet">
  <!-- 붙어서 온 아이들 -->
  <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/subbootstrap.css" rel="stylesheet">
 <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
 <script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/joinmemberForm.js"></script>
 
 <!-- ID 체크, submit은 el표기법때문에 jsp에 포함되어야 해서 이를 제외한 나머지만 joinmemberForm.js파일로 분리 -->
<script>
//아이디 유효성검사(onchange)
function checkJoinId()
{
	var inputId = document.getElementById('input-id');
	var exptext = /^[a-zA-Z0-9]*$/;

	if(inputId.value.length <5 || inputId.value.length > 12) {
		document.getElementById('joinMember-id-chk').innerHTML = '아이디의 길이(5자 이상 12자 이내)를 확인해주세요.';
		document.getElementById('joinMember-id-chk').style.color = "red";
	}
	else if(exptext.test(inputId.value) == false) {
		document.getElementById('joinMember-id-chk').innerHTML = '아이디는 영소문자, 숫자 조합만 등록 가능합니다.';
		document.getElementById('joinMember-id-chk').style.color = "red";
		}
	else {
		//아이디 중복확인
		var xmlhttp = new XMLHttpRequest();
		
		xmlhttp.onreadystatechange = function(){
			
    		if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
    		{
    			//true: 입력한 아이디 사용가능
    			var result = xmlhttp.responseText;
    			if(result == "true")
    			{
    				document.getElementById('joinMember-id-chk').innerHTML = '사용가능한 아이디입니다.';
    				document.getElementById('joinMember-id-chk').style.color = "#00a000";
    				joinIdValuedCheck = true;
    			}
    			else
    			{
    				document.getElementById('joinMember-id-chk').innerHTML = '사용중인 아이디입니다. 다른 아이디를 입력해주세요.';
    				document.getElementById('joinMember-id-chk').style.color = "red";
    			}
    		}
		};
		xmlhttp.open("POST","${pageContext.request.contextPath}/checkValidId",true);
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlhttp.send("member_id="+inputId.value);
	}
}

//폼 데이터 전송
function submitAction()
{
	document.getElementById('joinMemberForm').action="${pageContext.request.contextPath}/actionJoinMemberForm";
	document.getElementById('joinMemberForm').submit();
}
</script>
 
</head>

<body>
  <!-- 헤더 영역 -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
  	<jsp:include page="commons/header.jsp"/>
  </nav>

<!-- wrapper 시작 -->
  <div class="wrapper body">
    <!-- row 시작 -->
    <div class="row">
      <!-- /.col-lg-3 시작 -->
      <div class="col-lg-3">
        <!-- 사이드바 -->
        <h1 class="my-4">Home</h1>
        <jsp:include page="commons/asideHome.jsp"/>
        <div class="advertiseAside">
          <p>광고</p>
        </div>
      </div>
      <!-- /.col-lg-3 끝 -->
      <!-- col-lg-9 시작 -->
      <div class="col-lg-9">
          <!-- 내용 넣는 공간 -->
          
    <div id="joinmember-input-box">	
    <h3>회원가입</h3>
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<!-- Start form -->
			<div id='joinmember-form-wrap'>
				<form id="joinMemberForm" method="post" autocomplete="off">
					<div class="form-group">
                        <label for="input-id">아이디</label> <!-- 여기 id값 바꿔야 함 -->
                        <input type="text" class="form-control" name="member_id" id="input-id" placeholder="영소문자+숫자 5자이상 12자 이내" onchange="checkJoinId()">
                  	  <div class="view-sys-msg"><span id="joinMember-id-chk"></span></div>
                    </div>
                    
                	<div class="form-group">
                        <label for="input-pw">패스워드</label>
                        <input type="password" class="form-control" name="member_pw" id="input-pw" placeholder="모든 문자 8자 이상 15자 이내" onkeyup="checkJoinPw()" onblur="checkJoinPw()">
                        <div class="view-sys-msg"><span id="joinMember-pw-chk1"></span></div>
                    </div>
                    <div class="form-group">
                        <label for="input-pw-chk">패스워드 확인</label>
                        <input type="password" class="form-control" id="input-pw-chk" placeholder="패스워드 확인" onkeyup="checkPwAndPwchk()" onblur="checkPwAndPwchk()">
                         <div class="view-sys-msg"><span id="joinMember-pw-chk2"></span></div>
                    </div>
                    <div class="view-sys-msg"><span id="joinMember-id-chk"></span></div>
                    <div class="form-group">
                        <label for="input-nick">닉네임</label>
                        <input type="text" class="form-control" name="member_nick" id="input-nick" placeholder="특수문자 제외한 문자 3자이상 15자 이내" onkeyup="checkJoinNick()" onblur="checkJoinNick()">
                        <div class="view-sys-msg"><span id="joinMember-nick-chk"></span></div>
                    </div>
                    <div class="form-group">
                        <label for="input-email">이메일</label>
                        <input type="email" class="form-control" name="member_email"id="input-email" aria-describedby="emailHelp" placeholder="이메일" onkeyup="checkJoinEmail()" onblur="checkJoinEmail()">
                        <div class="view-sys-msg"><span id="joinMember-email-chk">본 메일로 인증번호 발송 및 아이디/비밀번호를 찾을 수 있습니다.<br>정확한 메일을 입력해주시길 바랍니다.</span></div>
                    </div>
                     <div class="form-group">
                     	<input type="checkbox" id="agree-join"> <a href="#">약관</a>에 동의합니다.
                     </div>
                    <div class="form-check" id="joinmemberForm-btn">
                    		<button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/loginForm'">취소</button>
                   	    	<!-- joinMemberFormCheck()는 joinmemberForm.js 에 선언 -->
                   	    	<button type="button" class="btn btn-primary" onclick="javascript:joinMemberFormCheck(); return false;">회원가입</button>
                    </div>
				</form>
            </div>
		</div>
	</div>
</div>
          
      </div>
      <!-- /.col-lg-9 끝 -->
    </div> 
    <!-- /.row 끝 -->
  </div>
  <!-- /.wrapper 끝 -->
  <%-- <jsp:include page="commons/advertiseAside.jsp"/> --%>
  <!-- Footer 시작 -->
  <footer class="py-5 bg-dark">
    <jsp:include page="commons/footer.jsp"/>
  </footer>
  <!-- Footer 끝 -->
  </body>
</html>