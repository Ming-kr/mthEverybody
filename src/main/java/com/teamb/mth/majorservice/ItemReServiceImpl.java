package com.teamb.mth.majorservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teamb.mth.mapper.ItemReSQLMapper;
import com.teamb.mth.mapper.MemberSQLMapper;
import com.teamb.mth.util.PagingData;
import com.teamb.mth.vo.*;
import com.teamb.mth.data.*;

@Service
public class ItemReServiceImpl implements ItemReService 
{
	@Autowired
	ItemReSQLMapper itemReSQLMapper;
	
	@Autowired
	MemberSQLMapper memberSQLMapper;

	//댓글 리스트 가져오기(삭제회원Nick = 알수없음)
	@Override
	public ArrayList<ItemReData> getItemReListByItemIdx(String item_idx, PagingData requestPagingData) 
	{
		ArrayList<ItemReData> itemReDataList = new ArrayList<>();
		
		ArrayList<ItemReVO> itemReList = itemReSQLMapper.selectByItemIdx(item_idx,requestPagingData);
		
		for(ItemReVO itemReVO : itemReList)
		{
			MemberVO memberVO = memberSQLMapper.selectByIdx(itemReVO.getMember_idx());
			if(memberVO.getMember_code().equals("DEL_MEMBER")) { memberVO.setMember_nick("알수없음"); }

			ItemReData itemReData = new ItemReData(itemReVO, memberVO);
			itemReDataList.add(itemReData);
		}
		
		return itemReDataList;
	}

	//댓글 갯수 가져오기
	@Override
	public int getItemReListTotalDataCount(String item_idx) {
		return itemReSQLMapper.getItemReListTotalDataCount(item_idx);
	}

	//댓글 가져오기
	@Override
	public ItemReVO getItemReVO(String item_re_idx) 
	{
		return itemReSQLMapper.selectByIdx(item_re_idx);
	}
	
	//댓글 작성
	@Override
	public void writeItemReContent(ItemReVO requestItemReVO) 
	{
		itemReSQLMapper.insert(requestItemReVO);
	}

	//댓글 수정
	@Override
	public void updateItemReContent(ItemReVO requestItemReVO) 
	{
		itemReSQLMapper.updateItemReContent(requestItemReVO);
	}
	//댓글 삭제
	@Override
	public void deleteItemRe(String item_re_idx) 
	{
		itemReSQLMapper.deleteItemRe(item_re_idx);
	}
}
