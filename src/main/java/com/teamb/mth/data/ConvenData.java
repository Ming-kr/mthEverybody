package com.teamb.mth.data;

import com.teamb.mth.vo.BrandVO;
import com.teamb.mth.vo.ConvenVO;
import com.teamb.mth.vo.MemberVO;
import com.teamb.mth.vo.StateVO;

public class ConvenData {
	private ConvenVO convenVO;
	private MemberVO memberVO;
	private BrandVO brandVO;
	private StateVO stateVO;
	
	
	public ConvenData() {
		
	}
	
	public ConvenData(ConvenVO convenVO, BrandVO brandVO, StateVO stateVO) {
		super();
		this.convenVO = convenVO;
		this.brandVO = brandVO;
		this.stateVO = stateVO;
	}
	
	public ConvenData(ConvenVO convenVO, MemberVO memberVO, BrandVO brandVO, StateVO stateVO) {
		super();
		this.convenVO = convenVO;
		this.memberVO = memberVO;
		this.brandVO = brandVO;
		this.stateVO = stateVO;
	}
	
	public ConvenVO getConvenVO() {
		return convenVO;
	}
	public void setConvenVO(ConvenVO convenVO) {
		this.convenVO = convenVO;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public BrandVO getBrandVO() {
		return brandVO;
	}
	public void setBrandVO(BrandVO brandVO) {
		this.brandVO = brandVO;
	}
	public StateVO getStateVO() {
		return stateVO;
	}
	public void setStateVO(StateVO stateVO) {
		this.stateVO = stateVO;
	}
	
}
