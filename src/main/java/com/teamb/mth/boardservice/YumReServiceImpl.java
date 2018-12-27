package com.teamb.mth.boardservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamb.mth.mapper.YumReSQLMapper;
import com.teamb.mth.vo.YumReVO;

@Service
public class YumReServiceImpl implements YumReService {
	@Autowired
	YumReSQLMapper yumReSQLMapper;

	@Override
	public String writeYumReContent(YumReVO requestYumReVO) {
		// TODO Auto-generated method stub
		String yum_re_idx = yumReSQLMapper.getNextSeq();
		requestYumReVO.setYum_re_idx(yum_re_idx);
		yumReSQLMapper.insert(requestYumReVO);
		return yum_re_idx;
	}

	@Override
	public ArrayList<YumReVO> getYumReListByYumIdx(YumReVO requestYumReVO) {
		return yumReSQLMapper.selectByYumIdx(requestYumReVO);
	}

	@Override
	public YumReVO getYumReVO(YumReVO requestYumReVO) {
		// TODO Auto-generated method stub
		return yumReSQLMapper.selectByIdx(requestYumReVO);
	}

	@Override
	public void deleteYumReContent(YumReVO requestYumReVO) {
		// TODO Auto-generated method stub
		yumReSQLMapper.updateReState(requestYumReVO.getYum_re_idx());
	}

	// 댓글 갯수 구하기
	@Override
	public String countYumReply(String yum_idx) {
		// TODO Auto-generated method stub
		return yumReSQLMapper.countReByYumIdx(yum_idx);
	}
}
