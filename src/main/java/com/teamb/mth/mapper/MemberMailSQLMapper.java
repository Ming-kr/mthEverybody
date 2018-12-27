package com.teamb.mth.mapper;

import org.apache.ibatis.annotations.*;
import com.teamb.mth.vo.MemberMailVO;

public interface MemberMailSQLMapper 
{
	@Select("SELECT * FROM MTH_MEMBER_MAIL WHERE MEMBER_IDX=#{member_idx} AND DUEDATE>=SYSDATE")
	public MemberMailVO selectInDuedate(String member_idx);
	
	@Select("SELECT * FROM MTH_MEMBER_MAIL WHERE MEMBER_IDX=#{member_idx}")
	public MemberMailVO selectByMemberIdx(String member_idx);
	
	@Insert("INSERT INTO MTH_MEMBER_MAIL VALUES(#{member_idx}, #{key}, 'N', SYSDATE+1)")
	public void insertKey(MemberMailVO requestMemberMailVO);
	
	@Update("UPDATE MTH_MEMBER_MAIL SET CERTIFY_CHECK='Y' WHERE MEMBER_IDX=#{member_idx} AND DUEDATE>=SYSDATE")
	public void updateCertifyCheck(String member_idx);
	
	@Update("UPDATE MTH_MEMBER_MAIL SET KEY=#{key}, DUEDATE=SYSDATE+1 WHERE MEMBER_IDX=#{member_idx}")
	public void updateKey(MemberMailVO requestMemberMailVO);
}
