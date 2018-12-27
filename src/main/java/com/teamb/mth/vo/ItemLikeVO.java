package com.teamb.mth.vo;

public class ItemLikeVO {
	private String item_idx;
	private String member_idx;
	private String item_type_idx;
	
	public ItemLikeVO() {
		
	}
	
	public ItemLikeVO(String item_idx, String member_idx, String item_type_idx) {
		super();
		this.item_idx = item_idx;
		this.member_idx = member_idx;
		this.item_type_idx = item_type_idx;
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
	public String getItem_type_idx() {
		return item_type_idx;
	}
	public void setItem_type_idx(String item_type_idx) {
		this.item_type_idx = item_type_idx;
	}
	
	
}
