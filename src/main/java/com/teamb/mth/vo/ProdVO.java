package com.teamb.mth.vo;

public class ProdVO {
	private String prod_idx;
	private String item_idx;
	private String member_idx;
	private String conven_idx;
	private String state_idx;
	private String prod_content;
	private String prod_saleprice;
	private String prod_salefinalprice;
	private String prod_condition;
	private String prod_exdate;
	private String prod_regdate;
	
	public ProdVO() {
		
	}
	
	public ProdVO(String prod_idx, String item_idx, String member_idx, String conven_idx, String state_idx,
			String prod_content, String prod_saleprice, String prod_salefinalprice, String prod_condition,
			String prod_exdate, String prod_regdate) {
		super();
		this.prod_idx = prod_idx;
		this.item_idx = item_idx;
		this.member_idx = member_idx;
		this.conven_idx = conven_idx;
		this.state_idx = state_idx;
		this.prod_content = prod_content;
		this.prod_saleprice = prod_saleprice;
		this.prod_salefinalprice = prod_salefinalprice;
		this.prod_condition = prod_condition;
		this.prod_exdate = prod_exdate;
		this.prod_regdate = prod_regdate;
	}
	
	public String getProd_idx() {
		return prod_idx;
	}
	public void setProd_idx(String prod_idx) {
		this.prod_idx = prod_idx;
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
	public String getConven_idx() {
		return conven_idx;
	}
	public void setConven_idx(String conven_idx) {
		this.conven_idx = conven_idx;
	}
	public String getState_idx() {
		return state_idx;
	}
	public void setState_idx(String state_idx) {
		this.state_idx = state_idx;
	}
	public String getProd_content() {
		return prod_content;
	}
	public void setProd_content(String prod_content) {
		this.prod_content = prod_content;
	}
	public String getProd_saleprice() {
		return prod_saleprice;
	}
	public void setProd_saleprice(String prod_saleprice) {
		this.prod_saleprice = prod_saleprice;
	}
	public String getProd_salefinalprice() {
		return prod_salefinalprice;
	}
	public void setProd_salefinalprice(String prod_salefinalprice) {
		this.prod_salefinalprice = prod_salefinalprice;
	}
	public String getProd_condition() {
		return prod_condition;
	}
	public void setProd_condition(String prod_condition) {
		this.prod_condition = prod_condition;
	}
	public String getProd_exdate() {
		return prod_exdate;
	}
	public void setProd_exdate(String prod_exdate) {
		this.prod_exdate = prod_exdate;
	}
	public String getProd_regdate() {
		return prod_regdate;
	}
	public void setProd_regdate(String prod_regdate) {
		this.prod_regdate = prod_regdate;
	}
	
	
	
}
