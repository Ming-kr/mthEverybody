package com.teamb.mth.vo;

public class FreeImgVO {
	private String free_img_idx;
	private String free_idx;
	private String free_img_imgfilename;
	private String free_img_uploaddate;
	
	public FreeImgVO() {
		
	}
	
	public FreeImgVO(String free_img_idx, String free_idx, String free_img_imgfilename, String free_img_uploaddate) {
		super();
		this.free_img_idx = free_img_idx;
		this.free_idx = free_idx;
		this.free_img_imgfilename = free_img_imgfilename;
		this.free_img_uploaddate = free_img_uploaddate;
	}
	
	public String getFree_img_idx() {
		return free_img_idx;
	}
	public void setFree_img_idx(String free_img_idx) {
		this.free_img_idx = free_img_idx;
	}
	public String getFree_idx() {
		return free_idx;
	}
	public void setFree_idx(String free_idx) {
		this.free_idx = free_idx;
	}
	public String getFree_img_imgfilename() {
		return free_img_imgfilename;
	}
	public void setFree_img_imgfilename(String free_img_imgfilename) {
		this.free_img_imgfilename = free_img_imgfilename;
	}
	public String getFree_img_uploaddate() {
		return free_img_uploaddate;
	}
	public void setFree_img_uploaddate(String free_img_uploaddate) {
		this.free_img_uploaddate = free_img_uploaddate;
	}
	
	
}
