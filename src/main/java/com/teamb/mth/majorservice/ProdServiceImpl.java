package com.teamb.mth.majorservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamb.mth.data.ProdData;
import com.teamb.mth.data.ProdRegisterData;
import com.teamb.mth.data.ProdStockData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.mapper.BrandSQLMapper;
import com.teamb.mth.mapper.ConvenSQLMapper;
import com.teamb.mth.mapper.ItemSQLMapper;
import com.teamb.mth.mapper.ItemTypeSQLMapper;
import com.teamb.mth.mapper.MemberSQLMapper;
import com.teamb.mth.mapper.ProdSQLMapper;
import com.teamb.mth.mapper.StateSQLMapper;
import com.teamb.mth.vo.BrandVO;
import com.teamb.mth.vo.ConvenVO;
import com.teamb.mth.vo.ItemTypeVO;
import com.teamb.mth.vo.ItemVO;
import com.teamb.mth.vo.MemberVO;
import com.teamb.mth.vo.ProdVO;
import com.teamb.mth.vo.StateVO;

@Service
public class ProdServiceImpl implements ProdService {
	@Autowired
	ProdSQLMapper prodSQLMapper;
	@Autowired
	ItemSQLMapper itemSQLMapper;
	@Autowired
	ItemTypeSQLMapper itemTypeSQLMapper;
	@Autowired
	ConvenSQLMapper convenSQLMapper;
	@Autowired
	MemberSQLMapper memberSQLMapper;
	@Autowired
	BrandSQLMapper brandSQLMapper;
	@Autowired
	StateSQLMapper stateSQLMapper;

	@Override
	public ArrayList<ProdVO> getNormalProdList() {
		// TODO Auto-generated method stub
		return prodSQLMapper.selectAll();
	}

	// 메인페이지용 재고량 추가
	@Override
	public ArrayList<ProdData> getNormalStockCountProdData(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		ArrayList<ProdData> prodDataList = new ArrayList<ProdData>();
		ArrayList<ProdStockData> prodStockDataList = prodSQLMapper.selectProdStockListOrder(requestSearchData);

		for (ProdStockData prodStockData : prodStockDataList) {
			ProdData prodData = new ProdData();

			ProdVO prodVO = prodSQLMapper.selectByProdIdx(prodStockData.getProd_idx());
			String prod_stockCount = prodStockData.getProd_stockCount();

			prodData.setProdVO(prodVO);
			prodData.setProd_stockCount(prod_stockCount);

			prodDataList.add(prodData);
		}

		return prodDataList;
	}

	@Override
	public ArrayList<ProdData> getProdListLowerPrice(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		ArrayList<ProdData> prodDataList = new ArrayList<ProdData>();
		ArrayList<ProdStockData> prodStockDataList = prodSQLMapper.selectLowerPriceOrder(requestSearchData);

		for (ProdStockData prodStockData : prodStockDataList) {
			ProdData prodData = new ProdData();

			ProdVO prodVO = prodSQLMapper.selectByProdIdx(prodStockData.getProd_idx());
			String prod_stockCount = prodStockData.getProd_stockCount();

			prodData.setProdVO(prodVO);
			prodData.setProd_stockCount(prod_stockCount);

			prodDataList.add(prodData);
		}

		return prodDataList;
	}

	@Override
	public ArrayList<ProdData> getProdListUpperPrice(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		ArrayList<ProdData> prodDataList = new ArrayList<ProdData>();
		ArrayList<ProdStockData> prodStockDataList = prodSQLMapper.selectUpperPriceOrder(requestSearchData);

		for (ProdStockData prodStockData : prodStockDataList) {
			ProdData prodData = new ProdData();

			ProdVO prodVO = prodSQLMapper.selectByProdIdx(prodStockData.getProd_idx());
			String prod_stockCount = prodStockData.getProd_stockCount();

			prodData.setProdVO(prodVO);
			prodData.setProd_stockCount(prod_stockCount);

			prodDataList.add(prodData);
		}

		return prodDataList;
	}

	@Override
	public void registerSellProduct(ProdRegisterData prodRegisterData) {
		// TODO Auto-generated method stub

		if ((prodRegisterData.getProdVO().getProd_salefinalprice()).equals("")) {
			prodRegisterData.getProdVO().setProd_salefinalprice(prodRegisterData.getProdVO().getProd_saleprice());
		}

		prodSQLMapper.insert(prodRegisterData);

	}

	@Override
	public ArrayList<ProdVO> getMyProdList(String conven_idx) {
		// TODO Auto-generated method stub
		return prodSQLMapper.selectByConvenIdx(conven_idx);
	}

	@Override
	public ProdVO getProdVO(String prod_idx) {
		// TODO Auto-generated method stub
		return prodSQLMapper.selectByProdIdx(prod_idx);
	}

	@Override
	public void updateProdCondition(ProdVO prodVO) {
		// TODO Auto-generated method stub
		prodSQLMapper.updateProdCondition(prodVO);
	}

