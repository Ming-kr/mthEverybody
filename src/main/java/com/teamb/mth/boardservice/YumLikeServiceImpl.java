package com.teamb.mth.boardservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamb.mth.mapper.YumLikeSQLMapper;
import com.teamb.mth.mapper.YumSQLMapper;
import com.teamb.mth.vo.YumLikeVO;
import com.teamb.mth.vo.YumVO;

@Service
public class YumLikeServiceImpl implements YumLikeService {
	
	@Autowired
	YumSQLMapper yumSQLMapper;
	
	@Autowired
	YumLikeSQLMapper yumLikeSQLMapper;
	
	@Override
	public String likeYumContent(YumLikeVO requestYumLikeVO) {
		// TODO Auto-generated method stub
		
		if(yumLikeSQLMapper.selectByMemberIdx(requestYumLikeVO)==null) {
			yumLikeSQLMapper.insert(requestYumLikeVO);
			YumVO yumVO = yumSQLMapper.selectByIdx(requestYumLikeVO.getYum_idx());
			if(yumVO != null && yumVO.getYum_state().equals("NORMAL") && yumLikeSQLMapper.countByYumIdx(requestYumLikeVO.getYum_idx()) >= 3) {
				yumVO.setYum_state("BEST");
				yumSQLMapper.updateState(yumVO);
			}
			return "insert";
		} else {
			yumLikeSQLMapper.delete(requestYumLikeVO);
			YumVO yumVO = yumSQLMapper.selectByIdx(requestYumLikeVO.getYum_idx());
			if(yumVO != null && yumVO.getYum_state().equals("BEST") && yumLikeSQLMapper.countByYumIdx(requestYumLikeVO.getYum_idx()) < 3) {
				yumVO.setYum_state("NORMAL");
				yumSQLMapper.updateState(yumVO);
			}
			return "delete";
		}
	}

	@Override
	public int countYumLike(String yum_idx) {
		// TODO Auto-generated method stub
		int yumLikeCount = yumLikeSQLMapper.countByYumIdx(yum_idx);
		return yumLikeCount;
	}
}
