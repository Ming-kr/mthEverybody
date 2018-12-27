package com.teamb.mth.vo;

public class ItemVO {
	private String item_idx;
	private String brand_idx;
	private String item_type_idx;
	private String item_name;
	private String item_originprice;
	private String item_imgfilename;
	private String item_content;
	private String item_condition;
	
	public ItemVO() {
		
	}
	
	
	
	public ItemVO(String brand_idx, String item_type_idx) {
		super();
		this.brand_idx = brand_idx;
		this.item_type_idx = item_type_idx;
	}



	public ItemVO(String item_idx, String brand_idx, String item_type_idx, String item_name, String item_originprice,
			String item_imgfilename, String item_content, String item_condition) {
		super();
		this.item_idx = item_idx;
		this.brand_idx = brand_idx;
		this.item_type_idx = item_type_idx;
		this.item_name = item_name;
		this.item_originprice = item_originprice;
		this.item_imgfilename = item_imgfilename;
		this.item_content = item_content;
		this.item_condition = item_condition;
	}
	
	public String getItem_idx() {
		return item_idx;
	}
	public void setItem_idx(String item_idx) {
		this.item_idx = item_idx;
	}
	public String getBrand_idx() {
		return brand_idx;
	}
	public void setBrand_idx(String brand_idx) {
		this.brand_idx = brand_idx;
	}
	public String getItem_type_idx() {
		return item_type_idx;
	}
	public void setItem_type_idx(String item_type_idx) {
		this.item_type_idx = item_type_idx;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_originprice() {
		return item_originprice;
	}
	public void setItem_originprice(String item_originprice) {
		this.item_originprice = item_originprice;
	}
	public String getItem_imgfilename() {
		return item_imgfilename;
	}
	public void setItem_imgfilename(String item_imgfilename) {
		this.item_imgfilename = item_imgfilename;
	}
	public String getItem_content() {
		return item_content;
	}
	public void setItem_content(String item_content) {
		this.item_content = item_content;
	}
	public String getItem_condition() {
		return item_condition;
	}
	public void setItem_condition(String item_condition) {
		this.item_condition = item_condition;
	}

	
}
