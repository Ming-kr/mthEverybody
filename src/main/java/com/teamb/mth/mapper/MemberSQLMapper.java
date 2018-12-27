package com.teamb.mth.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.teamb.mth.data.SearchData;
import com.teamb.mth.vo.MemberVO;

public interface MemberSQLMapper {

	@Select("SELECT * FROM mth_member WHERE member_code!='DEL_MEMBER'")
	public ArrayList<MemberVO> selectAll();

	@Select("SELECT * FROM MTH_MEMBER WHERE MEMBER_ID=#{member_id} AND MEMBER_CODE!='DEL_MEMBER'")
	public MemberVO selectById(MemberVO requestMemberVO);

	@Select("SELECT * FROM MTH_MEMBER WHERE MEMBER_EMAIL=#{member_email} AND MEMBER_CODE!='DEL_MEMBER'")
	public MemberVO selectByEmail(MemberVO requestMemberVO);

	@Select("SELECT * FROM MTH_MEMBER WHERE MEMBER_IDX=#{member_idx} AND MEMBER_CODE!='DEL_MEMBER'")
	public MemberVO selectByIdx(String member_idx);

	// 메일 인증시 회원가입 절차
	@Insert("INSERT INTO MTH_MEMBER VALUES(SEQ_MEMBER_IDX.NEXTVAL,#{member_id}, #{member_pw}, #{member_nick}, #{member_email},'JOINING', SYSDATE,'','','')")
	public void insert(MemberVO requestMemberVO);

	@Update("UPDATE MTH_MEMBER SET MEMBER_CODE='CUSTOMER' WHERE MEMBER_IDX=#{member_idx}")
	public void updateForJoining(String member_idx);

	@Update("UPDATE MTH_MEMBER SET MEMBER_CODE='REQ_SELLER', MEMBER_NAME=#{member_name}, MEMBER_PHONE=#{member_phone}, MEMBER_COMPANYNUM=#{member_companynum} WHERE MEMBER_IDX=#{member_idx}")
	public void updateForReqSeller(MemberVO requestMemberVO);

	// 판매자 승인 거부시 개인정보 삭제 같이..
	@Update("UPDATE MTH_MEMBER SET MEMBER_CODE='CUSTOMER', MEMBER_NAME='',MEMBER_PHONE='', MEMBER_COMPANYNUM='' WHERE MEMBER_IDX=#{member_idx}")
	public void updateRejectSeller(String member_idx);

	@Update("UPDATE MTH_MEMBER SET MEMBER_NICK=#{member_nick} WHERE MEMBER_IDX=#{member_idx}")
	public void updateForCustomerInfo(MemberVO requestMemberVO);

	@Update("UPDATE MTH_MEMBER SET MEMBER_PW=#{member_pw} WHERE MEMBER_IDX=#{member_idx}")
	public void updateMember_pw(MemberVO requestMemberVO);

	@Update("UPDATE MTH_MEMBER SET MEMBER_ID=(select MEMBER_ID||#{addStrAtId} FROM MTH_MEMBER WHERE MEMBER_IDX=#{requestMemberVO.member_idx}), MEMBER_CODE='DEL_MEMBER' WHERE MEMBER_IDX=#{requestMemberVO.member_idx}")
	public void updateForDeleteMember(@Param("requestMemberVO") MemberVO requestMemberVO,
			@Param("addStrAtId") String addStrAtId);

	@Select("SELECT SEQ_DELMEMBER_IDX.NEXTVAL FROM DUAL")
	public String selectDeleteMemberIdxKey();

	@Select("SELECT * FROM mth_member WHERE member_idx=#{member_idx}")
	public MemberVO selectByMemberIdx(String member_idx);

	@Update("UPDATE mth_member SET member_nick=#{member_nick}, member_phone= #{member_phone} WHERE member_idx=#{member_idx}")
	public void updateSellerMemberVO(MemberVO requestMemberVO);
	// 관리자 멤버리스트 용...
	// 관리자 멤버리스트 용...

	// 모든 회원
	@Select("SELECT * FROM mth_member WHERE member_code != 'DEL_MEMBER' ORDER BY member_idx DESC")
	public ArrayList<MemberVO> selectList();

	@Select("SELECT COUNT(1) FROM mth_member WHERE member_code!='DEL_MEMBER' ")
	public String countAll();

	@Select("SELECT * FROM mth_member WHERE member_code != 'DEL_MEMBER' AND ${searchSelect} LIKE '%'||#{searchWord}||'%' ORDER BY member_idx DESC")
	public ArrayList<MemberVO> selectSearchList(SearchData requestSearchData);

	@Select("SELECT COUNT(1) FROM mth_member WHERE member_code != 'DEL_MEMBER' AND ${searchSelect} LIKE '%'||#{searchWord}||'%'")
	public String countSearch(SearchData requestSearchData);

	// 구매자 회원
	@Select("SELECT COUNT(1) FROM mth_member WHERE member_code ='CUSTOMER'")
	public String countCustomerAll();

	@Select("SELECT COUNT(1) FROM mth_member WHERE member_code = 'CUSTOMER' AND ${searchSelect} LIKE '%'||#{searchWord}||'%'")
	public String countSearchCustomer(SearchData requestSearchData);

	// 판매자 회원
	@Select("SELECT COUNT(1) FROM mth_member WHERE member_code ='SELLER'")
	public String countSellerAll();

	@Select("SELECT COUNT(1) FROM mth_member WHERE member_code = 'SELLER' AND ${searchSelect} LIKE '%'||#{searchWord}||'%'")
	public String countSearchSeller(SearchData requestSearchData);

	// 판매자 승인 신청 대기 회원
	@Select("SELECT COUNT(1) FROM mth_member WHERE member_code ='REQ_SELLER'")
	public String countReqSellerAll();

	@Select("SELECT COUNT(1) FROM mth_member WHERE member_code = 'REQ_SELLER' AND ${searchSelect} LIKE '%'||#{searchWord}||'%'")
	public String countSearchReqSeller(SearchData requestSearchData);

	// 판매자 승인 신청 대기 회원
	@Select("SELECT COUNT(1) FROM mth_member WHERE member_code ='ADMIN'")
	public String countAdminAll();

	@Select("SELECT COUNT(1) FROM mth_member WHERE member_code = 'ADMIN' AND ${searchSelect} LIKE '%'||#{searchWord}||'%'")
	public String countSearchAdmin(SearchData requestSearchData);

	@Update("UPDATE mth_member SET member_code='ADMIN', member_name=#{member_name},member_email=#{member_email}, member_phone= #{member_phone} WHERE member_idx=#{member_idx}")
	public void updateGrantAdmin(MemberVO requestMemberVO);
	// 관리자 멤버리스트 용 끝...
	// 임시...

	@Update("UPDATE mth_member SET member_code='CUSTOMER' WHERE member_idx=#{member_idx}")
	public void updateCustomer(String member_idx);

	@Update("UPDATE mth_member SET member_code='SELLER' WHERE member_idx=#{member_idx}")
	public void updateSeller(String member_idx);

	@Update("UPDATE mth_member SET member_code='ADMIN' WHERE member_idx=#{member_idx}")
	public void updateAdmin(String member_idx);

}
