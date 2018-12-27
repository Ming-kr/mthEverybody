<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>자취생을 위한 모두의 떨이</title>
<link rel="shortcut icon" type="image⁄x-icon"
	href="${pageContext.request.contextPath}/resources/img/logo/image2.png">
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<!-- Custom styles for this template -->
<link
	href="${pageContext.request.contextPath}/resources/css/shop-homepage.css"
	rel="stylesheet">

<!-- 붙어서 온 아이들 -->
<link
	href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/subbootstrap.css"
	rel="stylesheet">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>

<!--//////////////새로 추가한 친구들///////////////  -->
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var exdate = document.getElementById('exdate').value;
		document.getElementById("prod_exdate").value = exdate;
	});
	
	function check() {
		
		if(fr.prod_exdate.value == "" && fr.prod_exHour.value == "" 
				&& fr.prod_exMin.value == "" && fr.prod_exSec.value == ""){
			return true;
		}
		
		if(fr.prod_exdate.value == "") {
		 alert("유통기한 '날짜'를 입력해 주세요.");
		  fr.prod_exdate.focus();
		  return false;
		}
		else if(fr.prod_exHour.value == "") {
		  alert("유통기한 '시간(24시간 기준)'을 입력해 주세요.");
		  fr.prod_exHour.focus();
		  return false;
		}else if(fr.prod_exMin.value == "") {
		   alert("유통기한 '분'을 입력해 주세요.");
		   fr.prod_exMin.focus();
		   return false;
		}else if(fr.prod_exSec.value == "") {
		   alert("유통기한 '초'를 입력해 주세요.");
		   fr.prod_exSec.focus();
		   return false;
		}
		else return true;
	} 
</script>
<style>
.time{
	float: left;
	width: 70px;
	height:30px;
	margin: 2px 5px;
}
#contatti {
	background-color: #ffffff;
	letter-spacing: 2px;
}

@media ( max-width : 575.98px) {
	#contatti {
		padding-bottom: 800px;
	}
	#contatti .maps iframe {
		width: 100%;
		height: 450px;
	}
}

@media ( min-width : 576px) {
	#contatti {
		padding-bottom: 800px;
	}
	#contatti .maps iframe {
		width: 100%;
		height: 450px;
	}
}

@media ( min-width : 768px) {
	#contatti {
		padding-bottom: 350px;
	}
	#contatti .maps iframe {
		width: 100%;
		height: 850px;
	}
}

@media ( min-width : 992px) {
	#contatti {
		padding-bottom: 200px;
	}
	#contatti .maps iframe {
		width: 100%;
		height: 700px;
	}
}
</style>
</head>

<body>
	<!-- 헤더 영역 -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<jsp:include page="../commons/header.jsp" />
	</nav>

	<!-- wrapper 시작 -->
	<div class="wrapper">
		<!-- row 시작 -->
		<div class="row">
			<!-- /.col-lg-3 시작 -->
			<div class="col-lg-3">
				<!-- 사이드바 -->
				<h1 class="my-4">Seller</h1>
				<jsp:include page="../commons/asideMypage.jsp" />
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
				<form name="fr"
					action="${pageContext.request.contextPath }/seller/actionUpdateSellProductForm" onsubmit="return check()"
					method="post" enctype="multipart/form-data">
					<h2 class="text-uppercase mt-3 font-weight-bold">내 떨이물품 수정</h2>
					<div class="col-lg-4">
						<div class="form-group imageUpload">
							<label></label>
							<div
								style="height: 250px; border-radius: 10px; display: table; width: 100%;">
								<div
									style="display: table-cell; vertical-align: middle; text-align: center;">
									
									<c:choose>
	<c:when test="${prodRegisterData.itemVO.item_imgfilename eq 'noImage'}">
		<img style="width: 250px; height: 250px;" id="img"
										src="${pageContext.request.contextPath}/resources/img/noImage.jpg">
	</c:when>
	<c:otherwise>
		<img style="width: 250px; height: 250px;" id="img"
										src="/upload/${prodRegisterData.itemVO.item_imgfilename }">
	</c:otherwise>
