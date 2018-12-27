package com.teamb.mth.majorservice;

import java.util.ArrayList;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamb.mth.data.SearchData;
import com.teamb.mth.data.SessionLoginedData;
import com.teamb.mth.mapper.MemberSQLMapper;
import com.teamb.mth.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberSQLMapper memberSQLMapper;

	@Override
	public ArrayList<MemberVO> getAll() {
		return memberSQLMapper.selectAll();
	}

	// 로그인 처리
	@Override
	public SessionLoginedData login(MemberVO requestMemberVO) {
		// 비밀번호 해싱 처리
		String digestPwValue = DigestUtils.sha256Hex(requestMemberVO.getMember_pw());
		requestMemberVO.setMember_pw(digestPwValue);

		MemberVO memberVOById = memberSQLMapper.selectById(requestMemberVO);

		// 가져온 데이타가 없거나, 패스워드가 다르거나, 코드가 회원탈퇴한 코드(DEL_MEMBER)일 땐 로그인X
		if (memberVOById != null && requestMemberVO.getMember_pw().equals(memberVOById.getMember_pw())) {
			return new SessionLoginedData(memberVOById.getMember_idx(), memberVOById.getMember_nick(),
					memberVOById.getMember_code());
		}

		return null;
	}

	// 신규회원 등록 처리
	@Override
	public void joinMember(MemberVO requestMemberVO) {
		// 비밀번호 해싱 처리
		String digestPwValue = DigestUtils.sha256Hex(requestMemberVO.getMember_pw());
		requestMemberVO.setMember_pw(digestPwValue);

		memberSQLMapper.insert(requestMemberVO);
	}

	// 회원가입시 아이디 중복확인 처리
	@Override
	public boolean checkValidJoinId(MemberVO requestMemberVO) {
		MemberVO unvalidMemberData = memberSQLMapper.selectById(requestMemberVO);
		if (unvalidMemberData != null) {
			return false;
		}

		return true;
	}

	// 회원가입시 이메일 중복확인
	@Override
	public boolean checkValidJoinEmail(MemberVO requestMemberVO) 
	{
		MemberVO unvalidMemberData = memberSQLMapper.selectByEmail(requestMemberVO);

		if (unvalidMemberData != null) {
			return false;
		}
		return true;
	}
	
	//member_id로 회원정보 불러오기
	@Override
	public MemberVO getMemberVOById(MemberVO requestMemberVO) 
	{
		MemberVO memberVO = memberSQLMapper.selectById(requestMemberVO);
		// 보안위해 비밀번호 null처리
		if(memberVO != null) { memberVO.setMember_pw(null); }

		return memberVO;
	}

	// member_idx로 해당 회원정보 불러오기
	@Override
	public MemberVO getMemberVO(MemberVO requestMemberVO) {
		MemberVO memberVO = memberSQLMapper.selectByIdx(requestMemberVO.getMember_idx());
		// 보안위해 비밀번호 null처리
		if(memberVO != null) { memberVO.setMember_pw(null); }

		return memberVO;
	}

	// member_idx로 해당 회원 비밀번호와 입력값 확인 후 동일하면 true 리턴
	@Override
	public boolean checkPassword(MemberVO requestMemberVO) {
		// 비밀번호 해싱 처리
		String digestPwValue = DigestUtils.sha256Hex(requestMemberVO.getMember_pw());
		requestMemberVO.setMember_pw(digestPwValue);

		MemberVO checkMemberVO = memberSQLMapper.selectByIdx(requestMemberVO.getMember_idx());

		if (checkMemberVO.getMember_pw().equals(requestMemberVO.getMember_pw())) {
			return true;
		}

		return false;
	}

	// 판매자회원 신청
	@Override
	public void requestSeller(MemberVO requestMemberVO) {
		memberSQLMapper.updateForReqSeller(requestMemberVO);
	}

	// 일반회원 정보 수정 처리
	@Override
	public void updateCustomerInfo(MemberVO requestMemberVO) {
		memberSQLMapper.updateForCustomerInfo(requestMemberVO);
	}

	// 회원 비밀번호 수정 처리
	@Override
	public void updateMemberPassword(MemberVO requestMemberVO) {
		// 비밀번호 해싱 처리
		String digestPwValue = DigestUtils.sha256Hex(requestMemberVO.getMember_pw());
		requestMemberVO.setMember_pw(digestPwValue);
		memberSQLMapper.updateMember_pw(requestMemberVO);
	}

	// 회원코드 DELETEDMEMBER 처리(회원탈퇴)
	@Override
	public void deleteMemberVO(MemberVO requestMemberVO) {
		// 탈퇴회원 id 수정(탈퇴회원idx 생성해서 문자열로 받아와서 기존ID 뒤에 문자열 합침)
		String addStrAtId = "_DM" + memberSQLMapper.selectDeleteMemberIdxKey();

		memberSQLMapper.updateForDeleteMember(requestMemberVO, addStrAtId);
	}

	@Override
	public MemberVO getMemberVO(String member_idx) {
		// TODO Auto-generated method stub
		return memberSQLMapper.selectByMemberIdx(member_idx);
	}

	@Override
	public void updateSellerMyInfo(MemberVO requestMemberVO) {
		// TODO Auto-generated method stub
		memberSQLMapper.updateSellerMemberVO(requestMemberVO);
	}

	// 임시
	@Override
	public void updateCustomer(String member_idx) {
		// TODO Auto-generated method stub
		memberSQLMapper.updateCustomer(member_idx);
	}

	@Override
	public void updateSeller(String member_idx) {
		// TODO Auto-generated method stub
		memberSQLMapper.updateSeller(member_idx);
	}

	@Override
	public void updateAdmin(String member_idx) {
		// TODO Auto-generated method stub
		memberSQLMapper.updateAdmin(member_idx);
	}
	// 임시 끝..

	// 관리자 멤버리스트 서치용...
	// 모두
	@Override
	public ArrayList<MemberVO> getMemberList() {
		// TODO Auto-generated method stub
		return memberSQLMapper.selectList();
	}

	@Override
	public String countList() {
		// TODO Auto-generated method stub
		return memberSQLMapper.countAll();
	}

	@Override
	public ArrayList<MemberVO> getSearchMemberList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		/*
		 * requestSearchData.setSearchWord("'%"+requestSearchData.getSearchWord()+"%'");
		 */
		return memberSQLMapper.selectSearchList(requestSearchData);
	}

	@Override
	public String countSearchList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return memberSQLMapper.countSearch(requestSearchData);
	}

	// 구매자
	@Override
	public String countCustomerList() {
		// TODO Auto-generated method stub
		return memberSQLMapper.countCustomerAll();
	}

	@Override
	public String countSearchCustomerList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return memberSQLMapper.countSearchCustomer(requestSearchData);
	}

	// 판매자 승인 대기
	@Override
	public String countReqSellerList() {
		// TODO Auto-generated method stub
		return memberSQLMapper.countReqSellerAll();
	}
	
	//판매자 승인 거부
	@Override
	public void rejectReqSeller(String member_idx) 
	{
		memberSQLMapper.updateRejectSeller(member_idx);
	}

	@Override
	public String countSearchReqSellerList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return memberSQLMapper.countSearchReqSeller(requestSearchData);
	}

	// 판매자
	@Override
	public String countSellerList() {
		// TODO Auto-generated method stub
		return memberSQLMapper.countSellerAll();
	}

	@Override
	public String countSearchSellerList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return memberSQLMapper.countSearchSeller(requestSearchData);
	}
	
	@Override
	public String countAdminList() {
		// TODO Auto-generated method stub
		return memberSQLMapper.countAdminAll();
	}
	
	@Override
	public String countSearchAdminList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return memberSQLMapper.countSearchAdmin(requestSearchData);
	}

	@Override
	public void updateGrantAdmin(MemberVO requestMemberVO) {
		// TODO Auto-generated method stub
		memberSQLMapper.updateGrantAdmin(requestMemberVO);
	}
	// 관리자 멤버리스트 서치용 끝...

}
