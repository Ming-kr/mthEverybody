package com.teamb.mth.boardservice;

import com.teamb.mth.vo.YumLikeVO;

public interface YumLikeService {
	public String likeYumContent(YumLikeVO requestYumLikeVO);
	public int countYumLike(String yum_idx);
}
