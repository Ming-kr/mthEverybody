package com.teamb.mth.data;

import com.teamb.mth.vo.MemberVO;
import com.teamb.mth.vo.YumReVO;
import com.teamb.mth.vo.YumVO;

public class YumReData {
	private YumReVO yumReVO;
	private YumVO yumVO;
	private MemberVO memberVO;
	
	public YumReData() {
		
	}
	
	public YumReData(YumReVO yumReVO, MemberVO memberVO) {
		super();
		this.yumReVO = yumReVO;
		this.memberVO = memberVO;
	}
	
	public YumReData(YumReVO yumReVO, YumVO yumVO, MemberVO memberVO) {
		super();
		this.yumReVO = yumReVO;
		this.yumVO = yumVO;
		this.memberVO = memberVO;
	}
	
	public YumReVO getYumReVO() {
		return yumReVO;
	}
	public void setYumReVO(YumReVO yumReVO) {
		this.yumReVO = yumReVO;
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
	
	
}
