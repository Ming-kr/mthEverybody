package com.teamb.mth.data;

import com.teamb.mth.vo.BrandVO;
import com.teamb.mth.vo.ConvenVO;
import com.teamb.mth.vo.ItemTypeVO;
import com.teamb.mth.vo.ItemVO;
import com.teamb.mth.vo.MemberVO;
import com.teamb.mth.vo.ProdVO;
import com.teamb.mth.vo.StateVO;

public class ProdData {
	private ProdVO prodVO;
	private ItemVO itemVO;
	private MemberVO memberVO;
	private ConvenVO convenVO;
	private BrandVO brandVO;
	private ItemTypeVO itemTypeVO;
	private StateVO stateVO;
	private int itemLikeCount;
	private boolean itemLikeCheck;
	private int itemReCount;
	private String prod_stockCount;
	
	public ProdData() {
		
	}
	
	public ProdData(ProdVO prodVO, ItemVO itemVO, MemberVO memberVO, ConvenVO convenVO, BrandVO brandVO,
			ItemTypeVO itemTypeVO, StateVO stateVO) {
		super();
		this.prodVO = prodVO;
		this.itemVO = itemVO;
		this.memberVO = memberVO;
		this.convenVO = convenVO;
		this.brandVO = brandVO;
		this.itemTypeVO = itemTypeVO;
		this.stateVO = stateVO;
	}

	public ProdData(ProdVO prodVO, ItemVO itemVO, MemberVO memberVO, ConvenVO convenVO, BrandVO brandVO,
			ItemTypeVO itemTypeVO, StateVO stateVO, int itemLikeCount, boolean itemLikeCheck, int itemReCount) {
		super();
		this.prodVO = prodVO;
		this.itemVO = itemVO;
		this.memberVO = memberVO;
		this.convenVO = convenVO;
		this.brandVO = brandVO;
		this.itemTypeVO = itemTypeVO;
		this.stateVO = stateVO;
		this.itemLikeCount = itemLikeCount;
		this.itemLikeCheck = itemLikeCheck;
		this.setItemReCount(itemReCount);
	}
	
	public ProdData(ProdVO prodVO, ItemVO itemVO, MemberVO memberVO, ConvenVO convenVO, BrandVO brandVO,
			ItemTypeVO itemTypeVO, StateVO stateVO, int itemLikeCount, boolean itemLikeCheck, int itemReCount,
			String prod_stockCount) {
		super();
		this.prodVO = prodVO;
		this.itemVO = itemVO;
		this.memberVO = memberVO;
		this.convenVO = convenVO;
		this.brandVO = brandVO;
		this.itemTypeVO = itemTypeVO;
		this.stateVO = stateVO;
		this.itemLikeCount = itemLikeCount;
		this.itemLikeCheck = itemLikeCheck;
		this.itemReCount = itemReCount;
		this.setProd_stockCount(prod_stockCount);
	}

	public ProdVO getProdVO() {
		return prodVO;
	}

	public void setProdVO(ProdVO prodVO) {
		this.prodVO = prodVO;
	}

	public ItemVO getItemVO() {
		return itemVO;
	}

	public void setItemVO(ItemVO itemVO) {
		this.itemVO = itemVO;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public ConvenVO getConvenVO() {
		return convenVO;
	}

	public void setConvenVO(ConvenVO convenVO) {
		this.convenVO = convenVO;
	}

	public BrandVO getBrandVO() {
		return brandVO;
	}

	public void setBrandVO(BrandVO brandVO) {
		this.brandVO = brandVO;
	}

	public ItemTypeVO getItemTypeVO() {
		return itemTypeVO;
	}

	public void setItemTypeVO(ItemTypeVO itemTypeVO) {
		this.itemTypeVO = itemTypeVO;
	}

	public StateVO getStateVO() {
		return stateVO;
	}

	public void setStateVO(StateVO stateVO) {
		this.stateVO = stateVO;
	}

	public int getItemLikeCount() {
		return itemLikeCount;
	}

	public void setItemLikeCount(int itemLikeCount) {
		this.itemLikeCount = itemLikeCount;
	}

	public boolean isItemLikeCheck() {
		return itemLikeCheck;
	}

	public void setItemLikeCheck(boolean itemLikeCheck) {
		this.itemLikeCheck = itemLikeCheck;
	}

	public int getItemReCount() {
		return itemReCount;
	}

	public void setItemReCount(int itemReCount) {
		this.itemReCount = itemReCount;
	}

	public String getProd_stockCount() {
		return prod_stockCount;
	}

	public void setProd_stockCount(String prod_stockCount) {
		this.prod_stockCount = prod_stockCount;
	}
	
}
