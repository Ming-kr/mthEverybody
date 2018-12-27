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
.wrapper.box {
	margin: 60px 0;
}

@media ( min-width : 992px) {
	.col-lg-6 {
		-ms-flex: 0 0 65%;
		flex: 0 0 65%;
		max-width: 65%;
	}
}

button {
	float: left;
	margin-left: 15px;
	margin-top: 10px;
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
				<h1 class="my-4">Admin</h1>
				<jsp:include page="../commons/asideAdminpage.jsp" />
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
					<div class="wrapper box">
						<div class="row">
							<div class="col-md-6">
								<c:if test="${memberVO.member_code eq 'REQ_SELLER' }">
									<h2 class="text-uppercase mt-3 font-weight-bold">판매자 승인</h2>
								</c:if>
								<c:if test="${memberVO.member_code eq 'CUSTOMER' }">
									<h2 class="text-uppercase mt-3 font-weight-bold">구매자 정보/관리자 승인</h2>
								</c:if>
								<c:if test="${memberVO.member_code eq 'SELLER' }">
									<h2 class="text-uppercase mt-3 font-weight-bold">판매자 정보</h2>
								</c:if>
								<br>
								<div class="row">
									<div class="col-md-6">
										<h4>
											<c:choose>
												<c:when test="${memberVO.member_code eq 'REQ_SELLER' }">
													<label for="member_info"> [신청자 정보]</label>
												</c:when>
												<c:otherwise>
													<label for="member_info"> [회원 정보]</label>
												</c:otherwise>
											</c:choose>
										</h4>
										<div class="col-lg-6">
											<div class="form-group">
												<label for="member_id"> 회원 아이디</label>
												<div class="form-control mt-2">${memberVO.member_id }</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label for="member_nick"> 회원 닉네임</label>
												<div class="form-control mt-2">${memberVO.member_nick }</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label for="member_email">회원 이메일</label>
												<div class="form-control mt-2">${memberVO.member_email }</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label for="member_joindate">회원 가입일</label>
												<div class="form-control mt-2">${memberVO.member_joindate }</div>
											</div>
										</div>
									<c:if test="${memberVO.member_code ne 'CUSTOMER' }">
										<div class="col-lg-6">
											<div class="form-group">
												<label for="member_name">신청자 이 름</label>
												<div class="form-control mt-2">${memberVO.member_name }</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label for="member_phone">신청자 전화번호</label>
												<div class="form-control mt-2">${memberVO.member_phone }</div>
											</div>
										</div>
										<c:if test="${memberVO.member_code eq 'SELLER' }">
											<div class="col-lg-6">
												<div class="form-group">
													<label for="member_companynum">신청자 사업자번호</label>
													<div class="form-control mt-2">${memberVO.member_companynum }</div>
												</div>
											</div>
										</c:if>
									</c:if>
									</div>
									<c:choose>
									<c:when
										test="${memberVO.member_code eq 'REQ_SELLER' }">
										<div class="col-12">
											<a
												href="${pageContext.request.contextPath }/admin/updateGrantSeller?member_idx=${memberVO.member_idx }">
												<button style="border: 1px solid gray;"
													class="btn btn-light"> 판매자 승인완료</button>
											</a> <a
												href="${pageContext.request.contextPath }/admin/rejectGrantSeller?member_idx=${memberVO.member_idx }">
												<button style="border: 1px solid gray;"
													class="btn btn-light">판매자 승인거절</button>
											</a>
										</div>
									</c:when>
									<c:when
										test="${memberVO.member_code eq 'CUSTOMER' }">
										<div class="col-12">
											<a
												href="${pageContext.request.contextPath }/admin/grantAdminForm?member_idx=${memberVO.member_idx }">
												<button style="border: 1px solid gray;"
													class="btn btn-light"> 관리자 승인</button>
											</a>
											<a
												href="${pageContext.request.contextPath }/admin/memberList">
												<button style="border: 1px solid gray;"
													class="btn btn-light">확인</button>
											</a>
										
										</div>
									</c:when>
									<c:otherwise>
										<div class="col-12">
											<a
												href="${pageContext.request.contextPath }/admin/memberList">
												<button style="border: 1px solid gray;"
													class="btn btn-light">확인</button>
											</a>
										
										</div>
									</c:otherwise>
									</c:choose>
										
								</div>
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