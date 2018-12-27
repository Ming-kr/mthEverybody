package com.teamb.mth.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.teamb.mth.data.ItemData;
import com.teamb.mth.data.ItemReData;
import com.teamb.mth.data.ProdData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.data.SessionLoginedData;
import com.teamb.mth.majorservice.BrandService;
import com.teamb.mth.majorservice.ItemLikeService;
import com.teamb.mth.majorservice.ItemReService;
import com.teamb.mth.majorservice.ItemService;
import com.teamb.mth.majorservice.ItemTypeService;
import com.teamb.mth.majorservice.ProdService;
import com.teamb.mth.util.PagingData;
import com.teamb.mth.vo.BrandVO;
import com.teamb.mth.vo.ItemLikeVO;
import com.teamb.mth.vo.ItemReVO;
import com.teamb.mth.vo.ItemTypeVO;
import com.teamb.mth.vo.ItemVO;
import com.teamb.mth.vo.ProdVO;

@Controller
public class ItemController {

	@Autowired
	BrandService brandService;

	@Autowired
	ItemService itemService;

	@Autowired
	ItemTypeService itemTypeService;

	@Autowired
	ItemReService itemReService;

	@Autowired
	ItemLikeService itemLikeService;

	@Autowired
	ProdService prodService;

	////////////////////////// 1차 취합 후 새로 추가한 코드
	////////////////////////// 시작/////////////////////////////////////////////

	@RequestMapping("/admin/deleteItem")
	public String deleteItem(@RequestParam("item_idx") String item_idx) {
		itemService.deleteItemVO(item_idx);
		return "redirect:/admin/itemList";
	}

	@RequestMapping("/admin/updateNormalItem")
	public String updateNormalItem(@RequestParam("item_idx") String item_idx) {
		//판매 중단 물품 판매 가능으로 변경
		itemService.normalItemVO(item_idx);

		return "redirect:/admin/itemList";
	}

	@RequestMapping("/admin/updateExtinctItem")
	public String updateExtinctItem(@RequestParam("item_idx") String item_idx) {
		// 판매 물품 판매중단 처리
		itemService.extinctItemVO(item_idx);

		// 해당 떨이 물품 판매중단 처리
		prodService.deleteProdList(item_idx);

		return "redirect:/admin/itemList";
	}

	@RequestMapping("/admin/updateItemForm")
	public String updateItemForm(Model model, @RequestParam("item_idx") String item_idx) {

		ItemVO itemVO = itemService.getItemVO(item_idx);

		model.addAttribute("itemVO", itemVO);
		model.addAttribute("brandList", brandService.getBrandList());
		model.addAttribute("itemTypeList", itemTypeService.getItemTypeList());

		return "/admin/updateItemForm";
	}

	@RequestMapping("/admin/actionUpdateItemForm")
	public String actionUpdateItemForm(ItemVO requestItemVO, MultipartFile[] files) {

		// 이미지 파일 처리
		// 이미지 파일 처리
		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				ItemVO itemVO = itemService.getItemVO(requestItemVO.getItem_idx());
				requestItemVO.setItem_imgfilename(itemVO.getItem_imgfilename());
				continue;
			}
			System.out.println("name : " + file.getOriginalFilename());
			System.out.println("size : " + file.getSize());

			// String uploadPath = "C:\\tempupload\\";
			String uploadPath = new File("/upload/").getAbsolutePath();
			// uploadPath += file.getOriginalFilename();
			// 추가할 내용
			// 파일명 변경...시간 , 랜덤값, UUID
			// 날짜별 폴더 생성...
			uploadPath += "/";

			File f = new File(uploadPath, file.getOriginalFilename());
			try {
				file.transferTo(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 추가 내용...db에 itemVO 저장처리...
			requestItemVO.setItem_imgfilename(file.getOriginalFilename());
		}

		itemService.updateItemVO(requestItemVO);

		return "redirect:/admin/itemList";
	}

	// 판매 물품 등록 처리...
	@RequestMapping("/admin/actionRegisterItemForm")
	public String actionRegisterItemForm(ItemVO requestItemVO, MultipartFile[] files) {
		// 이미지 파일 처리
		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				requestItemVO.setItem_imgfilename("noImage");
				continue;
			}

			System.out.println("name : " + file.getOriginalFilename());
			System.out.println("size : " + file.getSize());

			/* String uploadPath = "C:\\upload\\"; */
			String uploadPath = new File("/upload/").getAbsolutePath();

			// 추가할 내용
			// 파일명 변경...시간 , 랜덤값, UUID
			// 날짜별 폴더 생성...

			File d = new File(uploadPath, file.getOriginalFilename());
			if (!d.exists()) {
				d.mkdirs();
			}
			uploadPath += "/";

			File f = new File(uploadPath, file.getOriginalFilename());
			try {
				file.transferTo(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 추가 내용...db에 itemVO 저장처리...
			requestItemVO.setItem_imgfilename(file.getOriginalFilename());
		}
		itemService.registerItemVO(requestItemVO);
		return "redirect:/admin/itemList";
	}

//////////////////////////1차 취합 후 새로 추가한 코드
//////////////////////////끝/////////////////////////////////////////////

	// 판매 물품 등록 페이지
	@RequestMapping("/admin/registerItemForm")
	public String registerItemForm(Model model) {
		// 물품 종류 와 브랜드 불러오기
		model.addAttribute("itemTypeList", itemTypeService.getItemTypeList());
		model.addAttribute("brandList", brandService.getBrandList());

		return "/admin/registerItemForm";
	}

