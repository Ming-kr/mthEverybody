package com.teamb.mth.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.teamb.mth.data.FreeBoardData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.vo.FreeVO;

public interface FreeSQLMapper {
	//게시판 로드할 때 사용 state가 HIDE아닌걸 idx내림차순으로 y선언 후,  순서 ROWNUM 순서매기기 as rnum으로 선언하고 startNum endNum사이로 
	@Select("SELECT * FROM (SELECT ROWNUM as rnum, y.* FROM (SELECT * FROM mth_free WHERE free_state != 'HIDE' ORDER BY free_idx DESC) y) WHERE rnum between ${startNum} and ${endNum}")
	public ArrayList<FreeVO> selectAll(SearchData requestSearchFreeData);
	
	//게시판 글 클릭 시 사용
	@Select("SELECT * FROM mth_free WHERE free_idx=#{free_idx}")
	public FreeVO selectByIdx(String free_idx);
	
	//게시글 등록 시 free_idx 키 값 찾기
	@Select("SELECT seq_free_idx.nextval FROM dual")
	public String getNextSeq();
	
	//게시글 등록 시 사용
	@Insert("INSERT INTO mth_free VALUES(#{free_idx},#{member_idx},#{free_category},#{free_title},#{free_content},'NORMAL',0,SYSDATE)")
	public void insert(FreeVO requestFreeVO);
	
	//게시글 업데이트 사용
	@Update("UPDATE mth_free SET free_title=#{free_title},free_category=#{free_category},free_content=#{free_content} WHERE free_idx=#{free_idx}")
	public void update(FreeVO requestFreeVO);
	
	//게시글 삭제 시 free_state값 HIDE로 바꿔주는 SQL문
	@Update("UPDATE mth_free SET free_state=#{free_state} WHERE free_idx=#{free_idx}")
	public void updateState(FreeVO requestFreeVO);
	
	//게시글 조회수 
	@Update("UPDATE mth_free SET free_count=#{free_count} WHERE free_idx=#{free_idx}")
	public void updateCount(FreeVO requestFreeVO);

	//게시글 검색 시 사용
	@Select("SELECT * FROM (SELECT ROWNUM as rnum, y.* FROM (SELECT * FROM mth_free WHERE free_state != 'HIDE' AND ${searchSelect} LIKE '%'||#{searchWord}||'%' ORDER BY free_idx DESC) y) WHERE rnum between ${startNum} and ${endNum}")
	public ArrayList<FreeVO> selectSearch(SearchData requestSearchfreeData);
	
	//검색된 게시글 개수 찾기 로직
	@Select("SELECT COUNT(1) FROM mth_free WHERE free_state != 'HIDE' AND ${searchSelect} LIKE '%'||#{searchWord}||'%' ORDER BY free_idx DESC")
	public String countSearch(SearchData requestSearchFreeData);
	
	//게시글 개수 찾기 로직...
	@Select("SELECT COUNT(1) FROM mth_free WHERE free_state !='HIDE' ORDER BY free_idx DESC")
	public String countAll();
	
	//게시글 이전다음 불러오기 (HIDE제외)
	@Select("SELECT * FROM (SELECT free_idx,LAG(free_idx,1) OVER(ORDER BY free_idx) prev_idx, LEAD(free_idx,1) OVER(ORDER BY free_idx) next_idx FROM (SELECT * FROM mth_free WHERE free_state != 'HIDE')) WHERE free_idx = #{free_idx}")
	public FreeBoardData selectFreeBoardData(String free_idx);
	
}
