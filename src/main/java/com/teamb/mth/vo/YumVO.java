package com.teamb.mth.vo;

public class YumVO {
	private String yum_idx;
	private String member_idx;
	private String yum_category;
	private String yum_title;
	private String yum_content;
	private String yum_state;
	private String yum_count;
	private String yum_writedate;

	public YumVO() {
		
	}
	
	public YumVO(String yum_idx, String member_idx, String yum_category, String yum_title, String yum_content,
			String yum_state, String yum_count, String yum_writedate) {
		super();
		this.yum_idx = yum_idx;
		this.member_idx = member_idx;
		this.yum_category = yum_category;
		this.yum_title = yum_title;
		this.yum_content = yum_content;
		this.yum_state = yum_state;
		this.yum_count = yum_count;
		this.yum_writedate = yum_writedate;
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
	public String getYum_category() {
		return yum_category;
	}
	public void setYum_category(String yum_category) {
		this.yum_category = yum_category;
	}
	public String getYum_title() {
		return yum_title;
	}
	public void setYum_title(String yum_title) {
		this.yum_title = yum_title;
	}
	public String getYum_content() {
		return yum_content;
	}
	public void setYum_content(String yum_content) {
		this.yum_content = yum_content;
	}
	public String getYum_state() {
		return yum_state;
	}
	public void setYum_state(String yum_state) {
		this.yum_state = yum_state;
	}
	public String getYum_count() {
		return yum_count;
	}
	public void setYum_count(String yum_count) {
		this.yum_count = yum_count;
	}
	public String getYum_writedate() {
		return yum_writedate;
	}
	public void setYum_writedate(String yum_writedate) {
		this.yum_writedate = yum_writedate;
	}
	
}
