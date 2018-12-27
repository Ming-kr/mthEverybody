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

	<title>자취생을 위한 모두의 떨이</title>
	<link rel="shortcut icon" type="image⁄x-icon" href="${pageContext.request.contextPath}/resources/img/logo/image2.png">
	<!-- Bootstrap core CSS -->
	<link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
	<!-- Custom styles for this template -->
	<link href="${pageContext.request.contextPath}/resources/css/shop-homepage.css" rel="stylesheet">

	<!-- 붙어서 온 아이들 -->
	<link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/subbootstrap.css" rel="stylesheet">
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
	<!-- //////////내가 추가한 친구들////////////// -->
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

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

.table-filter .media .title span.CLEAR {
	color: #5cb85c;
}

.table-filter .media .title span.DENIED {
	color: #f0ad4e;
}

.table-filter .media .title span.PROBLEM {
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
		margin-top: 100px;
	}
	.btn.icon-btn.btn-success{
		margin-top: -15px;
	}
}

form{
	margin-bottom: 40px;
}

#searchBox{
    display: inline-flex;
    float: left;
}

select.form-control:not([size]):not([multiple]) {
    width: 170px;
    margin-right: 10px;
}
    
.form-control.text{
    width: 200px;
    margin-right: 10px;
}
</style>
	<script type="text/javascript">
		$(document).ready(function () {

			$('.btn-filter').on('click', function () {
				var $target = $(this).data('target');
				var xmlhttp = new XMLHttpRequest();

				var searchSelect = $("#searchSelect option:selected").val();
				var searchWord = $("#searchWord").val();

				/* var pagingUl = $('#pagingUl'); //페이징 부모 ul */
				$('#report_condition').val($(this).data('target'));

				$('.numberpage').remove(); //페이징 다 지우기...
				//콜백 설정...
				xmlhttp.onreadystatechange = function () { //xmlhttp readystate가 변경될 때마다 익명함수 호출			//처음엔 설정만 해두는 것이고 상태가 변경될 때 마다 호출

					if (xmlhttp.readyState == 4 && xmlhttp.status == 200) { //200성공했을 경우, 둘다 받은 경우
						var searchData = JSON.parse(xmlhttp.responseText);
						for (var i = searchData.paging; i >= 1; i--) { //페이징 크기만큼 for문
							if (i == 1) {
								var pagingLi = "<li class='li-page active numberpage' value='" + i + "'>" +
									"<span style='cursor:pointer' onClick='pageReportList()'>" +
									i +
									"</span></li>"; //페이징 태그 만듦
								$('.point').after(
									pagingLi); //페이징 다음 옆에 추가
								/* $('.pagingUl').append(pagingLi);  //부모 밑에 추가 */
							} else {
								var pagingLi = "<li class='li-page numberpage' value='" + i + "'>" +
									"<span style='cursor:pointer' onClick='pageReportList()'>" +
									i +
									"</span></li>"; //페이징 태그 만듦
								$('.point').after(
									pagingLi); //페이징 다음 옆에 추가
								/* $('.pagingUl').append(pagingLi);  //부모 밑에 추가 */
							}
						}
						if ($target != 'all') {
							$('.table tr').css('display', 'none');
							for (var i = 1; i <= 10; i++) {
								$('.table tr[data-status="' + $target + i + '"]').fadeIn('slow');
							}
						} else {
							$('.table tr').css('display', 'none');
							for (var i = 1; i <= 10; i++) {
								$('.list' + i + '').fadeIn('slow');
							}
						}

					}
				};

				if (searchWord == "") {
					xmlhttp.open("POST", "${pageContext.request.contextPath}/admin/filterReportList", true); //3번째는 동기식, 비동기식이냐 의미, 디폴트는 true
					xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
					xmlhttp.send("report_condition=" + $target);
				} else {
					xmlhttp.open("POST", "${pageContext.request.contextPath}/admin/filterSearchReportList", true); //3번째는 동기식, 비동기식이냐 의미, 디폴트는 true
					xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
					xmlhttp.send("report_condition=" + $target + "&searchSelect=" + searchSelect + "&searchWord=" + searchWord);
				}
			});

			var report_condition = '${report_condition}';
			if (report_condition == "all") {
				console.log("저기로 넘어옴");
				$('.table tr').css('display', 'none');
				for (var i = 1; i <= 10; i++) {
					$('.list' + i + '').fadeIn('slow');
				}
			} else {
				console.log("여기로 넘어옴");
				$('.table tr').css('display', 'none');
				for (var i = 1; i <= 10; i++) {
					$('.table tr[data-status="' + '${report_condition}' + i + '"]').fadeIn('slow');
				}
			}


		});

		//li-page 클릭했을 때 이벤트
		function pageReportList() {
			var xmlhttp = new XMLHttpRequest();
			var e = event.target;
			var p = e.parentElement.value;
			var report_condition = $('#report_condition').val();

			//콜백 설정...
			xmlhttp.onreadystatechange = function () { //xmlhttp readystate가 변경될 때마다 익명함수 호출			//처음엔 설정만 해두는 것이고 상태가 변경될 때 마다 호출

				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) { //200성공했을 경우, 둘다 받은 경우
					var searchData = JSON.parse(xmlhttp.responseText);
					if (report_condition != 'all') {
						$('.table tr').css('display', 'none');
						for (var i = searchData.startNum; i <= searchData.endNum; i++) {
							$('.table tr[data-status="' + report_condition + i + '"]').fadeIn('slow');
						}
					} else {
						$('.table tr').css('display', 'none');
						for (var i = searchData.startNum; i <= searchData.endNum; i++) {
							$('.list' + i + '').fadeIn('slow');
						}
					}

				}
			};
			xmlhttp.open("POST", "${pageContext.request.contextPath}/admin/pageReportList", true); //3번째는 동기식, 비동기식이냐 의미, 디폴트는 true
			xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			//전달할 값 로직 세팅....
			xmlhttp.send("nowPage=" + p);

		}
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
				<h1 class="my-4">Admin</h1>
				<jsp:include page="../commons/asideAdminpage.jsp" />
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
									<h1>신고글 리스트</h1>
									<div class="panel-body">
										<div class="buttonBox" style="height:70px;">
											<div class="btn-group">
												<button type="button" class="btn btn-danger btn-filter" data-target="PROBLEM">신고처리 중</button>
												<button type="button" class="btn btn-success btn-filter" data-target="CLEAR">신고처리 완료</button>
												<button type="button" class="btn btn-warning btn-filter" data-target="DENIED">신고처리 거부</button>
												<button type="button" class="btn btn-default btn-filter" data-target="all">모든 신고글</button>
											</div>
										</div>
										<div class="table-container">

											<table class="table table-filter">
												<tbody>

													<c:set var='problemCount' value='0' />
													<c:set var='clearCount' value='0' />
													<c:set var='deniedCount' value='0' />

													<c:choose>
														<c:when test="${empty reportDataList }">
															<td>
																<h2 align="center">등록된 신고글이 없습니다</h2>
															</td>
														</c:when>

														<c:otherwise>


															<c:forEach var="reportData" items="${reportDataList}" varStatus='status'>
																<c:choose>
																	<c:when test="${reportData.reportVO.report_condition eq 'PROBLEM'}">
																		<c:set var='problemCount' value='${problemCount+1 }' />
																		<tr class='list${status.count }' data-status="PROBLEM${problemCount }">
																			<td>
																				<div class="media">
																					<a href="#" class="pull-left"> <img src="https://s3.amazonaws.com/uifaces/faces/twitter/fffabs/128.jpg"
																						 class="media-photo">
																					</a>
																					<div class="media-body" style="float: left;">
																						<span class="media-meta pull-right" style="width: 70px;">
																							<c:if test="${reportData.reportVO.report_title eq 'ABUSE'}">
																								욕설, 비방
																							</c:if>
																							<c:if test="${reportData.reportVO.report_title eq 'AD'}">
																								부적절한 광고
																							</c:if>
																							<c:if test="${reportData.reportVO.report_title eq 'PORNO'}">
																								음란물 개제
																							</c:if>
																						</span>
																						<h4 class="title">
																							<a href="${pageContext.request.contextPath}/admin/readReport?report_idx=${reportData.reportVO.report_idx}">
																								<c:if test="${reportData.reportVO.report_where eq 'mth_yum'}">
																									[맛있는 편의점] 게시글 번호 ${reportData.reportVO.report_where_idx}번
																								</c:if>
																								<c:if test="${reportData.reportVO.report_where eq 'mth_free'}">
																									[자유 게시판] 게시글 번호 ${reportData.reportVO.report_where_idx} 번
																								</c:if>
																							</a>
																							<img style="width:20px;height:20px;" src="${pageContext.request.contextPath}/resources/img/report.png" />
																							신고 누적 ${reportData.board_reportCount}회 <span class="pull-right PROBLEM">(처리 중)</span>
																						</h4>
																						<p class="summary">
																							${reportData.reportVO.report_content}
																						</p>
																					</div>
																					<div class="wrap">
																						<div align="center" style="width: 50px; height: 20px; padding: 10px auto;">
																						</div>
																					</div>
																				</div>
																			</td>
																		</tr>
																	</c:when>

																	<c:when test="${reportData.reportVO.report_condition eq 'CLEAR'}">
																		<c:set var='clearCount' value='${clearCount+1 }' />
																		<tr class='list${status.count }' data-status="CLEAR${clearCount }">
																			<td>
																				<div class="media">
																					<a href="#" class="pull-left"> <img src="https://s3.amazonaws.com/uifaces/faces/twitter/fffabs/128.jpg"
																						 class="media-photo">
																					</a>
																					<div class="media-body" style="float: left;">
																						<span class="media-meta pull-right" style="width: 70px;">
																							<c:if test="${reportData.reportVO.report_title eq 'ABUSE'}">
																								욕설, 비방
																							</c:if>
																							<c:if test="${reportData.reportVO.report_title eq 'AD'}">
																								부적절한 광고
																							</c:if>
																							<c:if test="${reportData.reportVO.report_title eq 'PORNO'}">
																								음란물 개제
																							</c:if>
																						</span>
																						<h4 class="title">

																							<c:if test="${reportData.reportVO.report_where eq 'mth_yum'}">
																								[맛있는 게시판] 게시글 번호 ${reportData.reportVO.report_where_idx}번
																							</c:if>
																							<c:if test="${reportData.reportVO.report_where eq 'mth_free'}">
																								[자유 게시판] 게시글 번호 ${reportData.reportVO.report_where_idx} 번
																							</c:if>
																							신고 누적 ${reportData.board_reportCount}회 <span class="pull-right PROBLEM">(신고 처리 완료)</span>
																						</h4>
																						<p class="summary">
																							${reportData.reportVO.report_content}
																						</p>
																					</div>
																					<div class="wrap">
																						<div align="center" style="width: 50px; height: 20px; padding: 10px auto;">
																						</div>
																					</div>
																				</div>
																			</td>
																		</tr>
																	</c:when>

																	<c:when test="${reportData.reportVO.report_condition eq 'DENIED'}">
																		<c:set var='deniedCount' value='${deniedCount+1 }' />
																		<tr class='list${status.count }' data-status="DENIED${deniedCount }">
																			<td>
																				<div class="media">
																					<a href="#" class="pull-left"> <img src="https://s3.amazonaws.com/uifaces/faces/twitter/fffabs/128.jpg"
																						 class="media-photo">
																					</a>
																					<div class="media-body" style="float: left;">
																						<span class="media-meta pull-right" style="width: 70px;">
																							<c:if test="${reportData.reportVO.report_title eq 'ABUSE'}">
																								욕설, 비방
																							</c:if>
																							<c:if test="${reportData.reportVO.report_title eq 'AD'}">
																								부적절한 광고
																							</c:if>
																							<c:if test="${reportData.reportVO.report_title eq 'PORNO'}">
																								음란물 개제
																							</c:if>
																						</span>
																						<h4 class="title">

																							<c:if test="${reportData.reportVO.report_where eq 'mth_yum'}">
																								[맛있는 게시판] 게시글 번호 ${reportData.reportVO.report_where_idx}번
																							</c:if>
																							<c:if test="${reportData.reportVO.report_where eq 'mth_free'}">
																								[자유 게시판] 게시글 번호 ${reportData.reportVO.report_where_idx} 번
																							</c:if>
																							신고 누적 ${reportData.board_reportCount}회 <span class="pull-right PROBLEM">(신고 처리 거부)</span>
																						</h4>
																						<p class="summary">
																							${reportData.reportVO.report_content}
																						</p>
																					</div>
																					<div class="wrap">
																						<div align="center" style="width: 50px; height: 20px; padding: 10px auto;">
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
											<!-- 페이징 부분 -->
											<div class="page-nation">
												<ul id='pagingUl' class="pagination pagination-large">
													<li class="point" hidden />
													<!--     
        <li class="li-page previous-end" value='1'>
	        <span style="cursor:pointer" onClick='pageMemberList()'>&laquo;</span>
        </li>
	    
        <li class="li-page previous" value='1'>
	        <span style="cursor:pointer" onClick='pageMemberList()'>&lsaquo; </span>
        </li>
    -->
													<!-- 페이징로직... -->
													<c:forEach var='i' begin='1' end='${searchData.paging }' step='1' varStatus='status'>
														<c:choose>
															<c:when test="${i == searchData.pageNum}">
																<li class="li-page active numberpage" value='${i }'>
																	<span style="cursor:pointer" onClick='pageReportList()'>${i }</span>
																</li>
															</c:when>
															<c:otherwise>
																<li class="li-page numberpage" value='${i }'>
																	<span style="cursor:pointer" onClick='pageReportList()'>${i }</span>
																</li>
															</c:otherwise>
														</c:choose>
													</c:forEach>
													<!-- 페이징 끝 -->
													<%-- 	    
	    <li class="li-page next" value='2'>
	        <span style="cursor:pointer" onClick='pageMemberList()'>&rsaquo;</span>
        </li>
	    
        <li class="li-page next-end" value='${searchData.paging }'>
	        <span style="cursor:pointer" onClick='pageMemberList()'>&raquo;</span>
        </li>
 --%>
												</ul>
											</div>
											<!-- 페이징 끝 -->
											<!-- 검색 부분 -->
											<form action='${pageContext.request.contextPath }/admin/reportList' method='post'>

												<div id='searchBox'>
													<select id='searchSelect' name='searchSelect' class="form-control select">
														<c:choose>
															<c:when test='${searchData.searchSelect=="member_nick" }'>
																<option value='report_content'>신고내용</option>
																<option value='member_nick' selected='selected'>신고자 닉네임</option>
																<option value='reported_member_nick'>피신고자 닉네임</option>
																<option value='board_title'>신고된 게시글 제목</option>
															</c:when>
															<c:when test='${searchData.searchSelect=="reported_member_nick" }'>
																<option value='report_content'>신고내용</option>
																<option value='member_nick'>신고자 닉네임</option>
																<option value='reported_member_nick' selected='selected'>피신고자 닉네임</option>
																<option value='board_title'>신고된 게시글 제목</option>
															</c:when>
															<c:when test='${searchData.searchSelect=="board_title" }'>
																<option value='report_content'>신고내용</option>
																<option value='member_nick'>신고자 닉네임</option>
																<option value='reported_member_nick'>피신고자 닉네임</option>
																<option value='board_title' selected='selected'>신고된 게시글 제목</option>
															</c:when>
															<c:otherwise>
																<option value='report_content' selected='selected'>신고내용</option>
																<option value='member_nick'>신고자 닉네임</option>
																<option value='reported_member_nick'>피신고자 닉네임</option>
																<option value='board_title'>신고된 게시글 제목</option>
															</c:otherwise>
														</c:choose>
													</select>
													<input type='text' id='searchWord' name='searchWord' value='${searchData.searchWord }' class="form-control text">
													<input type='hidden' id='report_condition' name='report_condition' value='${report_condition }'>
													<input type='submit' value='검색' class="btn btn-light">
												</div>

											</form>
											<!-- 검색 끝 -->
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
	<script>
			$('.li-page').click(function () {
				for (var i = 0; i < $('.li-page').length; i++) {
					$('.li-page').removeClass('active');
				}
				$(this).addClass('active');
			});
		</script>
</body>

</html>