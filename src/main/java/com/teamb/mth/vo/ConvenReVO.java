package com.teamb.mth.vo;

public class ConvenReVO {
	private String conven_re_idx;
	private String conven_idx;
	private String member_idx;
	private String conven_re_content;
	private String conven_re_state;
	private String conven_re_writedate;
	
	public ConvenReVO() {
		
	}
	
	public ConvenReVO(String conven_re_idx, String conven_idx, String member_idx, String conven_re_content,
			String conven_re_state, String conven_re_writedate) {
		super();
		this.conven_re_idx = conven_re_idx;
		this.conven_idx = conven_idx;
		this.member_idx = member_idx;
		this.conven_re_content = conven_re_content;
		this.conven_re_state = conven_re_state;
		this.conven_re_writedate = conven_re_writedate;
	}
	
	public String getConven_re_idx() {
		return conven_re_idx;
	}
	public void setConven_re_idx(String conven_re_idx) {
		this.conven_re_idx = conven_re_idx;
	}
	public String getConven_idx() {
		return conven_idx;
	}
	public void setConven_idx(String conven_idx) {
		this.conven_idx = conven_idx;
	}
	public String getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(String member_idx) {
		this.member_idx = member_idx;
	}
	public String getConven_re_content() {
		return conven_re_content;
	}
	public void setConven_re_content(String conven_re_content) {
		this.conven_re_content = conven_re_content;
	}
	public String getConven_re_state() {
		return conven_re_state;
	}
	public void setConven_re_state(String conven_re_state) {
		this.conven_re_state = conven_re_state;
	}
	public String getConven_re_writedate() {
		return conven_re_writedate;
	}
	public void setConven_re_writedate(String conven_re_writedate) {
		this.conven_re_writedate = conven_re_writedate;
	}
	
	
}
