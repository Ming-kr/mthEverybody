package com.teamb.mth.vo;

public class MemberMailVO 
{
	private String member_idx;
	private String key;
	private String certify_check;
	
	public MemberMailVO() {
		super();
	}
	public MemberMailVO(String member_idx, String key, String certify_check) {
		super();
		this.member_idx = member_idx;
		this.key = key;
		this.certify_check = certify_check;
	}
	public String getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(String member_idx) {
		this.member_idx = member_idx;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getCertify_check() {
		return certify_check;
	}
	public void setCertify_check(String certify_check) {
		this.certify_check = certify_check;
	}
}
