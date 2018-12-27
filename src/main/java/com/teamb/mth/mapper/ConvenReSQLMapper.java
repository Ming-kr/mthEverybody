package com.teamb.mth.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.*;

import com.teamb.mth.util.PagingData;
import com.teamb.mth.vo.ConvenReVO;

public interface ConvenReSQLMapper {
	@Select("SELECT * FROM(SELECT ROWNUM RNUM, FIR.* FROM (SELECT *  FROM MTH_CONVEN_RE WHERE CONVEN_IDX=#{conven_idx} AND CONVEN_RE_STATE !='HIDE' ORDER BY CONVEN_RE_WRITEDATE)FIR)SEC WHERE SEC.RNUM BETWEEN #{requestPagingData.startNum} AND #{requestPagingData.endNum}")
	public ArrayList<ConvenReVO> selectByConvenIdx(@Param("conven_idx") String conven_idx,
			@Param("requestPagingData") PagingData requestPagingData);

	@Select("SELECT COUNT(*) AS totalDataCount FROM MTH_CONVEN_RE WHERE CONVEN_IDX=#{conven_idx} AND CONVEN_RE_STATE !='HIDE'")
	public int getConvenReListTotalCount(String conven_idx);

	// 댓글 작성, 수정, 삭제
	@Insert("INSERT INTO MTH_CONVEN_RE VALUES(SEQ_CONVEN_RE_IDX.NEXTVAL,#{conven_idx},#{member_idx},#{conven_re_content}, 'NORMAL', SYSDATE)")
	public void insert(ConvenReVO requestConvenReVO);

	@Select("SELECT * FROM MTH_CONVEN_RE WHERE CONVEN_RE_IDX=#{conven_re_idx} AND CONVEN_RE_STATE !='HIDE'")
	public ConvenReVO selectByIdx(String conven_re_idx);

	@Update("UPDATE MTH_CONVEN_RE SET CONVEN_RE_CONTENT=#{conven_re_content} WHERE CONVEN_RE_IDX=#{conven_re_idx}")
	public void updateConvenReContent(ConvenReVO requestConvenReVO);

	@Update("UPDATE MTH_CONVEN_RE SET CONVEN_RE_STATE='HIDE' WHERE CONVEN_RE_IDX=#{conven_re_idx}")
	public void deleteConvenRe(String conven_re_idx);
}
