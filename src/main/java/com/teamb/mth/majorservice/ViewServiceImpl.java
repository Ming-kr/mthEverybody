package com.teamb.mth.majorservice;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teamb.mth.data.*;
import com.teamb.mth.mapper.ViewSQLMapper;
import com.teamb.mth.util.PagingData;
import com.teamb.mth.vo.*;

@Service
public class ViewServiceImpl implements ViewService
{
	@Autowired
	ViewSQLMapper viewSQLMapper;
	
	//작성일 처리하기 위해 현재 요일 가져오기
	Calendar calendar = Calendar.getInstance();
	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONDAY)+1;
	int date = calendar.get(Calendar.DATE);
	String today = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(date);
	
	//좋아하는 물품 리스트 뷰 관련
	@Override
	public ItemLikeViewData getItemLikeViewDataList(MemberVO requestMemberVO, PagingData requestPagingData) 
	{
		ArrayList<ItemLikeViewVO> itemLikeViewList = viewSQLMapper.selectItemLikeView(requestMemberVO, requestPagingData);
		ItemLikeViewData itemLikeViewDataList = new ItemLikeViewData(itemLikeViewList, requestPagingData);
		
		return itemLikeViewDataList;
	}
	@Override
	public int getItemLikeViewTotalDataCount(MemberVO requestMemberVO)  { return viewSQLMapper.getItemLikeViewTotalDataCount(requestMemberVO); }

	//내가 쓴 댓글 뷰 관련
	@Override
	public AllReplyViewData getAllReplyViewDataList(MemberVO requestMemberVO, PagingData requestPagingData) 
	{
		ArrayList<AllReplyViewVO> allReplyViewList = viewSQLMapper.selectAllReplyView(requestMemberVO, requestPagingData);
		
		//표시할 댓글 내용 + 작성일 처리
		for(int Index=0; Index<allReplyViewList.size(); Index++)
		{
			AllReplyViewVO replyViewVO = allReplyViewList.get(Index);
			
			//해당 댓글이 15글자 일때만 수정
			if(replyViewVO.getRe_content().length() > 15) {
				String modifiedContent = replyViewVO.getRe_content().substring(0, 15);
				modifiedContent += "…";
				replyViewVO.setRe_content(modifiedContent);
			}
			
			//작성일 처리
			if(replyViewVO.getWritedate().contains(today)) {
				String writetime = replyViewVO.getWritedate().substring(11, 16);
				replyViewVO.setWritedate(writetime);
			}
			else {
				String writedate = replyViewVO.getWritedate().substring(0, 10);
				replyViewVO.setWritedate(writedate);
			}
		}
		AllReplyViewData allReplyViewDataList = new AllReplyViewData(allReplyViewList, requestPagingData);
		
		return allReplyViewDataList;
	}
	
	@Override
	public int getAllReplyViewTotalDataCount(MemberVO requestMemberVO) { return viewSQLMapper.getAllReplyViewTotalDataCount(requestMemberVO); }
	
	//내가 쓴 게시글 뷰 관련
	@Override
	public AllBoardViewData getAllBoardViewDataList(MemberVO requestMemberVO, PagingData requestPagingData) 
	{
		ArrayList<AllBoardViewVO> allBoardViewList = viewSQLMapper.selectAllBoardView(requestMemberVO, requestPagingData);
		
		for(int Index=0; Index<allBoardViewList.size(); Index++)
		{
			AllBoardViewVO allBoardViewVO = allBoardViewList.get(Index);
			
			//작성일 처리
			if(allBoardViewVO.getWritedate().contains(today)) {
				String writetime = allBoardViewVO.getWritedate().substring(11, 16);
				allBoardViewVO.setWritedate(writetime);
			}
			else {
				String writedate = allBoardViewVO.getWritedate().substring(0, 10);
				allBoardViewVO.setWritedate(writedate);
			}
		}
		AllBoardViewData allBoardViewDataList = new AllBoardViewData(allBoardViewList, requestPagingData);
		
		return allBoardViewDataList;
	}

	@Override
	public int getAllBoardViewTotalDataCount(MemberVO requestMemberVO) { return viewSQLMapper.getAllBoardViewTotalDataCount(requestMemberVO); }
}
