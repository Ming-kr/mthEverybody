package com.teamb.mth.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.teamb.mth.vo.YumLikeVO;

public interface YumLikeSQLMapper {
	
	@Select("SELECT COUNT(1) FROM mth_yum_like where yum_idx = #{yum_idx}")
	public int countByYumIdx(String yum_idx);
	
	@Select("SELECT * FROM mth_yum_like WHERE yum_idx = #{yum_idx} AND member_idx = #{member_idx}")
	public YumLikeVO selectByMemberIdx(YumLikeVO requestYumLikeVO);
	
	@Insert("INSERT INTO mth_yum_like VALUES(#{member_idx}, #{yum_idx}, SYSDATE)")
	public void insert(YumLikeVO requestYumLikeVO);
	
	@Delete("DELETE mth_yum_like WHERE yum_idx = #{yum_idx} AND member_idx = #{member_idx}")
	public void delete(YumLikeVO requestYumLikeVO);
}

