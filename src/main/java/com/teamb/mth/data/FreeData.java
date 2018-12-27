package com.teamb.mth.data;

import java.util.ArrayList;

import com.teamb.mth.vo.FreeImgVO;
import com.teamb.mth.vo.FreeVO;
import com.teamb.mth.vo.MemberVO;

public class FreeData {

	private FreeVO freeVO;
	private MemberVO memberVO;
	private ArrayList<FreeImgVO> freeImgList;
	
	private int free_likeCount;
	private String free_re_count;
	
	public FreeData() {
		
	}

	public FreeData(FreeVO freeVO, MemberVO memberVO, ArrayList<FreeImgVO> freeImgList, int free_likeCount,
			String free_re_count) {
		super();
		this.freeVO = freeVO;
		this.memberVO = memberVO;
		this.freeImgList = freeImgList;
		this.free_likeCount = free_likeCount;
		this.free_re_count = free_re_count;
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

	public ArrayList<FreeImgVO> getFreeImgList() {
		return freeImgList;
	}

	public void setFreeImgList(ArrayList<FreeImgVO> freeImgList) {
		this.freeImgList = freeImgList;
	}

	public int getFree_likeCount() {
		return free_likeCount;
	}

	public void setFree_likeCount(int free_likeCount) {
		this.free_likeCount = free_likeCount;
	}

	public String getFree_re_count() {
		return free_re_count;
	}

	public void setFree_re_count(String free_re_count) {
		this.free_re_count = free_re_count;
	}

		
}