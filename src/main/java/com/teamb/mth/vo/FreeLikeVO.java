package com.teamb.mth.vo;

public class FreeLikeVO {
	private String member_idx;
	private String free_idx;
	private String free_like_regdate;
	
	public FreeLikeVO() {
		
	}
	
	public FreeLikeVO(String member_idx, String free_idx, String free_like_regdate) {
		super();
		this.member_idx = member_idx;
		this.free_idx = free_idx;
		this.free_like_regdate = free_like_regdate;
	}
	
	public String getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(String member_idx) {
		this.member_idx = member_idx;
	}
	public String getFree_idx() {
		return free_idx;
	}
	public void setFree_idx(String free_idx) {
		this.free_idx = free_idx;
	}
	public String getFree_like_regdate() {
		return free_like_regdate;
	}
	public void setFree_like_regdate(String free_like_regdate) {
		this.free_like_regdate = free_like_regdate;
	}
	
	
}
