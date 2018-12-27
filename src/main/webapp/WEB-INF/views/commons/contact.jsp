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
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>

  <title>자취생을 위한 모두의 떨이</title>
  <link rel="shortcut icon" type="image⁄x-icon" href="${pageContext.request.contextPath}/resources/img/logo/image2.png">
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/subbootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/css/shop-homepage.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main/selectbox.css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

    <script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
    <style>
        .wrapper.box >img{
            width: 100%;
            height: 500px;
            margin: 30px 0;
        }
      
        .wrapper.box{
            width: 100%;
        }
        </style>
</head>

<body>
  <!-- 헤더 영역 -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
  	<jsp:include page="../commons/header.jsp"/>
  </nav>

<!-- wrapper 시작 -->
  <div class="wrapper body">
    <!-- row 시작 -->
    <div class="row">
      <!-- /.col-lg-3 시작 -->
      <div class="col-lg-3">
        <!-- 사이드바 -->
        <h1 class="my-4">Home</h1>
        <jsp:include page="../commons/asideHome.jsp"/>
        <!-- 사이드바 끝 -->
        <!-- 광고상자 -->
        <div class="advertiseAside">
          <p>광고</p>
        </div>
        <!-- 광고상자 끝 -->
      </div>
      <!-- /.col-lg-3 끝 -->
      <!-- col-lg-9 시작 -->
      <div class="col-lg-9">
        <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
          <!-- 내용 넣는 공간 -->
          <div class="wrapper box">
            <img src="${pageContext.request.contextPath}/resources/img/about/contact.png" alt="">
          </div>
          
        </div>
        
      </div>
      <!-- /.col-lg-9 끝 -->
      
    </div> 
    <!-- /.row 끝 -->
  </div>
  <!-- /.wrapper 끝 -->
  
  <!-- Footer 시작 -->
  <footer class="py-5 bg-dark">
    <jsp:include page="../commons/footer.jsp"/>
  </footer>
  <!-- Footer 끝 -->
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>
</html>