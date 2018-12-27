package com.teamb.mth.util;

import java.util.regex.Pattern;

import com.teamb.mth.vo.MemberVO;

public class CheckRequestMemberInfo 
{
	private MemberVO requestMemberVO;
	
	public CheckRequestMemberInfo(MemberVO requestMemberVO)
	{
		this.requestMemberVO = requestMemberVO;
	}
	
	//회원가입할 때 확인
	public boolean checkJoinMemberVO()
	{
		//입력받은 값
		String member_id = requestMemberVO.getMember_id();
		String member_pw = requestMemberVO.getMember_pw();
		String member_nick = requestMemberVO.getMember_nick();
		String member_email = requestMemberVO.getMember_email();
		
		//NULL check
		if(member_id == null || member_pw == null || member_nick == null || member_email == null) { return false; }
		
		//정규표현식
		Pattern idVaildPattern = Pattern.compile("^[a-zA-Z0-9]{5,12}$");
		Pattern nickUnvaildPattern = Pattern.compile("^[a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣|0-9]{3,15}$");
		Pattern emailValidPattern = Pattern.compile("^[a-z0-9A-Z_-]*@[a-z0-9A-Z]*.[a-zA-Z.]*$");
		
		//ID: 5자 이상 12자 이내 영소문자+숫자 조합만 가능(공백X)
		if(member_id.indexOf(' ') != -1) { return false; }
		if(idVaildPattern.matcher(member_id).find() == false) { return false; }
		System.out.println("아이디 체크확인");
		
		//PW: 8자 이상 15자 이내 문자(공백X)
		if(member_pw.indexOf(' ') != -1) { return false; }
		if(member_pw.length() > 15 || member_pw.length() < 8) { return false; }
		System.out.println("비밀번호 체크확인");
		
		//NICK: 3자 이상 15자 이내 영문자+숫자+한글 조합만(공백X)
		if(member_nick.indexOf(' ') != -1) { return false; }
		if(nickUnvaildPattern.matcher(member_nick).find() == false) { return false; }
		System.out.println("닉네임 체크확인");
		
		//Email 형식체크(공백X,  존재하는 메일인지 확인은 불가..)
		if(member_email.indexOf(' ') != -1) { return false; }
		if(emailValidPattern.matcher(member_email).find() == false) { return false; }
		System.out.println("이메일 체크확인");
		
		return true;
	}
	
	//회원정보 수정
	public boolean checkInfoForUpdate()
	{
		String member_code = requestMemberVO.getMember_code();
		String member_nick = requestMemberVO.getMember_nick();
		String member_phone = requestMemberVO.getMember_phone();
		
		Pattern nickUnvaildPattern = Pattern.compile("^[a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣|0-9]{3,15}$");
		Pattern phoneVaildPattern = Pattern.compile("^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$");
		
		if(member_nick == null) { return false; }

		//NICK: 3자 이상 15자 이내 문자(공백X, 특수문자X)
		if(member_nick.indexOf(' ') != -1) { return false; }
		if(nickUnvaildPattern.matcher(member_nick).find() == false) { return false; }
		System.out.println("닉네임 체크확인");
		
		//판매자, 관리자일때
		if(member_code.equals("SELLER") || member_code.equals("ADMIN"))
		{
			if(member_phone == null) { return false; }
			
			//PHONE: 010|011|016~9 - 숫자{3,4} - 숫자{4} 만 가능
			if(member_phone.indexOf(' ') != -1) { return false; }
			if(phoneVaildPattern.matcher(member_phone).find() == false) { return false; }
			System.out.println("핸드폰 체크확인");
		}
		
		return true;
	}
	/*
	//닉네임 수정시 확인(구매자 회원 정보수정시)
	public boolean checkInfoForNotSeller()
	{
		String member_nick = requestMemberVO.getMember_nick();
		Pattern nickUnvaildPattern = Pattern.compile("^[a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣|0-9]{3,15}$");

		if(member_nick == null) { return false; }

		//NICK: 3자 이상 15자 이내 문자(공백X, 특수문자X)
		if(member_nick.indexOf(' ') != -1) { return false; }
		if(nickUnvaildPattern.matcher(member_nick).find() == false) { return false; }
		System.out.println("닉네임 체크확인");
		
		return true;
	}
	
	//판매자회원 정보 수정(nick, 핸드폰번호만 수정 가능)
	public boolean checkInfoForSeller()
	{
		String member_nick = requestMemberVO.getMember_nick();
		String member_phone = requestMemberVO.getMember_phone();
		
		Pattern nickUnvaildPattern = Pattern.compile("^[a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣|0-9]{3,15}$");
		Pattern phoneVaildPattern = Pattern.compile("^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$");
		
		//NULL check
		if(member_nick == null || member_phone == null) { return false; }

		//NICK: 3자 이상 15자 이내 문자(공백X, 특수문자X)
		if(member_nick.indexOf(' ') != -1) { return false; }
		if(nickUnvaildPattern.matcher(member_nick).find() == false) { return false; }
		System.out.println("닉네임 체크확인");
		
		//PHONE: 010|011|016~9 - 숫자{3,4} - 숫자{4} 만 가능
		if(member_phone.indexOf(' ') != -1) { return false; }
		if(phoneVaildPattern.matcher(member_phone).find() == false) { return false; }
		System.out.println("핸드폰 체크확인");
		
		return true;
	}
	*/
	//비밀번호 변경시 확인
	public boolean checkOnlyPassword()
	{
		String member_pw = requestMemberVO.getMember_pw();
		if(member_pw == null) { return false; }
		
		//PW: 8자 이상 15자 이내 문자(공백X)
		if(member_pw.indexOf(' ') != -1) { return false; }
		if(member_pw.length() > 15 || member_pw.length() < 8) { return false; }
		
		return true;
	}
	
	//판매자회원 신청 확인
	public boolean checkReqSellerInfo()
	{
		String member_name = requestMemberVO.getMember_name();
		String member_phone = requestMemberVO.getMember_phone();
		String member_companynum = requestMemberVO.getMember_companynum();
		
		//NULL check
		if(member_name == null || member_phone == null || member_companynum == null) { return false; }
		
		Pattern nameVaildPattern = Pattern.compile("^[가-힣]{2,8}$");
		Pattern phoneVaildPattern = Pattern.compile("^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$");
		Pattern companynumVaildPattern = Pattern.compile("^[0-9]{10}$");
		
		//NAME: 2자 이상 8자 이내 한글만 가능(공백X)
		if(member_name.indexOf(' ') != -1) { return false; }
		if(nameVaildPattern.matcher(member_name).find() == false) { return false; }
		
		//PHONE: 010|011|016~9 - 숫자{3,4} - 숫자{4} 만 가능
		if(member_phone.indexOf(' ') != -1) { return false; }
		if(phoneVaildPattern.matcher(member_phone).find() == false) { return false; }
		
		//Companynum(사업자등록번호): 10자 숫자
		if(member_companynum.indexOf(' ') != -1) { return false; }
		if(companynumVaildPattern.matcher(member_companynum).find() == false) { return false; }
		
		return true;
	}
}
