package com.teamb.mth.boardservice;

import java.util.ArrayList;

import com.teamb.mth.vo.YumReVO;

public interface YumReService {
	public String writeYumReContent(YumReVO requestYumReVO);

	public ArrayList<YumReVO> getYumReListByYumIdx(YumReVO requestYumReVO);

	public YumReVO getYumReVO(YumReVO requestYumReVO);

	public void deleteYumReContent(YumReVO requestYumReVO);

	// 댓글 갯수 구하기
	public String countYumReply(String yum_idx);
}
