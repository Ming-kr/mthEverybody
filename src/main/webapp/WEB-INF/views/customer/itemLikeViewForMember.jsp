
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 좋아요한 물품 리스트 -->
	<div class="panel-heading boot">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3">
				<h2 class="text-center pull-left"
					style="padding-left: 5px; padding-bottom: 5px;">좋아요한 물품</h2>
			</div>
		</div>
	</div>

	<div class="panel-body table-responsive">
		<table class="table table-hover">
			<thead>
				<tr>
					<th class="text-center">편의점 브랜드</th>
					<th class="text-center">종류</th>
					<th class="text-center">이름</th>
				</tr>
			</thead>

			<tbody>
				<!-- 내용 -->
				<c:forEach var='itemLikeData'
					items='${itemLikeViewDataList.itemLikeViewList}'>
					<tr class="edit" id="detail" style="cursor:pointer"
						onclick="location.href='${pageContext.request.contextPath}/readItem?item_idx=${itemLikeData.item_idx}'">
						<td id="name" class="text-center">${itemLikeData.brand_name}
						</td>
						<td id="mobile" class="text-center">
							${itemLikeData.item_type_name}</td>
						<td id="mail" class="text-center">${itemLikeData.item_name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 좋아요한 물품 페이징 부분 -->
		<c:if test='${itemLikeViewDataList.pagingData.totalPageCount != 0}'>
		<div class="page-nation">
			<ul class="pagination pagination-large">
				<!-- 첫번째 페이지면 좌로 가는 버튼X -->
				<c:if test='${itemLikeViewDataList.pagingData.nowPageNum != 0 }'>
					<li class="previous-end"
						onclick="pagingFunction('requestItemLikePaging', ${itemLikeViewDataList.pagingData.firstPagingNum-1})"><span>&laquo;</span></li>
					<li class="previous"
						onclick="pagingFunction('requestItemLikePaging', ${itemLikeViewDataList.pagingData.nowPageNum-1})"><span>&lsaquo;
					</span></li>
				</c:if>
				<c:forEach var='pageNum'
					begin='${itemLikeViewDataList.pagingData.firstPagingNum}'
					end='${itemLikeViewDataList.pagingData.lastPagingNum}'>
					<c:if
						test='${pageNum == itemLikeViewDataList.pagingData.nowPageNum }'>
						<li class="active"><span>${pageNum+1}</span></li>
					</c:if>
					<c:if
						test='${pageNum != itemLikeViewDataList.pagingData.nowPageNum }'>
						<li><a
							href="javascript:pagingFunction('requestItemLikePaging', ${pageNum})">${pageNum+1}</a></li>
					</c:if>
				</c:forEach>
				<!-- 마지막 페이지면 우로 가는 버튼X -->
				<c:if
					test='${itemLikeViewDataList.pagingData.nowPageNum != (itemLikeViewDataList.pagingData.totalPageCount-1) }'>
					<li class="next"
						onclick="pagingFunction('requestItemLikePaging', ${itemLikeViewDataList.pagingData.nowPageNum+1})"><span>&rsaquo;</span></li>
					<li class="next-end"
						onclick="pagingFunction('requestItemLikePaging', ${itemLikeViewDataList.pagingData.lastPagingNum+1})"><span>&raquo;</span></li>
				</c:if>
			</ul>
		</div>
		</c:if>
	</div>
