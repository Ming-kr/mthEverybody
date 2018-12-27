package com.teamb.mth.vo;

public class YumReVO {
	private String yum_re_idx;
	private String yum_idx;
	private String member_idx;
	private String yum_re_content;
	private String yum_re_state;
	private String yum_re_writedate;
	
	public YumReVO() {
		
	}
	
	public YumReVO(String yum_re_idx, String yum_idx, String member_idx, String yum_re_content, String yum_re_state,
			String yum_re_writedate) {
		super();
		this.yum_re_idx = yum_re_idx;
		this.yum_idx = yum_idx;
		this.member_idx = member_idx;
		this.yum_re_content = yum_re_content;
		this.yum_re_state = yum_re_state;
		this.yum_re_writedate = yum_re_writedate;
	}
	
	public String getYum_re_idx() {
		return yum_re_idx;
	}
	public void setYum_re_idx(String yum_re_idx) {
		this.yum_re_idx = yum_re_idx;
	}
	public String getYum_idx() {
		return yum_idx;
	}
	public void setYum_idx(String yum_idx) {
		this.yum_idx = yum_idx;
	}
	public String getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(String member_idx) {
		this.member_idx = member_idx;
	}
	public String getYum_re_content() {
		return yum_re_content;
	}
	public void setYum_re_content(String yum_re_content) {
		this.yum_re_content = yum_re_content;
	}
	public String getYum_re_state() {
		return yum_re_state;
	}
	public void setYum_re_state(String yum_re_state) {
		this.yum_re_state = yum_re_state;
	}
	public String getYum_re_writedate() {
		return yum_re_writedate;
	}
	public void setYum_re_writedate(String yum_re_writedate) {
		this.yum_re_writedate = yum_re_writedate;
	}
	
	
}
