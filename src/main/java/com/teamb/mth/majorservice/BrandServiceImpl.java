package com.teamb.mth.majorservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamb.mth.mapper.BrandSQLMapper;
import com.teamb.mth.vo.BrandVO;

@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	BrandSQLMapper brandSQLMapper;

	@Override
	public BrandVO getBrandVO(String brand_idx) {
		// TODO Auto-generated method stub
		return brandSQLMapper.selectByIdx(brand_idx);
	}

	@Override
	public ArrayList<BrandVO> getBrandList() {
		// TODO Auto-generated method stub
		return brandSQLMapper.selectAll();
	}

}
