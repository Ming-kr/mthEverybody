package com.teamb.mth.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.teamb.mth.vo.ItemLikeVO;

public interface ItemLikeSQLMapper {
	@Select("SELECT * FROM mth_item_like where item_idx = #{item_idx}")
	public ArrayList<ItemLikeVO> selectByItemIdx(String item_idx); 
	
	@Select("SELECT COUNT(1) FROM mth_item_like where item_idx = #{item_idx}")
	public int countByItemIdx(String item_idx);
	
	@Select("SELECT * FROM mth_item_like WHERE item_idx = #{item_idx} AND member_idx = #{member_idx}")
	public ItemLikeVO selectByItemIdxAndMemberIdx(ItemLikeVO requestItemLikeVO);
	
	@Insert("INSERT INTO mth_item_like VALUES(#{item_idx}, #{member_idx}, SYSDATE)")
	public void insert(ItemLikeVO requestItemLikeVO);
	
	@Delete("DELETE mth_item_like WHERE item_idx = #{item_idx} AND member_idx = #{member_idx}")
	public void delete(ItemLikeVO requestItemLikeVO);
}
