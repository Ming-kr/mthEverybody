package com.teamb.mth.vo;

public class YumLikeVO {
	private String member_idx;
	private String yum_idx;
	private String yum_like_regdate;
	
	public YumLikeVO() {
		
	}
	
	public YumLikeVO(String member_idx, String yum_idx, String yum_like_regdate) {
		super();
		this.member_idx = member_idx;
		this.yum_idx = yum_idx;
		this.yum_like_regdate = yum_like_regdate;
	}
	public String getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(String member_idx) {
		this.member_idx = member_idx;
	}
	public String getYum_idx() {
		return yum_idx;
	}
	public void setYum_idx(String yum_idx) {
		this.yum_idx = yum_idx;
	}
	public String getYum_like_regdate() {
		return yum_like_regdate;
	}
	public void setYum_like_regdate(String yum_like_regdate) {
		this.yum_like_regdate = yum_like_regdate;
	}
	
	
}
