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

  <!-- 붙어서 온 아이들 -->
  <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/subbootstrap.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/css/main/loginform.css" rel="stylesheet">
 <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
 <script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
 <script>
 	//로그인 로직
  	function submitLogin(){
  		
  		//iput 입력값 유효성체크
  		var inputId = document.getElementById('input-id');
  		var inputPw = document.getElementById('input-pw');
  		var loginForm = document.getElementById('login-form');
  		
  		if(inputId.value == "" || inputId.value==null) { 
  			document.getElementById('login-valid-check-box').innerHTML = '아이디를 입력해주세요.'; 
  		}
  		else if(inputPw.value == "" || inputPw.value==null) { 
  			document.getElementById('login-valid-check-box').innerHTML = '패스워드를 입력해주세요.'; 
  		}
  		else {
  			//이전 페이지 url 함께 보내기(추가사항)
  			var beforeUrl = document.referrer;
  			
  			var hiddenField = document.createElement("input");
  			hiddenField.setAttribute("type", "hidden");
  			hiddenField.setAttribute("name", "beforeUrl");
  			hiddenField.setAttribute("value", beforeUrl);
  			loginForm.appendChild(hiddenField);
  			
  			loginForm.action = "${pageContext.request.contextPath}/actionLoginForm";
  			loginForm.submit();
  		}
  	}
  	
  	//엔터키 입력시 각각 input 이벤트 수행(onkeyup)
  	function tabPasswordInput(){
  		if(window.event.keyCode == 13){ document.getElementById('input-pw').focus(); }
  	}
  	function enterkey(){
  		if(window.event.keyCode == 13){ submitLogin() }
  	}
</script>
<style>
  @media(min-width:992px){
    #login-input-box{
      width: 65% !important;
    }
  }
  @media(min-width:768px){
    #login-input-box{
      width: 70%;
    }
  }
  @media(max-width:767px){
    #login-input-box{
      width: 100%;
    }
  }
  
</style>
</head>

<body>
  <!-- 헤더 영역 -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
  	<jsp:include page="commons/header.jsp"/>
  </nav>

<!-- wrapper 시작 -->
  <div class="wrapper">
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
          
          <div id='login-input-box'>
           <div class="form-wrap">
                <hr>
                <h3>로그인</h3>
                    <form role="form" method="post" id="login-form" autocomplete="off">
                        <div class="form-group">
                            <label for="input-id" class="sr-only">ID</label>
                            <input type="text" name="member_id" id="input-id" class="form-control" placeholder="아이디" onkeyup="tabPasswordInput()">
                        </div>
                        <div class="form-group">
                            <label for="input-pw" class="sr-only">Password</label>
                            <input type="password" name="member_pw" id="input-pw" class="form-control" placeholder="패스워드" onkeyup="enterkey()">
                            <div class="label" id="login-valid-check-box">
                            	<c:if test='${loginFailed == true}'>
                            		로그인에 실패하였습니다.
                            	</c:if>
                            	<c:if test='${loginedFilter == false}'>
                            		로그인이 만료되어 로그인 페이지로 이동했습니다.
                            	</c:if>
                            </div>
                        </div>
                        <input type="button" id="btn-login" class="btn btn-custom btn-lg btn-block" value="로그인" onclick="submitLogin()">
                    </form>
                    <a href="#">Forgot your ID or password?</a><br>
                    <a href="${pageContext.request.contextPath}/joinMemberForm">회원가입</a>
                    <hr>
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