package com.teamb.mth.mapper;



import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.teamb.mth.vo.FreeImgVO;
import com.teamb.mth.vo.FreeVO;

public interface FreeImgSQLMapper {
	//업로드
	@Insert("INSERT INTO mth_free_img VALUES(seq_free_img_idx.nextval,#{free_idx},#{free_img_imgfilename},SYSDATE)")
	public void insert(FreeImgVO freeImgVO);
	
	//
	@Select("SELECT * FROM mth_free_img WHERE free_idx=#{free_idx}")
	public ArrayList<FreeImgVO> selectByIdx(FreeVO freeVO);


}
