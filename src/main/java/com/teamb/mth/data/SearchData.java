package com.teamb.mth.data;

public class SearchData {
	private String searchSelect;
	private String searchWord;
	private String searchOrder;
	private String searchPrice;
	private String paging;
	private String pageNum;
	private String startNum;
	private String endNum;
	
	public SearchData() {
		
	}
	
	public SearchData(String searchSelect, String searchWord, String searchOrder, String searchPrice, String paging,
			String pageNum, String startNum, String endNum) {
		super();
		this.searchSelect = searchSelect;
		this.searchWord = searchWord;
		this.searchOrder = searchOrder;
		this.searchPrice = searchPrice;
		this.paging = paging;
		this.pageNum = pageNum;
		this.startNum = startNum;
		this.endNum = endNum;
	}

	public String getSearchSelect() {
		return searchSelect;
	}

	public void setSearchSelect(String searchSelect) {
		this.searchSelect = searchSelect;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getSearchOrder() {
		return searchOrder;
	}

	public void setSearchOrder(String searchOrder) {
		this.searchOrder = searchOrder;
	}

	public String getSearchPrice() {
		return searchPrice;
	}

	public void setSearchPrice(String searchPrice) {
		this.searchPrice = searchPrice;
	}

	public String getPaging() {
		return paging;
	}

	public void setPaging(String paging) {
		this.paging = paging;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getStartNum() {
		return startNum;
	}

	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}

	public String getEndNum() {
		return endNum;
	}

	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}
}
