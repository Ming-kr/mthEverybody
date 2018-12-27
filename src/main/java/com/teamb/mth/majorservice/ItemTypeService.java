package com.teamb.mth.majorservice;

import java.util.ArrayList;

import com.teamb.mth.vo.ItemTypeVO;

public interface ItemTypeService {
	public ItemTypeVO getItemTypeVO(String item_type_idx);
	public ArrayList<ItemTypeVO> getItemTypeList();
	
}
