package com.teamb.mth.vo;

public class StateVO {
	private String state_idx;
	private String state_name;
	
	public StateVO() {
		
	}
	
	public StateVO(String state_idx, String state_name) {
		super();
		this.state_idx = state_idx;
		this.state_name = state_name;
	}
	
	public String getState_idx() {
		return state_idx;
	}
	public void setState_idx(String state_idx) {
		this.state_idx = state_idx;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
}
