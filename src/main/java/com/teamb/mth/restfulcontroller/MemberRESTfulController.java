package com.teamb.mth.restfulcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamb.mth.data.SearchData;
import com.teamb.mth.data.SessionLoginedData;
import com.teamb.mth.majorservice.MemberService;
import com.teamb.mth.vo.MemberVO;

@RestController
public class MemberRESTfulController {
	@Autowired
	MemberService memberService;

	//회원가입 아이디 중복확인
	@RequestMapping("checkValidId")
	public boolean checkValidId(MemberVO requestMemberVO) 
	{
		if(requestMemberVO.getMember_id() == "") { return false; }
		return memberService.checkValidJoinId(requestMemberVO);
	}
	
	//회원가입시 이미 가입한 이메일 조회
	@RequestMapping("checkValidEmail")
	public boolean checkValidEmail(MemberVO requestMemberVO)
	{
		if(requestMemberVO.getMember_email() == "") { return false; }
		return memberService.checkValidJoinId(requestMemberVO);
	}
	
	//비밀번호확인
	@RequestMapping("checkPassword")
	public boolean checkPassword(HttpSession session, MemberVO requestMemberVO)
	{
		if(requestMemberVO.getMember_pw() == "") { return false; }
		
		//requestMemberVo 세팅
		String member_idx = ((SessionLoginedData)session.getAttribute("sessionLoginedData")).getMember_idx();
		requestMemberVO.setMember_idx(member_idx);
		
		return memberService.checkPassword(requestMemberVO);
	}
	
	@RequestMapping("/admin/filterSearchMemberList")
	public SearchData filterSearchMemberList(MemberVO requestMemberVO, @RequestParam("searchSelect")String searchSelect, @RequestParam("searchWord")String searchWord) {
		
		String member_code = requestMemberVO.getMember_code();
		
		SearchData searchData = new SearchData();
		searchData.setSearchSelect(searchSelect);
		searchData.setSearchWord(searchWord);
		searchData.setSearchWord("'%"+searchData.getSearchWord()+"%'");

		//멤버 코드 검색에 따른 페이지 크기...//
		if(member_code.equals("SELLER")) {
			//페이지 크기...//
			String paging = memberService.countSearchSellerList(searchData); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
			//페이지 크기 끝...//
		} else if(member_code.equals("REQ_SELLER")) {
			//페이지 크기...//
			String paging = memberService.countSearchReqSellerList(searchData); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
			//페이지 크기 끝...//
		} else if(member_code.equals("CUSTOMER")) {
			//페이지 크기...//
			String paging = memberService.countSearchCustomerList(searchData); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
			//페이지 크기 끝...//
		} else if(member_code.equals("ADMIN")) {
			//페이지 크기...//
			String paging = memberService.countSearchAdminList(searchData); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
			//페이지 크기 끝...//
		} else {
			String paging = memberService.countSearchList(searchData); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
		}
		return searchData;
	}
	
	
	@RequestMapping("/admin/filterMemberList")
	public SearchData filterMemberList(MemberVO requestMemberVO) {
		
		String member_code = requestMemberVO.getMember_code();
		
		SearchData searchData = new SearchData();
		//멤버 코드 검색에 따른 페이지 크기...//
		if(member_code.equals("SELLER")) {
			//페이지 크기...//
			String paging = memberService.countSellerList(); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
			//페이지 크기 끝...//
		} else if(member_code.equals("REQ_SELLER")) {
			//페이지 크기...//
			String paging = memberService.countReqSellerList(); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
			//페이지 크기 끝...//
		} else if(member_code.equals("CUSTOMER")) {
			//페이지 크기...//
			String paging = memberService.countCustomerList(); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
			//페이지 크기 끝...//
		} else if(member_code.equals("ADMIN")) {
			//페이지 크기...//
			String paging = memberService.countAdminList(); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
			//페이지 크기 끝...//
		} else {
			String paging = memberService.countList(); //총 게시글 개수
			if(Integer.parseInt(paging)<=1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging)-1)/10+1);
			}
			searchData.setPaging(paging);
		}
		return searchData;
	}
	
	@RequestMapping("/admin/pageMemberList")
	public SearchData pageMemberList(SearchData requestSearchData, @RequestParam("nowPage") int nowPage) {
		
		SearchData searchData = new SearchData();
		//페이지 이동 처리 로직...//
		if(nowPage<=1) { //페이지 넘버 없을 때
			requestSearchData.setPageNum("1");
			requestSearchData.setStartNum("1");
			requestSearchData.setEndNum("10"); 
		} else {
			System.out.println("paging:"+nowPage);
			requestSearchData.setPageNum(String.valueOf(nowPage));
			requestSearchData.setStartNum(String.valueOf(nowPage*10-9)); //페이지 넘버로 rownum 시작값 세팅
			requestSearchData.setEndNum(String.valueOf(nowPage*10)); //페이지 넘버로 rownum 끝나는 값 세팅
		}
		searchData.setPageNum(requestSearchData.getPageNum());
		searchData.setStartNum(requestSearchData.getStartNum());
		searchData.setEndNum(requestSearchData.getEndNum());
		//페이지  이동 처리 로직 끝...//
		
		return searchData;
	}
}
