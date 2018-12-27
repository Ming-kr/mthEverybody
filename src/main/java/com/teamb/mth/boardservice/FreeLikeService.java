package com.teamb.mth.boardservice;

import com.teamb.mth.vo.FreeLikeVO;

public interface FreeLikeService {

	public String likeFreeContent(FreeLikeVO freeLikeVO);
	int countFreeLike(String free_idx);

}
