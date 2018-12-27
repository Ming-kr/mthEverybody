package com.teamb.mth.boardservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamb.mth.data.FreeBoardData;
import com.teamb.mth.data.FreeData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.mapper.FreeImgSQLMapper;
import com.teamb.mth.mapper.FreeLikeSQLMapper;
import com.teamb.mth.mapper.FreeReSQLMapper;
import com.teamb.mth.mapper.FreeSQLMapper;
import com.teamb.mth.mapper.MemberSQLMapper;
import com.teamb.mth.vo.FreeImgVO;
import com.teamb.mth.vo.FreeVO;
import com.teamb.mth.vo.MemberVO;

@Service
public class FreeServiceImpl implements FreeService {
	@Autowired
	FreeSQLMapper freeSQLMapper;
	@Autowired
	MemberSQLMapper memberSQLMapper;
	@Autowired
	FreeImgSQLMapper freeImgSQLMapper;

	@Autowired
	FreeLikeSQLMapper freeLikeSQLMapper;

	@Autowired
	FreeReSQLMapper freeReSQLMapper;

	// 게시판 글 로드하기 위해 컨텐츠 테이블에서 정보 가져오는거
	// ArrayList로 로드하는 이유는 여러개 파일을 리스트로 가져올라고
	@Override
	public ArrayList<FreeVO> getFreeList(SearchData requestSearchFreeData) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = df.format(date); // 오늘 날짜 구하기 로직

