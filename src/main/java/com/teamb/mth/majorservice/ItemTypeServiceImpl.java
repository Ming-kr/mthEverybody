package com.teamb.mth.majorservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamb.mth.mapper.ItemTypeSQLMapper;
import com.teamb.mth.vo.ItemTypeVO;

@Service
public class ItemTypeServiceImpl implements ItemTypeService {
	@Autowired
	ItemTypeSQLMapper itemTypeSQLMapper;
	
	//itemType 다 불러오기
	@Override
	public ArrayList<ItemTypeVO> getItemTypeList() {
		// TODO Auto-generated method stub
		return itemTypeSQLMapper.selectAll();
	}

	@Override
	public ItemTypeVO getItemTypeVO(String item_type_idx) {
		// TODO Auto-generated method stub
		return itemTypeSQLMapper.selectByIdx(item_type_idx);
	}

}
