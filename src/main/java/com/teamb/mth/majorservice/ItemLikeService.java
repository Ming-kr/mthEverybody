package com.teamb.mth.majorservice;

import java.util.ArrayList;

import com.teamb.mth.vo.ItemLikeVO;

public interface ItemLikeService {
	public String likeItem(ItemLikeVO requestItemLikeVO);
	public ArrayList<ItemLikeVO> likeMemberList(String item_idx);
	public int countItemLike(String item_idx);
}
