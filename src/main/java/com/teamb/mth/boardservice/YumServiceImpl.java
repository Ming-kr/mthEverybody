package com.teamb.mth.boardservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamb.mth.data.SearchData;
import com.teamb.mth.data.YumBoardData;
import com.teamb.mth.data.YumData;
import com.teamb.mth.mapper.MemberSQLMapper;
import com.teamb.mth.mapper.YumLikeSQLMapper;
import com.teamb.mth.mapper.YumReSQLMapper;
import com.teamb.mth.mapper.YumSQLMapper;
import com.teamb.mth.vo.MemberVO;
import com.teamb.mth.vo.YumVO;

@Service
public class YumServiceImpl implements YumService {

	@Autowired
	YumSQLMapper yumSQLMapper;
	
	@Autowired
	MemberSQLMapper memberSQLMapper;
	
	@Autowired
	YumLikeSQLMapper yumLikeSQLMapper;
	
	@Autowired
	YumReSQLMapper yumReSQLMapper;

	@Override
	public ArrayList<YumVO> getYumList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = df.format(date); // 오늘 날짜 구하기 로직

		ArrayList<YumVO> yumList = yumSQLMapper.selectAll(requestSearchData);
		for (YumVO yumVO : yumList) {
			String setDate = yumVO.getYum_writedate().substring(0, 10);
			if (!today.equals(setDate)) { // 오늘날짜가 아니면
				yumVO.setYum_writedate(setDate.substring(5)); // 월-일로 출력
			} else { // 오늘날짜이면
				yumVO.setYum_writedate(yumVO.getYum_writedate().substring(11, 16)); // 시:분으로 출력
			}
		}

