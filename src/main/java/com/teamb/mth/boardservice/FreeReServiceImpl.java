package com.teamb.mth.boardservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamb.mth.mapper.FreeReSQLMapper;
import com.teamb.mth.vo.FreeReVO;

@Service
public class FreeReServiceImpl implements FreeReService {
	@Autowired
	FreeReSQLMapper freeReSQLMapper;

	// 댓글리스트 얻어올 때 사용
	@Override
	public ArrayList<FreeReVO> getFreeReListByFreeIdx(FreeReVO requestFreeReVO) {
		return freeReSQLMapper.selectByFreeIdx(requestFreeReVO);
	}

	// 댓글 작성(writeFreeReContent)시 사용 AJAX쪽 리플 작성시 idx값 getNextSeq 미리생성.
	// insert()넣었다가 return빼내기 free_re_idx값.
	@Override
	public String writeFreeReContent(FreeReVO requestFreeReVO) {
		String free_re_idx = freeReSQLMapper.getNextSeq();
		requestFreeReVO.setFree_re_idx(free_re_idx);
		freeReSQLMapper.insert(requestFreeReVO);
		return free_re_idx;

	}

	// 댓글 다시 돌려받을 때 게시글 리플 받기
	@Override
	public FreeReVO getFreeReVO(FreeReVO requestFreeReVO) {
		return freeReSQLMapper.selectByIdx(requestFreeReVO);

	}

	// 댓글 삭제 state를 HIDE로 변경 후 업데이트
	@Override
	public void deleteFreeReContent(FreeReVO requestFreeReVO) {
		// TODO Auto-generated method stub

		requestFreeReVO.setFree_re_state("HIDE");

		freeReSQLMapper.updateReState(requestFreeReVO);

	}

	// 댓글갯수 구하기
	@Override
	public String countFreeReply(String free_idx) {
		return freeReSQLMapper.countReByFreeIdx(free_idx);
	}

}
