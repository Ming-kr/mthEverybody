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
	<!-- Bootstrap core CSS -->
	<link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/subbootstrap.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="${pageContext.request.contextPath}/resources/css/shop-homepage.css" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main/selectbox.css">

	<script src="//netdna.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
	<!-- 체크박스 폰트 -->
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	<title>자취생을 위한 모두의 떨이</title>
	<link rel="shortcut icon" type="image⁄x-icon" href="${pageContext.request.contextPath}/resources/img/logo/image2.png">
	<script>
		$(document).ready(function() {
		$('.mb-4').css('display', 'none');
 		for(var i=1; i<=9; i++) {
			$('.prodIndex'+i+'').fadeIn('slow');
		}
	});
	
	$(window).scroll(function() {
		
		var scrollHeight = $(document).height();
		var scrollPosition = $(window).height() + $(window).scrollTop();
		if ((scrollHeight - scrollPosition) / scrollHeight === 0) {
			var prodCount = $('#prodController').val();
			prodCount *= 1; 
			prodCount = prodCount + 6;
			console.log(prodCount);
			$('#prodController').val(prodCount);
		} else {
			$("body").css("background","white");
		}
		for(var i=prodCount-6; i<=prodCount; i++) {
			$('.prodIndex'+i+'').fadeIn('slow');
		}	
	});
	
	function getTime() { 
	    var now = new Date();
	   
	    for(var i=0; i<document.getElementsByClassName("counter0").length; i++) { //클래스[배열] 길이만큼 for문을 돌려서 각 클래스 처음부터 끝까지 시행
	        var exdate = document.getElementsByClassName("counter0")[i].value;
	    	var exyear = exdate.substr(0,4);
	    	var exmonth = exdate.substr(5,2);
	    	var exday = exdate.substr(8,2);
	    	var extime = exdate.substr(11,2);
	        var exminutes = exdate.substr(14,2);
	        var exseconds = exdate.substr(17,2);
	        
	        var dday = new Date(exyear, exmonth, exday, extime, exminutes, exseconds); // 원하는 날짜, 시간 정확하게 초단위까지 기입.
	        var days = (dday - now) / 1000 / 60 / 60 / 24;
	        var daysRound = Math.floor(days);
	        var hours = (dday - now) / 1000 / 60 / 60 - (24 * daysRound);
	        var hoursRound = Math.floor(hours);
	        var minutes = (dday - now) / 1000 / 60 - (24 * 60 * daysRound) - (60 * hoursRound);
	        var minutesRound = Math.floor(minutes);
	        var seconds = (dday - now) / 1000 - (24 * 60 * 60 * daysRound) - (60 * 60 * hoursRound) - (60 * minutesRound);
	        var secondsRound = Math.round(seconds);
	        
	  	    document.getElementsByClassName("counter1")[i].innerHTML = hoursRound;
	        document.getElementsByClassName("counter2")[i].innerHTML = minutesRound;
	        document.getElementsByClassName("counter3")[i].innerHTML = secondsRound;
	    }
	    newtime = window.setTimeout("getTime();", 1000);
	  }
	
	function likeItem(item_idx) { //좋아요 ajax 로직...
		var xmlhttp = new XMLHttpRequest();
		
		xmlhttp.onreadystatechange = function(){
		
			if(xmlhttp.readyState == 4 && xmlhttp.status==200) { //200이면 완전 정상
				var likeItem = JSON.parse(xmlhttp.responseText);
				if(likeItem.success == "fail") {
					alert("로그인을 먼저하세요!");
				} else if(likeItem.likeItemInsert == "insert") {
					$(".img"+item_idx+"").attr("src","${pageContext.request.contextPath}/resources/img/like_inline.png");
					$(".likeCount"+item_idx+"").text(likeItem.likeItemCount);
				} else {
					$(".img"+item_idx+"").attr("src","${pageContext.request.contextPath}/resources/img/like_outline.png");
					$(".likeCount"+item_idx+"").text(likeItem.likeItemCount);
				}
			}
		};
		
		xmlhttp.open("POST", "${pageContext.request.contextPath}/likeItem", true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xmlhttp.send("item_idx="+item_idx);
	   	
	}	
	  getTime();
</script>
<style>
	.form-control{
		display: inline-block;
		margin-top: 10px;
	}
	.form-control.select1{
		width: 120px;
	}
	.form-control.select2{
		width: 105px;
	}
	.form-control.text{
		width: 200px;
	}
	.btn.btn-success{
		margin-bottom: 7px;
	}
</style>
</head>

<body>
	<input type="hidden" id="prodController" value=6>
	<!-- 헤더 영역 -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<jsp:include page="commons/header.jsp" />
	</nav>


	<div class="wrapper">
		<div class="row">

			<div class="col-lg-3">
				<!-- 사이드바 -->
				<h1 class="my-4">Home</h1>
				<jsp:include page="commons/asideHome.jsp" />
				<div class="advertiseAside">
					<p>광고</p>
				</div>
			</div>

			<!-- /.col-lg-3 -->

			<div class="col-lg-9">

				<div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">

					<div class="carousel-inner" role="listbox">
						<div class="carousel-item active">
							<div class="row">
								<div class="col-md-6">
									<div class="panel with-nav-tabs panel-info">
										<div class="panel-heading">
											<ul class="nav nav-tabs">
												<li class="active"><a href="#tab1info" data-toggle="tab">편의점별로 검색</a></li>
												<li><a href="#tab2info" data-toggle="tab">물품 종류별로
														검색</a></li>
												<li><a href="#tab3info" data-toggle="tab">지역단위(구)별로
														검색</a></li>
												<li><a href="#tab4info" data-toggle="tab">가격별 검색</a></li>
											</ul>
										</div>
										<div class="panel-body">
											<!-- 셀렉트박스 시작 -->
											<form action="${pageContext.request.contextPath }/mainPage" method="post">
												<div class="tab-content">

													<!-- 브랜드 출력 -->
													<div class="tab-pane fade in active" id="tab1info">

														<c:forEach var='brandVO' items='${brandList }'>
															<div class="checkbox checkbox-info">

																<!-- 브랜드 체크한 것 검사 -->
																<c:set var='checkBrand' value='false' />
																<c:forEach var='brandName' items='${brandNameList }'>
																	<c:if test='${brandName eq brandVO.brand_name }'>
																		<c:set var='checkBrand' value='true' />
																	</c:if>
																</c:forEach>

																<c:if test='${checkBrand}'>
																	<input id="${brandVO.brand_name }" type="checkbox" name="brand_name" value="${brandVO.brand_name }"
																	 data-filter="${brandVO.brand_name }" checked="checked">
																</c:if>

																<c:if test='${!checkBrand}'>
																	<input id="${brandVO.brand_name }" type="checkbox" name="brand_name" value="${brandVO.brand_name }"
																	 data-filter="${brandVO.brand_name }">
																</c:if>
																<!-- 브랜드 체크한 것 검사 끝 -->

																<label for="${brandVO.brand_name }">
																	${brandVO.brand_name } </label>
															</div>
														</c:forEach>

													</div>
													<!-- 브랜드 출력 끝 -->

													<!-- 물품 종류 출력 -->
													<div class="tab-pane fade" id="tab2info">

														<c:forEach var='itemTypeVO' items='${itemTypeList }'>
															<div class="checkbox checkbox-info">

																<!-- 물품 종류 체크한 것 검사 -->
																<c:set var='checkItemType' value='false' />
																<c:forEach var='itemTypeName' items='${itemTypeNameList }'>
																	<c:if test='${itemTypeName eq itemTypeVO.item_type_name }'>
																		<c:set var='checkItemType' value='true' />
																	</c:if>
																</c:forEach>

																<c:if test='${checkItemType}'>
																	<input id="${itemTypeVO.item_type_name }" type="checkbox" name="item_type_name" value="${itemTypeVO.item_type_name }"
																	 data-filter="${itemTypeVO.item_type_name }" checked="checked">
																</c:if>

																<c:if test='${!checkItemType}'>
																	<input id="${itemTypeVO.item_type_name }" type="checkbox" name="item_type_name" value="${itemTypeVO.item_type_name }"
																	 data-filter="${itemTypeVO.item_type_name }">
																</c:if>
																<!-- 물품 종류 체크 검사 끝 -->

																<label for="${itemTypeVO.item_type_name }">
																	${itemTypeVO.item_type_name } </label>
															</div>
														</c:forEach>

													</div>
													<!-- 물품종류 끝 -->

													<div class="tab-pane fade" id="tab3info">

														<c:forEach var='stateVO' items='${stateList }'>
															<div class="checkbox checkbox-info">

																<!-- 구 체크한 것 검사 -->
																<c:set var='checkState' value='false' />
																<c:forEach var='stateName' items='${stateNameList }'>
																	<c:if test='${stateName eq stateVO.state_name }'>
																		<c:set var='checkState' value='true' />
																	</c:if>
																</c:forEach>

																<c:if test='${checkState}'>
																	<input id="${stateVO.state_name }" type="checkbox" name="state_name" value="${stateVO.state_name }"
																	 data-filter="${stateVO.state_name }" checked="checked">
																</c:if>

																<c:if test='${!checkState}'>
																	<input id="${stateVO.state_name }" type="checkbox" name="state_name" value="${stateVO.state_name }"
																	 data-filter="${stateVO.state_name }">
																</c:if>
																<!-- 구 체크한 것 검사 끝 -->
																<label for="${stateVO.state_name }">
																	${stateVO.state_name } </label>
															</div>
														</c:forEach>

													</div>
													<div class="tab-pane fade" id="tab4info">
														<div class="radio radio-primary">
															<c:if test='${searchData.searchPrice == 1000 }'>
																<input type="radio" name="searchPrice" id="radio1" value="1000" checked="checked">
															</c:if>
															<c:if test='${searchData.searchPrice != 1000 }'>
																<input type="radio" name="searchPrice" id="radio1" value="1000">
															</c:if>
															<label for="radio1"> 1000원 이하 </label>
														</div>
														<div class="radio radio-primary">
															<c:if test='${searchData.searchPrice == 2000 }'>
																<input type="radio" name="searchPrice" id="radio2" value="2000" checked="checked">
															</c:if>
															<c:if test='${searchData.searchPrice != 2000 }'>
																<input type="radio" name="searchPrice" id="radio2" value="2000">
															</c:if>
															<label for="radio2"> 2000원 이하 </label>
														</div>
														<div class="radio radio-primary">
															<c:if test='${searchData.searchPrice == 3000 }'>
																<input type="radio" name="searchPrice" id="radio3" value="3000" checked="checked">
															</c:if>
															<c:if test='${searchData.searchPrice != 3000 }'>
																<input type="radio" name="searchPrice" id="radio3" value="3000">
															</c:if>
															<label for="radio3"> 3000원 이하 </label>
														</div>
														<div class="radio radio-primary">
															<c:if test='${searchData.searchPrice == 4000 }'>
																<input type="radio" name="searchPrice" id="radio4" value="4000" checked="checked">
															</c:if>
															<c:if test='${searchData.searchPrice != 4000 }'>
																<input type="radio" name="searchPrice" id="radio4" value="4000">
															</c:if>
															<label for="radio4"> 4000원 이하 </label>
														</div>
														<div class="radio radio-primary">
															<c:if test='${searchData.searchPrice == 5000 }'>
																<input type="radio" name="searchPrice" id="radio5" value="5000" checked="checked">
															</c:if>
															<c:if test='${searchData.searchPrice != 5000 }'>
																<input type="radio" name="searchPrice" id="radio5" value="5000">
															</c:if>
															<label for="radio5"> 5000원 이하 </label>
														</div>
														<div class="radio radio-primary">
															<c:if test='${searchData.searchPrice == 5001 }'>
																<input type="radio" name="searchPrice" id="radio6" value="5001" checked="checked">
															</c:if>
															<c:if test='${searchData.searchPrice != 5001 }'>
																<input type="radio" name="searchPrice" id="radio6" value="5001">
															</c:if>
															<label for="radio6"> 5000원 이상 </label>
														</div>
													</div>
												</div>
												<!-- 정렬순 -->
												<select id='searchOrder' name='searchOrder' class="form-control select1">
													<c:choose>
														<c:when test='${searchData.searchOrder eq "prod_exdate"}'>
															<option value='prod_idx'>등록순</option>
															<option value='prod_exdate' selected>유통기한순</option>
															<option value='prod_saleprice'>가격순</option>
														</c:when>
														<c:when test='${searchData.searchOrder eq "prod_saleprice"}'>
															<option value='prod_idx'>등록순</option>
															<option value='prod_exdate'>유통기한순</option>
															<option value='prod_saleprice' selected>가격순</option>
														</c:when>
														<c:otherwise>
															<option value='prod_idx' selected>등록순</option>
															<option value='prod_exdate'>유통기한순</option>
															<option value='prod_saleprice'>가격순</option>
														</c:otherwise>
													</c:choose>
												</select>
												<!-- 검색카테고리 -->
												<select id='searchSelect' name='searchSelect' class="form-control select2">
													<c:if test='${searchData.searchSelect ==  conven_name}'>
														<option value='item_name'>물품명</option>
														<option value='conven_name' selected>편의점명</option>
													</c:if>
													<c:if test='${searchData.searchSelect !=  conven_name}'>
														<option value='item_name' selected>물품명</option>
														<option value='conven_name'>편의점명</option>
													</c:if>
												</select>
												<!-- 검색... -->
												<input type='text' id='searchWord' name='searchWord' value='${searchData.searchWord }' class="form-control text"> 
												<input type="submit"
												 value="검색" class="btn btn-success">
											</form>
											<!-- 셀렉트박스 끝 -->


										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>

				<ul id="lists" class="row items">

					<c:forEach var='prodData' items='${prodDataList }' varStatus='prodStatus'>
						<li class="${prodData.brandVO.brand_name } ${prodData.itemVO.item_name } ${prodData.stateVO.state_name } col-lg-4 col-md-6 mb-4 prodIndex${prodStatus.count}"
						 value="${prodData.prodVO.prod_saleprice }">
							<div class="card h-100">

								<!-- 클릭하면 물품 정보로 넘어가게... -->
								<a href="${pageContext.request.contextPath }/readSellProduct?prod_idx=${prodData.prodVO.prod_idx}">

									<!-- 이미지 파일 넣기 로직.. -->
									<%-- <img class="card-img-top" src="${pageContext.request.contextPath}/resources/img/prod/${prodData.itemVO.item_imgfilename}" alt="모찌롤"> --%>

									<c:choose>
										<c:when test="${prodData.itemVO.item_imgfilename eq 'noImage'}">
											<img class="card-img-top" src="${pageContext.request.contextPath}/resources/img/noImage.jpg">
										</c:when>
										<c:otherwise>
											<img class="card-img-top" src="/upload/${prodData.itemVO.item_imgfilename }">
										</c:otherwise>
									</c:choose>
									<!-- 브랜드 로고 넣기 로직... -->
									<c:if test='${prodData.brandVO.brand_name=="CU" }'>
										<img src="${pageContext.request.contextPath}/resources/img/logo/CU.svg" alt="CU로고" class="con-logo">
									</c:if>
									<c:if test='${prodData.brandVO.brand_name=="GS25" }'>
										<img src="${pageContext.request.contextPath}/resources/img/logo/gs25.svg" alt="GS25로고" class="con-logo">
									</c:if>
									<c:if test='${prodData.brandVO.brand_name=="세븐일레븐" }'>
										<img src="${pageContext.request.contextPath}/resources/img/logo/7-eleven.svg" alt="세븐일레븐로고" class="con-logo">
									</c:if>
									<c:if test='${prodData.brandVO.brand_name=="이마트24" }'>
										<img src="${pageContext.request.contextPath}/resources/img/logo/emart24.svg" alt="이마트24로고" class="con-logo">
									</c:if>
									<c:if test='${prodData.brandVO.brand_name=="미니스톱" }'>
										<img src="${pageContext.request.contextPath}/resources/img/logo/ministop.svg" alt="미니스톱로고" class="con-logo">
									</c:if>
									<c:if test='${prodData.brandVO.brand_name=="기타" }'>
										<img src="${pageContext.request.contextPath}/resources/img/logo/logo.svg" alt="기타로고" class="con-logo">
									</c:if> <!-- 브랜드 로고 넣기 로직 끝... -->


								</a>

								<div class="card-body">
									<div class="con-places">
										<span class="con-place">${prodData.stateVO.state_name }</span>

										<!-- a태그로 감싸서 클릭하면 편의점 정보를 볼 수 있게... -->
										<h6>
											<a href="${pageContext.request.contextPath }/readConvenience?conven_idx=${prodData.convenVO.conven_idx}">
												${prodData.convenVO.conven_name }</a>
										</h6>

									</div>
									<h4 class="card-title">

										<!-- 클릭하면 물품 정보로 넘어가게... -->
										<a href="${pageContext.request.contextPath }/readSellProduct?prod_idx=${prodData.prodVO.prod_idx}">${prodData.itemVO.item_name
											}</a>
									</h4>
									<h5>할인가: ${prodData.prodVO.prod_saleprice } 재고량:
										${prodData.prod_stockCount }</h5>
									<h3 class="origin-price">정가:
										${prodData.itemVO.item_originprice }</h3>
									<p class="card-text">${prodData.prodVO.prod_content }</p>
									<div id=countTime>
										<input type="hidden" class="counter0" value="${prodData.prodVO.prod_exdate}"> <SPAN class=counter1></SPAN>시간
										<SPAN class=counter2></SPAN>분 <SPAN class=counter3></SPAN>초 <SPAN></SPAN>
									</div>
								</div>
								<div class="card-footer">

									<!-- 누르면 추천 수 증가... -->
									<!-- 이미지는 빨간색으로 변경되게 -->
									<div>

										<c:if test='${prodData.itemLikeCheck }'>
											<img class='img${prodData.itemVO.item_idx }' src="${pageContext.request.contextPath}/resources/img/like_inline.png"
											 alt="좋아요" style="cursor: pointer;" onclick="likeItem(${prodData.itemVO.item_idx })">
										</c:if>
										<c:if test='${!prodData.itemLikeCheck }'>
											<img class='img${prodData.itemVO.item_idx }' src="${pageContext.request.contextPath}/resources/img/like_outline.png"
											 alt="좋아요" style="cursor: pointer;" onclick="likeItem(${prodData.itemVO.item_idx })">
										</c:if>
										<small style="cursor: pointer;" onclick="likeItem(${prodData.itemVO.item_idx })">좋아요 : </small><small class="text-muted likeCount${prodData.itemVO.item_idx }">${prodData.itemLikeCount
											}</small>

									</div>
									<div style="float: right;">
										<img src="${pageContext.request.contextPath}/resources/img/reply.png" alt="댓글" style="width: 1.3em;
										height: 1.3em;
										margin-right: 2px;">
										<small>댓글 : ${prodData.itemReCount }</small>
									</div>
								</div>
							</div>
						</li>
					</c:forEach>

				</ul>
				<!-- /.row -->

			</div>
			<!-- /.col-lg-9 -->

		</div>
		<!-- /.row -->

	</div>
	<!-- /.wrapper -->

	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<!-- /.wrapper -->
		<jsp:include page="commons/footer.jsp" />
	</footer>

	<!-- Bootstrap core JavaScript -->

	<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>

</html>