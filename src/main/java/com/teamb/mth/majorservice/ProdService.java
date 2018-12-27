package com.teamb.mth.majorservice;

import java.util.ArrayList;

import com.teamb.mth.data.ProdData;
import com.teamb.mth.data.ProdRegisterData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.vo.ProdVO;

public interface ProdService {

	public void registerSellProduct(ProdRegisterData prodRegisterData);

	public ArrayList<ProdVO> getNormalProdList();

	// 메인페이지용 재고량 추가
	public ArrayList<ProdData> getNormalStockCountProdData(SearchData requestSearchData);

	public ArrayList<ProdData> getProdListLowerPrice(SearchData requestSearchData);

	public ArrayList<ProdData> getProdListUpperPrice(SearchData requestSearchData);

	public ArrayList<ProdVO> getMyProdList(String conven_idx);

	public ProdVO getProdVO(String prod_idx);

	public void updateSellProduct(ProdRegisterData prodRegisterData);

	public ProdData getProdData(String prod_idx);

	public void updateProdCondition(ProdVO prodVO);

	// 시간 체크...
	// 유통기한
	public void updateOverTimeProd();

	// 최종가격
	public void updateSalePriceByFinalPrice();
	// 시간 체크.

	// 관리자 리스트용...
	public ArrayList<ProdData> getProdDataList(ArrayList<ProdVO> prodList);

	public ArrayList<ProdData> getSearchProdDataList(ArrayList<ProdVO> prodList, String searchSelect,
			String searchWord);

	// 모든
	public ArrayList<ProdVO> getProdList();

	public String countList();

	public ArrayList<ProdVO> getSearchProdList(SearchData requestSearchData);

	public String countSearch(SearchData requestSearchData);

	// 정상
	public String countNormalList();

	public String countSearchNomalList(SearchData requestSearchData);

	// 팔림
	public ArrayList<ProdVO> getProdSoldoutList();

	public String countSoldoutList();

	public String countSearchSoldoutList(SearchData requestSearchData);

	// 폐기
	public ArrayList<ProdVO> getProdExpiredList();

	public String countExpiredList();

	public String countSearchExpiredList(SearchData requestSearchData);

	// 관리자가 판매중단한 물품에 해당하는 떨이 물품 연쇄적으로 DELETE 처리
	public void deleteProdList(String item_idx);

	// 관리자가 판매가능한 물품에 해당하는 떨이 물품 연쇄적으로 NORMAL 처리
	public void normalProdList(String item_idx);

	public ProdRegisterData getProdRegisterData(ProdData prodData);
	
	public ArrayList<ProdVO> getProdDataByItemIdx(String item_idx);

	public ArrayList<ProdData> getNormalProdDataList(ArrayList<ProdVO> prodList);
}
