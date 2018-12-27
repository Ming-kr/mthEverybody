<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
   pageContext.setAttribute("enter", "\n");
   pageContext.setAttribute("br", "<br/>");
%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>

    <title>자취생을 위한 모두의 떨이</title>
    <link rel="shortcut icon" type="image⁄x-icon" href="${pageContext.request.contextPath}/resources/img/logo/image2.png">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/subbootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/css/shop-homepage.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main/selectbox.css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

    <script src="//code.jquery.com/jquery-3.2.1.min.js"></script>

    <link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main/popup.css"><!-- 팝업용 css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/css/conProductInfoReply.css"><!-- 물품/편의점 정보&댓글 css -->

    <style>
        .col-lg-8 {
    flex: 0 0 100%;
    max-width: 100%;
    }
    .content-reading{
      text-align: left;
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

    .text-center.pull-left{
        padding-bottom: 0;
        font-size: 20px;
        float: none !important;
        margin-top: 10px;
        margin-bottom: 5px;
    }

    .btn.btn-success.boot{
        position: absolute;
        right: 5em;
        top: 0.5em;
    }

    .btn.btn-danger.boot{
        position: absolute;
        right: 0;
        top: 0.5em;
    }

    .btn.btn-primary.boot{
        position: absolute;
        left: 0;
        top: 0.5em;
    }
  </style>
     <script type="text/javascript">
        function likeFreeContent() { //보드 출력 로직...
            var xmlhttp = new XMLHttpRequest();
            var free_idx = ${freeData.freeVO.free_idx};
            var free_likeCount = document.getElementById('free_likeCount').value;
            var old_like_view = document.getElementById('free_likeCount_child').innerHTML;

            //콜백 설정...
            xmlhttp.onreadystatechange = function () { //xmlhttp readystate가 변경될 때마다 익명함수 호출			//처음엔 설정만 해두는 것이고 상태가 변경될 때 마다 호출

                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) { //200성공했을 경우, 둘다 받은 경우
                    var likeFree = JSON.parse(xmlhttp.responseText);
                    //처리로직...
                    if (likeFree.success == "fail") { //로그인 안하고 했을 때 fail
                        alert("로그인을 먼저하세요!");
                    } else {
                        if (likeFree.likeFreeInsert == "insert") { //삽입했을 때 insert
                            free_likeCount = likeFree.likeFreeCount;
                        } else { //삭제했을 때 delete
                            free_likeCount = likeFree.likeFreeCount;
                        }
                        document.getElementById('free_likeCount').value = free_likeCount;
                        document.getElementById('free_likeCount_child').innerHTML = document.getElementById(
                            'free_likeCount_child').innerHTML.replace(document.getElementById(
                            'free_likeCount_child').innerHTML.substring(3), "<br>" + free_likeCount);
                    }
                }
            };
            xmlhttp.open("POST", "${pageContext.request.contextPath}/freeboard/likeFreeContent", true); //3번째는 동기식, 비동기식이냐 의미, 디폴트는 true
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            //전달할 값 로직 세팅....

            xmlhttp.send("free_idx=" + free_idx); //받는 쪽에서 request.getparameter로 받을 수 있음...		
        }
        /*댓글 달았을 때 AJAX  */
        function writeFreeReContent() { //보드 출력 로직...
            var xmlhttp = new XMLHttpRequest();

            var free_idx = ${freeData.freeVO.free_idx};
            var free_re_content = $('#free_re_content').val();

            //콜백 설정...
            xmlhttp.onreadystatechange = function () { //xmlhttp readystate가 변경될 때마다 익명함수 호출			
                //처음엔 설정만 해두는 것이고 상태가 변경될 때 마다 호출

                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) { //200성공했을 경우, 둘다 받은 경우
                	console.log(xmlhttp.responseText);
                    var freeReData = JSON.parse(xmlhttp.responseText);
                    
                    if(freeReData.success == "fail") { //로그인 안하고 했을 때 fail
    					alert("로그인을 먼저하세요!");
                    }
                    
                    var response_free_re_idx = freeReData.freeReVO.free_re_idx;
                    var response_member_nick = freeReData.memberVO.member_nick;
                    var response_free_re_writedate = freeReData.freeReVO.free_re_writedate;
                    var response_free_re_content = freeReData.freeReVO.free_re_content;



                    var response_reply = "<li class='list-group-item'>" +
                        "<div class='row'>" +
                        "<div class='col-xs-10 col-md-11'>" +
                        "<div>" +
                        "<strong>" + response_member_nick + "</strong>" +
                        "<div class='mic-info'>" +
                        response_free_re_writedate +
                        "</div>" +
                        "<a href='${pageContext.request.contextPath }/freeboard/deleteFreeReContent?free_idx=${freeData.freeVO.free_idx}&free_re_idx=" +
                        response_free_re_idx + "&pageNum=${pageNum}' class='mic-info delete'>삭제</a>" +
                        "</div>" +
                        "<div class='comment-text'>" +
                        response_free_re_content +
                        "</div>" +
                        "</div>" +
                        "</div>" +
                        "</li>";
                    $('#reply_list').append(response_reply);
                    document.getElementById('free_re_content').value = '';
                }
            };
            
           
            xmlhttp.open("POST", "${pageContext.request.contextPath }/freeboard/writeFreeReContent", true); //3번째는 동기식, 비동기식이냐 의미, 디폴트는 true
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            //전달할 값 로직 세팅....

            xmlhttp.send('free_idx=' + free_idx + '&free_re_content=' + free_re_content); //받는 쪽에서 request.getparameter로 받을 수 있음...		
        }
    </script>


