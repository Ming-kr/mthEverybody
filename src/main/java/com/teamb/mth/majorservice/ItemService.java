package com.teamb.mth.majorservice;

import java.util.ArrayList;

import com.teamb.mth.data.ItemData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.vo.ItemVO;

public interface ItemService {

	public ArrayList<ItemVO> getItemByBrandAndItemType(String brand_idx, String item_type_idx);

	public ArrayList<ItemVO> getAll();

	public ArrayList<ItemData> getAllItemDataList();

	public void registerItemVO(ItemVO requestItemVO);

	public ItemVO getItemVO(String item_idx);

	public void updateItemVO(ItemVO requestItemVO);

	public void extinctItemVO(String item_idx);

	public void normalItemVO(String item_idx);

	public void deleteItemVO(String item_idx);

	// 관리자 리스트용

	public ArrayList<ItemData> getItemDataList(ArrayList<ItemVO> itemList);

	public ArrayList<ItemData> getSearchItemDataList(ArrayList<ItemVO> itemList, String searchSelect,
			String searchWord);

	// 모든 아이템
	public ArrayList<ItemVO> getItemList();

	public String countList();

	public ArrayList<ItemVO> getSearchItemList(SearchData requestSearchData);

	public String countSearchList(SearchData requestSearchData);

	// 정상
	public ArrayList<ItemVO> getItemNormalList();

	public String countNormalList();

	public String countSearchNormalList(SearchData requestSearchData);

	// 단종
	public ArrayList<ItemVO> getItemExtinctionList();

	public String countExtinctionList();

	public String countSearchExtinctionList(SearchData requestSearchData);

}
