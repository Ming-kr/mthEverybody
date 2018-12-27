package com.teamb.mth.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.teamb.mth.vo.YumReVO;

public interface YumReSQLMapper {

	@Select("SELECT * FROM mth_yum_re WHERE yum_idx = #{yum_idx} AND yum_re_state != 'HIDE' ORDER BY yum_re_writedate ASC")
	public ArrayList<YumReVO> selectByYumIdx(YumReVO requestYumReVO);

	@Select("SELECT * FROM mth_yum_re WHERE yum_re_idx = #{yum_re_idx}")
	public YumReVO selectByIdx(YumReVO requestYumReVO);

	@Insert("INSERT INTO mth_yum_re VALUES(#{yum_re_idx},#{yum_idx},#{member_idx},#{yum_re_content},'NORMAL',SYSDATE)")
	public void insert(YumReVO requestYumReVO);

	@Select("SELECT seq_yum_re_idx.nextval FROM dual")
	public String getNextSeq();

	// 신고처리시 삭제글 안에 댓글들도 전부 삭제처리
	@Update("UPDATE mth_yum_re SET yum_re_state='HIDE' WHERE yum_re_idx=#{yum_re_idx}")
	public void updateReState(String yum_re_idx);

	@Select("SELECT * FROM mth_yum_re WHERE yum_idx = #{yum_idx}")
	public ArrayList<YumReVO> selectByYumIdxForDelete(String yum_idx);

	// 게시글 개수 찾기 로직
	@Select("SELECT COUNT(1) FROM mth_yum_re WHERE yum_re_state !='HIDE'AND yum_idx=#{yum_idx}")
	public String countReByYumIdx(String yum_idx);
}
