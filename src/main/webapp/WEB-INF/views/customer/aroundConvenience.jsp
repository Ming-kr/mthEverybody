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


    </style>
</head>

<body>
    <!-- 헤더 영역 -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <jsp:include page="../commons/header.jsp" />
    </nav>

    <!-- wrapper 시작 -->
    <div class="wrapper body">
        <!-- row 시작 -->
        <div class="row">
            <!-- /.col-lg-3 시작 -->
            <div class="col-lg-3">
                <!-- 사이드바 -->
                <h1 class="my-4">Home</h1>
                <jsp:include page="../commons/asideHome.jsp" />
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
                    <h3 style="margin-top:60px">브라우저에서 내 위치 사용을 허용하셔야 사용 가능합니다.</h3>
                    <h3>인터넷 사용환경에 따라 위치에 다소의 오차가 발생할 수 있습니다.</h3>
                    <div id="map" style="width:100%;height:500px;margin-bottom: 60px"></div>
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

                            var locPosition = new daum.maps.LatLng(33.450701, 126.570667),
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
                        setTimeout(function () {
                            // 장소 검색 객체를 생성합니다
                            var ps = new daum.maps.services.Places(map);

                            // 카테고리로 은행을 검색합니다
                            ps.categorySearch('CS2', placesSearchCB, {
                                useMapBounds: true
                            });

                            // 키워드 검색 완료 시 호출되는 콜백함수 입니다
                            function placesSearchCB(data, status, pagination) {
                                if (status === daum.maps.services.Status.OK) {
                                    for (var i = 0; i < data.length; i++) {
                                        displayMarkerPlace(data[i]);
                                    }
                                }
                            }

                            // 지도에 마커를 표시하는 함수입니다
                            function displayMarkerPlace(place) {
                                // 마커를 생성하고 지도에 표시합니다
                                var marker = new daum.maps.Marker({
                                    map: map,
                                    position: new daum.maps.LatLng(place.y, place.x)
                                });

                                // 마커에 클릭이벤트를 등록합니다
                                daum.maps.event.addListener(marker, 'click', function () {
                                    // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
                                    infowindow.setContent('<div style="padding:5px;font-size:12px;">' +
                                        place.place_name +
                                        '</div>');
                                    infowindow.open(map, marker);
                                });
                            }
                        }, 500);
                    </script>

                </div>

            </div>
            <!-- /.col-lg-9 끝 -->

        </div>
        <!-- /.row 끝 -->
    </div>
    <!-- /.wrapper 끝 -->

    <!-- Footer 시작 -->
    <footer class="py-5 bg-dark">
        <jsp:include page="../commons/footer.jsp" />
    </footer>
    <!-- Footer 끝 -->
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>