package com.teamb.mth.controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teamb.mth.data.ConvenData;
import com.teamb.mth.data.ItemReData;
import com.teamb.mth.data.ProdData;
import com.teamb.mth.data.ProdRegisterData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.data.SessionLoginedData;
import com.teamb.mth.majorservice.BrandService;
import com.teamb.mth.majorservice.ConvenService;
import com.teamb.mth.majorservice.ItemLikeService;
import com.teamb.mth.majorservice.ItemReService;
import com.teamb.mth.majorservice.ItemService;
import com.teamb.mth.majorservice.ItemTypeService;
import com.teamb.mth.majorservice.MemberService;
import com.teamb.mth.majorservice.ProdService;
import com.teamb.mth.majorservice.StateService;
import com.teamb.mth.util.PagingData;
import com.teamb.mth.vo.BrandVO;
import com.teamb.mth.vo.ConvenVO;
import com.teamb.mth.vo.ItemLikeVO;
import com.teamb.mth.vo.ItemTypeVO;
import com.teamb.mth.vo.ItemVO;
import com.teamb.mth.vo.MemberVO;
import com.teamb.mth.vo.ProdVO;
import com.teamb.mth.vo.StateVO;

@Controller
public class ProdController {

	@Autowired
	MemberService memberService;

	@Autowired
	ConvenService convenService;

	@Autowired
	ItemService itemService;

	@Autowired
	ItemLikeService itemLikeService;

	@Autowired
	ItemReService itemReService;

	@Autowired
	ProdService prodService;

	@Autowired
	BrandService brandService;

	@Autowired
	StateService stateService;

	@Autowired
	ItemTypeService itemTypeService;

	@RequestMapping("mainPage")
	public String mainPage(Model model, HttpSession session,
			@RequestParam(value = "brand_name", required = false) String[] brandNameList,
			@RequestParam(value = "item_type_name", required = false) String[] itemTypeNameList,
			@RequestParam(value = "state_name", required = false) String[] stateNameList,
			@RequestParam(value = "searchPrice", required = false) String searchPrice,
			@RequestParam(value = "searchOrder", required = false) String searchOrder,
			@RequestParam(value = "searchSelect", required = false) String searchSelect,
			@RequestParam(value = "searchWord", required = false) String searchWord) {

		SessionLoginedData user = (SessionLoginedData) session.getAttribute("sessionLoginedData");
		SearchData searchData = new SearchData();

		// 메인페이지 검색 카테고리 출력 로직...
		ArrayList<BrandVO> brandList = brandService.getBrandList();
		ArrayList<ItemTypeVO> itemTypeList = itemTypeService.getItemTypeList();
		ArrayList<StateVO> stateList = stateService.getStateList();

		// DB에 입력한 값 가져오기...
		model.addAttribute("brandList", brandList);
		model.addAttribute("itemTypeList", itemTypeList);
		model.addAttribute("stateList", stateList);

		// 검색 체크된 것 유지...
		model.addAttribute("brandNameList", brandNameList);
		model.addAttribute("itemTypeNameList", itemTypeNameList);
		model.addAttribute("stateNameList", stateNameList);
		model.addAttribute("searchPrice", searchPrice);
		model.addAttribute("searchOrder", searchOrder);
		model.addAttribute("searchSelect", searchSelect);
		model.addAttribute("searchWord", searchWord);

		if (searchOrder == null) {
			searchOrder = "prod_idx";
		}

		searchData.setSearchOrder(searchOrder);
		searchData.setSearchPrice(searchPrice);
		searchData.setSearchSelect(searchSelect);
		searchData.setSearchWord(searchWord);
		model.addAttribute("searchData", searchData);
		// 검색 체크된 것 유지 끝...

		// 메인페이지 검색 카테고리 출력 로직 끝...

		// 메인페이지 상품 리스트 출력 로직...
		ArrayList<ProdData> prodStockList = new ArrayList<ProdData>();

		// 가격 검색조건 처리...
		if (searchPrice == null) {
			prodStockList = prodService.getNormalStockCountProdData(searchData);
		} else if (searchPrice.equals("5001")) {
			prodStockList = prodService.getProdListUpperPrice(searchData);
		} else {
			prodStockList = prodService.getProdListLowerPrice(searchData);
		}
		// 가격 검색조건 처리 끝...

		ArrayList<ProdData> prodDataList = new ArrayList<ProdData>();

		for (ProdData prodData : prodStockList) {
			ProdVO prodVO = prodData.getProdVO();
			String prod_stockCount = prodData.getProd_stockCount();

			ItemVO itemVO = itemService.getItemVO(prodVO.getItem_idx());
			if (searchWord != "" && searchWord != null && searchSelect.equals("item_name")) {
				if (!itemVO.getItem_name().contains(searchWord)) {
					continue;
				}
			}
			ConvenVO convenVO = convenService.getConvenVO(prodVO.getConven_idx());
			if (searchWord != "" && searchWord != null && searchSelect.equals("conven_name")) {
				if (!convenVO.getConven_name().contains(searchWord)) {
					continue;
				}
			}

			MemberVO memberVO = memberService.getMemberVO(convenVO.getMember_idx());
			BrandVO brandVO = brandService.getBrandVO(convenVO.getBrand_idx());
			// 브랜드 검색조건 처리...
			if (brandNameList != null) {
				boolean check = false;
				for (String brand_name : brandNameList) {
					if (brandVO.getBrand_name().equals(brand_name)) {
						check = true;
					}
				}
				if (check == false) {
					continue;
				}
			}
			// 브랜드 검색조건 처리 끝...

			ItemTypeVO itemTypeVO = itemTypeService.getItemTypeVO(itemVO.getItem_type_idx());
			// 품목 검색조건 처리...
			if (itemTypeNameList != null) {
				boolean check = false;
				for (String item_type_name : itemTypeNameList) {
					if (itemTypeVO.getItem_type_name().equals(item_type_name)) {
						check = true;
					}
				}
				if (check == false) {
					continue;
				}
			}
			// 품목 검색조건 처리 끝...

			StateVO stateVO = stateService.getStateVO(convenVO.getState_idx());
			// 지역 검색조건 처리...
			if (stateNameList != null) {
				boolean check = false;
				for (String state_name : stateNameList) {
					if (stateVO.getState_name().equals(state_name)) {
						check = true;
					}
				}
				if (check == false) {
					continue;
				}
			}
			// 지역 검색조건 처리 끝...

			int itemLikeCount = itemLikeService.countItemLike(itemVO.getItem_idx()); // 물품 좋아요 한 수 뽑아오기...

			// 로그인한 멤버idx가 좋아요 한건지 체크...
			ArrayList<ItemLikeVO> likeMemberList = itemLikeService.likeMemberList(itemVO.getItem_idx());
			boolean itemLikeCheck = false;

			if (user != null) {
				for (ItemLikeVO itemLikeVO : likeMemberList) {
					if (user.getMember_idx().equals(itemLikeVO.getMember_idx())) {
						itemLikeCheck = true;
					}
				}
			}
			int itemReCount = itemReService.getItemReListTotalDataCount(itemVO.getItem_idx());
			// 로그인한 멤버idx가 좋아요 한건지 체크 끝...
			prodDataList.add(new ProdData(prodVO, itemVO, memberVO, convenVO, brandVO, itemTypeVO, stateVO,
					itemLikeCount, itemLikeCheck, itemReCount, prod_stockCount));
		}

		model.addAttribute("prodDataList", prodDataList);
		// 메인페이지 상품 리스트 출력 로직 끝...

		return "/mainPage";
	}

