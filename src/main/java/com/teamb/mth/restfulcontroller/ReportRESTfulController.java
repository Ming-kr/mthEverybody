package com.teamb.mth.restfulcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamb.mth.boardservice.ReportService;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.data.SessionLoginedData;
import com.teamb.mth.vo.ReportVO;

@RestController
public class ReportRESTfulController {
	@Autowired
	ReportService reportService;
	
	@RequestMapping("popup/actionWriteReportForm")
	public String actionWriteReportForm(HttpSession session, ReportVO requestReportVO) {
		
		if(session==null) {
			return "fail";
		}
		if(requestReportVO.getReport_content().length()<=0) {
			return "fail";
		}
		
		SessionLoginedData user = (SessionLoginedData)session.getAttribute("sessionLoginedData");
		requestReportVO.setMember_idx(user.getMember_idx());
		if(reportService.confirmReportExist(requestReportVO) != null) {
			return "alreadyExist";
		}
		reportService.writeReport(requestReportVO);
		if(reportService.countReportedTimes(requestReportVO) >= 3) {
			reportService.reportConditionUpdateReported(requestReportVO);;
		}
		return "success";
	}
	
	@RequestMapping("/admin/filterSearchReportList")
	public SearchData filterSearchReportList(ReportVO requestReportVO, @RequestParam("searchSelect")String searchSelect, @RequestParam("searchWord")String searchWord) {
		
		String report_condition = requestReportVO.getReport_condition();
		
		SearchData searchData = new SearchData();
		searchData.setSearchSelect(searchSelect);
		searchData.setSearchWord(searchWord);

		//멤버 코드 검색에 따른 페이지 크기...//
		if(report_condition.equals("PROBLEM")) {
			//페이지 크기...//
			String paging = reportService.countSearchProblemList(searchData); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
			//페이지 크기 끝...//
		} else if(report_condition.equals("CLEAR")) {
			//페이지 크기...//
			String paging = reportService.countSearchClearList(searchData); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
			//페이지 크기 끝...//
		} else if(report_condition.equals("DENIED")) {
			//페이지 크기...//
			String paging = reportService.countSearchClearList(searchData); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
			//페이지 크기 끝...//
		}else {
			//페이지 크기...//
			String paging = reportService.countSearchList(searchData); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
			//페이지 크기 끝...//
		}
		return searchData;
	}
	
	
	@RequestMapping("/admin/filterReportList")
	public SearchData filterReportList(ReportVO requestReportVO) {
		
		String report_condition = requestReportVO.getReport_condition();
		
		SearchData searchData = new SearchData();
		//멤버 코드 검색에 따른 페이지 크기...//
		if(report_condition.equals("PROBLEM")) {
			//페이지 크기...//
			String paging = reportService.countProblemList(); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
			//페이지 크기 끝...//
		} else if(report_condition.equals("CLEAR")) {
			//페이지 크기...//
			String paging = reportService.countClearList(); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
			//페이지 크기 끝...//
		} else if(report_condition.equals("DENIED")) {
			//페이지 크기...//
			String paging = reportService.countClearList(); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
			//페이지 크기 끝...//
		}else {
			String paging = reportService.countList(); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
		}
		return searchData;
	}
	
	@RequestMapping("/admin/pageReportList")
	public SearchData pageReportList(SearchData requestSearchData, @RequestParam("nowPage") int nowPage) {
		
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
