package com.teamb.mth.majorservice;

import java.util.ArrayList;
import com.teamb.mth.data.ItemReData;
import com.teamb.mth.util.PagingData;
import com.teamb.mth.vo.*;

public interface ItemReService 
{
	//item_idx에 따라 댓글 리스트 및 갯수 가져오기
	public ArrayList<ItemReData> getItemReListByItemIdx(String item_idx,  PagingData requestPagingData);
	public int getItemReListTotalDataCount(String item_idx);
	
	//댓글 작성,수정,삭제
	public ItemReVO getItemReVO(String item_re_idx);
	public void writeItemReContent(ItemReVO requestItemReVO);
	public void updateItemReContent(ItemReVO requestItemReVO);
	public void deleteItemRe(String item_re_idx);
}