	// 떨이 상품 등록 페이지
	@RequestMapping("/seller/registerSellProductForm")
	public String registerSellProductForm(Model model, HttpSession session) {

		// session에서 member_idx 가져오기
		String member_idx = ((SessionLoginedData) session.getAttribute("sessionLoginedData")).getMember_idx();

		// member_idx에 해당하는 나의 편의점 정보 불러오기
		ArrayList<ConvenData> convenDataList = convenService.getMyConvenDataList(member_idx);
		model.addAttribute("convenDataList", convenDataList);
		// 물품종류 불러오기
		model.addAttribute("itemTypeList", itemTypeService.getItemTypeList());

		return "/seller/registerSellProductForm";
	}

	// 떨이 상품 등록 처리 페이지
	@RequestMapping("/seller/actionRegisterSellProductForm")
	public String actionRegisterSellProductForm(HttpSession session, ProdVO requestProdVO,
			@RequestParam("prod_exHour") String prod_exHour, @RequestParam("prod_exMin") String prod_exMin,
			@RequestParam("prod_exSec") String prod_exSec) {
		String ex_date = requestProdVO.getProd_exdate() + " " + prod_exHour + ":" + prod_exMin + ":" + prod_exSec;
		System.out.println(ex_date);
		Timestamp prod_timeExdate = Timestamp.valueOf(ex_date);
		ProdRegisterData prodRegisterData = new ProdRegisterData(requestProdVO, prod_timeExdate);
		prodService.registerSellProduct(prodRegisterData);
		return "redirect:/seller/sellProductMyList";
	}

