package com.teamb.mth.boardservice;

import java.util.ArrayList;

import com.teamb.mth.data.FreeData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.vo.FreeImgVO;
import com.teamb.mth.vo.FreeVO;

public interface FreeService {
	
	//게시글 리스트로 불러오기
	public ArrayList<FreeVO> getFreeList(SearchData requestSearchFreeData);
	
	//게시판 글 로딩할 때 필요한 메소드
	public ArrayList<FreeVO> getSearchFreeList(SearchData requestSearchFreeData);
	
	//게시글 '클릭' 시 로드할 때 필요한 메소드
	public FreeData getFreeData(FreeVO requestFreeVO);
	
	//게시글 조회수 증가
	public void updateCountPlus(FreeVO requestFreeVO);
	
	//게시글 쓸 때
	public void writeFreeContent(FreeVO requestFreeVO,ArrayList<FreeImgVO>freeImgList);
	
	//게시글 수정
	public void updateFreeContent(FreeVO requestFreeVO);
	
	//게시글 삭제(HIDE로 변경됨 실제삭제x)
	public void deleteFreeContent(FreeVO requestFreeVO);
	
	//검색한 게시글 총 갯수
	public String countSearchFreeList(SearchData requestSearchFreeData);

	//총 게시글 개수
	public String countFreeList();

	//AJAX 댓글 다시 돌려주기 로직시 게시글 얻어오는 메소드
	public FreeVO getFreeVO(FreeVO requestFreeVO);

	//게시글 속 이전다음 얻어오기
	public ArrayList<FreeData> getFreeBoardData(FreeVO requestFreeVO);
	
	

	

}





