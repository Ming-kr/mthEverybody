package com.teamb.mth.data;

import com.teamb.mth.vo.MemberVO;
import com.teamb.mth.vo.YumVO;

public class YumData {
	private YumVO yumVO;
	private MemberVO memberVO;
	private int yum_likeCount;
	private String yum_re_count;
	
	public YumData() {
		
	}

	
	public YumData(YumVO yumVO, MemberVO memberVO, int yum_likeCount) {
		super();
		this.yumVO = yumVO;
		this.memberVO = memberVO;
		this.yum_likeCount = yum_likeCount;
	}


	public YumData(YumVO yumVO, MemberVO memberVO, int yum_likeCount, String yum_re_count) {
		super();
		this.yumVO = yumVO;
		this.memberVO = memberVO;
		this.yum_likeCount = yum_likeCount;
		this.yum_re_count = yum_re_count;
	}

	public YumVO getYumVO() {
		return yumVO;
	}

	public void setYumVO(YumVO yumVO) {
		this.yumVO = yumVO;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public int getYum_likeCount() {
		return yum_likeCount;
	}

	public void setYum_likeCount(int yum_likeCount) {
		this.yum_likeCount = yum_likeCount;
	}

	public String getYum_re_count() {
		return yum_re_count;
	}

	public void setYum_re_count(String yum_re_count) {
		this.yum_re_count = yum_re_count;
	}

	
}
