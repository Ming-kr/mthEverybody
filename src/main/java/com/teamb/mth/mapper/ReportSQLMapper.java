package com.teamb.mth.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.teamb.mth.data.SearchData;
import com.teamb.mth.vo.ReportVO;

public interface ReportSQLMapper {
	// 중복신고 안되게 검사
	@Select("SELECT * FROM mth_report WHERE report_where = #{report_where} AND report_where_idx = #{report_where_idx} AND member_idx = #{member_idx}")
	public ReportVO selectByWhereAndIdxAndMemberIdx(ReportVO requestReportVO);

	@Select("SELECT COUNT(1) FROM mth_report WHERE report_WHERE = #{report_where} AND report_where_idx = #{report_where_idx}")
	public String countByWhereAndIdx(ReportVO requestReportVO);

	@Insert("INSERT INTO mth_report VALUES(seq_report_idx.nextval,#{member_idx},#{report_title},#{report_content},#{report_where},#{report_where_idx},'PROBLEM',SYSDATE)")
	public void insert(ReportVO requestReportVO);

	// 관리자 신고리스트 용...
	// 모든 신고글
	@Select("SELECT * FROM mth_report ORDER BY report_idx")
	public ArrayList<ReportVO> selectList();

	@Select("SELECT COUNT(1) FROM mth_report")
	public String countAll();

	@Select("SELECT * FROM mth_report WHERE ${searchSelect} LIKE '%'||#{searchWord}||'%'t ORDER BY report_idx")
	public ArrayList<ReportVO> selectSearchList(SearchData requestSearchData);

	@Select("SELECT COUNT(1) FROM mth_report WHERE ${searchSelect} LIKE '%'||#{searchWord}||'%'")
	public String countSearchList(SearchData requestSearchData);

	// 처리중
	@Select("SELECT * FROM mth_report WHERE report_condition = 'PROBLEM' ORDER BY report_idx")
	public ArrayList<ReportVO> selectProblemList();

	@Select("SELECT COUNT(1) FROM mth_report WHERE report_condition = 'PROBLEM'")
	public String countProblemAll();

	@Select("SELECT COUNT(1) FROM mth_report WHERE report_condition = 'PROBLEM' AND ${searchSelect} LIKE '%'||#{searchWord}||'%'")
	public String countSearchProblemList(SearchData requestSearchData);

	// 처리 완료
	@Select("SELECT * FROM mth_report WHERE report_condition = 'CLEAR' ORDER BY report_idx")
	public ArrayList<ReportVO> selectClearList();

	@Select("SELECT COUNT(1) FROM mth_report WHERE report_condition = 'CLEAR'")
	public String countClearAll();

	@Select("SELECT COUNT(1) FROM mth_report WHERE report_condition = 'CLEAR' AND ${searchSelect} LIKE '%'||#{searchWord}||'%'")
	public String countSearchClearList(SearchData requestSearchData);

	@Update("UPDATE mth_report SET report_condition='CLEAR' WHERE report_where='${report_where}' AND report_where_idx='${report_where_idx}'")
	public void updateConditionCLEAR(ReportVO requestReportVO);

	// 처리 거부
	@Select("SELECT * FROM mth_report WHERE report_condition = 'DENIED' ORDER BY report_idx")
	public ArrayList<ReportVO> selectDeniedList();

	@Select("SELECT COUNT(1) FROM mth_report WHERE report_condition = 'DENIED'")
	public String countDeniedAll();

	@Select("SELECT COUNT(1) FROM mth_report WHERE report_condition = 'DENIED' AND ${searchSelect} LIKE '%'||#{searchWord}||'%'")
	public String countSearchDeniedList(SearchData requestSearchData);

	@Update("UPDATE mth_report SET report_condition='DENIED' WHERE report_where='${report_where}' AND report_where_idx='${report_where_idx}'")
	public void updateConditionDENIED(ReportVO requestReportVO);

	// 관리자 신고글 읽기
	@Select("SELECT * FROM mth_report WHERE report_idx=${report_idx}")
	public ReportVO adminReadReport(ReportVO reportVO);
	
	//신고글 3개 이상 신고처리
	@Update("UPDATE mth_report SET report_condition='REPORTED' WHERE report_where='${report_where}' AND report_where_idx='${report_where_idx}'")
	public void updateConditionReported(ReportVO requestReportVO);

}
