package com.teamb.mth.data;

import com.teamb.mth.vo.*;

public class ConvenReData 
{
	private ConvenReVO convenReVO;
	private MemberVO memberVO;
	
	public ConvenReData() {
		super();
	}
	
	public ConvenReData(ConvenReVO convenReVO, MemberVO memberVO) {
		super();
		this.convenReVO = convenReVO;
		this.memberVO = memberVO;
	}
	
	public ConvenReVO getConvenReVO() {
		return convenReVO;
	}
	
	public void setConvenReVO(ConvenReVO convenReVO) {
		this.convenReVO = convenReVO;
	}
	
	public MemberVO getMemberVO() {
		return memberVO;
	}
	
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
}
