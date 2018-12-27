<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	pageContext.setAttribute("enter", "\n");
	pageContext.setAttribute("br", "<br/>");
%>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/subbootstrap.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link
	href="${pageContext.request.contextPath}/resources/css/shop-homepage.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/main/selectbox.css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/main/popup.css">
<!-- 팝업용 css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/css/conProductInfoReply.css">
<!-- 물품/편의점 정보&댓글 css -->
<title>자취생을 위한 모두의 떨이</title>
<link rel="shortcut icon" type="image⁄x-icon"
	href="${pageContext.request.contextPath}/resources/img/logo/image2.png">
</head>

<body>
	<!-- 헤더 영역 -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<jsp:include page="commons/header.jsp" />
	</nav>

	<!-- wrapper 시작 -->
	<div class="wrapper body">
		<!-- row 시작 -->
		<div class="row">
			<!-- /.col-lg-3 시작 -->
			<div class="col-lg-3">
				<!-- 사이드바 -->
				<h1 class="my-4">Home</h1>
				<jsp:include page="commons/asideHome.jsp" />
				<!-- 광고상자 -->
				<div class="advertiseAside">
					<p>광고</p>
				</div>
				<!-- 광고상자 끝 -->
			</div>
			<!-- /.col-lg-3 끝 -->
			<!-- col-lg-9 시작 -->
			<div class="col-lg-9">
				<div id="carouselExampleIndicators" class="carousel slide my-4"
					data-ride="carousel">
					<!-- 내용 넣는 공간 -->
					<!-- 컨텐츠 정보 -->
					<div class="row user-infos cyruxx">
						<div
							class="col-xs-12 col-sm-12 col-md-10 col-lg-10 col-xs-offset-0 col-sm-offset-0 col-md-offset-1 col-lg-offset-1">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title">떨이 물품 정보</h3>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-3 col-lg-3">
											<a href="${pageContext.request.contextPath }/readItem?item_idx=${prodData.itemVO.item_idx}">
                                            	<c:choose>
													<c:when test="${prodData.itemVO.item_imgfilename eq 'noImage'}">
														<img class="content-info-img" src="${pageContext.request.contextPath}/resources/img/noImage.jpg">
													</c:when>
													<c:otherwise>
														<img class="content-info-img" src="/upload/${prodData.itemVO.item_imgfilename }">
													</c:otherwise>
												</c:choose>
                                            	
                                            </a>
										</div>
										<div class=" col-md-9">
											<strong>
												<!-- 클릭하면 물품 정보로 넘어가게... --> <a style="font-size: 20px;"
												href="${pageContext.request.contextPath }/readItem?item_idx=${prodData.itemVO.item_idx}">${prodData.itemVO.item_name}</a>
											</strong><br>
											<table class="table table-user-information">
												<tbody>
													<tr>
														<td>떨이 물품종류:</td>
														<td>${prodData.itemTypeVO.item_type_name}</td>
													</tr>
													<tr>
														<td>판매 지점명:</td>
														<td><a
															href="${pageContext.request.contextPath }/readConvenience?conven_idx=${prodData.convenVO.conven_idx}">
																${prodData.brandVO.brand_name}
																${prodData.convenVO.conven_name}</a></td>
													</tr>
													<tr>
														<td>소비자 권장가격:</td>
														<td>${prodData.itemVO.item_originprice}</td>
													</tr>
													<tr>
														<td>할인 가격</td>
														<td>${prodData.prodVO.prod_saleprice}</td>
													</tr>
													<tr>
														<td>유통 기한</td>
														<td>${prodData.prodVO.prod_exdate}</td>
													</tr>
													<tr>
														<td>상세 정보</td>
														<td>${prodData.prodVO.prod_saleprice}</td>
													</tr>
													<tr>
														<td>물품 등록일</td>
														<td>${prodData.prodVO.prod_regdate}</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
					<!-- 컨텐츠 정보 끝 -->

					<!-- 댓글 들어갈 보드 -->
					<div class="panel panel-default widget">
						<div class="panel-heading">
							<h3 class="panel-title">Reply</h3>
							<br>
							<h5>
								댓글 작성 및 수정/삭제는 <a
									href="${pageContext.request.contextPath }/readItem?item_idx=${prodData.itemVO.item_idx}">물품
									상세 정보 페이지</a> 에서만 가능합니다.
							</h5>
						</div>
						<div class="panel-body">
							<ul class="list-group">
								<c:choose>
									<c:when test="${empty itemReDataList}">
										<br>댓글이 없습니다.
									</c:when>
									<c:otherwise>
										<c:forEach var='itemReData' items='${itemReDataList}'>
											<li class="list-group-item">
												<div class="row">
													<div class="col-xs-10 col-md-11">
														<div>
															<strong>${itemReData.memberVO.member_nick}</strong>
															<div class="mic-info">
																${itemReData.itemReVO.item_re_writedate}</div>
															<c:if
																test='${sessionLoginedData != null && itemReData.itemReVO.member_idx == sessionLoginedData.member_idx}'>
																<a
																	href="javascript:updateItemReContet(${itemReData.itemReVO.item_re_idx});"
																	class="mic-info update">수정</a>
																<a
																	href="${pageContext.request.contextPath}/actionDeleteItemReContent?item_re_idx=${itemReData.itemReVO.item_re_idx}&item_idx=${prodData.itemVO.item_idx}"
																	class="mic-info delete">삭제</a>
															</c:if>
														</div>
														<div class="comment-text">
															${fn:replace(itemReData.itemReVO.item_re_content,enter,br) }
														</div>
													</div>
												</div>
											</li>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</ul>

							<!-- 페이징 부분 -->
							<div class="page-nation">
								<ul class="pagination pagination-large">
									<!-- 첫번째 페이지면 좌로 가는 버튼X -->
									<c:if test='${pagingData.nowPageNum != 0 }'>
										<li class="previous-end"
											onclick="location.href='${pageContext.request.contextPath}/readSellProduct?prod_idx=${prodData.prodVO.prod_idx}&requestPagingNum=${pagingData.firstPagingNum-1}'"><span>&laquo;</span></li>
										<li class="previous"
											onclick="location.href='${pageContext.request.contextPath}/readSellProduct?prod_idx=${prodData.prodVO.prod_idx}&requestPagingNum=${pagingData.nowPageNum-1}'"><span>&lsaquo;
										</span></li>
									</c:if>
									<c:forEach var='pageNum' begin='${pagingData.firstPagingNum}'
										end='${pagingData.lastPagingNum}'>
										<c:if test='${pageNum == pagingData.nowPageNum }'>
											<li class="active"><span>${pageNum+1}</span></li>
										</c:if>
										<c:if test='${pageNum != pagingData.nowPageNum }'>
											<li><a
												href="${pageContext.request.contextPath}/readSellProduct?prod_idx=${prodData.prodVO.prod_idx}&requestPagingNum=${pageNum}">${pageNum+1}</a></li>
										</c:if>
									</c:forEach>
									<!-- 마지막 페이지면 우로 가는 버튼X -->
									<c:if
										test='${pagingData.nowPageNum != (pagingData.totalPageCount-1) }'>
										<li class="next"
											onclick="location.href='${pageContext.request.contextPath}/readSellProduct?prod_idx=${prodData.prodVO.prod_idx}&requestPagingNum=${pagingData.nowPageNum+1}'"><span>&rsaquo;</span></li>
										<li class="next-end"
											onclick="location.href='${pageContext.request.contextPath}/readSellProduct?prod_idx=${prodData.prodVO.prod_idx}&requestPagingNum=${pagingData.lastPagingNum+1}'"><span>&raquo;</span></li>
									</c:if>
								</ul>
							</div>
						</div>
					</div>
					<!-- 댓글 들어갈 보드 끝 -->
				</div>
			</div>
			<!-- /.col-lg-9 끝 -->
		</div>
		<!-- /.row 끝 -->
	</div>
	<!-- /.wrapper 끝 -->
	<!-- Footer 시작 -->
	<footer class="py-5 bg-dark">
		<jsp:include page="commons/footer.jsp" />
	</footer>
	<!-- Footer 끝 -->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>