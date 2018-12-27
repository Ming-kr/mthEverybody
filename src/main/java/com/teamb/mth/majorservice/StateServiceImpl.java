package com.teamb.mth.majorservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamb.mth.mapper.StateSQLMapper;
import com.teamb.mth.vo.StateVO;

@Service
public class StateServiceImpl implements StateService {
	@Autowired
	StateSQLMapper stateSQLMapper;

	@Override
	public StateVO getStateVO(String state_idx) {
		// TODO Auto-generated method stub
		return stateSQLMapper.selectByIdx(state_idx);
	}

	@Override
	public ArrayList<StateVO> getStateList() {
		// TODO Auto-generated method stub
		return stateSQLMapper.selectAll();
	}

}
