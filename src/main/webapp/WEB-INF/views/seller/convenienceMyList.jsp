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

.table-filter .media .title span.NORMAL {
	color: #5cb85c;
}

.table-filter .media .title span.REQUEST {
	color: #f0ad4e;
}
.table-filter .media .title span.DENIED {
	color: #B22222;
}
.wrap a {
	display: block;
	margin: 20px auto;
	max-width: 50px;
	max-height: 50px;
	text-decoration: none;
	border-radius: 4px;
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
.btn-glyphicon { padding:8px; background:#ffffff; margin-right:4px; }
.icon-btn { padding: 1px 15px 3px 2px; border-radius:50px;}

.btn-group{
	float: right;
}
.btn.icon-btn.btn-success{
	margin-top: 12px;
}

@media(max-width:768px){
	.buttonBox{
		text-align: center;
	}
	.btn-group{
		display: grid;
		float: none;
	}

	.table-container{
		margin-top: 130px;
	}
	.btn.icon-btn.btn-success{
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
				<div class="container">
					<div class="row">

						<section class="content" style="width: 100%;">
							<div class="col-md-8 col-md-offset-2" style="margin: 20px 0;">
								<div class="panel panel-default">
									<h1>내 편의점 리스트</h1>
									<div class="panel-body">
										<div class="buttonBox" style="height:70px;">
											<div class="btn-group">
												<button type="button" class="btn btn-success btn-filter"
													data-target="NORMAL">판매가능 편의점</button>
												<button type="button" class="btn btn-warning btn-filter"
													data-target="REQUEST">승인 중인 내 편의점</button>
												<button type="button" class="btn btn-danger btn-filter"
													data-target="DENIED">승인 거절 내 편의점</button>
												<button type="button" class="btn btn-default btn-filter"
													data-target="all">모든 편의점</button>
											</div>
											<a class="btn icon-btn btn-success" href="${pageContext.request.contextPath}/seller/requestConvenienceForm"><span class="glyphicon btn-glyphicon glyphicon-plus img-circle text-success"></span>편의점 등록</a>
										</div>
										<div class="table-container">
											<table class="table table-filter">
												<tbody>
													<c:choose>
														<c:when test="${empty ConvenDataList }">
															<td><h2 align="center">등록하신 편의점이 없습니다</h2></td>
														</c:when>
														<c:otherwise>
															<c:forEach var="convenData" items="${ConvenDataList}">
																<c:choose>
																	<c:when
																		test="${convenData.convenVO.conven_condition eq 'REQUEST'}">
																		<tr data-status="REQUEST">
																			<td>
																				<div class="media">
																					<a href="" class="pull-left"> <img
																						src="https://s3.amazonaws.com/uifaces/faces/twitter/fffabs/128.jpg"
																						class="media-photo">
																					</a>
																					<div class="media-body" style="float: left;">
																						<span class="media-meta pull-right"
																							style="width: 70px;">${convenData.convenVO.conven_regdate }</span>
																						<h4 class="title">
																							${convenData.convenVO.conven_name } <span
																								class="pull-right REQUEST">(승인 중)</span>
																						</h4>
																						<p class="summary">
																							${convenData.brandVO.brand_name}<br> 상세주소 :
																							서울시 ${convenData.stateVO.state_name} ${convenData.convenVO.conven_adress}
																						</p>
																					</div>
																					<div class="wrap">
																						<div align="center" style="width: 50px; height: 20px; padding: 10px auto;"><a href="${pageContext.request.contextPath}/seller/deleteConven?conven_idx=${convenData.convenVO.conven_idx }" class="button">삭제</a></div>
																					</div>
																				</div>
																			</td>
																		</tr>

																	</c:when>
																	<c:when
																		test="${convenData.convenVO.conven_condition eq 'NORMAL'}">
																		<tr data-status="NORMAL">
																			<td>
																				<div class="media">
																					<a href="${pageContext.request.contextPath }/readConvenience?conven_idx=${convenData.convenVO.conven_idx}" class="pull-left">
																					<img
																						src="https://s3.amazonaws.com/uifaces/faces/twitter/fffabs/128.jpg"
																						class="media-photo">
																					</a>
																					<div class="media-body">
																						<span class="media-meta pull-right"
																							style="width: 70px;">${convenData.convenVO.conven_regdate }</span>
																						<h4 class="title">
																						<a href="${pageContext.request.contextPath}/readConvenience?conven_idx=${convenData.convenVO.conven_idx }">
																							${convenData.convenVO.conven_name }</a> <span
																								class="pull-right NORMAL">(판매가능)</span>
																						</h4>
																						<p class="summary">
																							${convenData.brandVO.brand_name} <br> 상세주소 :
																							서울시 ${convenData.stateVO.state_name} ${convenData.convenVO.conven_adress}
																						</p>
																					</div>
																					<div class="wrap">
																						<%-- <a href="${pageContext.request.contextPath}/updateConvenienceForm?conven_idx=${data.convenVO.conven_idx }" class="button">
																						<div align="center" style="width: 50px; height: 20px; padding: 10px auto;">수정</div></a> --%>
																						<div align="center" style="width: 50px; height: 20px; padding: 10px auto;"><a href="${pageContext.request.contextPath}/seller/deleteConven?conven_idx=${convenData.convenVO.conven_idx }" class="button">삭제</a></div>
																					</div>
																				</div>
																			</td>
																		</tr>
																	</c:when>
																	<c:when
																		test="${convenData.convenVO.conven_condition eq 'DENIED'}">
																		<tr data-status="DENIED">
																			<td>
																				<div class="media">
																					<a href="#" class="pull-left"> <img
																						src="https://s3.amazonaws.com/uifaces/faces/twitter/fffabs/128.jpg"
																						class="media-photo">
																					</a>
																					<div class="media-body">
																						<span class="media-meta pull-right"
																							style="width: 70px;">${convenData.convenVO.conven_regdate }</span>
																						<h4 class="title">
																							${convenData.convenVO.conven_name } <span
																								class="pull-right DENIED">(승인거절)</span>
																						</h4>
																						<p class="summary">
																							${convenData.brandVO.brand_name} <br> 상세주소 :
																							서울시 ${convenData.stateVO.state_name} ${convenData.convenVO.conven_adress}
																						</p>
																					</div>
																					<div class="wrap">
																						<div align="center" style="width: 50px; height: 20px; padding: 10px auto;"><a href="${pageContext.request.contextPath}/seller/deleteConven?conven_idx=${convenData.convenVO.conven_idx }" class="button">삭제</a></div>
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