package com.teamb.mth.boardservice;

import java.util.ArrayList;

import com.teamb.mth.vo.FreeReVO;

public interface FreeReService {
	// 게시글 읽기 할때 댓글 리스트 얻어오기 idx으로
	public ArrayList<FreeReVO> getFreeReListByFreeIdx(FreeReVO requestFreeReVO);

	// 댓글 작성시 사용됨
	public String writeFreeReContent(FreeReVO requestFreeReVO);

	// 댓글 다시 돌려받을 때 게시글 리플 받기
	public FreeReVO getFreeReVO(FreeReVO requestFreeReVO);

	// 댓글 삭제 (HIDE로 변경됨 실제삭제 x)
	public void deleteFreeReContent(FreeReVO requestFreeReVO);

	// 댓글 갯수 구하기
	public String countFreeReply(String free_idx);
}
