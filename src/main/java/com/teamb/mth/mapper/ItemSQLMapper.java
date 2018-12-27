package com.teamb.mth.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.teamb.mth.data.SearchData;
import com.teamb.mth.vo.ItemVO;

public interface ItemSQLMapper {

	@Select("SELECT * FROM mth_item WHERE item_type_idx=#{item_type_idx} AND item_condition='NORMAL' AND (brand_idx=#{brand_idx} OR brand_idx=100)")
	public ArrayList<ItemVO> selectByBrandIdxAndItemTypeIdx(ItemVO requestItemVO);

	@Select("SELECT * FROM mth_item WHERE item_condition !='DELETE' ORDER BY item_idx DESC")
	public ArrayList<ItemVO> selectAll();

	@Insert("INSERT INTO mth_item VALUES(seq_item_idx.nextval,#{brand_idx},#{item_type_idx},#{item_name},#{item_originprice},#{item_imgfilename},#{item_content},#{item_condition})")
	public void insert(ItemVO requestItemVO);

	@Select("SELECT * FROM mth_item WHERE item_idx=#{item_idx}")
	public ItemVO selectByIdx(String item_idx);
	
	@Update("UPDATE mth_item SET brand_idx = #{brand_idx},item_type_idx=#{item_type_idx},"
			+ "item_name=#{item_name},item_originprice=#{item_originprice},item_imgfilename=#{item_imgfilename},"
			+ "item_content=#{item_content} WHERE item_idx=#{item_idx}")
	public void update(ItemVO requestItemVO);

	@Update("UPDATE mth_item SET item_condition ='EXTINCTION'WHERE item_idx=#{item_idx}")
	public void updateExtinct(String item_idx);

	@Update("UPDATE mth_item SET item_condition ='NORMAL'WHERE item_idx=#{item_idx}")
	public void updateNormal(String item_idx);

	@Update("UPDATE mth_item SET item_condition ='DELETE'WHERE item_idx=#{item_idx}")
	public void delete(String item_idx);

	// 관리자 아이템 리스트 용...

	// 전부
	@Select("SELECT * FROM mth_item WHERE item_condition != 'DELETE' ORDER BY item_idx DESC")
	public ArrayList<ItemVO> selectList();

	@Select("SELECT COUNT(1) FROM mth_item WHERE item_condition != 'DELETE'")
	public String countAll();

	@Select("SELECT * FROM mth_item WHERE item_condition != 'DELETE' AND ${searchSelect} LIKE '%'||#{searchWord}||'%' ORDER BY item_idx DESC")
	public ArrayList<ItemVO> selectSearchList(SearchData requestSearchData);

	@Select("SELECT COUNT(1) FROM mth_item WHERE item_condition != 'DELETE' AND ${searchSelect} LIKE '%'||#{searchWord}||'%'")
	public String countSearch(SearchData requestSearchData);

	// 정상
	@Select("SELECT * FROM mth_item WHERE item_condition = 'NORMAL' ORDER BY item_idx DESC")
	public ArrayList<ItemVO> selectNormalList();
	
	@Select("SELECT COUNT(1) FROM mth_item WHERE item_condition = 'NORMAL'")
	public String countNormalAll();

	@Select("SELECT COUNT(1) FROM mth_item WHERE item_condition = 'NORMAL' AND ${searchSelect} LIKE '%'||#{searchWord}||'%'")
	public String countSearchNormal(SearchData requestSearchData);

	// 단종
	@Select("SELECT * FROM mth_item WHERE item_condition = 'EXTINCTION' ORDER BY item_idx DESC")
	public ArrayList<ItemVO> selectExtinctionList();
	
	@Select("SELECT COUNT(1) FROM mth_item WHERE item_condition = 'EXTINCTION'")
	public String countExtinctionAll();

	@Select("SELECT COUNT(1) FROM mth_item WHERE item_condition = 'EXTINCTION' AND ${searchSelect} LIKE '%'||#{searchWord}||'%'")
	public String countSearchExtinction(SearchData requestSearchData);
}