</head>

<body>
    <!-- 헤더 영역 -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <jsp:include page="../commons/header.jsp" />
    </nav>

    <!-- wrapper 시작 -->
    <div class="wrapper body">
        <!-- row 시작 -->
        <div class="row">
            <!-- /.col-lg-3 시작 -->
            <div class="col-lg-3">
                <!-- 사이드바 -->
                <h1 class="my-4">Board</h1>
                <jsp:include page="../commons/asideHome.jsp" />
                <!-- 사이드바 끝 -->
                <!-- 광고상자 -->
                <div class="advertiseAside">
                    <p>광고</p>
                </div>
                <!-- 광고상자 끝 -->
            </div>
            <!-- /.col-lg-3 끝 -->
            <!-- col-lg-9 시작 -->
            <div class="col-lg-9">
                <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
                    <!-- 내용 넣는 공간 -->
                    <div class="row">
                        <div class="col-lg-8">


                            <!-- 글 제목 -->
                            <h1>[${freeData.freeVO.free_category}] ${freeData.freeVO.free_title }</h1>
                            <hr>
                            <!-- the actual blog post: title/author/date/content -->
                            <p class="lead">
                                <span style="float:left">작성자 : ${freeData.memberVO.member_nick }</span>
                                <span style="float:center">${freeData.freeVO.free_writedate }</span>
                                <span style="float:right">조회 : ${freeData.freeVO.free_count } | 추천 : ${freeData.free_likeCount }</span>
                            </p>
                            <hr>
                            <!--  이미지   -->
                            <c:forEach var="freeImgVO" items="${freeData.freeImgList }">
                                <img width="200" src="${freeImgVO.free_img_imgfilename}" class="img-responsive">
                            </c:forEach>
                            <hr>
                            <!-- 내용 -->
                            <div class="content-reading">
                                ${freeData.freeVO.free_content }
                                <div style="text-align:center; position: relative; margin-top: 50px;">
                                    <a href='${pageContext.request.contextPath  }/freeboard/freeBoard?pageNum=${pageNum}'>
                                        <button type="button" class="btn btn-primary boot">목록으로</button>
                                    </a>
                                    <p id="free_likeCount_child" class='btn btn-primary' onClick='likeFreeContent()'>추천수<br>${freeData.free_likeCount
                                        }</p>
                                    <!-- 작성자와 세션 닉이 같으면 수정, 삭제-->
                                    <c:if test='${!empty sessionLoginedData && freeData.freeVO.member_idx == sessionLoginedData.member_idx }'>
                                        <a href='${pageContext.request.contextPath }/freeboard/updateFreeContentForm?free_idx=${freeData.freeVO.free_idx}&pageNum=${pageNum}'>
                                            <input type='button' class='btn btn-success boot' value='수정'>
                                        </a>
                                        <a href='${pageContext.request.contextPath }/freeboard/deleteFreeContent?free_idx=${freeData.freeVO.free_idx}&pageNum=${pageNum}'>
                                            <input type='button' class='btn btn-danger boot' value='삭제'>
                                        </a>
                                    </c:if>
                                    <!-- 수정, 삭제 끝 -->
                                    <c:if test='${!empty sessionLoginedData && sessionLoginedData.member_idx != freeData.freeVO.member_idx}'>
                                        <a href="#writeReport" id="writeReport_page" class="btn btn-danger boot">신고</a>
                                        <!-- 신고 -->
                                        <a href="#x" class="overlay" id="writeReport"></a>
                                        <div class="popup">
                                            <jsp:include page="../popup/writeReportForm.jsp" />
                                        </div>
                                    </c:if>
                                    
                                </div>
                                <input type="hidden" id="free_likeCount" value="${freeData.free_likeCount }">
                                <br>
                            </div>

                            <br />

                            <hr>
                            <!-- 댓글 들어갈 보드 -->
                            <div class="panel panel-default widget">
                                <div class="panel-heading">
                                    <h3 class="panel-title">댓글</h3>
                                </div>
                                <div class="panel-body">
                                    <ul id='reply_list' class="list-group">
                                        <c:forEach var='freeReData' items='${freeReDataList }'>

                                            <li class="list-group-item">
                                                <div class="row">
                                                    <div class="col-xs-10 col-md-11">
                                                        <div>
                                                            <strong>${freeReData.memberVO.member_nick }</strong>
                                                            <div class="mic-info">
                                                                ${freeReData.freeReVO.free_re_writedate }
                                                            </div>
                                                            <c:if test='${freeReData.memberVO.member_idx==sessionLoginedData.member_idx }'>
                                                                <%-- <a href="${pageContext.request.contextPath }/freeboard/updateFreeReContentForm?free_re_idx=${freeReData.freeReVO.free_re_idx}" class="mic-info update">수정</a> --%>
                                                                <a href="${pageContext.request.contextPath }/freeboard/deleteFreeReContent?free_idx=${freeData.freeVO.free_idx}&free_re_idx=${freeReData.freeReVO.free_re_idx}&pageNum=${pageNum}"
                                                                    class="mic-info delete">삭제</a>
                                                            </c:if>

                                                        </div>
                                                        <div class="comment-text">
                                                            ${fn:replace(freeReData.freeReVO.free_re_content,enter,br)
                                                            }
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>

                                        </c:forEach>

                                    </ul>

                                    <!-- 댓글 페이징 -->
                                    <!-- <div class="page-nation">
                        <ul class="pagination pagination-large">
                            <li class="previous-end"><span>&laquo;</span></li>
                            <li class="previous"><span>&lsaquo; </span></li>
                            <li class="active"><span>1</span></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li class="next"><span>&rsaquo;</span></li>
                            <li class="next-end"><span>&raquo;</span></li>

                        </ul>
                    </div> -->
                                </div>
                                <div class="col">
                                    <div class="panel-body">
                                        <form role="form">
                                            <fieldset>
                                                <div class="form-group">
                                                    <textarea id='free_re_content' class="form-control" rows="3"
                                                        placeholder="명예훼손, 개인정보 유출, 분쟁 유발, 허위사실 유포 등의 글은 이용약관에 의해 제재는
