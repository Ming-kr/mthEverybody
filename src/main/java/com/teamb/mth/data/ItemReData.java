package com.teamb.mth.data;

import com.teamb.mth.vo.*;

public class ItemReData 
{
	private ItemReVO itemReVO;
	private MemberVO memberVO;
	
	public ItemReData() {
		super();
	}

	public ItemReData(ItemReVO itemReVO, MemberVO memberVO) {
		super();
		this.itemReVO = itemReVO;
		this.memberVO = memberVO;
	}

	public ItemReVO getItemReVO() {
		return itemReVO;
	}

	public void setItemReVO(ItemReVO itemReVO) {
		this.itemReVO = itemReVO;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
}
