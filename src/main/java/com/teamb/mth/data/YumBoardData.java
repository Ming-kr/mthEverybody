package com.teamb.mth.data;

public class YumBoardData {
	
	private String yum_idx;
	private String yum_state;
	private String prev_idx;
	private String next_idx;
	
	public YumBoardData() {
		
	}

	public YumBoardData(String yum_idx, String yum_state, String prev_idx, String next_idx) {
		super();
		this.yum_idx = yum_idx;
		this.yum_state = yum_state;
		this.prev_idx = prev_idx;
		this.next_idx = next_idx;
	}

	public String getYum_idx() {
		return yum_idx;
	}

	public void setYum_idx(String yum_idx) {
		this.yum_idx = yum_idx;
	}

	public String getYum_state() {
		return yum_state;
	}

	public void setYum_state(String yum_state) {
		this.yum_state = yum_state;
	}

	public String getPrev_idx() {
		return prev_idx;
	}

	public void setPrev_idx(String prev_idx) {
		this.prev_idx = prev_idx;
	}

	public String getNext_idx() {
		return next_idx;
	}

	public void setNext_idx(String next_idx) {
		this.next_idx = next_idx;
	}
	

}
