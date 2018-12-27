package com.teamb.mth.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;

import com.teamb.mth.vo.ItemTypeVO;

public interface ItemTypeSQLMapper {
	
	@Select("SELECT * FROM mth_item_type")
	public ArrayList<ItemTypeVO> selectAll();
	
	@Select("SELECT * FROM mth_item_type WHERE item_type_idx = #{item_type_idx}")
	public ItemTypeVO selectByIdx(String item_type_idx);
}
