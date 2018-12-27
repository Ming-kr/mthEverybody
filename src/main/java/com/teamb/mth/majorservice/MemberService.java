package com.teamb.mth.majorservice;

import java.util.ArrayList;

import com.teamb.mth.data.SearchData;
import com.teamb.mth.data.SessionLoginedData;
import com.teamb.mth.vo.MemberVO;

public interface MemberService {

	public ArrayList<MemberVO> getAll();

	public SessionLoginedData login(MemberVO requestMemberVO);

	public MemberVO getMemberVO(MemberVO requestMemberVO);

	public MemberVO getMemberVOById(MemberVO requestMemberVO);

	public void joinMember(MemberVO requestMemberVO);

	public boolean checkValidJoinId(MemberVO requestMemberVO);

	public boolean checkValidJoinEmail(MemberVO requestMemberVO);

	public boolean checkPassword(MemberVO requestMemberVO);

	public void requestSeller(MemberVO requestMemberVO);

	public void updateCustomerInfo(MemberVO requestMemberVO);

	public void updateMemberPassword(MemberVO requestMemberVO);

	public void deleteMemberVO(MemberVO requestMemberVO);

	public MemberVO getMemberVO(String member_idx);

	public void updateSellerMyInfo(MemberVO requestMemberVO);
	
	public void updateGrantAdmin(MemberVO requestMemberVO);

	// 관리자 멤버리스트 관련...
	// 모든 회원
	public ArrayList<MemberVO> getMemberList();

	public String countList();

	public ArrayList<MemberVO> getSearchMemberList(SearchData requestSearchData);

	public String countSearchList(SearchData requestSearchData);

	// 구매자
	public String countCustomerList();

	public String countSearchCustomerList(SearchData requestSearchData);

	// 판매자 승인 대기
	public String countReqSellerList();

	public String countSearchReqSellerList(SearchData requestSearchData);

	// 판매자 승인 거절
	public void rejectReqSeller(String member_idx);

	// 판매자
	public String countSellerList();

	public String countSearchSellerList(SearchData requestSearchData);

	// 관리자
	public String countAdminList();

	public String countSearchAdminList(SearchData requestSearchData);
	//

	// 임시

	public void updateCustomer(String member_idx);

	public void updateSeller(String member_idx);

	public void updateAdmin(String member_idx);
}
