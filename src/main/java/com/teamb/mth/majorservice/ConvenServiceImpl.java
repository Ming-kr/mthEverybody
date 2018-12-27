package com.teamb.mth.majorservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamb.mth.data.ConvenData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.mapper.BrandSQLMapper;
import com.teamb.mth.mapper.ConvenSQLMapper;
import com.teamb.mth.mapper.MemberSQLMapper;
import com.teamb.mth.mapper.StateSQLMapper;
import com.teamb.mth.vo.BrandVO;
import com.teamb.mth.vo.ConvenVO;
import com.teamb.mth.vo.MemberVO;
import com.teamb.mth.vo.StateVO;

@Service
public class ConvenServiceImpl implements ConvenService {
	
	@Autowired
	ConvenSQLMapper convenSQLMapper;
	@Autowired
	BrandSQLMapper brandSQLMapper;
	@Autowired
	StateSQLMapper stateSQLMapper;
	@Autowired
	MemberSQLMapper memberSQLMapper;
	
	@Override
	public void registerConven(String member_idx, ConvenVO requestConvenVO) {
		// TODO Auto-generated method stub
		System.out.println("ConvenServiceImpl registerConven 도착!!!");

		//MEMBER_IDX 지정
		requestConvenVO.setMember_idx(member_idx);
		
		// 편의점 신청 == requestConven 문구로 처리
		requestConvenVO.setConven_condition("REQUEST");

		convenSQLMapper.insert(requestConvenVO);

		System.out.println("mth_Conven 데이터 저장!!!");

	}

	@Override
	public ArrayList<ConvenData> getMyConvenDataList(String member_idx) {
		// TODO Auto-generated method stub
		
		System.out.println("ConvenServiceImpl getConvenList 도착!!!");
		//member_idx로 내 편의점 리스트 불러오기
		ArrayList<ConvenVO> convenList = convenSQLMapper.selectByMemberIdx(member_idx);
		ArrayList<ConvenData> convenDataList = new ArrayList<ConvenData>();
		//convenVO에 해당하는 brandVO, stateVO 불러와서 convenDataList에 저장
		for (ConvenVO convenVO : convenList) {
			BrandVO brandVO = brandSQLMapper.selectByIdx(convenVO.getBrand_idx());
			StateVO stateVO = stateSQLMapper.selectByIdx(convenVO.getState_idx());
			convenDataList.add(new ConvenData(convenVO, brandVO, stateVO));
		}
		System.out.println("ConvenServiceImpl getConvenList 끝!!!");
		return convenDataList;
	}

	@Override
	public ConvenData getConvenData(String conven_idx) {
		// TODO Auto-generated method stub
		System.out.println("ConvenServiceImpl selectByConvenIdx 도착!!!");
		
		ConvenVO convenVO = convenSQLMapper.selectByIdx(conven_idx);
		MemberVO memberVO = memberSQLMapper.selectByIdx(convenVO.getMember_idx());
		BrandVO brandVO = brandSQLMapper.selectByIdx(convenVO.getBrand_idx());
		StateVO stateVO = stateSQLMapper.selectByIdx(convenVO.getState_idx());
		System.out.println("ConvenServiceImpl selectByConvenIdx 끝!!!");
		
		//conven_idx와 일치하는 convenVO, brandVO, stateVO 불러와서 convenData 보내기
		return new ConvenData(convenVO,memberVO, brandVO,stateVO);
	}

	
	@Override
	public void updateConven(ConvenVO requestConvenVO) {
		// TODO Auto-generated method stub
		convenSQLMapper.updateConven(requestConvenVO);
	}

	@Override
	public void deleteConven(String conven_idx) {
		// TODO Auto-generated method stub
		System.out.println("ConvenServiceImpl deleteConven 도착!!!");

		// conven_idx로 편의점 정보 가져오기
		ConvenVO convenVO = convenSQLMapper.selectByIdx(conven_idx);
		
		// 편의점 신청 == delete 문구로 처리
		convenVO.setConven_condition("DELETE");

		convenSQLMapper.updateConven(convenVO);

		System.out.println("mth_Conven 데이터 삭제!!!");
	}

