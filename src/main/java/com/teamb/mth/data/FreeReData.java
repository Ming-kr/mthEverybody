package com.teamb.mth.data;

import com.teamb.mth.vo.FreeReVO;
import com.teamb.mth.vo.FreeVO;
import com.teamb.mth.vo.MemberVO;

public class FreeReData {
	private FreeReVO freeReVO;
	private FreeVO freeVO;
	private MemberVO memberVO;
	
	public FreeReData() {
		
	}

	public FreeReData(FreeReVO freeReVO, MemberVO memberVO) {
		super();
		this.freeReVO = freeReVO;
		this.memberVO = memberVO;
	}


	public FreeReData(FreeReVO freeReVO, FreeVO freeVO, MemberVO memberVO) {
		super();
		this.freeReVO = freeReVO;
		this.freeVO = freeVO;
		this.memberVO = memberVO;
	}

	public FreeReVO getFreeReVO() {
		return freeReVO;
	}

	public void setFreeReVO(FreeReVO freeReVO) {
		this.freeReVO = freeReVO;
	}

	public FreeVO getFreeVO() {
		return freeVO;
	}

	public void setFreeVO(FreeVO freeVO) {
		this.freeVO = freeVO;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

}
