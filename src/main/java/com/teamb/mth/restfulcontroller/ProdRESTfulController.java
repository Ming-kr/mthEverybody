package com.teamb.mth.restfulcontroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamb.mth.data.ConvenData;
import com.teamb.mth.data.ProdData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.majorservice.ConvenService;
import com.teamb.mth.majorservice.ItemService;
import com.teamb.mth.majorservice.ProdService;
import com.teamb.mth.vo.ItemVO;
import com.teamb.mth.vo.ProdVO;

@RestController
public class ProdRESTfulController {

	@Autowired
	ConvenService convenService;

	@Autowired
	ItemService itemService;
	
	@Autowired
	ProdService prodService;

	// conven_idx, item_type_idx 둘다 만족하는 떨이 상품 리스트 불러오는 기능구현 ajax.....
	@RequestMapping("/changeSelect")
	public ArrayList<ItemVO> changeSelect(@RequestParam("conven_idx")String conven_idx, @RequestParam("item_type_idx")String item_type_idx) {

		if(item_type_idx.equals("- 떨이 상품 종류 선택 -")) {
			return null;
		}
		ArrayList<ItemVO> itemlist = new ArrayList<ItemVO>();
		
	
		// conven_idx로 편의점 정보 불러오기 -> 거기에서 해당하는 brand_idx 가져오기
		ConvenData convenData = convenService.getConvenData(conven_idx);
		String brand_idx = convenData.getBrandVO().getBrand_idx();
		
		// brand_idx, item_type_idx에 해당하는 itemVOList 불러오기
		itemlist = itemService.getItemByBrandAndItemType(brand_idx, item_type_idx);
		

		return itemlist;

	}

	// item_idx를 통해 itemVO 가져와서 원가 불러오는 기능구현 ajax....
	@RequestMapping("/changeSellProduct")
	public ItemVO changeSellProduct(String item_idx) {
		// itemVO 불러오기...
		ItemVO itemVO = itemService.getItemVO(item_idx);

		return itemVO;
	}

	// 관리자 prodList용...
		@RequestMapping("/admin/filterSearchProdList")
		public SearchData filterSearchProdList(ProdVO requestProdVO, @RequestParam("searchSelect") String searchSelect,
				@RequestParam("searchWord") String searchWord) {

			String prod_condition = requestProdVO.getProd_condition();

			SearchData searchData = new SearchData();
			searchData.setSearchSelect(searchSelect);
			searchData.setSearchWord(searchWord);

			// 멤버 코드 검색에 따른 페이지 크기...//
			if (prod_condition.equals("NORMAL")) {
				if(searchSelect.equals("prod_content") || searchSelect.equals("prod_saleprice")) {
					// 페이지 크기...//
					String paging = prodService.countSearchNomalList(searchData); // 총 게시글 개수
					if (Integer.parseInt(paging) <= 1) {
						paging = "1";
					} else {
						paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
					}
					searchData.setPaging(paging);
					// 페이지 크기 끝...//
				} else {
					ArrayList<ProdVO> prodList = prodService.getNormalProdList();
					ArrayList<ProdData> prodDataList = prodService.getSearchProdDataList(prodList, searchSelect, searchWord);
					
					String paging = String.valueOf(prodDataList.size());
					if (Integer.parseInt(paging) <= 1) {
						paging = "1";
					} else {
						paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
					}
					searchData.setPaging(paging);
				}
					
			} else if (prod_condition.equals("SOLDOUT")) {
				if(searchSelect.equals("prod_content") || searchSelect.equals("prod_saleprice")) {
					// 페이지 크기...//
					String paging = prodService.countSearchSoldoutList(searchData); // 총 게시글 개수
					if (Integer.parseInt(paging) <= 1) {
						paging = "1";
					} else {
						paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
					}
					searchData.setPaging(paging);
					// 페이지 크기 끝...//
				} else {
					ArrayList<ProdVO> prodList = prodService.getProdSoldoutList();
					ArrayList<ProdData> prodDataList = prodService.getSearchProdDataList(prodList, searchSelect, searchWord);
					
					String paging = String.valueOf(prodDataList.size());
					if (Integer.parseInt(paging) <= 1) {
						paging = "1";
					} else {
						paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
					}
					searchData.setPaging(paging);
				}
			} else if (prod_condition.equals("EXPIRED")) {
				if(searchSelect.equals("prod_content") || searchSelect.equals("prod_saleprice")) {
					// 페이지 크기...//
					String paging = prodService.countSearchExpiredList(searchData); // 총 게시글 개수
					if (Integer.parseInt(paging) <= 1) {
						paging = "1";
					} else {
						paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
					}
					searchData.setPaging(paging);
					// 페이지 크기 끝...//
				} else {
					ArrayList<ProdVO> prodList = prodService.getProdExpiredList();
					ArrayList<ProdData> prodDataList = prodService.getSearchProdDataList(prodList, searchSelect, searchWord);
					
					String paging = String.valueOf(prodDataList.size());
					if (Integer.parseInt(paging) <= 1) {
						paging = "1";
					} else {
						paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
					}
					searchData.setPaging(paging);
				}
			} else {
				if(searchSelect.equals("prod_content") || searchSelect.equals("prod_saleprice")) {
					String paging = prodService.countSearch(searchData); // 총 게시글 개수
					if (Integer.parseInt(paging) <= 1) {
						paging = "1";
					} else {
						paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
					}
					searchData.setPaging(paging);
				} else {
					ArrayList<ProdVO> prodList = prodService.getProdList();
					ArrayList<ProdData> prodDataList = prodService.getSearchProdDataList(prodList, searchSelect, searchWord);
					
					String paging = String.valueOf(prodDataList.size());
					if (Integer.parseInt(paging) <= 1) {
						paging = "1";
					} else {
						paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
					}
					searchData.setPaging(paging);
				}
			}
			return searchData;
		}

