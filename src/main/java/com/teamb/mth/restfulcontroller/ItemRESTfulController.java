package com.teamb.mth.restfulcontroller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamb.mth.data.ItemData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.data.SessionLoginedData;
import com.teamb.mth.majorservice.ItemLikeService;
import com.teamb.mth.majorservice.ItemReService;
import com.teamb.mth.majorservice.ItemService;
import com.teamb.mth.vo.ItemLikeVO;
import com.teamb.mth.vo.ItemVO;

@RestController
public class ItemRESTfulController {
	@Autowired
	ItemService itemService;
	
	@Autowired
	ItemReService itemReService;
	
	@Autowired
	ItemLikeService itemLikeService;
	
	@RequestMapping("/admin/filterSearchItemList")
	public SearchData filterSearchItemList(ItemVO requestItemVO, @RequestParam("searchSelect")String searchSelect, @RequestParam("searchWord")String searchWord) {
		
		String item_condition = requestItemVO.getItem_condition();
		
		SearchData searchData = new SearchData();
		searchData.setSearchSelect(searchSelect);
		searchData.setSearchWord(searchWord);

		//멤버 코드 검색에 따른 페이지 크기...//
		if(item_condition.equals("NORMAL")) {
			if(searchSelect.equals("item_name") || searchSelect.equals("item_content") || searchSelect.equals("item_originprice")) {
				//페이지 크기...//
				String paging = itemService.countSearchNormalList(searchData); //총 게시글 개수
				if(Integer.parseInt(paging)<=1) {
					paging = "1";
				} else {
					paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
				}
				searchData.setPaging(paging);
				//페이지 크기 끝...//
			} else {
				ArrayList<ItemVO> itemList = itemService.getItemNormalList();
				ArrayList<ItemData> itemDataList = itemService.getSearchItemDataList(itemList, searchSelect, searchWord);
				
				String paging = String.valueOf(itemDataList.size());
				if(Integer.parseInt(paging)<=1) {
					paging = "1";
				} else {
					paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
				}
				searchData.setPaging(paging);
			}
		} else if(item_condition.equals("EXTINCTION")) {
			if(searchSelect.equals("item_name") || searchSelect.equals("item_content") || searchSelect.equals("item_originprice")) {
				//페이지 크기...//
				String paging = itemService.countSearchExtinctionList(searchData); //총 게시글 개수
				if(Integer.parseInt(paging)<=1) {
					paging = "1";
				} else {
					paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
				}
				searchData.setPaging(paging);
				//페이지 크기 끝...//
			} else {
				ArrayList<ItemVO> itemList = itemService.getItemExtinctionList();
				ArrayList<ItemData> itemDataList = itemService.getSearchItemDataList(itemList, searchSelect, searchWord);
				
				String paging = String.valueOf(itemDataList.size());
				if(Integer.parseInt(paging)<=1) {
					paging = "1";
				} else {
					paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
				}
				searchData.setPaging(paging);
			}
		} else {
			if(searchSelect.equals("item_name") || searchSelect.equals("item_content") || searchSelect.equals("item_originprice")) {
				String paging = itemService.countSearchList(searchData); //총 게시글 개수
				if(Integer.parseInt(paging)<=1) {
					paging = "1";
				} else {
					paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
				}
				searchData.setPaging(paging);
			} else {
				ArrayList<ItemVO> itemList = itemService.getItemList();
				ArrayList<ItemData> itemDataList = itemService.getSearchItemDataList(itemList, searchSelect, searchWord);
				
				String paging = String.valueOf(itemDataList.size());
				if(Integer.parseInt(paging)<=1) {
					paging = "1";
				} else {
					paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
				}
				searchData.setPaging(paging);
			}
		}
		return searchData;
	}
	
	
	
	@RequestMapping("/admin/filterItemList")
	public SearchData filterItemList(ItemVO requestItemVO) {
		
		String item_condition = requestItemVO.getItem_condition();
		
		SearchData searchData = new SearchData();
		//멤버 코드 검색에 따른 페이지 크기...//
		if(item_condition.equals("NORMAL")) {
			//페이지 크기...//
			String paging = itemService.countNormalList(); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
			//페이지 크기 끝...//
		} else if(item_condition.equals("EXTINCTION")) {
			//페이지 크기...//
			String paging = itemService.countExtinctionList(); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
			//페이지 크기 끝...//
		} else {
			String paging = itemService.countList(); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
		}
		return searchData;
	}
	
	@RequestMapping("/admin/pageItemList")
	public SearchData pageItemList(SearchData requestSearchData, @RequestParam("nowPage") int nowPage) {
		
		SearchData searchData = new SearchData();
		//페이지 이동 처리 로직...//
		if(nowPage<=1) { //페이지 넘버 없을 때
			requestSearchData.setPageNum("1");
			requestSearchData.setStartNum("1");
			requestSearchData.setEndNum("10"); 
		} else {
			System.out.println("paging:"+nowPage);
			requestSearchData.setPageNum(String.valueOf(nowPage));
			requestSearchData.setStartNum(String.valueOf(nowPage*10-9)); //페이지 넘버로 rownum 시작값 세팅
			requestSearchData.setEndNum(String.valueOf(nowPage*10)); //페이지 넘버로 rownum 끝나는 값 세팅
		}
		searchData.setPageNum(requestSearchData.getPageNum());
		searchData.setStartNum(requestSearchData.getStartNum());
		searchData.setEndNum(requestSearchData.getEndNum());
		//페이지  이동 처리 로직 끝...//
		
		return searchData;
	}
	
	@RequestMapping(value="/getItemReContentByIdx", produces="text/plain;charset=UTF-8")
	public String getItemReContentByIdx(String item_re_idx)
	{
		String itemReContent = itemReService.getItemReVO(item_re_idx).getItem_re_content();
		
		return itemReContent;
	}
	
	@RequestMapping("likeItem")
	public HashMap<String, String> likeItem(ItemLikeVO requestItemLikeVO, HttpSession session) {
		SessionLoginedData sessionLoginedData = (SessionLoginedData)session.getAttribute("sessionLoginedData");
		
		//likeItem 세션여부, insert여부, likeItemCount 한꺼번에 가져오기... 
		HashMap<String, String> likeItem = new HashMap<String, String>();
		if(sessionLoginedData==null) {
			likeItem.put("success", "fail");
			return likeItem;
		} else {
			likeItem.put("success", "success");
			requestItemLikeVO.setMember_idx(sessionLoginedData.getMember_idx());
			String likeItemInsert = itemLikeService.likeItem(requestItemLikeVO);
			String likeItemCount = String.valueOf(itemLikeService.countItemLike(requestItemLikeVO.getItem_idx()));
			likeItem.put("likeItemInsert", likeItemInsert);
			likeItem.put("likeItemCount", likeItemCount);
		}
		return likeItem;
	}
	
}
