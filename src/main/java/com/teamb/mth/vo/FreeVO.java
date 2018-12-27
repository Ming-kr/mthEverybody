package com.teamb.mth.vo;

public class FreeVO {
	private String free_idx;
	private String member_idx;
	private String free_category;
	private String free_title;
	private String free_content;
	private String free_state;
	private String free_count;
	private String free_writedate;
	
	public FreeVO() {
		
	}
	
	public FreeVO(String free_idx, String member_idx, String free_category, String free_title, String free_content,
			String free_state, String free_count, String free_writedate) {
		super();
		this.free_idx = free_idx;
		this.member_idx = member_idx;
		this.free_category = free_category;
		this.free_title = free_title;
		this.free_content = free_content;
		this.free_state = free_state;
		this.free_count = free_count;
		this.free_writedate = free_writedate;
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
	public String getFree_category() {
		return free_category;
	}
	public void setFree_category(String free_category) {
		this.free_category = free_category;
	}
	public String getFree_title() {
		return free_title;
	}
	public void setFree_title(String free_title) {
		this.free_title = free_title;
	}
	public String getFree_content() {
		return free_content;
	}
	public void setFree_content(String free_content) {
		this.free_content = free_content;
	}
	public String getFree_state() {
		return free_state;
	}
	public void setFree_state(String free_state) {
		this.free_state = free_state;
	}
	public String getFree_count() {
		return free_count;
	}
	public void setFree_count(String free_count) {
		this.free_count = free_count;
	}
	public String getFree_writedate() {
		return free_writedate;
	}
	public void setFree_writedate(String free_writedate) {
		this.free_writedate = free_writedate;
	}
	
	
	
}
