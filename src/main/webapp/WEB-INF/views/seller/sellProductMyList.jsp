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
<!-- //////////내가 추가한 친구들////////////// -->
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<style>
body {
	font-family: 'Open Sans', sans-serif;
	color: #353535;
}

.content h1 {
	text-align: center;
}
/*	--------------------------------------------------
	:: Table Filter
	-------------------------------------------------- */
.panel {
	border: 1px solid #ddd;
	background-color: #fcfcfc;
	width: 900px;
}

.panel .btn-group {
	margin: 15px 0 30px;
}

.panel .btn-group .btn {
	transition: background-color .3s ease;
}

.table-filter {
	background-color: #fff;
	border-bottom: 1px solid #eee;
}

.table-filter tbody tr:hover {
	cursor: pointer;
	background-color: #eee;
}

.table-filter tbody tr td {
	padding: 10px;
	vertical-align: middle;
	border-top-color: #eee;
}

.table-filter tbody tr.selected td {
	background-color: #eee;
}

/* .table-filter tr td:first-child {
	width: 380px;
} */

/* .table-filter tr td:nth-child(2) {
	width: 35px;
}
 */
.table-filter .media-photo {
	width: 35px;
}

.table-filter .media-body {
	display: block;
	/* Had to use this style to force the div to expand (wasn't necessary with my bootstrap version 3.3.6) */
}

.table-filter .media-meta {
	font-size: 11px;
	color: #999;
}

.table-filter .media .title {
	color: #2BBCDE;
	font-size: 14px;
	font-weight: bold;
	line-height: normal;
	margin: 0;
}

.table-filter .media .title span {
	font-size: .8em;
	margin-right: 20px;
}

.table-filter .media .title span.ONSALE {
	color: #5cb85c;
}

.table-filter .media .title span.SOLDOUT {
	color: #f0ad4e;
}

.table-filter .media .title span.EXPIRED {
	color: #d9534f;
}

.wrap {
	width: 70px;
	height: 70px;
}

.wrap a {
	display: block;
	max-width: 50px;
	max-height: 50px;
	text-decoration: none;
	border-radius: 4px;
	margin: 2px auto;
}

a.button {
	color: rgba(30, 22, 54, 0.6);
	box-shadow: rgba(30, 22, 54, 0.4) 0 0px 0px 2px inset;
}

a.button:hover {
	color: rgba(255, 255, 255, 0.85);
	box-shadow: rgba(30, 22, 54, 0.7) 0 0px 0px 40px inset;
}

.col-md-8 {
	width: 100%;
	flex: 0 0 100%;
	max-width: 100%;
}

.panel {
	width: 100%;
}

.btn-glyphicon {
	padding: 8px;
	background: #ffffff;
	margin-right: 4px;
}

.icon-btn {
	padding: 1px 15px 3px 2px;
	border-radius: 50px;
}

.btn-group {
	float: right;
}

.btn.icon-btn.btn-success {
	margin-top: 12px;
}

.non {
	height: 20px;
}

@media ( max-width :768px) {
	.buttonBox {
		text-align: center;
	}
	.btn-group {
		display: grid;
		float: none;
	}
	.table-container {
		margin-top: 130px;
	}
	.btn.icon-btn.btn-success {
		margin-top: -15px;
	}
}
</style>
<script type="text/javascript">
	$(document).ready(function() {

		$('.btn-filter').on('click', function() {
			var $target = $(this).data('target');
			if ($target != 'all') {
				$('.table tr').css('display', 'none');
				$('.table tr[data-status="' + $target + '"]').fadeIn('slow');
			} else {
				$('.table tr').css('display', 'none').fadeIn('slow');
			}
		});

	});