물론 법률에 의해 처벌받을 수 있습니다. 건전한 커뮤니티를 위해 자제를 당부 드립니다."></textarea>
                                                </div>
	                                            <button type="button" class="btn btn-success" data-loading-text="Loading..." onClick='writeFreeReContent()'>댓글 달기</button>                                                
                                            </fieldset>
                                        </form>
                                    </div>
                                </div>

                            </div>
                            <!-- 댓글 들어갈 보드 끝 -->

                            <!--  이전게시글 다음게시글 출력 -->
                            <div class="panel panel-warning boot">
                                <div class="panel-heading boot">
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-12 col-md-3">
                                            <h2 class="text-center pull-left" style="padding-left: 30px;font-size: 1.2em;">글 목록</h2>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-body table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th class="text-center"> 번호 </th>
                                                <th class="text-center"> 카테고리 </th>
                                                <th class="text-center"> 제목 </th>
                                                <th class="text-center"> 작성자 </th>
                                                <th class="text-center"> 등록일 </th>
                                                <th class="text-center"> 조회 </th>
                                                <th class="text-center"> 추천수 </th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                            <!-- 메인 보드 가져오기... -->
                                            <c:forEach var="boardData" items='${freeBoardData }'>
                                                <c:choose>
                                                    <c:when test='${boardData.freeVO.free_category=="비밀" }'>
                                                        <c:choose>

                                                            <c:when test='${sessionLoginedData.member_code=="ADMIN"||boardData.freeVO.member_idx==sessionLoginedData.member_idx }'>
                                                                <tr>
                                                                    <td class="text-center">${boardData.freeVO.free_idx}</td>
                                                                    <td class="text-center">${boardData.freeVO.free_category}</td>
                                                                    <td class="text-center">
                                                                        <a href='${pageContext.request.contextPath  }
											/freeboard/readFreeContent?free_idx=${boardData.freeVO.free_idx }&pageNum=${pageNum}'>
                                                                            ${boardData.freeVO.free_title} [${
                                                                            boardData.free_re_count}]
                                                                        </a>
                                                                    </td>
                                                                    <td class="text-center">${boardData.memberVO.member_nick}</td>
                                                                    <td class="text-center">${boardData.freeVO.free_writedate}</td>
                                                                    <td class="text-center">${boardData.freeVO.free_count}</td>
                                                                    <td class="text-center">${boardData.free_likeCount}</td>
                                                                </tr>
                                                            </c:when>

                                                            <c:otherwise>
                                                                <tr>
                                                                    <td class="text-center">##</td>
                                                                    <td class="text-center">${boardData.freeVO.free_category}</td>
                                                                    <td class="text-center">[secret]
                                                                        ${boardData.freeVO.free_title} [${
                                                                        boardData.free_re_count}]</td>
                                                                    <td class="text-center">###</td>
                                                                    <td class="text-center">##-##</td>
                                                                    <td class="text-center">##</td>
                                                                    <td class="text-center">##</td>
                                                                </tr>
                                                            </c:otherwise>

                                                        </c:choose>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <!-- 다출력 -->
                                                        <tr>
                                                            <td class="text-center">${boardData.freeVO.free_idx}</td>
                                                            <td class="text-center">${boardData.freeVO.free_category}</td>
                                                            <td class="text-center">
                                                                <a href='${pageContext.request.contextPath  }
								/freeboard/readFreeContent?free_idx=${boardData.freeVO.free_idx }&pageNum=${pageNum}'>
                                                                    ${boardData.freeVO.free_title} [${
                                                                    boardData.free_re_count}]
                                                                </a>
                                                            </td>
                                                            <td class="text-center">${boardData.memberVO.member_nick}</td>
                                                            <td class="text-center">${boardData.freeVO.free_writedate}</td>
                                                            <td class="text-center">${boardData.freeVO.free_count}</td>
                                                            <td class="text-center">${boardData.free_likeCount}</td>
                                                        </tr>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>

                            </div>
                            <!--  이전게시글 다음게시글 출력 -->

                            <hr>

                            <a href="${pageContext.request.contextPath }/freeboard/freeBoard?pageNum=${pageNum}"><button
                                    type="button" class="btn btn-primary">목록으로</button></a>
                        </div>

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
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>