	@Override
	public ConvenVO getConvenVO(String conven_idx) {
		// TODO Auto-generated method stub
		return convenSQLMapper.selectByIdx(conven_idx);
	}

	@Override
	public ArrayList<ConvenVO> getAll() {
		// TODO Auto-generated method stub
		return convenSQLMapper.selectAll();
	}

	@Override
	public ArrayList<ConvenVO> getConvenList() {
		// TODO Auto-generated method stub
		return convenSQLMapper.selectList();
	}

	@Override
	public String countList() {
		// TODO Auto-generated method stub
		return convenSQLMapper.countAll();
	}

	@Override
	public ArrayList<ConvenVO> getSearchConvenList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return convenSQLMapper.selectSearchList(requestSearchData);
	}

	@Override
	public String countSearchList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return convenSQLMapper.countSearch(requestSearchData);
	}

	@Override
	public String countNormalList() {
		// TODO Auto-generated method stub
		return convenSQLMapper.countNormalAll();
	}

	@Override
	public String countSearchNormalList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return convenSQLMapper.countNormalSearch(requestSearchData);
	}

	@Override
	public String countRequestList() {
		// TODO Auto-generated method stub
		return convenSQLMapper.countRequestAll();
	}

	@Override
	public String countSearchRequestList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return convenSQLMapper.countRequestSearch(requestSearchData);
	}
	
	@Override
	public String countDeniedList() {
		// TODO Auto-generated method stub
		return convenSQLMapper.countDeniedAll();
	}

	@Override
	public String countSearchDeniedList(SearchData requestSearchData) {
		// TODO Auto-generated method stub
		return convenSQLMapper.countDeniedSearch(requestSearchData);
	}

	@Override
	public ArrayList<ConvenData> getConvenDataList(ArrayList<ConvenVO> convenList) {
		// TODO Auto-generated method stub
		ArrayList<ConvenData> convenDataList = new ArrayList<ConvenData>();
		for(ConvenVO convenVO : convenList) {
			MemberVO memberVO = memberSQLMapper.selectByIdx(convenVO.getMember_idx());
			BrandVO brandVO = brandSQLMapper.selectByIdx(convenVO.getBrand_idx());
			StateVO stateVO = stateSQLMapper.selectByIdx(convenVO.getState_idx());
			
			convenDataList.add(new ConvenData(convenVO, memberVO, brandVO, stateVO));
		}
		return convenDataList;
	}

	@Override
	public ArrayList<ConvenData> getSearchConvenDataList(ArrayList<ConvenVO> convenList, String searchSelect,
			String searchWord) {
		// TODO Auto-generated method stub
		ArrayList<ConvenData> convenDataList = new ArrayList<ConvenData>();
		for(ConvenVO convenVO : convenList) {
			MemberVO memberVO = memberSQLMapper.selectByIdx(convenVO.getMember_idx());
			if(searchSelect.equals("member_nick") && !memberVO.getMember_nick().contains(searchWord)) {
				continue;
			}
			BrandVO brandVO = brandSQLMapper.selectByIdx(convenVO.getBrand_idx());
			if(searchSelect.equals("brand_name") && !brandVO.getBrand_name().contains(searchWord)) {
				continue;
			}
			StateVO stateVO = stateSQLMapper.selectByIdx(convenVO.getState_idx());
			
			convenDataList.add(new ConvenData(convenVO, memberVO, brandVO, stateVO));
		}
		return convenDataList;
	}

	@Override
	public ArrayList<ConvenVO> getConvenNomalList() {
		// TODO Auto-generated method stub
		return convenSQLMapper.selectNormalList();
	}

	@Override
	public ArrayList<ConvenVO> getConvenRequestList() {
		// TODO Auto-generated method stub
		return convenSQLMapper.selectRequestList();
	}
	
	@Override
	public ArrayList<ConvenVO> getConvenDeniedList() {
		// TODO Auto-generated method stub
		return convenSQLMapper.selectDeniedList();
	}

}
