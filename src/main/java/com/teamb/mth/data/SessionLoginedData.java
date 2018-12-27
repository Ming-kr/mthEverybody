package com.teamb.mth.data;

public class SessionLoginedData {
	private String member_idx;
	private String member_nick;
	private String member_code;
	
	public SessionLoginedData() {
		
	}
	
	public SessionLoginedData(String member_idx, String member_nick, String member_code) {
		super();
		this.member_idx = member_idx;
		this.member_nick = member_nick;
		this.member_code = member_code;
	}
	
	public String getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(String member_idx) {
		this.member_idx = member_idx;
	}
	public String getMember_nick() {
		return member_nick;
	}
	public void setMember_nick(String member_nick) {
		this.member_nick = member_nick;
	}
	public String getMember_code() {
		return member_code;
	}
	public void setMember_code(String member_code) {
		this.member_code = member_code;
	}
	
	
}
