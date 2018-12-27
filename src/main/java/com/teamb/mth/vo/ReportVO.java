package com.teamb.mth.vo;

public class ReportVO {
	private String report_idx;
	private String member_idx;
	private String report_title;
	private String report_content;
	private String report_where;
	private String report_where_idx;
	private String report_condition;
	private String report_writedate;
	
	public ReportVO() {
		
	}

	public ReportVO(String report_idx, String member_idx, String report_title, String report_content,
			String report_where, String report_where_idx, String report_condition, String report_writedate) {
		super();
		this.report_idx = report_idx;
		this.member_idx = member_idx;
		this.report_title = report_title;
		this.report_content = report_content;
		this.report_where = report_where;
		this.report_where_idx = report_where_idx;
		this.report_condition = report_condition;
		this.report_writedate = report_writedate;
	}

	public String getReport_idx() {
		return report_idx;
	}

	public void setReport_idx(String report_idx) {
		this.report_idx = report_idx;
	}

	public String getMember_idx() {
		return member_idx;
	}

	public void setMember_idx(String member_idx) {
		this.member_idx = member_idx;
	}

	public String getReport_title() {
		return report_title;
	}

	public void setReport_title(String report_title) {
		this.report_title = report_title;
	}

	public String getReport_content() {
		return report_content;
	}

	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}

	public String getReport_where() {
		return report_where;
	}

	public void setReport_where(String report_where) {
		this.report_where = report_where;
	}

	public String getReport_where_idx() {
		return report_where_idx;
	}

	public void setReport_where_idx(String report_where_idx) {
		this.report_where_idx = report_where_idx;
	}

	public String getReport_condition() {
		return report_condition;
	}

	public void setReport_condition(String report_condition) {
		this.report_condition = report_condition;
	}

	public String getReport_writedate() {
		return report_writedate;
	}

	public void setReport_writedate(String report_writedate) {
		this.report_writedate = report_writedate;
	}
	
	
	
	
}
