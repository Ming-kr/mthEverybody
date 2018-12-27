package com.teamb.mth.vo;

public class BrandVO {
	private String brand_idx;
	private String brand_name;
	
	public BrandVO() {
	}
	
	public BrandVO(String brand_idx, String brand_name) {
		super();
		this.brand_idx = brand_idx;
		this.brand_name = brand_name;
	}
	public String getBrand_idx() {
		return brand_idx;
	}
	public void setBrand_idx(String brand_idx) {
		this.brand_idx = brand_idx;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
}
