package com.teamb.mth.majorservice;

import com.teamb.mth.vo.MemberMailVO;
import com.teamb.mth.vo.MemberVO;

public interface MemberMailService 
{
	public void setKeyForJoin(MemberVO requestMemberVO, String key);
	public boolean certifyKey(MemberMailVO requestMemberMailVO);
	public void successCertify(String member_idx);
	public void updateKey(String member_idx, String key);
	public MemberMailVO getMemberMailVO(String member_idx);
}
