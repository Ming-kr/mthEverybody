<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>

<head>
    <style>
        select.form-control:not([size]):not([multiple]) {
      height: calc(3.25rem + 2px);
    }    
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
    <script>
    function report() { //보드 출력 로직...
		var xmlhttp = new XMLHttpRequest();
  		var report_where = document.getElementById('report_where').value;
  		var report_where_idx = document.getElementById('report_where_idx').value;
  		
 		var report_title = document.getElementById('report_title');
  		var report_title_value = report_title.options[report_title.selectedIndex].value;
  		var report_content = document.getElementById('report_content').value;
  		
		//콜백 설정...
		xmlhttp.onreadystatechange = function(){ //xmlhttp readystate가 변경될 때마다 익명함수 호출			//처음엔 설정만 해두는 것이고 상태가 변경될 때 마다 호출
			
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200){ //200성공했을 경우, 둘다 받은 경우
				//처리로직...
				if(xmlhttp.responseText == "fail") { //로그인 안하고 했을 때 fail
					alert("신고 접수가 실패하였습니다. 다시 한번 시도해 주세요");
					location.href='#';
				} else {
					if(xmlhttp.responseText == "success") { //삽입했을 때 insert
						alert("성공적으로 신고가 접수되었습니다.");
						if('${reportVO.report_where }' == 'mth_yum'){
							location.href='${pageContext.request.contextPath}/yummyboard/yummyBoard';
						}else if('${reportVO.report_where }' == 'mth_free'){
							location.href='${pageContext.request.contextPath}/freeboard/freeBoard';
						}
					} else if(xmlhttp.responseText == "alreadyExist") { //삭제했을 때 delete
						alert("한 게시글에는 한번만 신고할 수 있습니다.");
						location.href='#';
					} else{
						alert("신고 접수가 실패하였습니다. 다시 한번 시도해 주세요");
						location.href='#';
					}
				}
			}
		};
		xmlhttp.open("POST", "${pageContext.request.contextPath}/popup/actionWriteReportForm", true); //3번째는 동기식, 비동기식이냐 의미, 디폴트는 true
		xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		//전달할 값 로직 세팅....
		
		xmlhttp.send("report_title="+report_title_value+"&report_content="+report_content+"&report_where="+report_where+"&report_where_idx="+report_where_idx);		
	}
    </script>
</head>

<body>
        <h3>신고</h3>
        <div class="form-group">
        <input type='hidden' id='report_where' value='${reportVO.report_where }'>
        <input type='hidden' id='report_where_idx' value='${reportVO.report_where_idx }'>
            <label for="report-option" class="sr-only">신고 내용</label>
            <select id="report_title" class="form-control">
                <option value="ABUSE">욕설, 비방</option>
                <option value="AD">부적절한 광고</option>
                <option value="PORNO">음란물 개제</option>
            </select>
        </div>
        <div class="form-group">
            <textarea id="report_content" cols="30" rows="10" class="form-control"></textarea>
        </div>
        <button type="button" class="btn btn-primary" onClick="location.href='#'">취소</button>
        <button type="button" class="btn btn-danger" onClick='report()'>신고하기</button>
</body>
</html>