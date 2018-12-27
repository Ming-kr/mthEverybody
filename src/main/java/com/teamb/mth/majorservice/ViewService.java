package com.teamb.mth.majorservice;

import com.teamb.mth.data.*;
import com.teamb.mth.util.PagingData;
import com.teamb.mth.vo.*;

public interface ViewService 
{
	//내가 좋아하는 물품 리스트 뷰
	public ItemLikeViewData getItemLikeViewDataList(MemberVO requestMemberVO, PagingData requestPagingData);
	public int getItemLikeViewTotalDataCount(MemberVO requestMemberVO);
	
	//내가 쓴 댓글 리스트 뷰
	public AllReplyViewData getAllReplyViewDataList(MemberVO requestMemberVO, PagingData requestPagingData);
	public int getAllReplyViewTotalDataCount(MemberVO requestMemberVO);
	
	//내가 쓴 게시글 리스트 뷰
	public AllBoardViewData getAllBoardViewDataList(MemberVO requestMemberVO, PagingData requestPagingData);
	public int getAllBoardViewTotalDataCount(MemberVO requestMemberVO);
}
