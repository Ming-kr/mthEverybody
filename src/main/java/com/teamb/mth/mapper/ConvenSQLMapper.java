package com.teamb.mth.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.teamb.mth.data.SearchData;
import com.teamb.mth.vo.ConvenVO;

public interface ConvenSQLMapper {

	// CONVEN버전...

	@Insert("INSERT INTO mth_conven VALUES(seq_conven_idx.nextval,#{member_idx},#{brand_idx},"
			+ "#{state_idx},#{conven_name},#{conven_adress},#{conven_condition},SYSDATE)")
	public void insert(ConvenVO requestConvenVO);

	@Select("SELECT * FROM mth_conven WHERE member_idx = #{member_idx} AND conven_condition != 'DELETE' ORDER BY conven_regdate DESC")
	public ArrayList<ConvenVO> selectByMemberIdx(String member_idx);

	@Select("SELECT * FROM mth_conven WHERE conven_idx=#{conven_idx}")
	public ConvenVO selectByIdx(String conven_idx);

	@Update("UPDATE mth_conven SET brand_idx = #{brand_idx}, state_idx=#{state_idx},conven_name=#{conven_name},"
			+ "conven_adress=#{conven_adress},conven_condition=#{conven_condition} WHERE conven_idx=#{conven_idx}")
	public void updateConven(ConvenVO requestConvenVO);

	@Select("SELECT * FROM mth_conven ORDER BY conven_regdate DESC")
	public ArrayList<ConvenVO> selectAll();

	// 관리자 편의점 리스트용...

	// 모든 회원
	@Select("SELECT * FROM mth_conven WHERE conven_condition != 'DELETE' ORDER BY conven_idx DESC")
	public ArrayList<ConvenVO> selectList();

	@Select("SELECT COUNT(1) FROM mth_conven WHERE conven_condition!='DELETE'")
	public String countAll();

	@Select("SELECT * FROM mth_conven WHERE conven_condition != 'DELETE' AND ${searchSelect} LIKE '%'||#{searchWord}||'%' ORDER BY conven_idx DESC")
	public ArrayList<ConvenVO> selectSearchList(SearchData requestSearchData);

	@Select("SELECT COUNT(1) FROM mth_conven WHERE conven_condition != 'DELETE' AND ${searchSelect} LIKE '%'||#{searchWord}||'%'")
	public String countSearch(SearchData requestSearchData);

	// 정상
	@Select("SELECT * FROM mth_conven WHERE conven_condition = 'NORMAL' ORDER BY conven_idx DESC")
	public ArrayList<ConvenVO> selectNormalList();
	
	@Select("SELECT COUNT(1) FROM mth_conven WHERE conven_condition = 'NORMAL'")
	public String countNormalAll();

	@Select("SELECT COUNT(1) FROM mth_conven WHERE conven_condition = 'NORMAL' AND ${searchSelect} LIKE '%'||#{searchWord}||'%'")
	public String countNormalSearch(SearchData requestSearchData);

	// 신청상태
	@Select("SELECT * FROM mth_conven WHERE conven_condition = 'REQUEST' ORDER BY conven_idx DESC")
	public ArrayList<ConvenVO> selectRequestList();
	
	@Select("SELECT COUNT(1) FROM mth_conven WHERE conven_condition = 'REQUEST'")
	public String countRequestAll();

	@Select("SELECT COUNT(1) FROM mth_conven WHERE conven_condition = 'REQUEST' AND ${searchSelect} LIKE '%'||#{searchWord}||'%'")
	public String countRequestSearch(SearchData requestSearchData);

	// 편의점 승인 거절
	@Select("SELECT * FROM mth_conven WHERE conven_condition = 'DENIED' ORDER BY conven_idx DESC")
	public ArrayList<ConvenVO> selectDeniedList();

	@Select("SELECT COUNT(1) FROM mth_conven WHERE conven_condition = 'DENIED'")
	public String countDeniedAll();

	@Select("SELECT COUNT(1) FROM mth_conven WHERE conven_condition = 'DENIED' AND ${searchSelect} LIKE '%'||#{searchWord}||'%'")
	public String countDeniedSearch(SearchData requestSearchData);

}