package com.teamb.mth.data;

import com.teamb.mth.vo.BrandVO;
import com.teamb.mth.vo.ItemTypeVO;
import com.teamb.mth.vo.ItemVO;

public class ItemData {
	private ItemVO itemVO;
	private BrandVO brandVO;
	private ItemTypeVO itemTypeVO;
	
	private int item_LikeCount;
	
	public ItemData() {
		
	}

	public ItemData(ItemVO itemVO, BrandVO brandVO, ItemTypeVO itemTypeVO, int item_LikeCount) {
		super();
		this.itemVO = itemVO;
		this.brandVO = brandVO;
		this.itemTypeVO = itemTypeVO;
		this.item_LikeCount = item_LikeCount;
	}

	public ItemVO getItemVO() {
		return itemVO;
	}

	public void setItemVO(ItemVO itemVO) {
		this.itemVO = itemVO;
	}

	public BrandVO getBrandVO() {
		return brandVO;
	}

	public void setBrandVO(BrandVO brandVO) {
		this.brandVO = brandVO;
	}

	public ItemTypeVO getItemTypeVO() {
		return itemTypeVO;
	}

	public void setItemTypeVO(ItemTypeVO itemTypeVO) {
		this.itemTypeVO = itemTypeVO;
	}

	public int getItem_LikeCount() {
		return item_LikeCount;
	}

	public void setItem_LikeCount(int item_LikeCount) {
		this.item_LikeCount = item_LikeCount;
	}
	
	
}
