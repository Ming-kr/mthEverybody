package com.teamb.mth.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;

import com.teamb.mth.vo.BrandVO;

public interface BrandSQLMapper {
	
	@Select("SELECT * FROM mth_brand WHERE brand_idx=#{brand_idx}")
	public BrandVO selectByIdx(String brand_idx);

	@Select("SELECT * FROM mth_brand")
	public ArrayList<BrandVO> selectAll();
}
