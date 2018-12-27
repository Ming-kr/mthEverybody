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
<link href="${pageContext.request.contextPath}/resources/css/css/requestsellerform.css" rel="stylesheet">
  <!-- 붙어서 온 아이들 -->
  <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/subbootstrap.css" rel="stylesheet">
 <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
 <script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
 <script src="${pageContext.request.contextPath}/resources/js/requestsellerform.js"></script>

 <!-- 각 항목 유효성 검사는 requestsellerform.js 파일에 분리 -->
 <script>
 //모든 값이 유효하면 form action 설정해주고 서브밋 수행
 function submitRequestSellerForm(){
	 if(checkAllValue == true)
	 {
		document.getElementById('requestSellerForm').action="${pageContext.request.contextPath}/actionRequestSellerForm";
		document.getElementById('requestSellerForm').submit();
	 }
	 else { alert('입력값이 유효한지 확인해주세요.'); return false; }
 }
 </script>
</head>

<body>
  <!-- 헤더 영역 -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
  	<jsp:include page="../commons/header.jsp"/>
  </nav>

<!-- wrapper 시작 -->
  <div class="wrapper">
    <!-- row 시작 -->
    <div class="row">
      <!-- /.col-lg-3 시작 -->
      <div class="col-lg-3">
        <!-- 사이드바 -->
        <h1 class="my-4">Home</h1>
        <jsp:include page="../commons/asideHome.jsp"/>
        <!-- 광고상자 -->
        <div class="advertiseAside">
          <p>광고</p>
        </div>
        <!-- 광고상자 끝 -->
      </div>
      <!-- /.col-lg-3 끝 -->
      <!-- col-lg-9 시작 -->
      <div class="col-lg-9">
          <!-- 내용 넣는 공간 -->
          
    <div id="requestSeller-input-box">	
	<div class="row">
	<h3>판매자 회원 신청</h3><br/>
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<!-- Start form -->
			<div id='requestSeller-form-wrap'>
					<div class="form-group">
                        <label>닉네임</label>
                        <input type="text" class="form-control" value="${sessionLoginedData.member_nick}" readonly>
                    </div>
                    
				<form id="requestSellerForm" method="post" autocomplete="off">
                    <div class="form-group">
                        <label for="input-name">실명</label>
                        <input type="text" class="form-control" name="member_name" id="input-name" placeholder="한글 2자이상 20자이내를 입력해주세요." required>
                    </div>
                    <div class="form-group">
                        <label for="input-phone">전화번호</label>
                        <input type="email" class="form-control" name="member_phone"id="input-phone" placeholder="-를 제외한 숫자를 입력해주세요.(ex. 01012345678)" required>
                    </div>
                    <div class="form-group">
                        <label for="input-companynum">사업자등록번호</label>
                        <input type="email" class="form-control" name="member_companynum"id="input-companynum" placeholder="숫자 10자를 입력해주세요." required>
                    </div>
                     <div class="form-group">
                     	<input type="checkbox" id="agree-request"> <a href="#">개인정보 관련 약관</a>에 동의합니다.
                     </div>
                    <div class="form-check" id="requestSellerForm-btn">
                    		<button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/mainPage'">취소</button>
                    		<!-- requestSellerFormCheck()는 requestsellerform.js에 선언 -->
                   	    	<button type="button" class="btn btn-primary" onclick="javascript:requestSellerFormCheck(); return false;">판매회원 신청</button>
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
    <jsp:include page="../commons/footer.jsp"/>
  </footer>
  <!-- Footer 끝 -->
  </body>
</html>