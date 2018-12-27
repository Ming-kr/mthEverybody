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
<script>
   var sel_file;
   $(document).ready(function() {
      $("#ex_file").on("change", handleImgFileSelect);
   });
   
   function handleImgFileSelect(e) {
      var files = e.target.files;
      var filesArr = Array.prototype.slice.call(files);
      
      filesArr.forEach(function(f) {
         if(!f.type.match("image.*")) {
            alert("확장자는 이미지 확장자만 가능합니다.");
            return;
         }
         
         sel_file = f;
         
         var reader = new FileReader();
         reader.onload = function(e) {
            $("#img").attr("src", e.target.result);
            document.getElementById("preimg").innerHTML = "";
         }
         reader.readAsDataURL(f);
      });
   }
</script>
<style>
form {
	width: 100%;
	margin: 50px 0 100px 0;
}

.text-uppercase.mt-3.font-weight-bold {
	margin-left: 13px;
}

select.form-control:not ([size] ):not ([multiple] ) {
	height: calc(3.25rem + 2px);
}

.col-4, .col-lg-4 {
	-ms-flex: 0 0 100%;
	flex: 0 0 100%;
	max-width: 100%;
}

.btn.btn-success {
	float: right;
}

.form-group.imageUpload {
	width: 100%;
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

				<form
					action="${pageContext.request.contextPath }/admin/actionUpdateItemForm"
					method="post" enctype="multipart/form-data">
					<h2 class="text-uppercase mt-3 font-weight-bold">관리자 물품 수정</h2>
					<div class="col-lg-4">
						<div class="form-group imageUpload">
							<label></label>
							<div
								style="height: 250px; border-radius: 10px; display: table; width: 100%;">
								<div
									style="display: table-cell; vertical-align: middle; text-align: center;">
									<c:choose>
										<c:when test="${itemVO.item_imgfilename eq 'noImage'}">
											<img style="max-width:100%;max-height:90%;" id="img" name="item_" style="width:250px;height:250px;" src="${pageContext.request.contextPath}/resources/img/noImage.jpg"><p id="preImg"></p>
										</c:when>
										<c:otherwise>
											<img style="max-width:100%;max-height:90%;" id="img" name="item_" style="width:250px;height:250px;" src="/upload/${itemVO.item_imgfilename }"><p id="preImg"></p>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="filebox">
								<label for="ex_file" class="btn btn-light"
									style="margin-top: 10px;">이미지 업로드</label> <input type="file"
									name="files" multiple="multiple" id="ex_file" hidden="true">
							</div>
						</div>
					</div>
					<div class="row" style="margin-top: 20px;">
						<div class="col-lg-6">
							<div class="form-group">
								<label>작성자</label>
								<div class="form-control mt-2">
									${sessionLoginedData.member_nick }</div>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<input type="hidden" name="item_idx"
									value="${itemVO.item_idx}"> <input
									type="hidden" name="item_condition" value="NORMAL">
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label for="brand_idx">판매 브랜드명</label> <select name="brand_idx"
									class="form-control mt-2" style="height: 35px;" required>
									<c:forEach var="brandVO" items="${brandList }">
										<c:choose>
											<c:when test="${brandVO.brand_idx eq itemVO.brand_idx}">
												<option value="${brandVO.brand_idx }" selected>${brandVO.brand_name }</option>
											</c:when>
											<c:otherwise>
												<option value="${brandVO.brand_idx }">${brandVO.brand_name }</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label for="item_type_idx">판매 물품 종류 </label> 
								<select name="item_type_idx" class="form-control mt-2" style="height: 35px;" required>
								<c:forEach var="itemTypeVO" items="${itemTypeList }">
									<c:choose>
										<c:when test="${itemTypeVO.item_type_idx eq itemVO.item_type_idx}">
											<option value="${itemTypeVO.item_type_idx }" selected>${itemTypeVO.item_type_name }</option>
										</c:when>
										<c:otherwise>
											<option value="${itemTypeVO.item_type_idx }">${itemTypeVO.item_type_name }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label for="item_name">판매 물품명</label> <input type="text"
									class="form-control mt-2" name="item_name" value="${itemVO.item_name }" required>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label for="item_originprice">소비자 판매가격</label> <input
									type="number" class="form-control mt-2" name="item_originprice" value="${itemVO.item_originprice }"
									required>
							</div>
						</div>
						<div class="col-lg-12">
							<div class="form-group">
								<label for="item_content">관리자물품 상세정보</label>
								<textarea class="form-control" id="exampleFormControlTextarea1"
									placeholder="관리자물품에 대한 부가설명을 작성해주세요" rows="3"
									name="item_content" required>${itemVO.item_content }</textarea>
							</div>
						</div>
						<div class="col-4">
							<button class="btn btn-success" type="submit">수정</button>
						</div>
					</div>
				</form>

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