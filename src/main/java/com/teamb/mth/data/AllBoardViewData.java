package com.teamb.mth.data;

import java.util.ArrayList;

import com.teamb.mth.util.PagingData;
import com.teamb.mth.vo.*;

public class AllBoardViewData 
{
	private ArrayList<AllBoardViewVO> allBoardViewList;
	private PagingData pagingData;
	
	public AllBoardViewData() {
		super();
	}

	public AllBoardViewData(ArrayList<AllBoardViewVO> allBoardViewList, PagingData pagingData) {
		super();
		this.allBoardViewList = allBoardViewList;
		this.pagingData = pagingData;
	}

	public ArrayList<AllBoardViewVO> getAllBoardViewList() {
		return allBoardViewList;
	}

	public void setAllBoardViewList(ArrayList<AllBoardViewVO> allBoardViewList) {
		this.allBoardViewList = allBoardViewList;
	}

	public PagingData getPagingData() {
		return pagingData;
	}

	public void setPagingData(PagingData pagingData) {
		this.pagingData = pagingData;
	}
}
