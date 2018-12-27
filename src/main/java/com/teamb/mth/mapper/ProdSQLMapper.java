package com.teamb.mth.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.teamb.mth.data.ProdRegisterData;
import com.teamb.mth.data.ProdStockData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.vo.ProdVO;

public interface ProdSQLMapper {

	@Insert("INSERT INTO mth_prod VALUES(seq_prod_idx.nextval,#{prodVO.item_idx},#{prodVO.conven_idx},#{prodVO.prod_content},"
			+ "#{prodVO.prod_saleprice},#{prodVO.prod_salefinalprice},#{prodVO.prod_condition},#{prod_timeExdate},SYSDATE)")
	public void insert(ProdRegisterData prodRegisterData);

	@Select("SELECT * FROM mth_prod WHERE prod_condition = 'NORMAL'")
	public ArrayList<ProdVO> selectAll();

	//메인페이지 재고량 확인...
	@Select("SELECT p.* FROM (SELECT MIN(prod_idx) prod_idx, count(*) prod_stockcount FROM mth_prod p WHERE prod_condition='NORMAL' GROUP BY item_idx, conven_idx, prod_saleprice, prod_salefinalprice, prod_exdate ORDER BY #{searchOrder}) p")
	public ArrayList<ProdStockData> selectProdStockListOrder(SearchData requestSearchData); 
	
	@Select("SELECT p.* FROM (SELECT MIN(prod_idx) prod_idx, count(*) prod_stockcount FROM mth_prod p WHERE prod_condition='NORMAL' AND prod_saleprice <= #{searchPrice} GROUP BY item_idx, conven_idx, prod_saleprice, prod_salefinalprice, prod_exdate ORDER BY #{searchOrder}) p")
	public ArrayList<ProdStockData> selectLowerPriceOrder(SearchData requestSearchData);
	
	@Select("SELECT p.* FROM (SELECT MIN(prod_idx) prod_idx, count(*) prod_stockcount FROM mth_prod p WHERE prod_condition='NORMAL' AND prod_saleprice >= #{searchPrice} GROUP BY item_idx, conven_idx, prod_saleprice, prod_salefinalprice, prod_exdate ORDER BY #{searchOrder}) p")
	public ArrayList<ProdStockData> selectUpperPriceOrder(SearchData requestSearchData);
	//메인페이지 재고량 확인 끝...
	
	@Select("SELECT * FROM mth_prod WHERE conven_idx = #{conven_idx} ORDER BY prod_idx DESC")
	public ArrayList<ProdVO> selectByConvenIdx(String conven_idx);

	@Select("SELECT * FROM mth_prod WHERE prod_idx = #{prod_idx}")
	public ProdVO selectByProdIdx(String prod_idx);

	@Update("UPDATE mth_prod SET prod_condition = #{prod_condition} WHERE prod_idx = #{prod_idx}")
	public void updateProdCondition(ProdVO prodVO);

	@Update("UPDATE mth_prod SET prod_saleprice = #{prodVO.prod_saleprice},prod_content = #{prodVO.prod_content},prod_exdate = #{prod_timeExdate},prod_salefinalprice = #{prodVO.prod_salefinalprice} WHERE prod_idx = #{prodVO.prod_idx}")
	public void update(ProdRegisterData prodRegisterData);

	// 유통기한 체크

	@Select("SELECT * FROM mth_prod WHERE prod_exdate <= sysdate AND prod_condition = 'NORMAL'")
	public ArrayList<ProdVO> selectByProdExdateOver();

	// 시간 되었을 때 최종가격으로 업데이트 용

	@Select("SELECT * FROM (SELECT p.*, (TO_DATE(TO_CHAR(SYSDATE,'yyyymmddhh'),'yyyymmddhh') - TO_DATE(TO_CHAR(p.prod_exdate,'yyyymmddhh'), 'yyyymmddhh'))*24 prod_timeLimit from dual, mth_prod p WHERE prod_saleprice != prod_salefinalprice) WHERE prod_timelimit >= -1")
	public ArrayList<ProdVO> selectTimeLimit();

	@Update("UPDATE mth_prod SET prod_saleprice = #{prod_saleprice} WHERE prod_idx = #{prod_idx}")
	public void updateSalePrice(ProdVO prodVO);

	// 관리자 떨이 리스트용...

	// 모든 회원
	@Select("SELECT * FROM mth_prod WHERE prod_condition != 'DELETE' ORDER BY prod_idx DESC")
	public ArrayList<ProdVO> selectProdList();

	@Select("SELECT COUNT(1) FROM mth_prod WHERE prod_condition != 'DELETE'")
	public String countAll();

	@Select("SELECT * FROM mth_prod WHERE prod_condition != 'DELETE' AND ${searchSelect} LIKE '%'||#{searchWord}||'%' ORDER BY prod_idx DESC")
	public ArrayList<ProdVO> selectSearchList(SearchData requestSearchData);

	@Select("SELECT COUNT(1) FROM mth_prod WHERE prod_condition != 'DELETE' AND ${searchSelect} LIKE '%'||#{searchWord}||'%' ORDER BY prod_idx DESC")
	public String countSearch(SearchData requestSearchData);

	// 파는중

	@Select("SELECT COUNT(1) FROM mth_prod WHERE prod_condition = 'NORMAL'")
	public String countNormalAll();

	@Select("SELECT SELECT * FROM mth_prod WHERE prod_condition = 'NORMAL' AND ${searchSelect} LIKE '%'||#{searchWord}||'%' ORDER BY prod_idx DESC")
	public String countNormalSearch(SearchData requestSearchData);

	// 팔림
	@Select("SELECT * FROM mth_prod WHERE prod_condition = 'SOLDOUT' ORDER BY prod_idx DESC")
	public ArrayList<ProdVO> selectProdSoldoutList();

	@Select("SELECT COUNT(1) FROM mth_prod WHERE prod_condition = 'SOLDOUT'")
	public String countSoldoutAll();

	@Select("SELECT COUNT(1) FROM mth_prod WHERE prod_condition = 'SOLDOUT' AND ${searchSelect} LIKE '%'||#{searchWord}||'%' ORDER BY prod_idx DESC")
	public String countSoldoutSearch(SearchData requestSearchData);

	// 폐기
	@Select("SELECT * FROM mth_prod WHERE prod_condition = 'EXPIRED' ORDER BY prod_idx DESC")
	public ArrayList<ProdVO> selectProdExpiredList();

	@Select("SELECT COUNT(1) FROM mth_prod WHERE prod_condition = 'EXPIRED'")
	public String countExpiredAll();

	@Select("SELECT COUNT(1) FROM mth_prod WHERE prod_condition = 'EXPIRED' AND ${searchSelect} LIKE '%'||#{searchWord}||'%' ORDER BY prod_idx DESC")
	public String countExpiredSearch(SearchData requestSearchData);

	// 관리자가 판매중단한 물품에 해당하는 떨이 물품 가져오기
	@Select("SELECT * FROM mth_prod WHERE item_idx=#{item_idx}")
	public ArrayList<ProdVO> selectByItemIdx(String item_idx);
}
