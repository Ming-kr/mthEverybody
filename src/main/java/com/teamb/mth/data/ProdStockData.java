package com.teamb.mth.data;

public class ProdStockData {
	private String prod_idx;
	private String prod_stockCount;
	
	public ProdStockData() {
		
	}
	
	public ProdStockData(String prod_idx, String prod_stockCount) {
		super();
		this.prod_idx = prod_idx;
		this.prod_stockCount = prod_stockCount;
	}
	
	public String getProd_idx() {
		return prod_idx;
	}
	public void setProd_idx(String prod_idx) {
		this.prod_idx = prod_idx;
	}
	public String getProd_stockCount() {
		return prod_stockCount;
	}
	public void setProd_stockCount(String prod_stockCount) {
		this.prod_stockCount = prod_stockCount;
	}
}
