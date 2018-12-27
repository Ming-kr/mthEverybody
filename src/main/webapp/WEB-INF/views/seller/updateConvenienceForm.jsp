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
<style>
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
				<jsp:include page="../commons/asideHome.jsp" />
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
				<div class="row" id="contatti">
					<div class="container mt-5">

						<div class="row" style="height: 550px;">
							<div class="col-md-6 maps">
								<iframe
									src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d11880.492291371422!2d12.4922309!3d41.8902102!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x28f1c82e908503c4!2sColosseo!5e0!3m2!1sit!2sit!4v1524815927977"
									style="border: 0;height:400px;"></iframe>
								<!-- <iframe
								src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d11880.492291371422!2d12.4922309!3d41.8902102!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x28f1c82e908503c4!2sColosseo!5e0!3m2!1sit!2sit!4v1524815927977"
								frameborder="0" style="border: 0;height:400px;" allowfullscreen></iframe> -->
							</div>
							<div class="col-md-6">
								<h2 class="text-uppercase mt-3 font-weight-bold">내 편의점 정보 수정</h2>
								<form action="${pageContext.request.contextPath }/seller/updateConvenienceFormAction"
									method="post">
									<div class="row">
										<div class="col-lg-6">
											<div class="form-group">
												<input type="hidden" name="conven_idx"
													value="${convenData.convenVO.conven_idx}">
												<div class="form-control mt-2">작성자
													${sessionLoginedData.member_nick }</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<input type="text" class="form-control mt-2"
													placeholder="편의점 명" name="conven_name" value="${convenData.convenVO.conven_name } " required>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<select name="brand_idx" class="form-control mt-2" style="height:35px;" required>
													<c:forEach var="brand" items="${brandVO }">
														<c:choose>
															<c:when test="${brand.brand_idx eq convenData.brandVO.brand_idx}">
																<option value="${brand.brand_idx }" selected>${brand.brand_name }</option>
															</c:when>
															<c:otherwise>
																<option value="${brand.brand_idx }">${brand.brand_name }</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
													
													<%-- <option value="${convenData.brandVO.brand_idx}" selected>${convenData.brandVO.brand_name }</option>
													<option value="1">GS25</option>
													<option value="2">CU</option>
													<option value="3">EMARAT24</option>
													<option value="4">MINISTOP</option>
													<option value="5">ETC</option> --%>
												</select>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<select name="state_idx" class="form-control mt-2" style="height:35px;" required>
													
													<c:forEach var="state" items="${stateVO }">
														<c:choose>
															<c:when test="${state.state_idx eq convenData.stateVO.state_idx}">
																<option value="${state.state_idx }" selected>${state.state_name }</option>
															</c:when>
															<c:otherwise>
																<option value="${state.state_idx }">${state.state_name }</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
													
													<%-- <option value="${convenData.stateVO.state_idx}" selected>${convenData.stateVO.state_name }</option>
													<option value="1">서대문구</option>
													<option value="2">양천구</option>
													<option value="3">중구</option>
													<option value="4">송파구</option>
													<option value="5">은평구</option> --%>
												</select>
											</div>
										</div>
										<div class="col-12">
											<div class="form-group">
												<textarea class="form-control"
													id="exampleFormControlTextarea1"
													rows="3" name="conven_adress" required>${convenData.convenVO.conven_adress}</textarea>
											</div>
										</div>
										<div class="col-12">
											<div class="form-group">
												<div class="form-check">
													<input class="form-check-input" type="checkbox" value=""
														id="invalidCheck2" required>&nbsp; &nbsp; <label
														class="form-check-label" for="invalidCheck2">
														위 편의점은 관리자 수정 승인 이후 물품등록 가능합니다.</label>
												</div>
											</div>
										</div>
										<div class="col-12">
											<button style="border:1px solid gray;" class="btn btn-light" type="submit">수정</button>
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