		return yumList;
	}

	@Override
	public void writeYumContent(YumVO requestYumVO) {
		// TODO Auto-generated method stub
		yumSQLMapper.insert(requestYumVO);
	}

	@Override
	public YumVO getYumVO(YumVO requestYumVO) {
		// TODO Auto-generated method stub
		YumVO yumVO = yumSQLMapper.selectByIdx(requestYumVO.getYum_idx());
		// 조회수 증가...
		yumVO.setYum_count(String.valueOf(Integer.parseInt(yumVO.getYum_count()) + 1));
		yumSQLMapper.updateCount(yumVO);
		return yumVO;
	}

	@Override
	public void updateYumContent(YumVO requestYumVO) {
		// TODO Auto-generated method stub
		yumSQLMapper.update(requestYumVO);
	}

	@Override
	public void deleteYumContent(YumVO requestYumVO) {
		// TODO Auto-generated method stub
		requestYumVO.setYum_state("HIDE");
		yumSQLMapper.updateState(requestYumVO);
	}

	@Override
	public ArrayList<YumVO> getSearchYumList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = df.format(date); // 오늘 날짜 구하기 로직

		ArrayList<YumVO> yumList = yumSQLMapper.selectSearch(requestSearchData);
		for (YumVO yumVO : yumList) {
			String setDate = yumVO.getYum_writedate().substring(0, 10);
			if (!today.equals(setDate)) { // 오늘날짜가 아니면
				yumVO.setYum_writedate(setDate.substring(5)); // 월-일로 출력
			} else { // 오늘날짜이면
				yumVO.setYum_writedate(yumVO.getYum_writedate().substring(11, 16)); // 시:분으로 출력
			}
		}

		return yumList;
	}

	@Override
	public String countYumList() {
		// TODO Auto-generated method stub
		return yumSQLMapper.countAll();
	}

	@Override
	public String countSearchYumList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return yumSQLMapper.countSearch(requestSearchData);
	}

	@Override
	public ArrayList<YumVO> getNoticeYumList() {
		// TODO Auto-generated method stub
		return yumSQLMapper.selectNotice();
	}

	@Override
	public ArrayList<YumVO> getBestYumList() {
		// TODO Auto-generated method stub
		return yumSQLMapper.selectBest();
	}

	// 조회수 순으로 가져오기 로직... 안 씀...
	/*@Override
	public ArrayList<YumVO> getTopCountYumList() {
		// TODO Auto-generated method stub
		ArrayList<YumVO> yumList = yumSQLMapper.selectTopCount();
		ArrayList<YumVO> yumTopCountList = new ArrayList<YumVO>();

		int cnt = 0;
		for (YumVO yumVO : yumList) {
			yumTopCountList.add(yumVO);
			cnt++;
			if (cnt == 5)
				break;
		}

		return yumTopCountList;
	}*/
	// 조회수 순으로 가져오기 로직 끝... 안 씀...

	// 게시글 '클릭' 시 로드할 때 필요한 메소드
	@Override
	public YumData getYumData(YumVO requestYumVO) {
		// TODO Auto-generated method stub
		YumVO yumVO = yumSQLMapper.selectByIdx(requestYumVO.getYum_idx());
		MemberVO memberVO = memberSQLMapper.selectByIdx(yumVO.getMember_idx());
		int yum_likeCount = yumLikeSQLMapper.countByYumIdx(requestYumVO.getYum_idx());

		return new YumData(yumVO, memberVO, yum_likeCount);
	}

	// 게시글 조회수 업데이트(클릭시에만)
	@Override
	public void updateCountPlus(YumVO requestYumVO) {
		// TODO Auto-generated method stub
		YumVO yumVO = yumSQLMapper.selectByIdx(requestYumVO.getYum_idx());
		yumVO.setYum_count(String.valueOf(Integer.parseInt(yumVO.getYum_count()) + 1));// yum_count하나 올려주고 yumVO 넣어주고
		yumSQLMapper.updateCount(yumVO);// 업데이트 시키기

	}

	// 게시글 속 이전다음 얻어오기
	@Override
	public ArrayList<YumData> getYumBoardData(YumVO requestYumVO) {
		// TODO Auto-generated method stub
		ArrayList<YumData> yumDataList = new ArrayList<YumData>();

		YumBoardData yumBoardData = yumSQLMapper.selectYumBoardData(requestYumVO.getYum_idx());

		String prev_idx = yumBoardData.getPrev_idx();
		String yum_idx = yumBoardData.getYum_idx();
		String next_idx = yumBoardData.getNext_idx();

		String yum_re_count = null; // yum_re_count라는 변수를 선언 (String 타입이라고)
		int yum_likeCount = 0;

		if (next_idx != null) {
			YumVO nextYumVO = yumSQLMapper.selectByIdx(next_idx);
			nextYumVO.setYum_title("[다음글] " + nextYumVO.getYum_title());
			MemberVO nextMemberVO = memberSQLMapper.selectByIdx(nextYumVO.getMember_idx());
			yum_re_count = yumReSQLMapper.countReByYumIdx(next_idx); // =nextYumVO.getYum_idx()
			yum_likeCount = yumLikeSQLMapper.countByYumIdx(next_idx);
			// String yum_re_count = yumReSQLMapper.countReByYumIdx(nextYumVO.getYum_idx());
			// 저 위에 선언 안했을 땐 이런식으로
			yumDataList.add(new YumData(nextYumVO, nextMemberVO, yum_likeCount, yum_re_count));
		}

		YumVO yumYumVO = yumSQLMapper.selectByIdx(yum_idx);
		yumYumVO.setYum_title("[현재글] " + yumYumVO.getYum_title());
		MemberVO yumMemberVO = memberSQLMapper.selectByIdx(yumYumVO.getMember_idx());
		yum_re_count = yumReSQLMapper.countReByYumIdx(yum_idx);
		yum_likeCount = yumLikeSQLMapper.countByYumIdx(yum_idx);
		yumDataList.add(new YumData(yumYumVO, yumMemberVO, yum_likeCount, yum_re_count));

		if (prev_idx != null) {
			YumVO prevYumVO = yumSQLMapper.selectByIdx(prev_idx);
			prevYumVO.setYum_title("[이전글] " + prevYumVO.getYum_title());
			MemberVO prevMemberVO = memberSQLMapper.selectByIdx(prevYumVO.getMember_idx());
			yum_re_count = yumReSQLMapper.countReByYumIdx(prev_idx);
			yum_likeCount = yumLikeSQLMapper.countByYumIdx(prev_idx);
			yumDataList.add(new YumData(prevYumVO, prevMemberVO, yum_likeCount, yum_re_count));
		}

		return yumDataList;
	}

}
