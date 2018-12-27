package com.teamb.mth.majorservice;

import java.util.ArrayList;

import com.teamb.mth.data.ConvenData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.vo.ConvenVO;

public interface ConvenService {
	public void registerConven(String member_idx, ConvenVO requestConvenVO);

	public ArrayList<ConvenData> getMyConvenDataList(String member_idx);

	public ConvenData getConvenData(String conven_idx);

	public ConvenVO getConvenVO(String conven_idx);

	public ArrayList<ConvenVO> getAll();

	public void updateConven(ConvenVO requestConvenVO);

	public void deleteConven(String conven_idx);

	// 관리자 편의점리스트 관련...
	public ArrayList<ConvenData> getConvenDataList(ArrayList<ConvenVO> convenList);

	public ArrayList<ConvenData> getSearchConvenDataList(ArrayList<ConvenVO> convenList, String searchSelect,
			String searchWord);

	// 모든 편의점
	public ArrayList<ConvenVO> getConvenList();

	public String countList();

	public ArrayList<ConvenVO> getSearchConvenList(SearchData requestSearchData);

	public String countSearchList(SearchData requestSearchData);

	// 정상 편의점
	public ArrayList<ConvenVO> getConvenNomalList();

	public String countNormalList();

	public String countSearchNormalList(SearchData requestSearchData);

	// 신청상태 편의점
	public ArrayList<ConvenVO> getConvenRequestList();

	public String countRequestList();

	public String countSearchRequestList(SearchData requestSearchData);

	// 편의점 승인 거절
	public ArrayList<ConvenVO> getConvenDeniedList();

	public String countDeniedList();

	public String countSearchDeniedList(SearchData requestSearchData);
}
