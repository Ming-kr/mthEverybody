package com.teamb.mth.data;

import com.teamb.mth.vo.MemberVO;
import com.teamb.mth.vo.ReportVO;

public class ReportData {
	private ReportVO reportVO;
	private MemberVO memberVO;
	private MemberVO reportedMemberVO;
	private String board_category;
	private String board_title;
	private String board_content;
	private String board_state;
	private String board_count;
	private String board_writedate;
	private int board_reportCount;
	
	public ReportData() {
		
	}
	
	public ReportData(ReportVO reportVO, MemberVO memberVO, MemberVO reportedMemberVO, String board_category,
			String board_title, String board_content, String board_state, String board_count, String board_writedate,
			int board_reportCount) {
		super();
		this.reportVO = reportVO;
		this.memberVO = memberVO;
		this.reportedMemberVO = reportedMemberVO;
		this.board_category = board_category;
		this.board_title = board_title;
		this.board_content = board_content;
		this.board_state = board_state;
		this.board_count = board_count;
		this.board_writedate = board_writedate;
		this.board_reportCount = board_reportCount;
	}

	public ReportVO getReportVO() {
		return reportVO;
	}

	public void setReportVO(ReportVO reportVO) {
		this.reportVO = reportVO;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public MemberVO getReportedMemberVO() {
		return reportedMemberVO;
	}

	public void setReportedMemberVO(MemberVO reportedMemberVO) {
		this.reportedMemberVO = reportedMemberVO;
	}

	public String getBoard_category() {
		return board_category;
	}

	public void setBoard_category(String board_category) {
		this.board_category = board_category;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public String getBoard_state() {
		return board_state;
	}

	public void setBoard_state(String board_state) {
		this.board_state = board_state;
	}

	public String getBoard_count() {
		return board_count;
	}

	public void setBoard_count(String board_count) {
		this.board_count = board_count;
	}

	public String getBoard_writedate() {
		return board_writedate;
	}

	public void setBoard_writedate(String board_writedate) {
		this.board_writedate = board_writedate;
	}

	public int getBoard_reportCount() {
		return board_reportCount;
	}

	public void setBoard_reportCount(int board_reportCount) {
		this.board_reportCount = board_reportCount;
	}
	
	
}
