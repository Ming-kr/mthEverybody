package com.teamb.mth.boardservice;

import java.util.ArrayList;

import com.teamb.mth.data.SearchData;
import com.teamb.mth.data.YumData;
import com.teamb.mth.vo.YumVO;

public interface YumService {
	public ArrayList<YumVO> getYumList(SearchData requestSearchData);

	public ArrayList<YumVO> getSearchYumList(SearchData requestSearchData);

	public String countYumList();

	public String countSearchYumList(SearchData requestSearchData);

	public YumVO getYumVO(YumVO requestYumVO);

	public void writeYumContent(YumVO requestYumVO);

	public void updateYumContent(YumVO requestYumVO);

	public void deleteYumContent(YumVO requestYumVO);

	public ArrayList<YumVO> getNoticeYumList();

	public ArrayList<YumVO> getBestYumList();

	// 안쓰는 로직...
//	public ArrayList<YumVO> getTopCountYumList(); // 조회수순으로 보드 가져오기

	// 게시글 '클릭' 시 로드할 때 필요한 메소드
	public YumData getYumData(YumVO requestYumVO);

	// 게시글 조회수 증가
	public void updateCountPlus(YumVO requestYumVO);

	// 게시글 속 이전다음 얻어오기
	public ArrayList<YumData> getYumBoardData(YumVO requestYumVO);
}
