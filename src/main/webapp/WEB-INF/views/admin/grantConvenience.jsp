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

	@media (min-width: 992px) {
		.col-lg-6 {
			-ms-flex: 0 0 65%;
			flex: 0 0 65%;
			max-width: 65%;
		}
	}

	button{
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
								
								<c:if test="${convenData.convenVO.conven_condition eq 'REQUEST' }">
									<h2 class="text-uppercase mt-3 font-weight-bold">편의점 승인</h2><br>
								</c:if>
								<c:if test="${convenData.convenVO.conven_condition ne 'REQUEST' }">
									<h2 class="text-uppercase mt-3 font-weight-bold">편의점 정보</h2><br>
								</c:if>
								
								<div class="row">
									<div class="col-md-6">
										
										<c:if test="${convenData.convenVO.conven_condition eq 'REQUEST' }">
											<h4><label for="member_info"> [신청자 정보]</label></h4>
										</c:if>
										<c:if test="${convenData.convenVO.conven_condition ne 'REQUEST' }">
											<h4><label for="member_info"> [판매자 정보]</label></h4>
										</c:if>
										
										<div class="col-lg-6">
											<div class="form-group">
												<label for="member_nick"> 판매자 닉네임</label>
												<div class="form-control mt-2">${convenData.memberVO.member_nick }</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label for="member_name">판매자 이 름</label>
												<div class="form-control mt-2">${convenData.memberVO.member_name }</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label for="member_phone">판매자 전화번호</label>
												<div class="form-control mt-2">${convenData.memberVO.member_phone }</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label for="member_email">판매자 이메일</label>
												<div class="form-control mt-2">${convenData.memberVO.member_email }</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label for="member_companynum">판매자 사업자번호</label>
												<div class="form-control mt-2">${convenData.memberVO.member_companynum }</div>
											</div>
										</div>
									</div>	
								
									<div class="col-md-6">
										<c:if test="${convenData.convenVO.conven_condition eq 'REQUEST' }">
											<h4><label for="member_info"> [신청 편의점 정보]</label></h4>
										</c:if>
										<c:if test="${convenData.convenVO.conven_condition ne 'REQUEST' }">
											<h4><label for="member_info"> [편의점 정보]</label></h4>
										</c:if>
										
										<div class="col-lg-6">
											<div class="form-group">
												<label for="member_name">편의점 명</label>
												<div class="form-control mt-2">${convenData.convenVO.conven_name }</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label for="member_name">편의점 브랜드</label>
												<div class="form-control mt-2">${convenData.brandVO.brand_name }</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label for="member_name">편의점 위치(구)</label>
												<div class="form-control mt-2">${convenData.stateVO.state_name }</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label for="member_name">편의점 위치(상세정보)</label>
												<div class="form-control mt-2">${convenData.convenVO.conven_adress }</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label for="member_name">승인 신청일</label>
												<div class="form-control mt-2">${convenData.convenVO.conven_regdate }</div>
											</div>
										</div>
									</div>	
										<c:if test="${convenData.convenVO.conven_condition eq 'REQUEST' }">
										<div class="col-12">
											<a href="${pageContext.request.contextPath }/admin/updateGrantConvenience?conven_idx=${convenData.convenVO.conven_idx}">
											<button style="border: 1px solid gray;" class="btn btn-light">승인완료
											</button></a>
											<a href="${pageContext.request.contextPath }/admin/rejectGrantConvenience?conven_idx=${convenData.convenVO.conven_idx}">
											<button style="border: 1px solid gray;" class="btn btn-light">승인거부
											</button></a>
											<a href="${pageContext.request.contextPath }/admin/convenienceList">
											<button style="border: 1px solid gray;" class="btn btn-light">취소
											</button></a>
										</div>
										</c:if>
										<c:if test="${convenData.convenVO.conven_condition eq 'NORMAL' }">
										<div class="col-12">
											<a href="${pageContext.request.contextPath }/admin/cancelGrantConvenience?conven_idx=${convenData.convenVO.conven_idx}">
											<button style="border: 1px solid gray;" class="btn btn-light">승인취소
											</button></a>
											<a href="${pageContext.request.contextPath }/admin/convenienceList">
											<button style="border: 1px solid gray;" class="btn btn-light">취소
											</button></a>
										</div>
										</c:if>
										<c:if test="${convenData.convenVO.conven_condition eq 'DENIED' }">
											<a href="${pageContext.request.contextPath }/admin/convenienceList">
												<button style="border: 1px solid gray;" class="btn btn-light">확인</button>
											</a>
										</c:if>
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