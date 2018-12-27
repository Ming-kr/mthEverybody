package com.teamb.mth.boardservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamb.mth.data.ReportData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.mapper.FreeReSQLMapper;
import com.teamb.mth.mapper.FreeSQLMapper;
import com.teamb.mth.mapper.ItemSQLMapper;
import com.teamb.mth.mapper.MemberSQLMapper;
import com.teamb.mth.mapper.ReportSQLMapper;
import com.teamb.mth.mapper.YumReSQLMapper;
import com.teamb.mth.mapper.YumSQLMapper;
import com.teamb.mth.vo.FreeReVO;
import com.teamb.mth.vo.FreeVO;
import com.teamb.mth.vo.MemberVO;
import com.teamb.mth.vo.ReportVO;
import com.teamb.mth.vo.YumReVO;
import com.teamb.mth.vo.YumVO;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	ReportSQLMapper reportSQLMapper;

	@Autowired
	MemberSQLMapper memberSQLMapper;

	@Autowired
	YumSQLMapper yumSQLMapper;

	@Autowired
	FreeSQLMapper freeSQLMapper;

	@Autowired
	ItemSQLMapper itemSQLMapper;

	@Autowired
	YumReSQLMapper yumReSQLMapper;

	@Autowired
	FreeReSQLMapper freeReSQLMapper;

	@Override
	public void writeReport(ReportVO requestReportVO) {
		// TODO Auto-generated method stub
		reportSQLMapper.insert(requestReportVO);
	}

	@Override
	public ArrayList<ReportData> getReportDataList(ArrayList<ReportVO> reportList) {
		// TODO Auto-generated method stub
		ArrayList<ReportData> reportDataList = new ArrayList<ReportData>();
		for (ReportVO reportVO : reportList) {

			MemberVO memberVO = memberSQLMapper.selectByIdx(reportVO.getMember_idx());
			MemberVO reportedMemberVO = null;
			String board_category = null;
			String board_title = null;
			String board_content = null;
			String board_state = null;
			String board_count = null;
			String board_writedate = null;

			if (reportVO.getReport_where().equals("mth_yum")) {
				YumVO yumVO = yumSQLMapper.selectByIdx(reportVO.getReport_where_idx());
				if (yumVO != null) {
					reportedMemberVO = memberSQLMapper.selectByIdx(yumVO.getMember_idx());
					board_category = yumVO.getYum_category();
					board_title = yumVO.getYum_title();
					board_content = yumVO.getYum_content();
					board_state = yumVO.getYum_state();
					board_count = yumVO.getYum_count();
					board_writedate = yumVO.getYum_writedate();
				}

			} else if (reportVO.getReport_where().equals("mth_free")) {
				FreeVO freeVO = freeSQLMapper.selectByIdx(reportVO.getReport_where_idx());
				if (freeVO != null) {
					reportedMemberVO = memberSQLMapper.selectByIdx(freeVO.getMember_idx());
					board_category = freeVO.getFree_category();
					board_title = freeVO.getFree_title();
					board_content = freeVO.getFree_content();
					board_state = freeVO.getFree_state();
					board_count = freeVO.getFree_count();
					board_writedate = freeVO.getFree_writedate();
				}

			}
			int board_reportCount = Integer.parseInt(reportSQLMapper.countByWhereAndIdx(reportVO));
			reportDataList.add(new ReportData(reportVO, memberVO, reportedMemberVO, board_category, board_title,
					board_content, board_state, board_count, board_writedate, board_reportCount));
		}
		return reportDataList;
	}

	@Override
	public ArrayList<ReportData> getSearchReportDataList(ArrayList<ReportVO> reportList, String searchSelect,
			String searchWord) {
		// TODO Auto-generated method stub
		ArrayList<ReportData> reportDataList = new ArrayList<ReportData>();
		for (ReportVO reportVO : reportList) {

			MemberVO memberVO = memberSQLMapper.selectByIdx(reportVO.getMember_idx());
			MemberVO reportedMemberVO = null;
			String board_category = null;
			String board_title = null;
			String board_content = null;
			String board_state = null;
			String board_count = null;
			String board_writedate = null;

			if (reportVO.getReport_where().equals("mth_yum")) {
				YumVO yumVO = yumSQLMapper.selectByIdx(reportVO.getReport_where_idx());
				reportedMemberVO = memberSQLMapper.selectByIdx(yumVO.getMember_idx());
				board_category = yumVO.getYum_category();
				board_title = yumVO.getYum_title();
				board_content = yumVO.getYum_content();
				board_state = yumVO.getYum_state();
				board_count = yumVO.getYum_count();
				board_writedate = yumVO.getYum_writedate();
			} else if (reportVO.getReport_where().equals("mth_free")) {
				FreeVO freeVO = freeSQLMapper.selectByIdx(reportVO.getReport_where_idx());
				reportedMemberVO = memberSQLMapper.selectByIdx(freeVO.getMember_idx());
				board_category = freeVO.getFree_category();
				board_title = freeVO.getFree_title();
				board_content = freeVO.getFree_content();
				board_state = freeVO.getFree_state();
				board_count = freeVO.getFree_count();
				board_writedate = freeVO.getFree_writedate();
			}
			int board_reportCount = Integer.parseInt(reportSQLMapper.countByWhereAndIdx(reportVO));
			reportDataList.add(new ReportData(reportVO, memberVO, reportedMemberVO, board_category, board_title,
					board_content, board_state, board_count, board_writedate, board_reportCount));
		}
		return reportDataList;
	}

	@Override
	public ArrayList<ReportVO> getReportList() {
		// TODO Auto-generated method stub
		return reportSQLMapper.selectList();
	}

	@Override
	public String countList() {
		// TODO Auto-generated method stub
		return reportSQLMapper.countAll();
	}

	@Override
	public ArrayList<ReportVO> getSearchReportList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return reportSQLMapper.selectSearchList(requestSearchData);
	}

	@Override
	public String countSearchList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return reportSQLMapper.countSearchList(requestSearchData);
	}

	@Override
	public String countProblemList() {
		// TODO Auto-generated method stub
		return reportSQLMapper.countProblemAll();
	}

	@Override
	public String countSearchProblemList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return reportSQLMapper.countSearchProblemList(requestSearchData);
	}

	@Override
	public String countClearList() {
		// TODO Auto-generated method stub
		return reportSQLMapper.countClearAll();
	}

	@Override
	public String countSearchClearList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return reportSQLMapper.countSearchClearList(requestSearchData);
	}

	// 신고글 읽기
	@Override
	public ReportData getReadReport(ReportVO reportVO) {
		// TODO Auto-generated method stub
		ReportData reportData = new ReportData();
		reportVO = reportSQLMapper.adminReadReport(reportVO);
		MemberVO memberVO = memberSQLMapper.selectByIdx(reportVO.getMember_idx());
		MemberVO reportedMemberVO = null;
		String board_category = null;
		String board_title = null;
		String board_content = null;
		String board_state = null;
		String board_count = null;
		String board_writedate = null;

		if (reportVO.getReport_where().equals("mth_yum")) {
			YumVO yumVO = yumSQLMapper.selectByIdx(reportVO.getReport_where_idx());
			if (yumVO != null) {
				reportedMemberVO = memberSQLMapper.selectByIdx(yumVO.getMember_idx());
				board_category = yumVO.getYum_category();
				board_title = yumVO.getYum_title();
				board_content = yumVO.getYum_content();
				board_state = yumVO.getYum_state();
				board_count = yumVO.getYum_count();
				board_writedate = yumVO.getYum_writedate();
			}

		} else if (reportVO.getReport_where().equals("mth_free")) {
			FreeVO freeVO = freeSQLMapper.selectByIdx(reportVO.getReport_where_idx());
			if (freeVO != null) {
				reportedMemberVO = memberSQLMapper.selectByIdx(freeVO.getMember_idx());
				board_category = freeVO.getFree_category();
				board_title = freeVO.getFree_title();
				board_content = freeVO.getFree_content();
				board_state = freeVO.getFree_state();
				board_count = freeVO.getFree_count();
				board_writedate = freeVO.getFree_writedate();
			}

		}

		int board_reportCount = Integer.parseInt(reportSQLMapper.countByWhereAndIdx(reportVO));
		reportData = new ReportData(reportVO, memberVO, reportedMemberVO, board_category, board_title, board_content,
				board_state, board_count, board_writedate, board_reportCount);

		return reportData;
	}

	@Override
	public ArrayList<ReportVO> getReportDeniedList() {
		// TODO Auto-generated method stub
		return reportSQLMapper.selectDeniedList();
	}

	@Override
	public String countDeniedList() {
		// TODO Auto-generated method stub
		return reportSQLMapper.countDeniedAll();
	}

	@Override
	public String countSearchDeniedList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return reportSQLMapper.countSearchDeniedList(requestSearchData);
	}

	@Override
	public ArrayList<ReportVO> getReportClearList() {
		// TODO Auto-generated method stub
		return reportSQLMapper.selectClearList();
	}

	@Override
	public ArrayList<ReportVO> getReportProblemList() {
		// TODO Auto-generated method stub
		return reportSQLMapper.selectProblemList();
	}

	@Override
	public void reportConditionUpdateDenied(ReportVO requestReportVO) {
		// TODO Auto-generated method stub
		if (requestReportVO.getReport_where().equals("mth_yum")) {
			YumVO yumVO = yumSQLMapper.selectByIdx(requestReportVO.getReport_where_idx());
			if(yumVO.getYum_state().equals("REPORTED")) {
				yumVO.setYum_state("NORMAL");
				yumSQLMapper.updateState(yumVO);
			}
		}else if (requestReportVO.getReport_where().equals("mth_free")) {
			FreeVO freeVO = freeSQLMapper.selectByIdx(requestReportVO.getReport_where_idx());
			if(freeVO.getFree_state().equals("REPORTED")) {
				freeVO.setFree_state("NORMAL");
				freeSQLMapper.updateState(freeVO);
			}
		}
		reportSQLMapper.updateConditionDENIED(requestReportVO);
	}

	@Override
	public void reportConditionUpdateClear(ReportVO requestReportVO) {
		if (requestReportVO.getReport_where().equals("mth_yum")) {
			YumVO yumVO = yumSQLMapper.selectByIdx(requestReportVO.getReport_where_idx());
			yumVO.setYum_state("HIDE");
			ArrayList<YumReVO> yumReList = yumReSQLMapper.selectByYumIdxForDelete(yumVO.getYum_idx());
			if (yumReList != null) {
				for (YumReVO yumReVO : yumReList) {
					yumReSQLMapper.updateReState(yumReVO.getYum_re_idx());
				}
			}
			yumSQLMapper.updateState(yumVO);
			reportSQLMapper.updateConditionCLEAR(requestReportVO);
		} else if (requestReportVO.getReport_where().equals("mth_free")) {

			FreeVO freeVO = freeSQLMapper.selectByIdx(requestReportVO.getReport_where_idx());
			freeVO.setFree_state("HIDE");
			ArrayList<FreeReVO> freeReList = freeReSQLMapper.selectByFreeIdxForDelete(freeVO.getFree_idx());
			if (freeReList != null) {
				for (FreeReVO freeReVO : freeReList) {
					freeReSQLMapper.updateReStateToHide(freeReVO.getFree_re_idx());
				}
			}

			freeSQLMapper.updateState(freeVO);
			reportSQLMapper.updateConditionCLEAR(requestReportVO);
		}
	}

	@Override
	public ReportVO confirmReportExist(ReportVO requestReportVO) {
		// TODO Auto-generated method stub
		return reportSQLMapper.selectByWhereAndIdxAndMemberIdx(requestReportVO);
	}

	@Override
	public int countReportedTimes(ReportVO requestReportVO) {
		// TODO Auto-generated method stub
		return Integer.parseInt(reportSQLMapper.countByWhereAndIdx(requestReportVO));
	}

	@Override
	public void reportConditionUpdateReported(ReportVO requestReportVO) {
		// TODO Auto-generated method stub
		if (requestReportVO.getReport_where().equals("mth_yum")) {
			YumVO yumVO = yumSQLMapper.selectByIdx(requestReportVO.getReport_where_idx());
			if(yumVO.getYum_state().equals("NORMAL")) {
				yumVO.setYum_state("REPORTED");
				yumSQLMapper.updateState(yumVO);
			}
		} else if(requestReportVO.getReport_where().equals("mth_free")) {
			FreeVO freeVO = freeSQLMapper.selectByIdx(requestReportVO.getReport_where_idx());
			if(freeVO.getFree_state().equals("NORMAL")) {
				freeVO.setFree_state("REPORTED");
				freeSQLMapper.updateState(freeVO);
			}
		}
	}
}
