package com.teamb.mth.vo;

public class AllReplyViewVO 
{
	private String re_loc;
	private String loc_idx;
	private String member_idx;
	private String re_content;
	private String writedate;
	
	public AllReplyViewVO() {
		super();
	}
	
	public AllReplyViewVO(String re_loc, String loc_idx, String member_idx, String re_content, String writedate) {
		super();
		this.re_loc = re_loc;
		this.loc_idx = loc_idx;
		this.member_idx = member_idx;
		this.re_content = re_content;
		this.writedate = writedate;
	}

	public String getRe_loc() {
		return re_loc;
	}

	public void setRe_loc(String re_loc) {
		this.re_loc = re_loc;
	}

	public String getLoc_idx() {
		return loc_idx;
	}

	public void setLoc_idx(String loc_idx) {
		this.loc_idx = loc_idx;
	}

	public String getMember_idx() {
		return member_idx;
	}

	public void setMember_idx(String member_idx) {
		this.member_idx = member_idx;
	}

	public String getRe_content() {
		return re_content;
	}

	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
}
