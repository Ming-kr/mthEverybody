package com.teamb.mth.util;

public class PagingData 
{
	//페이지번호 인덱스 0부터 시작 -> 번호 != 갯수
	//전체 목록 갯수
	private int totalCount;
	//전체 페이지 갯수
	private int totalPageCount;
	//보여줄 페이지번호 갯수
	private int pagingListCount;
	//보여줄 페이지번호
	private int nowPageNum;
	//보여줄 목록 갯수
	private int dataCountPerPage;
	//보여줄 첫번째 ROWNUM
	private int startNum;
	//보여줄 마지막 ROWNUM
	private int endNum;
	//보여줄 첫번째 페이지번호
	private int firstPagingNum;
	//보여줄 마지막 페이지번호
	private int lastPagingNum;
	
	public PagingData() {}
	
	//페이징 설정하기: 매개변수(전체목록수-sqlmapper리턴값, 보여줄 페이지번호 갯수, 보여줄 페이지번호, 보여줄 목록갯수)
	public PagingData(int totalCount, int pagingListCount, int nowPageNum, int dataCountPerPage)
	{
		super();
		this.totalCount = totalCount;
		this.totalPageCount = totalCount/dataCountPerPage;
			//totalCount랑 dataCountPerPage가 딱 떨어지지 않을때, 페이지수 1개 추가
			if(totalCount%dataCountPerPage>0) { this.totalPageCount++; }
		this.pagingListCount = pagingListCount;
			//임의로 url 통해 page수 이동할때 예외처리
			if(nowPageNum >= this.totalPageCount) {nowPageNum = this.totalPageCount-1; }
		this.nowPageNum = nowPageNum;
		this.dataCountPerPage = dataCountPerPage;
		this.startNum = (nowPageNum*dataCountPerPage) + 1;
		this.endNum = (startNum + dataCountPerPage) - 1;
			//마지막 endNum이 totalCount 안넘게 처리
			if(totalCount<this.endNum) { this.endNum = totalCount; }
		this.firstPagingNum = (nowPageNum/dataCountPerPage)*dataCountPerPage;
		this.lastPagingNum = firstPagingNum + (dataCountPerPage-1);
			//페이징 번호 시작/끝 예외처리
			if(firstPagingNum<0) { firstPagingNum = 0; }
			if(lastPagingNum>=(totalPageCount-1)) 
			{ 
				if(totalPageCount == 0) { lastPagingNum=0; }
				else{ lastPagingNum = (totalPageCount-1); }
			}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public int getPagingListCount() {
		return pagingListCount;
	}

	public int getNowPageNum() {
		return nowPageNum;
	}

	public int getDataCountPerPage() {
		return dataCountPerPage;
	}

	public int getStartNum() {
		return startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public int getFirstPagingNum() {
		return firstPagingNum;
	}

	public int getLastPagingNum() {
		return lastPagingNum;
	}
}
