package com.teamb.mth.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.teamb.mth.data.SearchData;
import com.teamb.mth.data.YumBoardData;
import com.teamb.mth.vo.YumVO;

public interface YumSQLMapper {

	@Select("SELECT * FROM (SELECT ROWNUM as rnum, y.* FROM (SELECT * FROM mth_yum WHERE yum_state != 'HIDE' ORDER BY yum_idx DESC) y) WHERE rnum between ${startNum} and ${endNum}")
	public ArrayList<YumVO> selectAll(SearchData requestSearchYumData);

	// 게시글 개수 찾기 로직...
	@Select("SELECT COUNT(1) FROM mth_yum WHERE yum_state != 'HIDE'")
	public String countAll();

	// 검색 로직...
	@Select("SELECT * FROM (SELECT ROWNUM as rnum, y.* FROM (SELECT * FROM mth_yum WHERE yum_state != 'HIDE' AND ${searchSelect} LIKE '%'||#{searchWord}||'%' ORDER BY yum_idx DESC) y) WHERE rnum between ${startNum} and ${endNum}")
	public ArrayList<YumVO> selectSearch(SearchData requestSearchYumData);

	// 검색된 게시글 개수 찾기 로직...
	@Select("SELECT COUNT(1) FROM mth_yum WHERE yum_state != 'HIDE' AND ${searchSelect} LIKE '%'||#{searchWord}||'%'")
	public String countSearch(SearchData requestSearchYumData);

	@Select("SELECT * FROM mth_yum WHERE yum_idx = #{yum_idx}")
	public YumVO selectByIdx(String yum_idx);

	// @Insert("INSERT INTO mth_yum VALUES(seq_yum_idx.nextval, #{member_idx},
	// #{yum_category}, #{yum_title}, #{yum_content}, 'NORMAL', 0, SYSDATE)")
	@Insert("INSERT INTO mth_yum VALUES(seq_yum_idx.nextval,#{member_idx},#{yum_category},#{yum_title},#{yum_content},'NORMAL',0,SYSDATE)")
	public void insert(YumVO requestYumVO);

	@Update("UPDATE mth_yum SET yum_title = #{yum_title}, yum_content = #{yum_content} where yum_idx = #{yum_idx}")
	public void update(YumVO requestYumVO);

	// 조회수 증가...
	@Update("UPDATE mth_yum SET yum_count = #{yum_count} where yum_idx = #{yum_idx}")
	public void updateCount(YumVO requestYumVO);

	// 상태 변화...
	@Update("UPDATE mth_yum SET yum_state = #{yum_state} where yum_idx = #{yum_idx}")
	public void updateState(YumVO requestYumVO);

	// 공지 검색
	@Select("SELECT * FROM (SELECT * FROM mth_yum WHERE yum_state != 'HIDE' AND yum_category = '공지'  ORDER BY yum_idx DESC) WHERE ROWNUM <= 5")
	public ArrayList<YumVO> selectNotice();
	//

	// 베스트 검색
	@Select("SELECT * FROM (SELECT * FROM mth_yum WHERE yum_state = 'BEST'  ORDER BY yum_idx DESC) WHERE ROWNUM <= 5")
	public ArrayList<YumVO> selectBest();
	//

	// 안쓰는 부분...
	// 조회수 순으로 검색 로직...
	@Select("SELECT * FROM mth_yum WHERE yum_state != 'HIDE' ORDER BY yum_count DESC")
	public ArrayList<YumVO> selectTopCount();

	// 게시글 이전다음 불러오기
	@Select("SELECT * FROM (SELECT yum_idx,LAG(yum_idx,1) OVER(ORDER BY yum_idx) prev_idx, LEAD(yum_idx,1) OVER(ORDER BY yum_idx) next_idx FROM (SELECT * FROM mth_yum WHERE yum_state != 'HIDE')) WHERE yum_idx = #{yum_idx}")
	public YumBoardData selectYumBoardData(String yum_idx);
}
