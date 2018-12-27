package com.teamb.mth.boardservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamb.mth.mapper.FreeLikeSQLMapper;
import com.teamb.mth.mapper.FreeSQLMapper;
import com.teamb.mth.vo.FreeLikeVO;
import com.teamb.mth.vo.FreeVO;

@Service
public class FreeLikeServiceImpl implements FreeLikeService {
	
	@Autowired
	FreeSQLMapper freeSQLMapper;
	
	@Autowired
	FreeLikeSQLMapper freeLikeSQLMapper;
	
	
	@Override
	public int countFreeLike(String free_idx) {
		int freeLikeCount = freeLikeSQLMapper.countByFreeIdx(free_idx);
		return freeLikeCount;
	}


	@Override
	public String likeFreeContent(FreeLikeVO requestFreeLikeVO) {
		// TODO Auto-generated method stub
		
		if(freeLikeSQLMapper.selectByMemberIdx(requestFreeLikeVO)==null) {
			freeLikeSQLMapper.insert(requestFreeLikeVO);
			FreeVO freeVO = freeSQLMapper.selectByIdx(requestFreeLikeVO.getFree_idx());
			if(freeVO != null && freeVO.getFree_state().equals("NORMAL") && freeLikeSQLMapper.countByFreeIdx(requestFreeLikeVO.getFree_idx()) >= 3) {
				freeVO.setFree_state("BEST");
				freeSQLMapper.updateState(freeVO);
		}
		
		return "insert";
	}else {
		freeLikeSQLMapper.delete(requestFreeLikeVO);
		FreeVO freeVO = freeSQLMapper.selectByIdx(requestFreeLikeVO.getFree_idx());
		if(freeVO != null && freeVO.getFree_state().equals("BEST") && freeLikeSQLMapper.countByfreeIdx(requestFreeLikeVO.getFree_idx()) < 3) {
			freeVO.setFree_state("NORMAL");
			freeSQLMapper.updateState(freeVO);
		}
		return "delete";
	}
}
}
