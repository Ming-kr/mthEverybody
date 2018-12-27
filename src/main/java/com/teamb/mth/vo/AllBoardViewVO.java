package com.teamb.mth.vo;

public class AllBoardViewVO 
{
	private String board_name;
	private String idx;
	private String member_idx;
	private String title;
	private String writedate;
	
	public AllBoardViewVO() {
		super();
	}

	public AllBoardViewVO(String board_name, String idx, String member_idx, String title, String writedate) {
		super();
		this.board_name = board_name;
		this.idx = idx;
		this.member_idx = member_idx;
		this.title = title;
		this.writedate = writedate;
	}

	public String getBoard_name() {
		return board_name;
	}

	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getMember_idx() {
		return member_idx;
	}

	public void setMember_idx(String member_idx) {
		this.member_idx = member_idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
}
