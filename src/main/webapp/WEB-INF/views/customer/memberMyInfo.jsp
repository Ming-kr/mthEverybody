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
<script>
//requestViewForChanging:페이지 변경할 뷰, pageNum:이동할 페이지
function pagingFunction(requestViewForChanging, pageNum)
{
	var requestItemLikePagingNum = ${itemLikeViewDataList.pagingData.nowPageNum};
	var requestAllReplyPagingNum = ${allReplyViewDataList.pagingData.nowPageNum};
	var requestAllBoardViewPagingNum = ${allBoardViewDataList.pagingData.nowPageNum};
	
	//배열[0]:좋아하는 물품 리스트, [1]:댓글 리스트, [2]:게시글 리스트
	var requestPagingNums = [requestItemLikePagingNum, requestAllReplyPagingNum, requestAllBoardViewPagingNum];
	
	if(requestViewForChanging == 'requestItemLikePaging') { requestPagingNums[0] = pageNum; }
	else if(requestViewForChanging == 'requestAllReplyPaging') { requestPagingNums[1] = pageNum; }
	else { requestPagingNums[2] = pageNum; }
	
	//post 방식으로 전송
	var form = document.createElement("form");
	form.setAttribute("method","post");
	form.setAttribute("action", "${pageContext.request.contextPath}/memberMyInfo");
	
	for(var i=0; i<3; i++)
	{
		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "requestPagingNums");
		hiddenField.setAttribute("value", requestPagingNums[i]);
		form.appendChild(hiddenField);
	}
	
	document.body.appendChild(form);
	form.submit();
}
</script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/main/popup.css">
<!-- 팝업용 css -->
<style>
@media ( min-width :1200px) {
	.panel-success.boot {
		margin-top: 5px;
		width: 48%;
		float: left;
	}
	.panel-danger.boot {
		margin-top: 5px;
		width: 48%;
		float: right;
	}
	.panel-info.boot {
		width: 100%;
		float: left;
		margin-top: 12px;
	}
}

.panel-heading.boot {
	padding: 0px 15px;
}

@media ( min-width : 768px) {
	.col-md-3 {
		flex: 0 0 100%;
		max-width: 100%;
	}
}

.joinmember-form-wrap {
	text-align: left;
	margin-bottom: 15px;
	margin-top: 20px;
	padding-bottom: 15px;
	border-bottom: 0.5px solid rgba(0, 0, 0, 0.5);
}

.form-check {
	text-align: right;
	margin-bottom: -5px;
}

.text-center.pull-left {
	padding-bottom: 0;
	font-size: 20px;
	float: none !important;
	margin-top: 10px;
	margin-bottom: 5px;
}

hr {
	margin-bottom: 15px;
}
</style>
<title>자취생을 위한 모두의 떨이</title>
<link rel="shortcut icon" type="image⁄x-icon"
	href="${pageContext.request.contextPath}/resources/img/logo/image2.png">
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
				<h1 class="my-4">Home</h1>
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
				<div id="carouselExampleIndicators" class="carousel slide my-4"
					data-ride="carousel">
					<!-- 내용 넣는 공간 -->
					<!-- 위부분 내정보/수정 -->
					<div id='view-memberInfo-wrap' class="view-memberInfo-wrap">
						<div class="form-group">
							<label for="input-id">아이디</label> <input type="text"
								class="form-control" name="member_id" id="input-id"
								value="${memberInfo.member_id}" readonly>
						</div>
						<div class="form-group">
							<label for="input-nick">닉네임</label> <input type="text"
								class="form-control" name="member_nick" id="input-nick"
								value="${memberInfo.member_nick}" readonly>
						</div>
						<div class="form-group">
							<label for="input-email">이메일</label> <input type="email"
								class="form-control" name="member_email" id="input-email"
								value="${memberInfo.member_email}" readonly>
						</div>
						<!-- 관리자/판매자일때 실명, 연락처 추가 -->
						<c:if test='${sessionLoginedData.member_code =="ADMIN" || sessionLoginedData.member_code =="SELLER"}'>
							<div class="form-group">
								<label for="input-name">실 명</label> <input type="text"
									class="form-control" name="member_name" id="input-name"
									value="${memberInfo.member_name}" readonly>
							</div>
							<div class="form-group">
								<label for="input-phone">연락처</label> <input type="text"
									class="form-control" name="member_phone" id="input-phone"
									value="${memberInfo.member_phone}" readonly>
							</div>
							<c:if test='${sessionLoginedData.member_code =="SELLER"}'>
								<div class="form-group">
									<label for="input-companynum">사업자등록번호</label> <input type="text"
										class="form-control" name="member_companynum" id="input-companynum"
										value="${memberInfo.member_companynum}" readonly>
								</div>
							</c:if>
						</c:if>
						<div class="form-group">
							<label for="input-code">회원구분</label> <input type="text"
								class="form-control" name="member_code" id="input-code"
								value="${memberInfo.member_code}" readonly>
						</div>
						<div class="form-check">
							<a href="#" id="updateMemberInfo" class="btn btn-primary"
								onclick="checkPassword('#updateMemberInfo_page')">수정</a> 
							<a href="#updatePwInfo_page" id="updatePwInfo"
								class="btn btn-primary">비밀번호 변경</a>
							<a href="#deleteMemberInfo_page" id="deleteMemberInfo"
								class="btn btn-primary">탈퇴</a>
							<!-- 수정 -->
							<a href="#x" class="overlay" id="updateMemberInfo_page"></a>
							<div class="popup">
								<jsp:include page="../popup/updateMemberInfoPopup.jsp" />
							</div>
							<!-- 비밀번호 변경 -->
							<a href="#x" class="overlay" id="updatePwInfo_page"></a>
							<div class="popup">
								<jsp:include page="../popup/updatePwInfoPopup.jsp" />
							</div>
							<!-- 탈퇴 -->
							<a href="#x" class="overlay" id="deleteMemberInfo_page"></a>
							<div class="popup">
								<jsp:include page="../popup/deleteMemberInfoPopup.jsp" />
							</div>
							<!-- 팝업 끝 -->
						</div>
					</div>

					<!-- 하는중 -->
					<hr>
					<!-- 좋아요한 물품 리스트 -->
					<div class="panel panel-success boot">
						<jsp:include page="itemLikeViewForMember.jsp" />
					</div>

					<!-- 댓글 쓴 물품/편의점/게시판 -->
					<div class="panel panel-danger boot">
						<jsp:include page="allReplyViewForMember.jsp" />
					</div>
					<!-- 내가 쓴 게시글 -->
					<div class="panel panel-info boot">
						<jsp:include page="allBoardViewForMember.jsp" />
					</div>
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
	<script
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>