package com.teamb.mth.majorservice;

import java.util.ArrayList;
import com.teamb.mth.data.*;
import com.teamb.mth.util.PagingData;
import com.teamb.mth.vo.*;

public interface ConvenReService 
{
	//Conven_idx에 따라 댓글 리스트 및 갯수 가져오기
	public ArrayList<ConvenReData> getConvenReListByConvenIdx(String conven_idx,  PagingData requestPagingData);
	public int getConvenReListTotalCount(String conven_idx);

	//댓글 작성,수정,삭제
	public ConvenReVO getConvenReVO(String conven_re_idx);
	public void writeConvenReContent(ConvenReVO requestConvenReVO);
	public void updateConvenReContent(ConvenReVO requestConvenReVO);
	public void deleteConvenRe(String conven_re_idx);
}
