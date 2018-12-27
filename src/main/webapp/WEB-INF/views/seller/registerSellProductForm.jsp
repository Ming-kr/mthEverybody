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
<script type="text/javascript">

	function changeSelect() {
		//전달 값 세팅...
		if (document.getElementById("conven_idx").value != null) {
			var conven_idx = document.getElementById("conven_idx").value;
		} else
			var conven_idx = 0;

		if (document.getElementById("item_type_idx").value != null) {
			var item_type_idx = document.getElementById("item_type_idx").value;
		} else
			var item_type_idx = 0;

		//AJAX...
		var xmlhttp = new XMLHttpRequest();

		xmlhttp.onreadystatechange = function() {
			//값이 바뀔때마다 실행될 콜백함수...

			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {//처리완료 : response를 수신 && (페이지존재)성공
				//정상 응답 시 처리...
				//var confirmData = JSON.parse(xmlhttp.responseText);

				//alert(xmlhttp.responseText);
				var itemList = JSON.parse(xmlhttp.responseText);

				//innerHTML로 보낼 string
				//이미지, 소비자권장가격 리셋...
				document.getElementById("prod_originprice").value = "";
				document.querySelector('img[name="product_img"]').src = "${pageContext.request.contextPath}/resources/img/noImage.jpg";
				
				var item = "<option value=''>- 떨이 물품 선택 -</option>";
				for (i = 0; i < itemList.length; i++) {
					item += "<option value="+itemList[i].item_idx+">"
							+ itemList[i].item_name + "</option>";
				}
				document.getElementById("item_idx").innerHTML = item;
				
			}
		};

		xmlhttp.open("POST",
				"${pageContext.request.contextPath }/changeSelect", true);//true : 비동기식 , false : 동기식

		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");//던질 데이터타입지정

		//전달 값 세팅...로직..
		xmlhttp.send("conven_idx=" + conven_idx + "&item_type_idx="
				+ item_type_idx);//던질 데이터 내용

	}

	function changeSellProduct() {
		//전달 값 세팅...
		if (document.getElementById("item_idx").value != null) {
			var item_idx = document.getElementById("item_idx").value;
		} else
			var item_idx = 0;

		//AJAX...
		var xmlhttp = new XMLHttpRequest();

		xmlhttp.onreadystatechange = function() {
			//값이 바뀔때마다 실행될 콜백함수...

			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {//처리완료 : response를 수신 && (페이지존재)성공
				//정상 응답 시 처리...
				//var confirmData = JSON.parse(xmlhttp.responseText);

				//alert(xmlhttp.responseText);
				var itemVO = JSON.parse(xmlhttp.responseText);

				//innerHTML로 보낼 string
				document.getElementById("prod_originprice").value = itemVO.item_originprice;
				document.querySelector('input[name="prod_saleprice"]').max = itemVO.item_originprice;
				document.querySelector('input[name="prod_salefinalprice"]').max = itemVO.item_originprice;
				
				if(itemVO.item_imgfilename != 'noImage'){
					document.querySelector('img[name="product_img"]').src = "/upload/"
						+ itemVO.item_imgfilename;
				}
				
				
				
			}
		};

		xmlhttp.open("POST",
				"${pageContext.request.contextPath }/changeSellProduct", true);//true : 비동기식 , false : 동기식

		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");//던질 데이터타입지정

		//전달 값 세팅...로직..
		xmlhttp.send("item_idx=" + item_idx);//던질 데이터 내용

	}
</script>
<style>
.time {
	float: left;
	width: 70px;
	height: 30px;
	margin: 2px 1px;
	
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
				<form
					action="${pageContext.request.contextPath }/seller/actionRegisterSellProductForm"
					method="post" enctype="multipart/form-data">
					<h2 class="text-uppercase mt-3 font-weight-bold">내 떨이물품 등록</h2>
					<div class="col-lg-4">
						<div class="form-group imageUpload">
							<label></label>
							<div
								style="height: 250px; border-radius: 10px; display: table; width: 100%;">
								<div
									style="display: table-cell; vertical-align: middle; text-align: center;">
									<img alt="" name="product_img" src="${pageContext.request.contextPath}/resources/img/noImage.jpg"
										style="width: 250px; height: 250px;">
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
								<input type="hidden" name="member_idx"
									value="${sessionLoginedData.member_idx}"> <input
									type="hidden" name="prod_condition" value="NORMAL">
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label for="conven_idx">판매 편의점명</label> <select
									name="conven_idx" id="conven_idx" class="form-control mt-2"
									style="height: 35px;" onchange="changeSelect()" required>
									<option value="">- 내 편의점 선택 -</option>
									<c:forEach var="convenData" items="${convenDataList }">
										<c:if
											test="${convenData.convenVO.conven_condition eq 'NORMAL' }">
											<option value="${convenData.convenVO.conven_idx }">${convenData.brandVO.brand_name }
												${convenData.convenVO.conven_name }
												(${convenData.stateVO.state_name })</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label for="item_type_idx">떨이 상품 종류 </label> <select
									name="item_type_idx" id="item_type_idx"
									class="form-control
								mt-2" style="height: 35px;"
									onchange="changeSelect()" required>
									<option value="">- 떨이 상품 종류 선택 -</option>
									<c:forEach var="itemType" items="${itemTypeList }">
										<option value="${itemType.item_type_idx }">${itemType.item_type_name }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label for="item_idx">떨이 상품 명</label> <select name="item_idx"
									id="item_idx" class="form-control mt-2" style="height: 35px;"
									onchange="changeSellProduct()" required>
								</select>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label for="prod_originprice">소비자 권장가격(원)</label> <input
									type="text" class="form-control mt-2" id="prod_originprice"
									readonly>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label for="prod_saleprice">할인판매가격(원)</label> <input type="number"
									class="form-control mt-2" placeholder="할인판매가격(원)"  min="1" max=""
									name="prod_saleprice" required>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label for="prod_exdate">유통기한(24시간 기준)</label> <input
									type="date" class="form-control mt-2" name="prod_exdate"
									required>
								<div style="width: 250px; height: 40px;">
									<div class="time">
										<select style="height:33px;" id="prod_exHour" class="form-control time" name="prod_exHour" required>
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
										<select style="height:33px;" id="prod_exMin" class="form-control time" name="prod_exMin" required >
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
										<select style="height:33px;" id="prod_exSec" class="form-control time" name="prod_exSec" required >
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
									class="form-control mt-2" placeholder="유통기한 1시간전 판매가격(원)"  min="1" max=""
									name="prod_salefinalprice" id ="prod_salefinalprice">
							</div>
						</div>
						<div class="col-12">
							<div class="form-group">
								<label for="prod_content">떨이 판매 물품 상세 정보</label>
								<textarea class="form-control" id="exampleFormControlTextarea1"
									placeholder="떨이 판매 물품 상세 정보" rows="3" name="prod_content"
									required></textarea>
							</div>
						</div>
						<div class="col-4">
							<button class="btn btn-success" type="submit">등록</button>
						</div>
					</div>
				</form>
			</div>

		</div>
		<!-- /.col-lg-9 끝 -->
	</div>
	<!-- /.row 끝 -->
	<!-- /.wrapper 끝 -->
	<%-- <jsp:include page="commons/advertiseAside.jsp"/> --%>
	<!-- Footer 시작 -->
	<footer class="py-5 bg-dark">
		<jsp:include page="../commons/footer.jsp" />
	</footer>
	<!-- Footer 끝 -->
</body>
</html>