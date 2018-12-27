package com.teamb.mth.data;

import java.util.ArrayList;

import com.teamb.mth.util.PagingData;
import com.teamb.mth.vo.ItemLikeViewVO;

public class ItemLikeViewData 
{
	private ArrayList<ItemLikeViewVO> itemLikeViewList;
	private PagingData pagingData;
	
	public ItemLikeViewData() {
		super();
	}

	public ItemLikeViewData(ArrayList<ItemLikeViewVO> itemLikeViewList, PagingData pagingData) {
		super();
		this.itemLikeViewList = itemLikeViewList;
		this.pagingData = pagingData;
	}

	public ArrayList<ItemLikeViewVO> getItemLikeViewList() {
		return itemLikeViewList;
	}

	public void setItemLikeViewList(ArrayList<ItemLikeViewVO> itemLikeViewList) {
		this.itemLikeViewList = itemLikeViewList;
	}

	public PagingData getPagingData() {
		return pagingData;
	}

	public void setPagingData(PagingData pagingData) {
		this.pagingData = pagingData;
	}
}
