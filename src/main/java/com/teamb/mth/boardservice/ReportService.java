package com.teamb.mth.boardservice;

import java.util.ArrayList;

import com.teamb.mth.data.ReportData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.vo.ReportVO;

public interface ReportService {

	public void writeReport(ReportVO requestReportVO);
	
	public ReportVO confirmReportExist(ReportVO requestReportVO);
	
	public int countReportedTimes(ReportVO requestReportVO);
	
	// 관리자 신고리스트 관련...
	public ArrayList<ReportData> getReportDataList(ArrayList<ReportVO> reportList);

	public ArrayList<ReportData> getSearchReportDataList(ArrayList<ReportVO> reportList, String searchSelect,
			String searchWord);

	// 모든 신고글
	public ArrayList<ReportVO> getReportList();

	public String countList();

	public ArrayList<ReportVO> getSearchReportList(SearchData requestSearchData);

	public String countSearchList(SearchData requestSearchData);

	// 처리중
	public ArrayList<ReportVO> getReportProblemList();

	public String countProblemList();

	public String countSearchProblemList(SearchData requestSearchData);

	// 처리안됨
	public ArrayList<ReportVO> getReportClearList();

	public String countClearList();

	public String countSearchClearList(SearchData requestSearchData);

	// 처리거부
	public ArrayList<ReportVO> getReportDeniedList();

	public String countDeniedList();

	public String countSearchDeniedList(SearchData requestSearchData);
	
	public void reportConditionUpdateDenied(ReportVO requestReportVO);

	// 관리자 신고글 읽기
	public ReportData getReadReport(ReportVO reportVO);
	
	//처리완료
	public void reportConditionUpdateClear(ReportVO requestReportVO);
	
	public void reportConditionUpdateReported(ReportVO requestReportVO);
}