		ArrayList<FreeVO> freeList = freeSQLMapper.selectAll(requestSearchFreeData);
		for (FreeVO freeVO : freeList) {
			String setDate = freeVO.getFree_writedate().substring(0, 10);
			if (!today.equals(setDate)) {// 오늘 날짜가 아니면
				freeVO.setFree_writedate(setDate.substring(5)); // 월-일로 출력
			} else { // 오늘 날짜면
				freeVO.setFree_writedate(freeVO.getFree_writedate().substring(11, 16)); // 시:분으로 출력
			}
		}
		return freeList;
	}
	
	// 게시판에서 글 '클릭'시.
	@Override
	public FreeData getFreeData(FreeVO requestFreeVO) {
		// TODO Auto-generated method stub
		FreeVO freeVO = freeSQLMapper.selectByIdx(requestFreeVO.getFree_idx());
		MemberVO memberVO = memberSQLMapper.selectByIdx(freeVO.getMember_idx());
		ArrayList<FreeImgVO> freeImgList = freeImgSQLMapper.selectByIdx(freeVO);
		int free_likeCount = freeLikeSQLMapper.countByFreeIdx(freeVO.getFree_idx());
		String free_re_count = freeReSQLMapper.countReByFreeIdx(freeVO.getFree_idx());

		return new FreeData(freeVO, memberVO, freeImgList, free_likeCount, free_re_count);
	}

	// 게시글 검색했을 때
	@Override
	public ArrayList<FreeVO> getSearchFreeList(SearchData requestSearchFreeData) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = df.format(date); // 오늘 날짜 구하기 로직

		ArrayList<FreeVO> freeList = freeSQLMapper.selectSearch(requestSearchFreeData);
		for (FreeVO freeVO : freeList) {
			String setDate = freeVO.getFree_writedate().substring(0, 10);
			if (!today.equals(setDate)) { // 오늘 날짜가 아니면
				freeVO.setFree_writedate(setDate.substring(5)); // 월-일로 출력
			} else { // 오늘 날짜라면
				freeVO.setFree_writedate(freeVO.getFree_writedate().substring(11, 16)); // 시-분 출력
			}
		}
		return freeList;
	}

	// 게시판 글 입력시
	@Override
	public void writeFreeContent(FreeVO requestFreeVO, ArrayList<FreeImgVO> fileImgList) {
		// 키(증가된 free_idx)를 가져오고
		String free_idx = freeSQLMapper.getNextSeq();
		requestFreeVO.setFree_idx(free_idx);

		freeSQLMapper.insert(requestFreeVO);

		// 반복문을 돌면서
		for (FreeImgVO freeImgVO : fileImgList) {

			freeImgVO.setFree_idx(free_idx);
			// DB에 저장
			freeImgSQLMapper.insert(freeImgVO);
		}

	}

	// 글 수정
	@Override
	public void updateFreeContent(FreeVO requestFreeVO) {
		// TODO Auto-generated method stub

		System.out.println(requestFreeVO.getFree_title());
		System.out.println(requestFreeVO.getFree_category());
		System.out.println(requestFreeVO.getFree_content());
		freeSQLMapper.update(requestFreeVO);

	}

	// 게시글 삭제(HIDE로 변경됨 실제삭제x)
	@Override
	public void deleteFreeContent(FreeVO requestFreeVO) {
		requestFreeVO.setFree_state("HIDE");
		freeSQLMapper.updateState(requestFreeVO);

	}

	// 검색한 게시글 총 갯수
	@Override
	public String countSearchFreeList(SearchData requestSearchFreeData) {
		return freeSQLMapper.countSearch(requestSearchFreeData);
	}

	@Override
	public String countFreeList() {
		return freeSQLMapper.countAll();
	}

	// AJAX 댓글 다시 돌려주기 로직시 게시글 얻어오는 메소드
	@Override
	public FreeVO getFreeVO(FreeVO requestFreeVO) {
		return freeSQLMapper.selectByIdx(requestFreeVO.getFree_idx());
	}

	// 게시글 속 이전다음 얻어오기
	@Override
	public ArrayList<FreeData> getFreeBoardData(FreeVO requestFreeVO) {
		// TODO Auto-generated method stub

		ArrayList<FreeData> freeDataList = new ArrayList<FreeData>();

		FreeBoardData freeBoardData = freeSQLMapper.selectFreeBoardData(requestFreeVO.getFree_idx());

		String prev_idx = freeBoardData.getPrev_idx();
		String free_idx = freeBoardData.getFree_idx();
		String next_idx = freeBoardData.getNext_idx();
		String free_re_count = null; // free_re_count라는 변수를 선언 (String 타입이라고)
		if (next_idx != null) {
			FreeVO nextFreeVO = freeSQLMapper.selectByIdx(next_idx);
			nextFreeVO.setFree_title("[다음글] " + nextFreeVO.getFree_title());
			MemberVO nextMemberVO = memberSQLMapper.selectByIdx(nextFreeVO.getMember_idx());

			// 추천수 로직
			int nextFree_likeCount = freeLikeSQLMapper.countByFreeIdx(next_idx);
			// 추천수 로직 끝
			free_re_count = freeReSQLMapper.countReByFreeIdx(next_idx); // =nextFreeVO.getFree_idx()
			// String free_re_count =
			// freeReSQLMapper.countReByFreeIdx(nextFreeVO.getFree_idx()); 저 위에 선언 안했을 땐
			// 이런식으로
			freeDataList.add(new FreeData(nextFreeVO, nextMemberVO, null, nextFree_likeCount, free_re_count));
		}

		FreeVO freeFreeVO = freeSQLMapper.selectByIdx(free_idx);
		freeFreeVO.setFree_title("[현재글] " + freeFreeVO.getFree_title());
		MemberVO freeMemberVO = memberSQLMapper.selectByIdx(freeFreeVO.getMember_idx());

		// 추천수 로직
		int free_likeCount = freeLikeSQLMapper.countByFreeIdx(free_idx);
		// 추천수 로직 끝
		free_re_count = freeReSQLMapper.countReByFreeIdx(free_idx);
		freeDataList.add(new FreeData(freeFreeVO,freeMemberVO, null, free_likeCount,free_re_count));

		if (prev_idx != null) {
			FreeVO prevFreeVO = freeSQLMapper.selectByIdx(prev_idx);
			prevFreeVO.setFree_title("[이전글] " + prevFreeVO.getFree_title());
			MemberVO prevMemberVO = memberSQLMapper.selectByIdx(prevFreeVO.getMember_idx());

			// 추천수 로직
			int prevFree_likeCount = freeLikeSQLMapper.countByFreeIdx(prev_idx);
			// 추천수 로직 끝
			free_re_count = freeReSQLMapper.countReByFreeIdx(prev_idx);
			freeDataList.add(new FreeData(prevFreeVO, prevMemberVO, null, prevFree_likeCount,free_re_count));
		}

		return freeDataList;
	}

	// 게시글 조회수 업데이트(클릭시에만)
	@Override
	public void updateCountPlus(FreeVO requestFreeVO) {
		// TODO Auto-generated method stub
		FreeVO freeVO = freeSQLMapper.selectByIdx(requestFreeVO.getFree_idx());
		freeVO.setFree_count(String.valueOf(Integer.parseInt(freeVO.getFree_count()) + 1));// free_count하나 올려주고 freeVO
																							// 넣어주고
		freeSQLMapper.updateCount(freeVO);// 업데이트 시키기
	}

}
