package com.teamb.mth.majorservice;

import java.util.ArrayList;

import com.teamb.mth.vo.BrandVO;

public interface BrandService {
	
	public BrandVO getBrandVO(String brand_idx);
	public ArrayList<BrandVO> getBrandList();
	
}
