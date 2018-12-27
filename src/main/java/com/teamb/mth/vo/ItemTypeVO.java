package com.teamb.mth.vo;

public class ItemTypeVO {
	private String item_type_idx;
	private String item_type_name;
	
	public ItemTypeVO() {
		
	}
	
	public ItemTypeVO(String item_type_idx, String item_type_name) {
		super();
		this.item_type_idx = item_type_idx;
		this.item_type_name = item_type_name;
	}
	public String getItem_type_idx() {
		return item_type_idx;
	}
	public void setItem_type_idx(String item_type_idx) {
		this.item_type_idx = item_type_idx;
	}
	public String getItem_type_name() {
		return item_type_name;
	}
	public void setItem_type_name(String item_type_name) {
		this.item_type_name = item_type_name;
	}
}
