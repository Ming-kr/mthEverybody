package com.teamb.mth.vo;

public class ItemLikeViewVO 
{
	private String item_idx;
	private String member_idx;
	private String brand_name;
	private String item_type_name;
	private String item_name;
	private String item_like_regdate;

	public ItemLikeViewVO() {
		super();
	}

	public ItemLikeViewVO(String item_idx, String member_idx, String brand_name, String item_type_name,
			String item_name, String item_like_regdate) {
		super();
		this.item_idx = item_idx;
		this.member_idx = member_idx;
		this.brand_name = brand_name;
		this.item_type_name = item_type_name;
		this.item_name = item_name;
		this.item_like_regdate = item_like_regdate;
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

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getItem_type_name() {
		return item_type_name;
	}

	public void setItem_type_name(String item_type_name) {
		this.item_type_name = item_type_name;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_like_regdate() {
		return item_like_regdate;
	}

	public void setItem_like_regdate(String item_like_regdate) {
		this.item_like_regdate = item_like_regdate;
	}
}
