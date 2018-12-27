package com.teamb.mth.majorservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teamb.mth.data.*;
import com.teamb.mth.mapper.*;
import com.teamb.mth.util.PagingData;
import com.teamb.mth.vo.*;

@Service
public class ConvenReServiceImpl implements ConvenReService {
	
	@Autowired
	ConvenReSQLMapper convenReSQLMapper;
	
	@Autowired
	MemberSQLMapper	memberSQLMapper;

	//편의점 댓글 불러오기(탈퇴회원Nick = 알수없음)
	@Override
	public ArrayList<ConvenReData> getConvenReListByConvenIdx(String conven_idx, PagingData requestPagingData) 
	{
		ArrayList<ConvenReData> convenReDataList = new ArrayList<>();
		
		ArrayList<ConvenReVO> convenReList = convenReSQLMapper.selectByConvenIdx(conven_idx, requestPagingData);
		
		for(ConvenReVO convenReVO : convenReList)
		{
			MemberVO memberVO = memberSQLMapper.selectByIdx(convenReVO.getMember_idx());
			if(memberVO.getMember_code().equals("DEL_MEMBER")) { memberVO.setMember_nick("알수없음"); }
			
			ConvenReData convenReData = new ConvenReData(convenReVO, memberVO);
			convenReDataList.add(convenReData);
		}
		
		return convenReDataList;
	}
	
	//편의점 댓글 갯수 가져오기
	@Override
	public int getConvenReListTotalCount(String conven_idx) 
	{
		return convenReSQLMapper.getConvenReListTotalCount(conven_idx);
	}

	//댓글 idx로 댓글VO가져오기
	@Override
	public ConvenReVO getConvenReVO(String conven_re_idx) 
	{
		return convenReSQLMapper.selectByIdx(conven_re_idx);
	}
	
	//댓글 쓰기
	@Override
	public void writeConvenReContent(ConvenReVO requestConvenReVO) 
	{
		convenReSQLMapper.insert(requestConvenReVO);
	}

	//댓글 수정
	@Override
	public void updateConvenReContent(ConvenReVO requestConvenReVO) 
	{
		convenReSQLMapper.updateConvenReContent(requestConvenReVO);
	}

	//댓글 삭제
	@Override
	public void deleteConvenRe(String conven_re_idx) 
	{
		convenReSQLMapper.deleteConvenRe(conven_re_idx);
	}

}
