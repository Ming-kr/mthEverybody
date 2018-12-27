package com.teamb.mth.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.teamb.mth.vo.FreeLikeVO;

public interface FreeLikeSQLMapper {
	
	@Select("SELECT COUNT(1) FROM mth_free_like where free_idx = #{free_idx}")
	public int countByFreeIdx(String free_idx);
	
	//게시글의 좋아요 호출을 위해 게시글 idx와 회원 idx 둘다 일치하는거 호출
	@Select("SELECT * FROM mth_free_like WHERE free_idx = #{free_idx} AND member_idx = #{member_idx}")
	public FreeLikeVO selectByMemberIdx(FreeLikeVO requestFreeLikeVO);

	@Insert("INSERT INTO mth_free_like VALUES(#{member_idx}, #{free_idx},SYSDATE)")
	public void insert(FreeLikeVO requestFreeLikeVO);

	@Delete("DELETE mth_free_like WHERE member_idx = #{member_idx}")
	public void delete(FreeLikeVO requestFreeLikeVO);

	@Select("SELECT COUNT(1) FROM mth_free_like WHERE free_idx = #{free_idx}")
	public int countByfreeIdx(String free_idx);

}
