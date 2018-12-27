package com.teamb.mth.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.teamb.mth.util.PagingData;
import com.teamb.mth.vo.ItemReVO;

public interface ItemReSQLMapper {
	// item_idx에 따라 댓글 리스트 및 댓글갯수 가져오기
	@Select("SELECT * FROM(SELECT ROWNUM RNUM, FIR.* FROM (SELECT *  FROM MTH_ITEM_RE WHERE ITEM_IDX=#{item_idx} AND ITEM_RE_STATE !='HIDE' ORDER BY ITEM_RE_WRITEDATE)FIR)SEC WHERE SEC.RNUM BETWEEN #{requestPagingData.startNum} AND #{requestPagingData.endNum}")
	public ArrayList<ItemReVO> selectByItemIdx(@Param("item_idx") String item_idx,
			@Param("requestPagingData") PagingData requestPagingData);

	@Select("SELECT COUNT(*) AS totalDataCount FROM MTH_ITEM_RE WHERE item_idx=#{item_idx} AND ITEM_RE_STATE !='HIDE'")
	public int getItemReListTotalDataCount(String item_idx);

	// 댓글 작성, 수정, 삭제
	@Insert("INSERT INTO MTH_ITEM_RE VALUES(SEQ_ITEM_RE_IDX.NEXTVAL,#{item_idx},#{member_idx},#{item_re_content}, 'NORMAL', SYSDATE)")
	public void insert(ItemReVO requestItemReVO);

	@Select("SELECT * FROM MTH_ITEM_RE WHERE ITEM_RE_IDX=#{item_re_idx} AND ITEM_RE_STATE !='HIDE'")
	public ItemReVO selectByIdx(String item_re_idx);

	@Update("UPDATE MTH_ITEM_RE SET ITEM_RE_CONTENT=#{item_re_content} WHERE ITEM_RE_IDX=#{item_re_idx}")
	public void updateItemReContent(ItemReVO requestItemReVO);

	@Update("UPDATE MTH_ITEM_RE SET ITEM_RE_STATE='HIDE' WHERE ITEM_RE_IDX=#{item_re_idx}")
	public void deleteItemRe(String item_re_idx);
}