	@RequestMapping("/seller/sellProductMyList")
	public String sellProductMyList(HttpSession session, Model model) {

		String member_idx = ((SessionLoginedData) session.getAttribute("sessionLoginedData")).getMember_idx();
		ArrayList<ConvenData> convenDataList = convenService.getMyConvenDataList(member_idx);

		ArrayList<ProdData> prodDataList = new ArrayList<ProdData>();

		for (ConvenData convenData : convenDataList) {
			ArrayList<ProdVO> prodList = prodService.getMyProdList(convenData.getConvenVO().getConven_idx());

			for (ProdVO prodVO : prodList) {
				ProdData prodData = prodService.getProdData(prodVO.getProd_idx());
				prodDataList.add(prodData);
			}
		}

		model.addAttribute("prodDataList", prodDataList);

		return "/seller/sellProductMyList";
	}

	@RequestMapping("/seller/expiredSellProduct")
	public String deleteSellProduct(@RequestParam("prod_idx") String prod_idx) {

		ProdVO prodVO = prodService.getProdVO(prod_idx);

		prodVO.setProd_condition("EXPIRED");

		prodService.updateProdCondition(prodVO);

		return "redirect:/seller/sellProductMyList";
	}

	@RequestMapping("/seller/deleteSellProduct")
	public String delteSellProduct(@RequestParam("prod_idx") String prod_idx) {

		ProdVO prodVO = prodService.getProdVO(prod_idx);

		prodVO.setProd_condition("DELETE");

		prodService.updateProdCondition(prodVO);

		return "redirect:/seller/sellProductMyList";
	}

	@RequestMapping("/seller/updateSellProductForm")
	public String updateSellProductForm(Model model, @RequestParam("prod_idx") String prod_idx) {

		ProdData prodData = prodService.getProdData(prod_idx);

		ProdRegisterData prodRegisterData = prodService.getProdRegisterData(prodData);

		model.addAttribute("prodRegisterData", prodRegisterData);

		return "/seller/updateSellProductForm";
	}

	@RequestMapping("/admin/sellProductList")
	public String sellProductList(Model model, SearchData requestSearchData,
			@RequestParam(value = "prod_condition", required = false) String prod_condition) {

		// 필터링을 위한 아이템 컨디션 값 넘겨주기...
		if (prod_condition == null) {
			prod_condition = "all";
		}
		model.addAttribute("prod_condition", prod_condition);

		SearchData searchData = new SearchData(); // 모델에 넘겨줄 searchData 세팅
		ArrayList<ProdData> prodDataList = new ArrayList<ProdData>(); // 모델에 넘겨줄 prodDataList 세팅...

		// 페이지 이동 처리 로직...//
		if (requestSearchData.getPageNum() == null) { // 페이지 넘버 없을 때
			requestSearchData.setPageNum("1");
			requestSearchData.setStartNum("1");
			requestSearchData.setEndNum("10");
		} else {
			String pageNum = requestSearchData.getPageNum();
			requestSearchData.setStartNum(String.valueOf(Integer.parseInt(pageNum) * 10 - 9)); // 페이지 넘버로 rownum 시작값 세팅
			requestSearchData.setEndNum(String.valueOf(Integer.parseInt(pageNum) * 10)); // 페이지 넘버로 rownum 끝나는 값 세팅
		}
		searchData.setPageNum(requestSearchData.getPageNum());
		searchData.setStartNum(requestSearchData.getStartNum());
		searchData.setEndNum(requestSearchData.getEndNum());
		// 페이지 이동 처리 로직 끝...//

		// 검색 출력 로직...//
		if (requestSearchData.getSearchWord() != "" && requestSearchData.getSearchWord() != null) { // 검색 워드를 넣었을 때...

			// 검색 선택 넣기 로직...//
			String searchSelect = requestSearchData.getSearchSelect();
			searchData.setSearchSelect(searchSelect); // 검색 셀렉트에 남아 있게 하기 위하여...
			// 검색 선택 넣기 로직... 끝//

			// 검색 내용 넣기 로직...//
			String searchWord = requestSearchData.getSearchWord();
			searchData.setSearchWord(searchWord); // 검색한 내용 검색창에 남아 있게 하기 위하여...
			// 검색 내용 넣기 로직...끝//

			// 검색 종류가 같은테이블인 경우...
			if (searchSelect.equals("prod_content") || searchSelect.equals("prod_saleprice")) {
				// 물품 컨디션 검색에 따른 페이지 크기...//
				String paging = prodService.countSearch(requestSearchData);
				if (Integer.parseInt(paging) <= 1) {
					paging = "1";
				} else {
					paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
				}
				searchData.setPaging(paging);
				// 페이지 크기 끝...//

				ArrayList<ProdVO> searchProdList = prodService.getSearchProdList(requestSearchData); // 검색된 item list
				prodDataList = prodService.getProdDataList(searchProdList);
			} else { // 검색 종류가 다른 테이블인 경우...
				ArrayList<ProdVO> prodList = prodService.getProdList();
				prodDataList = prodService.getSearchProdDataList(prodList, searchSelect, searchWord);

				String paging = String.valueOf(prodDataList.size());
				if (Integer.parseInt(paging) <= 1) {
					paging = "1";
				} else {
					paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
				}
				searchData.setPaging(paging);
				// 페이지 크기 끝...//
			}

		} else {
			// 페이지 크기...//
			String paging = prodService.countList();
			if (Integer.parseInt(paging) <= 1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
			}
			searchData.setPaging(paging);
			// 페이지 크기 끝...//

			ArrayList<ProdVO> prodList = prodService.getProdList();
			prodDataList = prodService.getProdDataList(prodList);
		}
		model.addAttribute("searchData", searchData);
		model.addAttribute("prodDataList", prodDataList);

		return "/admin/sellProductList";
	}