	@Override
	public ProdData getProdData(String prod_idx) {
		// TODO Auto-generated method stub
		ProdVO prodVO = prodSQLMapper.selectByProdIdx(prod_idx);

		ItemVO itemVO = itemSQLMapper.selectByIdx(prodVO.getItem_idx());
		ItemTypeVO itemTypeVO = itemTypeSQLMapper.selectByIdx(itemVO.getItem_type_idx());

		ConvenVO convenVO = convenSQLMapper.selectByIdx(prodVO.getConven_idx());
		MemberVO memberVO = memberSQLMapper.selectByIdx(convenVO.getMember_idx());
		BrandVO brandVO = brandSQLMapper.selectByIdx(convenVO.getBrand_idx());
		StateVO stateVO = stateSQLMapper.selectByIdx(convenVO.getState_idx());

		return new ProdData(prodVO, itemVO, memberVO, convenVO, brandVO, itemTypeVO, stateVO);

	}

	@Override
	public void updateSellProduct(ProdRegisterData prodRegisterData) {
		// TODO Auto-generated method stub

		if ((prodRegisterData.getProdVO().getProd_salefinalprice()).equals("")) {
			prodRegisterData.getProdVO().setProd_salefinalprice(prodRegisterData.getProdVO().getProd_saleprice());
		}

		prodSQLMapper.update(prodRegisterData);
	}

	@Override
	public ArrayList<ProdData> getProdDataList(ArrayList<ProdVO> prodList) {
		// TODO Auto-generated method stub

		ArrayList<ProdData> prodDataList = new ArrayList<ProdData>();

		for (ProdVO prodVO : prodList) {
			
			ItemVO itemVO = itemSQLMapper.selectByIdx(prodVO.getItem_idx());
			ItemTypeVO itemTypeVO = itemTypeSQLMapper.selectByIdx(itemVO.getItem_type_idx());

			ConvenVO convenVO = convenSQLMapper.selectByIdx(prodVO.getConven_idx());
			MemberVO memberVO = memberSQLMapper.selectByIdx(convenVO.getMember_idx());
			BrandVO brandVO = brandSQLMapper.selectByIdx(convenVO.getBrand_idx());
			StateVO stateVO = stateSQLMapper.selectByIdx(convenVO.getState_idx());

			prodDataList.add(new ProdData(prodVO, itemVO, memberVO, convenVO, brandVO, itemTypeVO, stateVO));
			
			
		}

		return prodDataList;
	}

	@Override
	public ArrayList<ProdVO> getProdList() {
		// TODO Auto-generated method stub
		return prodSQLMapper.selectProdList();
	}

	@Override
	public String countList() {
		// TODO Auto-generated method stub
		return prodSQLMapper.countAll();
	}

