package com.teamb.mth.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teamb.mth.boardservice.ReportService;
import com.teamb.mth.data.ReportData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.vo.ReportVO;

@Controller
public class ReportController {
	@Autowired
	ReportService reportService;
	
	@RequestMapping("/admin/reportList")
	public String reportList(Model model, SearchData requestSearchData, @RequestParam(value="report_condition", required=false)String report_condition ) {
		
		//필터링을 위한 리포트 컨디션 넘겨주기...
		if(report_condition == null) {
			report_condition = "all";
		}
		model.addAttribute("report_condition", report_condition);
		//필터링을 위한 리포트 컨디션 넘겨주기 끝...
		
		SearchData searchData = new SearchData(); // 모델에 넘겨줄 searchData 세팅
		ArrayList<ReportData> reportDataList = new ArrayList<ReportData>();
		
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
			
			if(searchSelect.equals("report_where") || searchSelect.equals("report_content")) {
				// 리포트 컨디션 에 따른 페이지 크기...//
				String paging = reportService.countSearchList(requestSearchData);
				if (Integer.parseInt(paging) <= 1) {
					paging = "1";
				} else {
					paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
				}
				searchData.setPaging(paging);
				// 페이지 크기 끝...//
				
				ArrayList<ReportVO> reportList = reportService.getSearchReportList(requestSearchData);
				reportDataList = reportService.getReportDataList(reportList);
			} else {
				ArrayList<ReportVO> reportList = reportService.getReportList();
				reportDataList = reportService.getSearchReportDataList(reportList, searchSelect, searchWord);
			}
		} else {
			// 페이지 크기...//
			String paging = reportService.countList();
			if (Integer.parseInt(paging) <= 1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
			}
			searchData.setPaging(paging);
			// 페이지 크기 끝...//
			
			ArrayList<ReportVO> reportList = reportService.getReportList();
			reportDataList = reportService.getReportDataList(reportList);
		}
		
		model.addAttribute("searchData", searchData);
		model.addAttribute("reportDataList", reportDataList);
		
		return "admin/reportList";
	}
	
	@RequestMapping("/admin/readReport")
	public String readReport(ReportVO requestReportVO, Model model) {
		ReportData reportData = reportService.getReadReport(requestReportVO);
		model.addAttribute("reportData", reportData);
		
		return "/admin/readReport";
	}
	
	@RequestMapping("/admin/updateReportConditionDenied")
	public String updateReportConditionDenied(ReportVO requestReportVO) {
		reportService.reportConditionUpdateDenied(requestReportVO);
		
		return "redirect:reportList";
	}
	
	@RequestMapping("/admin/updateReportConditionCleared")
	public String updateReportConditionCleared(ReportVO requestReportVO) {
		reportService.reportConditionUpdateClear(requestReportVO);
		return "redirect:reportList";
	}
	
	
}
