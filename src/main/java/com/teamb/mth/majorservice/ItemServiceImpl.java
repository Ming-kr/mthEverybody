package com.teamb.mth.majorservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamb.mth.data.ItemData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.mapper.BrandSQLMapper;
import com.teamb.mth.mapper.ItemLikeSQLMapper;
import com.teamb.mth.mapper.ItemSQLMapper;
import com.teamb.mth.mapper.ItemTypeSQLMapper;
import com.teamb.mth.vo.BrandVO;
import com.teamb.mth.vo.ItemTypeVO;
import com.teamb.mth.vo.ItemVO;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	ItemSQLMapper itemSQLMapper;
	
	@Autowired
	BrandSQLMapper brandSQLMapper;
	
	@Autowired
	ItemTypeSQLMapper itemTypeSQLMapper;
	
	@Autowired
	ItemLikeSQLMapper itemLikeSQLMapper;
	
	@Override
	public ArrayList<ItemVO> getItemByBrandAndItemType(String brand_idx, String item_type_idx) {
		// TODO Auto-generated method stub
		
		//brand_idx,item_type_idx으로 만들어진 itemVO 객체 생성(두 string값과 일치하는 itemVO 찾으려고 간단히 만듬)
		ItemVO itemVO = new ItemVO(brand_idx,item_type_idx);

		//brand_idx,item_type_idx와 일치하는 itemVO 리스트 불러오기
		return itemSQLMapper.selectByBrandIdxAndItemTypeIdx(itemVO);
	}

	@Override
	public ArrayList<ItemVO> getAll() {
		// TODO Auto-generated method stub
		//모든 판매물품 리스트 불러오기
		return itemSQLMapper.selectAll();
	}
	
	@Override
	public ArrayList<ItemData> getAllItemDataList() {
		// TODO Auto-generated method stub
		
		ArrayList<ItemVO> itemList = itemSQLMapper.selectAll();
		ArrayList<ItemData> itemDataList = new ArrayList<ItemData>();
		//convenVO에 해당하는 brandVO, stateVO 불러와서 convenDataList에 저장
		for (ItemVO itemVO : itemList) {
			BrandVO brandVO = brandSQLMapper.selectByIdx(itemVO.getBrand_idx());
			ItemTypeVO itemTypeVO = itemTypeSQLMapper.selectByIdx(itemVO.getItem_type_idx());
			int item_likeCount = itemLikeSQLMapper.countByItemIdx(itemVO.getItem_idx());
			itemDataList.add(new ItemData(itemVO, brandVO, itemTypeVO, item_likeCount));
		}
		return itemDataList;
	}

	@Override
	public void registerItemVO(ItemVO requestItemVO) {
		// TODO Auto-generated method stub
		//새로운 판매 물품 등록
		itemSQLMapper.insert(requestItemVO);
	}

	@Override
	public ItemVO getItemVO(String item_idx) {
		// TODO Auto-generated method stub
		return itemSQLMapper.selectByIdx(item_idx);
	}

	@Override
	public void updateItemVO(ItemVO requestItemVO) {
		// TODO Auto-generated method stub
		itemSQLMapper.update(requestItemVO);
	}

	@Override
	public void extinctItemVO(String item_idx) {
		// TODO Auto-generated method stub
		itemSQLMapper.updateExtinct(item_idx);
	}

	@Override
	public void normalItemVO(String item_idx) {
		// TODO Auto-generated method stub
		itemSQLMapper.updateNormal(item_idx);
	}

	@Override
	public void deleteItemVO(String item_idx) {
		// TODO Auto-generated method stub
		itemSQLMapper.delete(item_idx);
	}
	
	
	// 관리자용 
	//전부
	@Override
	public ArrayList<ItemVO> getItemList() {
		// TODO Auto-generated method stub
		return itemSQLMapper.selectList();
	}

	@Override
	public String countList() {
		// TODO Auto-generated method stub
		return itemSQLMapper.countAll();
	}

	@Override
	public ArrayList<ItemVO> getSearchItemList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return itemSQLMapper.selectSearchList(requestSearchData);
	}

	@Override
	public String countSearchList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return itemSQLMapper.countSearch(requestSearchData);
	}

	@Override
	public String countNormalList() {
		// TODO Auto-generated method stub
		return itemSQLMapper.countNormalAll();
	}

	@Override
	public String countSearchNormalList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return itemSQLMapper.countSearchNormal(requestSearchData);
	}

	@Override
	public String countExtinctionList() {
		// TODO Auto-generated method stub
		return itemSQLMapper.countExtinctionAll();
	}

	@Override
	public String countSearchExtinctionList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return itemSQLMapper.countSearchExtinction(requestSearchData);
	}

	@Override
	public ArrayList<ItemData> getItemDataList(ArrayList<ItemVO> itemList) {
		// TODO Auto-generated method stub
		ArrayList<ItemData> itemDataList = new ArrayList<ItemData>();
		//convenVO에 해당하는 brandVO, stateVO 불러와서 convenDataList에 저장
		for (ItemVO itemVO : itemList) {
			BrandVO brandVO = brandSQLMapper.selectByIdx(itemVO.getBrand_idx());
			ItemTypeVO itemTypeVO = itemTypeSQLMapper.selectByIdx(itemVO.getItem_type_idx());
			int item_likeCount = itemLikeSQLMapper.countByItemIdx(itemVO.getItem_idx());
			itemDataList.add(new ItemData(itemVO, brandVO, itemTypeVO, item_likeCount));
		}
		return itemDataList;
	}

	@Override
	public ArrayList<ItemData> getSearchItemDataList(ArrayList<ItemVO> itemList, String searchSelect,
			String searchWord) {
		// TODO Auto-generated method stub
		ArrayList<ItemData> itemDataList = new ArrayList<ItemData>();
		for (ItemVO itemVO : itemList) {
			BrandVO brandVO = brandSQLMapper.selectByIdx(itemVO.getBrand_idx());
			if(searchSelect.equals("brand_name") && !brandVO.getBrand_name().contains(searchWord)) {
				continue;
			}
			ItemTypeVO itemTypeVO = itemTypeSQLMapper.selectByIdx(itemVO.getItem_type_idx());
			if(searchSelect.equals("brand_name") && !itemTypeVO.getItem_type_name().contains(searchWord)) {
				continue;
			}
			int item_likeCount = itemLikeSQLMapper.countByItemIdx(itemVO.getItem_idx());
			itemDataList.add(new ItemData(itemVO, brandVO, itemTypeVO, item_likeCount));
		}
		return itemDataList;
	}

	@Override
	public ArrayList<ItemVO> getItemNormalList() {
		// TODO Auto-generated method stub
		return itemSQLMapper.selectNormalList();
	}

	@Override
	public ArrayList<ItemVO> getItemExtinctionList() {
		// TODO Auto-generated method stub
		return itemSQLMapper.selectExtinctionList();
	}
	
}
