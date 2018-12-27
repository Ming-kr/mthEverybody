package com.teamb.mth.vo;

public class ConvenVO {
	private String conven_idx;
	private String member_idx;
	private String brand_idx;
	private String state_idx;
	private String conven_name;
	private String conven_adress;
	private String conven_condition;
	private String conven_regdate;
	
	public ConvenVO() {
		
	}
	
	public ConvenVO(String conven_idx, String member_idx, String brand_idx, String state_idx, String conven_name,
			String conven_adress, String conven_condition, String conven_regdate) {
		super();
		this.conven_idx = conven_idx;
		this.member_idx = member_idx;
		this.brand_idx = brand_idx;
		this.state_idx = state_idx;
		this.conven_name = conven_name;
		this.conven_adress = conven_adress;
		this.conven_condition = conven_condition;
		this.conven_regdate = conven_regdate;
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
	public String getBrand_idx() {
		return brand_idx;
	}
	public void setBrand_idx(String brand_idx) {
		this.brand_idx = brand_idx;
	}
	public String getState_idx() {
		return state_idx;
	}
	public void setState_idx(String state_idx) {
		this.state_idx = state_idx;
	}
	public String getConven_name() {
		return conven_name;
	}
	public void setConven_name(String conven_name) {
		this.conven_name = conven_name;
	}
	public String getConven_adress() {
		return conven_adress;
	}
	public void setConven_adress(String conven_adress) {
		this.conven_adress = conven_adress;
	}
	public String getConven_condition() {
		return conven_condition;
	}
	public void setConven_condition(String conven_condition) {
		this.conven_condition = conven_condition;
	}
	public String getConven_regdate() {
		return conven_regdate;
	}
	public void setConven_regdate(String conven_regdate) {
		this.conven_regdate = conven_regdate;
	}
	
	
}
