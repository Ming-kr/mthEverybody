package com.teamb.mth.data;

import java.util.ArrayList;

import com.teamb.mth.util.PagingData;
import com.teamb.mth.vo.AllReplyViewVO;

public class AllReplyViewData 
{
	private ArrayList<AllReplyViewVO> allReplyViewList;
	private PagingData pagingData;
	
	public AllReplyViewData() {
		super();
	}
	public AllReplyViewData(ArrayList<AllReplyViewVO> allReplyViewList, PagingData pagingData) {
		super();
		this.allReplyViewList = allReplyViewList;
		this.pagingData = pagingData;
	}
	public ArrayList<AllReplyViewVO> getAllReplyViewList() {
		return allReplyViewList;
	}
	public void setAllReplyViewList(ArrayList<AllReplyViewVO> allReplyViewList) {
		this.allReplyViewList = allReplyViewList;
	}
	public PagingData getPagingData() {
		return pagingData;
	}
	public void setPagingData(PagingData pagingData) {
		this.pagingData = pagingData;
	}
}