	@RequestMapping("/seller/actionUpdateSellProductForm")
	public String actionUpdateSellProductForm(Model model, ProdVO requestProdVO,
			@RequestParam(value = "exdate") String exdate,
			@RequestParam(value = "prod_exHour", required = false) String prod_exHour,
			@RequestParam(value = "prod_exMin", required = false) String prod_exMin,
			@RequestParam(value = "prod_exSec", required = false) String prod_exSec) {
		String ex_date = null;
		System.out.println("prod_exHour: " + prod_exHour);
		System.out.println("prod_exMin: " + prod_exMin);
		System.out.println("prod_exSec: " + prod_exSec);
		// 유통기한 새로 안가져오면 처리....
		if (prod_exHour.length() <= 1 || prod_exMin.length() <= 1 || prod_exSec.length() <= 1) {
			ex_date = exdate;
			System.out.println(ex_date);
		} else {
			ex_date = requestProdVO.getProd_exdate() + " " + prod_exHour + ":" + prod_exMin + ":" + prod_exSec;
			System.out.println(ex_date);
		}
		Timestamp prod_timeExdate = Timestamp.valueOf(ex_date);
		ProdRegisterData prodRegisterData = new ProdRegisterData(requestProdVO, prod_timeExdate);
		prodService.updateSellProduct(prodRegisterData);
		return "redirect:/seller/sellProductMyList";

	}

	// 떨이 물품 상세보기 페이지....
	@RequestMapping("/readSellProduct")
	public String readSellProduct(Model model, @RequestParam("prod_idx") String prod_idx,
			@RequestParam(value = "requestPagingNum", required = false) Integer requestPagingNum) {
		// 떨이물품 상세 정보
		ProdData prodData = prodService.getProdData(prod_idx);
		model.addAttribute("prodData", prodData);

		// 물품 댓글리스트 페이징 처리
		if (requestPagingNum == null || requestPagingNum < 0) {
			requestPagingNum = 0;
		}
		PagingData requestPagingData = new PagingData(
				itemReService.getItemReListTotalDataCount(prodData.getItemVO().getItem_idx()), 5, requestPagingNum, 5);
		model.addAttribute("pagingData", requestPagingData);

		// 물품 댓글리스트 가져오기
		ArrayList<ItemReData> itemReDataList = itemReService.getItemReListByItemIdx(prodData.getItemVO().getItem_idx(),
				requestPagingData);
		model.addAttribute("itemReDataList", itemReDataList);

		return "/readSellProduct";
	}

	@RequestMapping("/seller/soldOutSellProduct")
	public String soldOutSellProduct(@RequestParam("prod_idx") String prod_idx) {

		ProdVO prodVO = prodService.getProdVO(prod_idx);

		prodVO.setProd_condition("SOLDOUT");

		prodService.updateProdCondition(prodVO);

		return "redirect:/seller/sellProductMyList";
	}

	@RequestMapping("/admin/deleteSellProd")
	public String deleteSellProd(@RequestParam("prod_idx") String prod_idx) {

		ProdVO prodVO = prodService.getProdVO(prod_idx);

		prodVO.setProd_condition("DELETE");

		prodService.updateProdCondition(prodVO);

		return "redirect:/admin/sellProductList";
	}

	@RequestMapping("/admin/expiredSellProd")
	public String expiredSellProd(@RequestParam("prod_idx") String prod_idx) {

		ProdVO prodVO = prodService.getProdVO(prod_idx);

		prodVO.setProd_condition("EXPIRED");

		prodService.updateProdCondition(prodVO);

		return "redirect:/admin/sellProductList";
	}
}
