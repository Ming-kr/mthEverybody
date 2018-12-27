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
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>

	<!--//////////////새로 추가한 친구들///////////////  -->
	<style>
		.wrapper.mapmap{
		margin: 60px 0;
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
				<div class="row">
					<div class="wrapper mapmap">
						<div class="row">
							<!-- 지도를 표시할 div -->
							<div id="map" style="width: 95%; height: 350px; text-align:center; margin-left: 2%;"></div>
							<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ecd4aaae07b466b2c5897897abe59586&libraries=services"></script>
							<script>
								var infowindow = new daum.maps.InfoWindow({
                            zIndex: 1
                        });
                        var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
                            mapOption = {
                                center: new daum.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
                                level: 3 // 지도의 확대 레벨 
                            };

                        var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

                        // HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
                        if (navigator.geolocation) {

                            // GeoLocation을 이용해서 접속 위치를 얻어옵니다
                            navigator.geolocation.getCurrentPosition(function (position) {

                                var lat = position.coords.latitude, // 위도
                                    lon = position.coords.longitude; // 경도

                                var locPosition = new daum.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
                                    message = '<div style="padding:5px;">여기에 계신가요?!</div>'; // 인포윈도우에 표시될 내용입니다

                                // 마커와 인포윈도우를 표시합니다
                                displayMarker(locPosition, message);

                            });

                        } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

                            var locPosition = new daum.maps.LatLng(37.566826, 126.9786567),
                                message = 'geolocation을 사용할수 없어요..'

                            displayMarker(locPosition, message);
                        }

                        // 지도에 마커와 인포윈도우를 표시하는 함수입니다
                        function displayMarker(locPosition, message) {

                            // 마커를 생성합니다
                            var marker = new daum.maps.Marker({
                                map: map,
                                position: locPosition
                            });

                            var iwContent = message, // 인포윈도우에 표시할 내용
                                iwRemoveable = true;

                            // 인포윈도우를 생성합니다
                            var infowindow = new daum.maps.InfoWindow({
                                content: iwContent,
                                removable: iwRemoveable
                            });

                            // 인포윈도우를 마커위에 표시합니다 
                            infowindow.open(map, marker);

                            // 지도 중심좌표를 접속위치로 변경합니다
                            map.setCenter(locPosition);
                        }
							</script>
							<div class="col-md-6">
								<h2 class="text-uppercase mt-3 font-weight-bold">내 편의점 등록</h2>
								<form action="${pageContext.request.contextPath }/seller/actionRequestConvenienceForm" method="post">
									<div class="row">
										<div class="col-lg-6">
											<div class="form-group">
												<input type="hidden" name="member_idx" value="${sessionLoginedData.member_idx}">
												<!--////////////////////SessionLoginedData//////////////////////  -->
												<input value="작성자  ${sessionLoginedData.member_nick }"
												type="text" class="form-control mt-2" readonly>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<input type="text" class="form-control mt-2" placeholder="편의점 명(ex : 용산용문점)" name="conven_name" required>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<select name="brand_idx" class="form-control mt-2" style="height: 35px;" required>
													<option value="">-편의점 브랜드-</option>
													<c:forEach var="brandVO" items="${brandList }">
														<option value="${brandVO.brand_idx }">${brandVO.brand_name }</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<select name="state_idx" class="form-control mt-2" style="height: 35px;" required>
													<option value="">-편의점 위치(구)-</option>
													<c:forEach var="stateVO" items="${stateList }">
														<option value="${stateVO.state_idx }">${stateVO.state_name }</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-12">
											<div class="form-group">
												<textarea class="form-control" id="exampleFormControlTextarea1" placeholder="편의점 위치 상세주소(ex : 효창원로 117)" rows="3" name="conven_adress"
												 required></textarea>
											</div>
										</div>
										<div class="col-12">
											<div class="form-group">
												<div class="form-check">
													<input class="form-check-input" type="checkbox" value="" id="invalidCheck2" required>&nbsp; &nbsp; <label
													 class="form-check-label" for="invalidCheck2"> 위
														편의점은 관리자 승인 이후 내편의점리스트 등록 및 물품등록 가능합니다. </label>
												</div>
											</div>
										</div>
										<div class="col-12">
											<button style="border: 1px solid gray;" class="btn btn-light" type="submit">등록</button>
										</div>
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
		<jsp:include page="../commons/footer.jsp" />
	</footer>
	<!-- Footer 끝 -->
</body>

</html>