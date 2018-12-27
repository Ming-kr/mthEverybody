package com.teamb.mth.vo;

public class FreeReVO {
	private String free_re_idx;
	private String free_idx;
	private String member_idx;
	private String free_re_content;
	private String free_re_state;
	private String free_re_writedate;
	
	public FreeReVO() {
		
	}
	
	public FreeReVO(String free_re_idx, String free_idx, String member_idx, String free_re_content, String free_re_state,
			String free_re_writedate) {
		super();
		this.free_re_idx = free_re_idx;
		this.free_idx = free_idx;
		this.member_idx = member_idx;
		this.free_re_content = free_re_content;
		this.free_re_state = free_re_state;
		this.free_re_writedate = free_re_writedate;
	}
	
	public String getFree_re_idx() {
		return free_re_idx;
	}
	public void setFree_re_idx(String free_re_idx) {
		this.free_re_idx = free_re_idx;
	}
	public String getFree_idx() {
		return free_idx;
	}
	public void setFree_idx(String free_idx) {
		this.free_idx = free_idx;
	}
	public String getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(String member_idx) {
		this.member_idx = member_idx;
	}
	public String getFree_re_content() {
		return free_re_content;
	}
	public void setFree_re_content(String free_re_content) {
		this.free_re_content = free_re_content;
	}
	public String getFree_re_state() {
		return free_re_state;
	}
	public void setFree_re_state(String free_re_state) {
		this.free_re_state = free_re_state;
	}
	public String getFree_re_writedate() {
		return free_re_writedate;
	}
	public void setFree_re_writedate(String free_re_writedate) {
		this.free_re_writedate = free_re_writedate;
	}
	
	
	
}
