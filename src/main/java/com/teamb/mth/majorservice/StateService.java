package com.teamb.mth.majorservice;

import java.util.ArrayList;

import com.teamb.mth.vo.StateVO;

public interface StateService {
	public StateVO getStateVO(String state_idx);

	public ArrayList<StateVO> getStateList();

}