	// 판매 물품 리스트 페이지
	@RequestMapping("/admin/itemList")
	public String ItemList(Model model, SearchData requestSearchData,
			@RequestParam(value = "item_condition", required = false) String item_condition) {

		// 필터링을 위한 멤버 코드 값 넘겨주기...
		if (item_condition == null) {
			item_condition = "all";
		}
		model.addAttribute("item_condition", item_condition);
		// 필터링을 위한 멤버 코드 값 넘겨주기 끝...

		SearchData searchData = new SearchData(); // 모델에 넘겨줄 searchData 세팅
		ArrayList<ItemData> itemDataList = new ArrayList<ItemData>(); // 모델에 넘겨줄 itemDataList 세팅

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

			// 아이템 컨디션 검색에 따른 페이지 크기...//
			if (searchSelect.equals("item_name") || searchSelect.equals("item_content")
					|| searchSelect.equals("item_originprice")) {
				String paging = itemService.countSearchList(requestSearchData);
				if (Integer.parseInt(paging) <= 1) {
					paging = "1";
				} else {
					paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
				}
				searchData.setPaging(paging);
				// 페이지 크기 끝...//
				ArrayList<ItemVO> searchItemList = itemService.getSearchItemList(requestSearchData); // 검색된 item list
				itemDataList = itemService.getItemDataList(searchItemList);
			} else {
				ArrayList<ItemVO> itemList = itemService.getItemList();
				itemDataList = itemService.getItemDataList(itemList);

				String paging = String.valueOf(itemDataList.size());
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
			String paging = itemService.countList();
			if (Integer.parseInt(paging) <= 1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
			}
			searchData.setPaging(paging);
			// 페이지 크기 끝...//

			ArrayList<ItemVO> itemList = itemService.getItemList();
			itemDataList = itemService.getItemDataList(itemList);
		}

		model.addAttribute("searchData", searchData);
		model.addAttribute("itemDataList", itemDataList);
		return "/admin/itemList";
	}

	// 물품 정보 보기
	@RequestMapping("readItem")
	public String readItem(HttpSession session, Model model, ItemVO requestItemVO,
			@RequestParam(value = "requestPagingNum", required = false) Integer requestPagingNum) {

		// 세션 정보 가져오기
		SessionLoginedData user = (SessionLoginedData) session.getAttribute("sessionLoginedData");

		// 물품 정보가져오기
		ItemVO itemVO = itemService.getItemVO(requestItemVO.getItem_idx());
		BrandVO brandVO = brandService.getBrandVO(itemVO.getBrand_idx());
		ItemTypeVO itemTypeVO = itemTypeService.getItemTypeVO(itemVO.getItem_type_idx());
		int itemLikeCount = itemLikeService.countItemLike(itemVO.getItem_idx());
		ItemData itemData = new ItemData(itemVO, brandVO, itemTypeVO, itemLikeCount);
		model.addAttribute("itemData", itemData);

		// 해당 떨이 물품 정보 가져오기
		ArrayList<ProdVO> prodList = prodService.getProdDataByItemIdx(requestItemVO.getItem_idx());
		ArrayList<ProdData> prodDataList = prodService.getNormalProdDataList(prodList);
		model.addAttribute("prodDataList", prodDataList);

		// 로그인한 멤버idx가 좋아요 한건지 체크...
		ArrayList<ItemLikeVO> likeMemberList = itemLikeService.likeMemberList(itemVO.getItem_idx());
		boolean itemLikeCheck = false;

		if (user != null) {
			for (ItemLikeVO itemLikeVO : likeMemberList) {
				if (user.getMember_idx().equals(itemLikeVO.getMember_idx())) {
					itemLikeCheck = true;
					break;
				}
			}
		}
		model.addAttribute("itemLikeCheck", itemLikeCheck);
		model.addAttribute("prodDataList", prodDataList);

		// 물품 댓글리스트 페이징 처리
		if (requestPagingNum == null || requestPagingNum < 0) {
			requestPagingNum = 0;
		}
		PagingData requestPagingData = new PagingData(
				itemReService.getItemReListTotalDataCount(requestItemVO.getItem_idx()), 5, requestPagingNum, 5);
		model.addAttribute("pagingData", requestPagingData);

		// 물품 댓글리스트 가져오기
		ArrayList<ItemReData> itemReDataList = itemReService.getItemReListByItemIdx(requestItemVO.getItem_idx(),
				requestPagingData);
		model.addAttribute("itemReDataList", itemReDataList);

		return "readItem";
	}

	// 물품 댓글 작성
	@RequestMapping("actionWriteItemReContent")
	public String actionWriteItemReContent(ItemReVO requestItemReVO, Integer requestPagingNum) {
		itemReService.writeItemReContent(requestItemReVO);
		String item_idx = requestItemReVO.getItem_idx();

		return "redirect:/readItem?requestPagingNum=" + requestPagingNum + "&item_idx=" + item_idx;
	}

	// 물품 댓글 수정
	@RequestMapping("actionUpdateItemReContent")
	public String actionUpdateItemReContent(ItemReVO requestItemReVO, Integer requestPagingNum) {
		itemReService.updateItemReContent(requestItemReVO);
		String item_idx = requestItemReVO.getItem_idx();

		return "redirect:/readItem?requestPagingNum=" + requestPagingNum + "&item_idx=" + item_idx;
	}

	// 물품 댓글 삭제
	@RequestMapping("actionDeleteItemReContent")
	public String actionDeleteItemReContent(ItemReVO requestItemReVO) {
		itemReService.deleteItemRe(requestItemReVO.getItem_re_idx());
		String item_idx = requestItemReVO.getItem_idx();

		return "redirect:/readItem?item_idx=" + item_idx;
	}
}