</c:choose>
								</div>
							</div>
						</div>
					</div>
					<div class="row" style="margin-top: 20px;">
						<div class="col-lg-6">
							<div class="form-group">
								<label>작성자</label>
								<input value="${sessionLoginedData.member_nick }"
								type="text" class="form-control mt-2" readonly>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<input type="hidden" name="prod_idx"
									value="${prodRegisterData.prodVO.prod_idx}"> <input
									type="hidden" name="prod_condition" value="NORMAL">
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label for="conven_idx">판매 편의점명</label> <input type="text"
									class="form-control mt-2" name="conven_idx"
									value="${prodRegisterData.convenVO.conven_name }" readonly>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label for="item_type_idx">떨이 상품 종류 </label>
								<input type="text"
									class="form-control mt-2" name="item_type_idx"
									value="${prodRegisterData.itemTypeVO.item_type_name }" readonly>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label for="item_idx">떨이 상품 명</label>
								<input type="text"
									class="form-control mt-2" name="item_idx"
									value="${prodRegisterData.itemVO.item_name }" readonly>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label for="prod_originprice">소비자 권장가격(원)</label> <input
									type="text" class="form-control mt-2" id="prod_originprice"
									value="${prodRegisterData.itemVO.item_originprice }" readonly>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label for="prod_saleprice">할인판매가격(원)</label>
								<input type="number"
									class="form-control mt-2" placeholder="할인판매가격(원)"  min="1" max="${prodRegisterData.itemVO.item_originprice }"
									name="prod_saleprice" value="${prodRegisterData.prodVO.prod_saleprice }" required>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label for="exdate">유통기한(24시간 기준)</label> 
									<input type="text"
									class="form-control mt-2" name="exdate" id="exdate"
									value="${prodRegisterData.prodVO.prod_exdate }" readonly>
							</div>
						</div>
						
							<div class="col-lg-6">
							<div class="form-group">
								<label for="prod_exdate">*유통기한 변경 시에만 작성</label> 
								<input type="date"
									class="form-control mt-2" id ="prod_exdate" name="prod_exdate"  value="">
							</div>
							</div>
							<div class="col-lg-6">
							<div class="form-group">
								<label for="time">(날짜,시간,분,초 모두 작성)</label> 
								<!-- <div style="width:250px;height:40px;">
									<div class="time"><input class="form-control time" placeholder="시" id="prod_exHour" name="prod_exHour">
									</div><div class="time"><input 
									class="form-control time" placeholder="분" id="prod_exMin" name="prod_exMin"></div><div class="time"><input 
									class="form-control time" placeholder="초" id="prod_exSec" name="prod_exSec"></div></div> -->
								<div style="width: 260px; height: 40px;">
									<div class="time">
										<select style="height:33px;" id="prod_exHour" class="form-control time" name="prod_exHour">
											<c:set var="n" value="0" />
											<option value="" >시</option>
											<c:forEach begin="0" end="23">
											<option value="${n }" >${n }</option>
											<c:set var="n" value="${n+1}" />
											</c:forEach>
										</select> 
										
									</div>
									<div style="width:7px;float:left;"><p align="center">:</p></div>
									<div class="time">
										<select style="height:33px;" id="prod_exMin" class="form-control time" name="prod_exMin" >
											<c:set var="n" value="0" />
											<option value="" >분</option>
											<c:forEach begin="0" end="59">
											<option value="${n }" >${n }</option>
											<c:set var="n" value="${n+1}" />
											</c:forEach>
											</select>
									</div>
									<div style="width:7px;float:left;"><p align="center">:</p></div>
									<div class="time">
										<select style="height:33px;" id="prod_exSec" class="form-control time" name="prod_exSec" >
											<c:set var="n" value="0" />
											<option value="" >초</option>
											<c:forEach begin="0" end="59">
											<option value="${n }" >${n }</option>
											<c:set var="n" value="${n+1}" />
											</c:forEach>
											</select>
									</div>
								</div>
							</div>
							</div>
						
					</div>
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<label for="prod_salefinalprice">유통기한 1시간전 판매가격(원)
									(*필수아님)</label>
								<input type="number"
									class="form-control mt-2" min="1" max="${prodRegisterData.itemVO.item_originprice }"
									name="prod_salefinalprice" value="${prodRegisterData.prodVO.prod_salefinalprice }" >
							</div>
						</div>
						<div class="col-12">
							<div class="form-group">
								<label for="prod_content">떨이 판매 물품 상세 정보</label>
								<textarea class="form-control" id="exampleFormControlTextarea1"
									placeholder="떨이 판매 물품 상세 정보" rows="3" name="prod_content"
									required>${prodRegisterData.prodVO.prod_content }</textarea>
							</div>
						</div>
						<div class="col-4">
							<button class="btn btn-success" type="submit">수정</button>
						</div>
					</div>
				</form>
		</div>
		<!-- /.col-lg-9 끝 -->
	</div>
	<!-- /.row 끝 -->
	</div>
	<!-- /.wrapper 끝 -->
	<%-- <jsp:include page="commons/advertiseAside.jsp"/> --%>
	<!-- Footer 시작 -->
	<footer class="py-5 bg-dark">
		<jsp:include page="../commons/footer.jsp" />
	</footer>
	<!-- Footer 끝 -->
</body>
</html>