package com.teamb.mth.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.teamb.mth.util.PagingData;
import com.teamb.mth.vo.AllBoardViewVO;
import com.teamb.mth.vo.AllReplyViewVO;
import com.teamb.mth.vo.ItemLikeViewVO;
import com.teamb.mth.vo.MemberVO;

public interface ViewSQLMapper 
{
	@Select("SELECT * FROM(SELECT ROWNUM RNUM, FIR.* FROM (SELECT *  FROM VIEW_ITEM_LIKE_MYINFO WHERE MEMBER_IDX=#{requestMemberVO.member_idx} ORDER BY ITEM_LIKE_REGDATE DESC)FIR)SEC WHERE SEC.RNUM BETWEEN #{requestPagingData.startNum} AND #{requestPagingData.endNum}")
	public ArrayList<ItemLikeViewVO> selectItemLikeView(@Param("requestMemberVO")MemberVO requestMemberVO, @Param("requestPagingData")PagingData requestPagingData);
	
	@Select("SELECT COUNT(*) AS totalDataCount FROM VIEW_ITEM_LIKE_MYINFO WHERE MEMBER_IDX=#{member_idx}")
	public int getItemLikeViewTotalDataCount(MemberVO requestMemberVO);
	
	@Select("SELECT * FROM(SELECT ROWNUM RNUM, FIR.* FROM (SELECT *  FROM VIEW_ALL_BOARD_MYINFO WHERE MEMBER_IDX=#{requestMemberVO.member_idx} ORDER BY WRITEDATE DESC)FIR)SEC WHERE SEC.RNUM BETWEEN #{requestPagingData.startNum} AND #{requestPagingData.endNum}")
	public ArrayList<AllBoardViewVO> selectAllBoardView(@Param("requestMemberVO")MemberVO requestMemberVO, @Param("requestPagingData")PagingData requestPagingData);
	
	@Select("SELECT COUNT(*) AS totalDataCount FROM VIEW_ALL_BOARD_MYINFO WHERE MEMBER_IDX=#{member_idx}")
	public int getAllBoardViewTotalDataCount(MemberVO requestMemberVO);
	
	@Select("SELECT * FROM(SELECT ROWNUM RNUM, FIR.* FROM (SELECT *  FROM VIEW_ALL_REPLY_MYINFO WHERE MEMBER_IDX=#{requestMemberVO.member_idx} ORDER BY WRITEDATE DESC)FIR)SEC WHERE SEC.RNUM BETWEEN #{requestPagingData.startNum} AND #{requestPagingData.endNum}")
	public ArrayList<AllReplyViewVO> selectAllReplyView(@Param("requestMemberVO")MemberVO requestMemberVO, @Param("requestPagingData")PagingData requestPagingData);
	
	@Select("SELECT COUNT(*) AS totalDataCount FROM VIEW_ALL_REPLY_MYINFO WHERE MEMBER_IDX=#{member_idx}")
	public int getAllReplyViewTotalDataCount(MemberVO requestMemberVO);
	
	
}