	@RequestMapping("/admin/filterProdList")
	public SearchData filterProdList(ProdVO requestProdVO) {

		String prod_condition = requestProdVO.getProd_condition();

		SearchData searchData = new SearchData();
		// 멤버 코드 검색에 따른 페이지 크기...//
		if (prod_condition.equals("NORMAL")) {
			// 페이지 크기...//
			String paging = prodService.countNormalList(); // 총 게시글 개수
			if (Integer.parseInt(paging) <= 1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
			}
			searchData.setPaging(paging);
			// 페이지 크기 끝...//
		} else if (prod_condition.equals("SOLDOUT")) {
			// 페이지 크기...//
			String paging = prodService.countSoldoutList(); // 총 게시글 개수
			if (Integer.parseInt(paging) <= 1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
			}
			searchData.setPaging(paging);
			// 페이지 크기 끝...//
		} else if (prod_condition.equals("EXPIRED")) {
			// 페이지 크기...//
			String paging = prodService.countExpiredList(); // 총 게시글 개수
			if (Integer.parseInt(paging) <= 1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
			}
			searchData.setPaging(paging);
			// 페이지 크기 끝...//
		} else {
			String paging = prodService.countList(); // 총 게시글 개수
			if (Integer.parseInt(paging) <= 1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
			}
			searchData.setPaging(paging);
		}
		return searchData;
	}

	@RequestMapping("/admin/pageProdList")
	public SearchData pageProdList(SearchData requestSearchData, @RequestParam("nowPage") int nowPage) {

		SearchData searchData = new SearchData();
		// 페이지 이동 처리 로직...//
		if (nowPage <= 1) { // 페이지 넘버 없을 때
			requestSearchData.setPageNum("1");
			requestSearchData.setStartNum("1");
			requestSearchData.setEndNum("10");
		} else {
			System.out.println("paging:" + nowPage);
			requestSearchData.setPageNum(String.valueOf(nowPage));
			requestSearchData.setStartNum(String.valueOf(nowPage * 10 - 9)); // 페이지 넘버로 rownum 시작값 세팅
			requestSearchData.setEndNum(String.valueOf(nowPage * 10)); // 페이지 넘버로 rownum 끝나는 값 세팅
		}
		searchData.setPageNum(requestSearchData.getPageNum());
		searchData.setStartNum(requestSearchData.getStartNum());
		searchData.setEndNum(requestSearchData.getEndNum());
		// 페이지 이동 처리 로직 끝...//

		return searchData;
	}
}
