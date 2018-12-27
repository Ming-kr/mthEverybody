package com.teamb.mth.vo;

public class MemberVO {
	private String member_idx;
	private String member_id;
	private String member_pw;
	private String member_nick;
	private String member_email;
	private String member_code;
	private String member_joindate;
	private String member_name;
	private String member_phone;
	private String member_companynum;
	
	public MemberVO() {
		
	}
	
	public MemberVO(String member_idx, String member_id, String member_pw, String member_nick, String member_email,
			String member_code, String member_joindate, String member_name, String member_phone,
			String member_companynum) {
		super();
		this.member_idx = member_idx;
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_nick = member_nick;
		this.member_email = member_email;
		this.member_code = member_code;
		this.member_joindate = member_joindate;
		this.member_name = member_name;
		this.member_phone = member_phone;
		this.member_companynum = member_companynum;
	}
	
	public String getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(String member_idx) {
		this.member_idx = member_idx;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getMember_nick() {
		return member_nick;
	}
	public void setMember_nick(String member_nick) {
		this.member_nick = member_nick;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_code() {
		return member_code;
	}
	public void setMember_code(String member_code) {
		this.member_code = member_code;
	}
	public String getMember_joindate() {
		return member_joindate;
	}
	public void setMember_joindate(String member_joindate) {
		this.member_joindate = member_joindate;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	public String getMember_companynum() {
		return member_companynum;
	}
	public void setMember_companynum(String member_companynum) {
		this.member_companynum = member_companynum;
	}
	
	
}
