package com.teamb.mth.vo;

public class ItemReVO {
	private String item_re_idx;
	private String item_idx;
	private String member_idx;
	private String item_re_content;
	private String item_re_state;
	private String item_re_writedate;
	
	public ItemReVO() {
		
	}
	
	public ItemReVO(String item_re_idx, String item_idx, String member_idx, String item_re_content, String item_re_state,
			String item_re_writedate) {
		super();
		this.item_re_idx = item_re_idx;
		this.item_idx = item_idx;
		this.member_idx = member_idx;
		this.item_re_content = item_re_content;
		this.item_re_state = item_re_state;
		this.item_re_writedate = item_re_writedate;
	}
	
	public String getItem_re_idx() {
		return item_re_idx;
	}
	public void setItem_re_idx(String item_re_idx) {
		this.item_re_idx = item_re_idx;
	}
	public String getItem_idx() {
		return item_idx;
	}
	public void setItem_idx(String item_idx) {
		this.item_idx = item_idx;
	}
	public String getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(String member_idx) {
		this.member_idx = member_idx;
	}
	public String getItem_re_content() {
		return item_re_content;
	}
	public void setItem_re_content(String item_re_content) {
		this.item_re_content = item_re_content;
	}
	public String getItem_re_state() {
		return item_re_state;
	}
	public void setItem_re_state(String item_re_state) {
		this.item_re_state = item_re_state;
	}
	public String getItem_re_writedate() {
		return item_re_writedate;
	}
	public void setItem_re_writedate(String item_re_writedate) {
		this.item_re_writedate = item_re_writedate;
	}
}
