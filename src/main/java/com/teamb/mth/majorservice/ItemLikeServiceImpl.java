package com.teamb.mth.majorservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamb.mth.mapper.ItemLikeSQLMapper;
import com.teamb.mth.vo.ItemLikeVO;

@Service
public class ItemLikeServiceImpl implements ItemLikeService {

	
	@Autowired
	ItemLikeSQLMapper itemLikeSQLMapper;

	@Override
	public String likeItem(ItemLikeVO requestItemLikeVO) {
		// TODO Auto-generated method stub
		if(itemLikeSQLMapper.selectByItemIdxAndMemberIdx(requestItemLikeVO)==null) {
			itemLikeSQLMapper.insert(requestItemLikeVO);
			return "insert";
		} else {
			itemLikeSQLMapper.delete(requestItemLikeVO);
			return "delete";
		}
	}

	@Override
	public int countItemLike(String item_idx) {
		// TODO Auto-generated method stub
		return itemLikeSQLMapper.countByItemIdx(item_idx);
	}

	@Override
	public ArrayList<ItemLikeVO> likeMemberList(String item_idx) {
		// TODO Auto-generated method stub
		return itemLikeSQLMapper.selectByItemIdx(item_idx);
	}

}
