package com.teamb.mth.restfulcontroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamb.mth.data.ConvenData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.majorservice.ConvenReService;
import com.teamb.mth.majorservice.ConvenService;
import com.teamb.mth.vo.ConvenVO;

@RestController
public class ConvenRESTfulController 
{
	@Autowired
	ConvenService convenService;
	@Autowired
	ConvenReService convenReService;

	@RequestMapping(value="/getConvenReContentByIdx", produces="text/plain;charset=UTF-8")
	public String getConvenReContentByIdx(String conven_re_idx)
	{
		String convenReContent = convenReService.getConvenReVO(conven_re_idx).getConven_re_content();
		return convenReContent;
	}
	
	@RequestMapping("/admin/filterSearchConvenList")
	public SearchData filterSearchConvenList(ConvenVO requestConvenVO, @RequestParam("searchSelect")String searchSelect, @RequestParam("searchWord")String searchWord) {
		
		String conven_condition = requestConvenVO.getConven_condition();
		
		SearchData searchData = new SearchData();
		searchData.setSearchSelect(searchSelect);
		searchData.setSearchWord(searchWord);

		//편의점 컨디션 검색에 따른 페이지 크기...//
		if(conven_condition.equals("NORMAL")) {
			if(searchSelect.equals("conven_name") || searchSelect.equals("conven_adress")) {
				//페이지 크기...//
				String paging = convenService.countSearchNormalList(searchData); //총 게시글 개수
				if(Integer.parseInt(paging)<=1) {
					paging = "1";
				} else {
					paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
				}
				searchData.setPaging(paging);
				//페이지 크기 끝...//
			} else {
				ArrayList<ConvenVO> convenList = convenService.getConvenNomalList();
				ArrayList<ConvenData> convenDataList = convenService.getSearchConvenDataList(convenList, searchSelect, searchWord);
				
				String paging = String.valueOf(convenDataList.size());
				if (Integer.parseInt(paging) <= 1) {
					paging = "1";
				} else {
					paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
				}
				searchData.setPaging(paging);
				// 검색 종류가 다른 테이블인 경우 끝...//
			}
		} else if(conven_condition.equals("REQUEST")) {
			if(searchSelect.equals("conven_name") || searchSelect.equals("conven_adress")) {
				//페이지 크기...//
				String paging = convenService.countSearchRequestList(searchData); //총 게시글 개수
				if(Integer.parseInt(paging)<=1) {
					paging = "1";
				} else {
					paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
				}
				searchData.setPaging(paging);
				//페이지 크기 끝...//
			} else {
				ArrayList<ConvenVO> convenList = convenService.getConvenRequestList();
				ArrayList<ConvenData> convenDataList = convenService.getSearchConvenDataList(convenList, searchSelect, searchWord);
				
				String paging = String.valueOf(convenDataList.size());
				if (Integer.parseInt(paging) <= 1) {
					paging = "1";
				} else {
					paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
				}
				searchData.setPaging(paging);
				// 검색 종류가 다른 테이블인 경우 끝...//
			}
			
		} else if(conven_condition.equals("DENIED")) {
			if(searchSelect.equals("conven_name") || searchSelect.equals("conven_adress")) {
				//페이지 크기...//
				String paging = convenService.countSearchDeniedList(searchData); //총 게시글 개수
				if(Integer.parseInt(paging)<=1) {
					paging = "1";
				} else {
					paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
				}
				searchData.setPaging(paging);
				//페이지 크기 끝...//
			} else {
				ArrayList<ConvenVO> convenList = convenService.getConvenDeniedList();
				ArrayList<ConvenData> convenDataList = convenService.getSearchConvenDataList(convenList, searchSelect, searchWord);
				
				String paging = String.valueOf(convenDataList.size());
				if (Integer.parseInt(paging) <= 1) {
					paging = "1";
				} else {
					paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
				}
				searchData.setPaging(paging);
				// 검색 종류가 다른 테이블인 경우 끝...//
			}
			
		} else {
			if(searchSelect.equals("conven_name") || searchSelect.equals("conven_adress")) {
				String paging = convenService.countSearchList(searchData); //총 게시글 개수
				if(Integer.parseInt(paging)<=1) {
					paging = "1";
				} else {
					paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
				}
				searchData.setPaging(paging);
			} else {
				ArrayList<ConvenVO> convenList = convenService.getConvenList();
				ArrayList<ConvenData> convenDataList = convenService.getSearchConvenDataList(convenList, searchSelect, searchWord);
				
				String paging = String.valueOf(convenDataList.size());
				if (Integer.parseInt(paging) <= 1) {
					paging = "1";
				} else {
					paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
				}
				searchData.setPaging(paging);
				// 검색 종류가 다른 테이블인 경우 끝...//
			}
		}
		return searchData;
	}
	
	
	@RequestMapping("/admin/filterConvenList")
	public SearchData filterConvenList(ConvenVO requestConvenVO) {
		
		String conven_condition = requestConvenVO.getConven_condition();
		
		SearchData searchData = new SearchData();
		//멤버 코드 검색에 따른 페이지 크기...//
		if(conven_condition.equals("NORMAL")) {
			//페이지 크기...//
			String paging = convenService.countNormalList(); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
			//페이지 크기 끝...//
		} else if(conven_condition.equals("REQUEST")) {
			//페이지 크기...//
			String paging = convenService.countRequestList(); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
			//페이지 크기 끝...//
		} else if(conven_condition.equals("DENIED")) {
			//페이지 크기...//
			String paging = convenService.countRequestList(); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
			//페이지 크기 끝...//
		} else {
			String paging = convenService.countList(); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
		}
		return searchData;
	}
	
	@RequestMapping("/admin/pageConvenList")
	public SearchData pageConvenList(SearchData requestSearchData, @RequestParam("nowPage") int nowPage) {
		
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
}
