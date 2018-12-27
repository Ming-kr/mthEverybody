package com.teamb.mth.data;

public class FreeBoardData { //FreeVO에서 prev next 꺼 생성해준거
	
	private String free_idx;
	private String free_state;
	private String prev_idx;
	private String next_idx;
	
	
	public FreeBoardData() {
		
	}


	public FreeBoardData(String free_idx, String free_state, String prev_idx, String next_idx) {
		super();
		this.free_idx = free_idx;
		this.free_state = free_state;
		this.prev_idx = prev_idx;
		this.next_idx = next_idx;
	}


	public String getFree_idx() {
		return free_idx;
	}


	public void setFree_idx(String free_idx) {
		this.free_idx = free_idx;
	}


	public String getFree_state() {
		return free_state;
	}


	public void setFree_state(String free_state) {
		this.free_state = free_state;
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
