<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel-heading boot">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-3">
			<h2 class="text-center pull-left"
				style="padding-left: 5px; padding-bottom: 5px;">내가 쓴 게시글</h2>
		</div>
	</div>
</div>

<div class="panel-body table-responsive">
	<table class="table table-hover">
		<thead>
			<tr>
				<th class="text-center">게시판</th>
				<th class="text-center">제목</th>
				<th class="text-center">작성일</th>
			</tr>
		</thead>

		<tbody>
			<!-- 내용 -->
			<c:forEach var='allBoardData'
				items='${allBoardViewDataList.allBoardViewList}'>
				<!-- 게시판 구분 한글처리+idx설정..(YUM: 맛있는편의점, FREE: 자유게시판) 페이지이동하는건 다 진영오빠거 적용... -->
				<c:if test='${allBoardData.board_name == "YUM"}'>
					<tr class="edit" id="detail" style="cursor: pointer"
						onclick="location.href='${pageContext.request.contextPath}/yummyboard/readYummyContent?yum_idx=${allBoardData.idx}'">
						<td id="no" class="text-center">[맛있는편의점]</td>
						<td id="name" class="text-center">${allBoardData.title}</td>
						<td id="mobile" class="text-center">${allBoardData.writedate}</td>
					</tr>
				</c:if>
				<c:if test='${allBoardData.board_name == "FREE"}'>
					<tr class="edit" id="detail" style="cursor: pointer"
						onclick="location.href='${pageContext.request.contextPath}/freeboard/readFreeContent?free_idx=${allBoardData.idx}'">
						<td id="no" class="text-center">[자유게시판]</td>
						<td id="name" class="text-center">${allBoardData.title}</td>
						<td id="mobile" class="text-center">${allBoardData.writedate}</td>
					</tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	<!-- 페이징 부분 -->
	<c:if test='${allBoardViewDataList.pagingData.totalPageCount != 0}'>
		<div class="page-nation">
			<ul class="pagination pagination-large">
				<!-- 첫번째 페이지면 좌로 가는 버튼X -->
				<c:if test='${allBoardViewDataList.pagingData.nowPageNum != 0 }'>
					<li class="previous-end"
						onclick="pagingFunction('requestAllBoardPaging', ${allBoardViewDataList.pagingData.firstPagingNum-1})"><span>&laquo;</span></li>
					<li class="previous"
						onclick="pagingFunction('requestAllBoardPaging', ${allBoardViewDataList.pagingData.nowPageNum-1})"><span>&lsaquo;
					</span></li>
				</c:if>
				<c:forEach var='pageNum'
					begin='${allBoardViewDataList.pagingData.firstPagingNum}'
					end='${allBoardViewDataList.pagingData.lastPagingNum}'>
					<c:if
						test='${pageNum == allBoardViewDataList.pagingData.nowPageNum }'>
						<li class="active"><span>${pageNum+1}</span></li>
					</c:if>
					<c:if
						test='${pageNum != allBoardViewDataList.pagingData.nowPageNum }'>
						<li><a
							href="javascript:pagingFunction('requestAllBoardPaging', ${pageNum})">${pageNum+1}</a></li>
					</c:if>
				</c:forEach>
				<!-- 마지막 페이지면 우로 가는 버튼X -->
				<c:if
					test='${allBoardViewDataList.pagingData.nowPageNum != (allBoardViewDataList.pagingData.totalPageCount-1) }'>
					<li class="next"
						onclick="pagingFunction('requestAllBoardPaging', ${allBoardViewDataList.pagingData.nowPageNum+1})"><span>&rsaquo;</span></li>
					<li class="next-end"
						onclick="pagingFunction('requestAllBoardPaging', ${allBoardViewDataList.pagingData.lastPagingNum+1})"><span>&raquo;</span></li>
				</c:if>
			</ul>
		</div>
		</c:if>
</div>

