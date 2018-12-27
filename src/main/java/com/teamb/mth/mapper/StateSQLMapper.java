package com.teamb.mth.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;

import com.teamb.mth.vo.StateVO;

public interface StateSQLMapper {
	
	@Select("SELECT * FROM mth_state WHERE state_idx=#{state_idx}")
	public StateVO selectByIdx(String state_idx);

	@Select("SELECT * FROM mth_State")
	public ArrayList<StateVO> selectAll();
}