</script>
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
				<div class="advertiseAside">
					<p>광고</p>
				</div>
			</div>
			<!-- /.col-lg-3 끝 -->
			<!-- col-lg-9 시작 -->
			<div class="col-lg-9">
				<!-- 내용 넣는 공간 -->
				<div class="container">
					<div class="row">

						<section class="content" style="width: 100%;">
							<div class="col-md-8 col-md-offset-2" style="margin: 20px 0;">
								<div class="panel panel-default">
									<h1>내 떨이 물품 리스트</h1>
									<div class="panel-body">
										<div class="buttonBox" style="height: 70px;">
											<div class="btn-group">
												<button type="button" class="btn btn-success btn-filter"
													data-target="ONSALE">판매 중 떨이 물품</button>
												<button type="button" class="btn btn-warning btn-filter"
													data-target="SOLDOUT">판매 완료 물품</button>
												<button type="button" class="btn btn-danger btn-filter"
													data-target="EXPIRED">판매 중단 물품</button>
												<button type="button" class="btn btn-default btn-filter"
													data-target="all">모든 떨이 물품</button>
											</div>
											<a class="btn icon-btn btn-success"
												href="${pageContext.request.contextPath}/seller/registerSellProductForm"><span
												class="glyphicon btn-glyphicon glyphicon-plus img-circle text-success"></span>판매
												물품 등록</a>
										</div>
										<div class="table-container">
											<table class="table table-filter">
												<tbody>
													<c:choose>
														<c:when test="${empty prodDataList }">
															<td><h2 align="center">등록하신 떨이 물품이 없습니다</h2></td>
														</c:when>
														<c:otherwise>
															<c:forEach var="prodData" items="${prodDataList}">
																<c:choose>
																	<c:when
																		test="${prodData.prodVO.prod_condition eq 'NORMAL'}">
																		<tr data-status="ONSALE">
																			<td>
																				<div class="media">
																					<a
																						href="${pageContext.request.contextPath }/readSellProduct?prod_idx=${prodData.prodVO.prod_idx}"
																						class="pull-left"> <c:choose>
																							<c:when
																								test="${prodData.itemVO.item_imgfilename eq 'noImage'}">
																								
																								<img
																									src="${pageContext.request.contextPath}/resources/img/noImage.jpg"
																									class="media-photo">
																							</c:when>
																							<c:otherwise>
																								<img
																									src="/upload/${prodData.itemVO.item_imgfilename }"
																									class="media-photo">
																							</c:otherwise>
																						</c:choose> 
																					</a>
																					<div class="media-body" style="float: left;">
																						<span class="media-meta pull-right"
																							style="width: 70px;">${prodData.brandVO.brand_name}
																							${prodData.itemTypeVO.item_type_name}<br>
																							${prodData.convenVO.conven_name}
																						</span>
																						<h4 class="title">
																							<a
																								href="${pageContext.request.contextPath}/readSellProduct?prod_idx=${prodData.prodVO.prod_idx }">
																								${prodData.itemVO.item_name }</a>
																							(${prodData.itemVO.item_originprice}원 ->
																							${prodData.prodVO.prod_saleprice}원)<span
																								class="pull-right ONSALE">(판매 중)</span>
																						</h4>
																						<p class="summary">
																							${prodData.prodVO.prod_exdate }까지<br>
																							${prodData.prodVO.prod_content}
																						</p>
																					</div>
																					<div class="wrap">
																						<div align="center"
																							style="width: 50px; height: 70px; padding: 10px auto;">
																							<a
																								href="${pageContext.request.contextPath}/seller/updateSellProductForm?prod_idx=${prodData.prodVO.prod_idx }"
																								class="button">수정</a> <a
																								href="${pageContext.request.contextPath}/seller/expiredSellProduct?prod_idx=${prodData.prodVO.prod_idx }"
																								class="button">폐기</a> <a
																								href="${pageContext.request.contextPath}/seller/soldOutSellProduct?prod_idx=${prodData.prodVO.prod_idx }"
																								class="button">팔림</a>
																						</div>
																					</div>
																				</div>
																			</td>
																		</tr>

																	</c:when>
																	<c:when
																		test="${prodData.prodVO.prod_condition eq 'EXPIRED'}">
																		<tr data-status="EXPIRED">
																			<td>
																				<div class="media">
																					<a href="" class="pull-left"> 
																						<c:choose>
																							<c:when
																								test="${prodData.itemVO.item_imgfilename eq 'noImage'}">
																								
																								<img
																									src="${pageContext.request.contextPath}/resources/img/noImage.jpg"
																									class="media-photo">
																							</c:when>
																							<c:otherwise>
																								<img
																									src="/upload/${prodData.itemVO.item_imgfilename }"
																									class="media-photo">
																							</c:otherwise>
																						</c:choose> 
																					</a>
																					<div class="media-body">
																						<span class="media-meta pull-right"
																							style="width: 70px;">${prodData.brandVO.brand_name}
																							${prodData.itemTypeVO.item_type_name}
																							${prodData.convenVO.conven_name}</span>
																						<h4 class="title">
																							${prodData.itemVO.item_name }(${prodData.itemVO.item_originprice}원
																							-> ${prodData.prodVO.prod_saleprice}원) <span
																								class="pull-right EXPIRED">(폐기처리)</span>
																						</h4>
																						<p class="summary">
																							${prodData.prodVO.prod_content}</p>
																					</div>
																					<div class="wrap">
																						<div align="center"
																							style="width: 50px; height: 20px; padding: 10px auto;">
																							<div class="non"></div>
																							<a
																								href="${pageContext.request.contextPath}/seller/deleteSellProduct?prod_idx=${prodData.prodVO.prod_idx }"
																								class="button">삭제</a>
																							<div class="non"></div>
																						</div>
																					</div>
																				</div>
																			</td>
																		</tr>
																	</c:when>
																	<c:when
																		test="${prodData.prodVO.prod_condition eq 'SOLDOUT'}">
																		<tr data-status="SOLDOUT">
																			<td>
																				<div class="media">
																					<a href="" class="pull-left">
																						<c:choose>
																							<c:when
																								test="${prodData.itemVO.item_imgfilename eq 'noImage'}">
																								
																								<img
																									src="${pageContext.request.contextPath}/resources/img/noImage.jpg"
																									class="media-photo">
																							</c:when>
																							<c:otherwise>
																								<img
																									src="/upload/${prodData.itemVO.item_imgfilename }"
																									class="media-photo">
																							</c:otherwise>
																						</c:choose> 
																					</a>
																					<div class="media-body">
																						<span class="media-meta pull-right"
																							style="width: 70px;">${prodData.brandVO.brand_name}
																							${prodData.itemTypeVO.item_type_name}
																							${prodData.convenVO.conven_name}</span>
																						<h4 class="title">
																							${prodData.itemVO.item_name }(${prodData.itemVO.item_originprice}원
																							-> ${prodData.prodVO.prod_saleprice}원) <span
																								class="pull-right SOLDOUT">(판매 완료)</span>
																						</h4>
																						<p class="summary">
																							${prodData.prodVO.prod_content}</p>
																					</div>
																					<div class="wrap">
																						<div align="center"
																							style="width: 50px; height: 20px; padding: 10px auto;">
																							<div class="non"></div>
																							<a
																								href="${pageContext.request.contextPath}/seller/deleteSellProduct?prod_idx=${prodData.prodVO.prod_idx }"
																								class="button">삭제</a>
																							<div class="non"></div>
																						</div>
																					</div>
																				</div>
																			</td>
																		</tr>
																	</c:when>
																</c:choose>
															</c:forEach>


														</c:otherwise>
													</c:choose>
												</tbody>

											</table>
										</div>
									</div>
								</div>
							</div>
						</section>

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