	@Override
	public ArrayList<ProdVO> getSearchProdList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return prodSQLMapper.selectSearchList(requestSearchData);
	}

	@Override
	public String countSearch(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return prodSQLMapper.countSearch(requestSearchData);
	}

	@Override
	public String countSoldoutList() {
		// TODO Auto-generated method stub
		return prodSQLMapper.countSoldoutAll();
	}

	@Override
	public String countSearchSoldoutList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return prodSQLMapper.countSoldoutSearch(requestSearchData);
	}

	@Override
	public String countExpiredList() {
		// TODO Auto-generated method stub
		return prodSQLMapper.countExpiredAll();
	}

	@Override
	public String countSearchExpiredList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return prodSQLMapper.countExpiredSearch(requestSearchData);
	}

	@Override
	public String countNormalList() {
		// TODO Auto-generated method stub
		return prodSQLMapper.countNormalAll();
	}

	@Override
	public String countSearchNomalList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return prodSQLMapper.countNormalSearch(requestSearchData);
	}

	@Override
	public void updateOverTimeProd() {
		// TODO Auto-generated method stub
		ArrayList<ProdVO> prodList = prodSQLMapper.selectByProdExdateOver();
		for (ProdVO prodVO : prodList) {
			prodVO.setProd_condition("EXPIRED");
			prodSQLMapper.updateProdCondition(prodVO);
		}
	}

	@Override
	public void updateSalePriceByFinalPrice() {
		// TODO Auto-generated method stub
		ArrayList<ProdVO> prodList = prodSQLMapper.selectTimeLimit();
		for (ProdVO prodVO : prodList) {
			prodVO.setProd_saleprice(prodVO.getProd_salefinalprice());
			prodSQLMapper.updateSalePrice(prodVO);
		}

	}

	@Override
	public ArrayList<ProdVO> getProdSoldoutList() {
		// TODO Auto-generated method stub
		return prodSQLMapper.selectProdSoldoutList();
	}

	@Override
	public ArrayList<ProdVO> getProdExpiredList() {
		// TODO Auto-generated method stub
		return prodSQLMapper.selectProdExpiredList();
	}

	// 검색조건인 다른테이블인 경우...
	@Override
	public ArrayList<ProdData> getSearchProdDataList(ArrayList<ProdVO> prodList, String searchSelect,
			String searchWord) {
		// TODO Auto-generated method stub
		ArrayList<ProdData> prodDataList = new ArrayList<ProdData>();
		for (ProdVO prodVO : prodList) {
			ItemVO itemVO = itemSQLMapper.selectByIdx(prodVO.getItem_idx());
			if (searchSelect.equals("item_name") && !itemVO.getItem_name().contains(searchWord)) {
				continue;
			}
			ItemTypeVO itemTypeVO = itemTypeSQLMapper.selectByIdx(itemVO.getItem_type_idx());
			if (searchSelect.equals("item_type_name") && !itemTypeVO.getItem_type_name().contains(searchWord)) {
				continue;
			}

			ConvenVO convenVO = convenSQLMapper.selectByIdx(prodVO.getConven_idx());
			MemberVO memberVO = memberSQLMapper.selectByIdx(convenVO.getMember_idx());
			BrandVO brandVO = brandSQLMapper.selectByIdx(convenVO.getBrand_idx());
			if (searchSelect.equals("brand_name") && !brandVO.getBrand_name().contains(searchWord)) {
				continue;
			}
			StateVO stateVO = stateSQLMapper.selectByIdx(convenVO.getState_idx());

			prodDataList.add(new ProdData(prodVO, itemVO, memberVO, convenVO, brandVO, itemTypeVO, stateVO));
		}
		return prodDataList;
	}

	// 관리자가 판매중단한 물품에 해당하는 떨이 물품 연쇄적으로 삭제 처리
	@Override
	public void deleteProdList(String item_idx) {
		// TODO Auto-generated method stub
		// 관리자가 판매중단한 물품에 해당하는 떨이 물품 리스트 가져오기
		ArrayList<ProdVO> prodList = prodSQLMapper.selectByItemIdx(item_idx);

		for (ProdVO prodVO : prodList) {// 각 물품마다 삭제처리
			prodVO.setProd_condition("DELETE");
			prodSQLMapper.updateProdCondition(prodVO);
		}
	}

	// 관리자가 판매중단한 물품에 해당하는 떨이 물품 연쇄적으로 판매가능 처리
	@Override
	public void normalProdList(String item_idx) {
		// TODO Auto-generated method stub
		// 관리자가 판매가능한 물품에 해당하는 떨이 물품 리스트 가져오기
		ArrayList<ProdVO> prodList = prodSQLMapper.selectByItemIdx(item_idx);

		for (ProdVO prodVO : prodList) {// 각 물품마다 판매가능처리(편의점이 판매가능인 상태일때만)

			ConvenVO convenVO = convenSQLMapper.selectByIdx(prodVO.getConven_idx());

			if ((convenVO.getConven_condition()).equals("NORMAL")) {
				prodVO.setProd_condition("NORMAL");
				prodSQLMapper.updateProdCondition(prodVO);
			}

		}
	}

	@Override
	public ProdRegisterData getProdRegisterData(ProdData prodData) {
		// TODO Auto-generated method stub

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			String from = prodData.getProdVO().getProd_exdate();
			Date date = transFormat.parse(from);
			System.out.println("date=" + date);
			ProdRegisterData prodRegisterData = new ProdRegisterData(prodData.getProdVO(), prodData.getItemVO(),
					prodData.getMemberVO(), prodData.getConvenVO(), prodData.getBrandVO(), prodData.getItemTypeVO(),
					prodData.getStateVO(), date);
			return prodRegisterData;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("prodServiceImpl 에러");
			ProdRegisterData prodRegisterData = new ProdRegisterData(prodData.getProdVO(), prodData.getItemVO(),
					prodData.getMemberVO(), prodData.getConvenVO(), prodData.getBrandVO(), prodData.getItemTypeVO(),
					prodData.getStateVO());
			e.printStackTrace();
			return prodRegisterData;
		}

	}

	@Override
	public ArrayList<ProdVO> getProdDataByItemIdx(String item_idx) {
		// TODO Auto-generated method stub
		return prodSQLMapper.selectByItemIdx(item_idx);
	}

	@Override
	public ArrayList<ProdData> getNormalProdDataList(ArrayList<ProdVO> prodList) {
		// TODO Auto-generated method stub
		ArrayList<ProdData> prodDataList = new ArrayList<ProdData>();

		for (ProdVO prodVO : prodList) {
			
			if((prodVO.getProd_condition()).equals("NORMAL")) {
			
				ItemVO itemVO = itemSQLMapper.selectByIdx(prodVO.getItem_idx());
				ItemTypeVO itemTypeVO = itemTypeSQLMapper.selectByIdx(itemVO.getItem_type_idx());
	
				ConvenVO convenVO = convenSQLMapper.selectByIdx(prodVO.getConven_idx());
				MemberVO memberVO = memberSQLMapper.selectByIdx(convenVO.getMember_idx());
				BrandVO brandVO = brandSQLMapper.selectByIdx(convenVO.getBrand_idx());
				StateVO stateVO = stateSQLMapper.selectByIdx(convenVO.getState_idx());
	
				prodDataList.add(new ProdData(prodVO, itemVO, memberVO, convenVO, brandVO, itemTypeVO, stateVO));
			}
			
		}

		return prodDataList;
	}

}
