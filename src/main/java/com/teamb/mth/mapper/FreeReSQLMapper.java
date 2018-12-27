package com.teamb.mth.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.teamb.mth.vo.FreeReVO;

public interface FreeReSQLMapper {
	// 댓글리스트 얻어올 때 쓰이는 SQL문
	@Select("SELECT * FROM mth_free_re WHERE free_idx = #{free_idx} AND free_re_state != 'HIDE' ORDER BY free_re_writedate ASC")
	ArrayList<FreeReVO> selectByFreeIdx(FreeReVO requestFreeReVO);

	// 댓글 작성시 사용 AJAX쪽 리플 작성시 idx값 한개 미리생성.
	@Select("SELECT seq_free_re_idx.nextval FROM dual")
	public String getNextSeq();

	// 댓글 작성 시 사용 idx 완성 후 삽입.
	@Insert("INSERT INTO mth_free_re VALUES(#{free_re_idx},#{free_idx},#{member_idx},#{free_re_content},'NORMAL',SYSDATE)")
	public void insert(FreeReVO requestFreeReVO);

	// 댓글 다시 돌려받을 때 게시글 리플 받기
	@Select("SELECT * FROM mth_free_re WHERE free_re_idx=#{free_re_idx}")
	FreeReVO selectByIdx(FreeReVO requestFreeReVO);

	// 댓글 삭제 (state를 HIDE로 바꾼 녀석을 다시 업데이트)
	@Update("UPDATE mth_free_re SET free_re_state=#{free_re_state} WHERE free_re_idx=#{free_re_idx}")
	public void updateReState(FreeReVO requestFreeReVO);

	// 게시글 개수 찾기 로직...
	@Select("SELECT COUNT(1) FROM mth_free_re WHERE free_re_state !='HIDE'AND free_idx=#{free_idx}")
	public String countReByFreeIdx(String free_idx);

	// 신고처리시 삭제글 안에 댓글들도 전부 삭제처리
	@Update("UPDATE mth_free_re SET free_re_state='HIDE' WHERE free_re_idx=#{free_re_idx}")
	public void updateReStateToHide(String free_re_idx);

	@Select("SELECT * FROM mth_free_re WHERE free_idx = #{free_idx}")
	public ArrayList<FreeReVO> selectByFreeIdxForDelete(String free_idx);
}
