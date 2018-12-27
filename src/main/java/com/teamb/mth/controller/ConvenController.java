package com.teamb.mth.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teamb.mth.data.ConvenData;
import com.teamb.mth.data.ConvenReData;
import com.teamb.mth.data.ProdData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.data.SessionLoginedData;
import com.teamb.mth.majorservice.BrandService;
import com.teamb.mth.majorservice.ConvenReService;
import com.teamb.mth.majorservice.ConvenService;
import com.teamb.mth.majorservice.ItemLikeService;
import com.teamb.mth.majorservice.MemberService;
import com.teamb.mth.majorservice.ProdService;
import com.teamb.mth.majorservice.StateService;
import com.teamb.mth.util.MailHandler;
import com.teamb.mth.util.PagingData;
import com.teamb.mth.util.ThreadSendMail;
import com.teamb.mth.vo.ConvenReVO;
import com.teamb.mth.vo.ConvenVO;
import com.teamb.mth.vo.ItemLikeVO;
import com.teamb.mth.vo.MemberVO;
import com.teamb.mth.vo.ProdVO;

@Controller
public class ConvenController {

	@Autowired
	MemberService memberService;

	@Autowired
	ConvenService convenService;

	@Autowired
	BrandService brandService;

	@Autowired
	StateService stateService;

	@Autowired
	ProdService prodService;

	@Autowired
	ConvenReService convenReService;

	@Autowired
	ItemLikeService itemLikeService;
	
	@Inject
	JavaMailSender mailSender;

	// 편의점 등록 페이지
	@RequestMapping("/seller/requestConvenienceForm")
	public String requestConvenienceForm(Model model) {
		// 등록 시 필요한 지역별 구 정보, 브랜드 정보 불러오기
		model.addAttribute("stateList", stateService.getStateList());
		model.addAttribute("brandList", brandService.getBrandList());

		return "/seller/requestConvenienceForm";
	}

	// 편의점 등록 처리
	@RequestMapping("/seller/actionRequestConvenienceForm")
	public String actionRequestConvenienceForm(HttpSession session, ConvenVO requestConvenVO) {
		// session에서 member_idx 가져오기
		String member_idx = ((SessionLoginedData) session.getAttribute("sessionLoginedData")).getMember_idx();
		convenService.registerConven(member_idx, requestConvenVO);

		return "redirect:/seller/convenienceMyList";
	}

	// 편의점 정보 삭제
	@RequestMapping("/seller/deleteConven")
	public String deleteConven(Model model, @RequestParam("conven_idx") String conven_idx) {
		// 편의점 삭제처리
		convenService.deleteConven(conven_idx);
		// 해당 편의점 떨이 물품 리스트 불러오기
		ArrayList<ProdVO> prodList = prodService.getMyProdList(conven_idx);

		for (ProdVO prodVO : prodList) {// 물품 condition 삭제처리...안보이게

			prodVO.setProd_condition("DELETE");

			prodService.updateProdCondition(prodVO);
		}

		return "redirect:/seller/convenienceMyList";
	}

	// 편의점 리스트 보여주는 페이지
	@RequestMapping("/seller/convenienceMyList")
	public String convenienceMyList(HttpSession session, Model model) {

		// session에서 member_idx 가져오기
		String member_idx = ((SessionLoginedData) session.getAttribute("sessionLoginedData")).getMember_idx();
		// member_idx와 일치하는 편의점리스트 불러오기
		ArrayList<ConvenData> ConvenDataList = convenService.getMyConvenDataList(member_idx);
		model.addAttribute("ConvenDataList", ConvenDataList);

		return "/seller/convenienceMyList";
	}

	////////////////// 수정
	////////////////// 끝/////////////////////////////////////////////////////////////////////////////////////////
	// 관리자 용...

	// 관리자 용...

	@RequestMapping("/admin/convenienceList")
	public String convenienceList(Model model, SearchData requestSearchData,
			@RequestParam(value = "conven_condition", required = false) String conven_condition) {

		// 필터링을 위한 아이템 컨디션 값 넘겨주기...
		if (conven_condition == null) {
			conven_condition = "all";
		}
		model.addAttribute("conven_condition", conven_condition);
		// 필터링을 위한 아이템 컨디션 값 넘겨주기 끝...

		// 모든 편의점 리스트 불러오기
		SearchData searchData = new SearchData(); // 모델에 넘겨줄 searchData 세팅
		ArrayList<ConvenData> convenDataList = new ArrayList<ConvenData>();

		// 페이지 이동 처리 로직...//
		if (requestSearchData.getPageNum() == null) { // 페이지 넘버 없을 때
			requestSearchData.setPageNum("1");
			requestSearchData.setStartNum("1");
			requestSearchData.setEndNum("10");
		} else {
			String pageNum = requestSearchData.getPageNum();
			requestSearchData.setStartNum(String.valueOf(Integer.parseInt(pageNum) * 10 - 9)); // 페이지 넘버로 rownum 시작값 세팅
			requestSearchData.setEndNum(String.valueOf(Integer.parseInt(pageNum) * 10)); // 페이지 넘버로 rownum 끝나는 값 세팅
		}
		searchData.setPageNum(requestSearchData.getPageNum());
		searchData.setStartNum(requestSearchData.getStartNum());
		searchData.setEndNum(requestSearchData.getEndNum());
		// 페이지 이동 처리 로직 끝...//

		// 검색 출력 로직...//
		if (requestSearchData.getSearchWord() != "" && requestSearchData.getSearchWord() != null) { // 검색 워드를 넣었을 때...

			// 검색 선택 넣기 로직...//
			String searchSelect = requestSearchData.getSearchSelect();
			searchData.setSearchSelect(searchSelect); // 검색 셀렉트에 남아 있게 하기 위하여...
			// 검색 선택 넣기 로직... 끝//

			// 검색 내용 넣기 로직...//
			String searchWord = requestSearchData.getSearchWord();
			searchData.setSearchWord(searchWord); // 검색한 내용 검색창에 남아 있게 하기 위하여...
			// 검색 내용 넣기 로직...끝//

			// 검색 종류가 같은테이블인 경우...
			if (searchSelect.equals("conven_name") || searchSelect.equals("conven_adress")) {
				// 아이템 컨디션 검색에 따른 페이지 크기...//
				String paging = convenService.countSearchList(requestSearchData);
				if (Integer.parseInt(paging) <= 1) {
					paging = "1";
				} else {
					paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
				}
				searchData.setPaging(paging);

				ArrayList<ConvenVO> searchConvenList = convenService.getSearchConvenList(requestSearchData);
				convenDataList = convenService.getConvenDataList(searchConvenList);
				// 검색 종류가 같은테이블인 경우 끝...//

			} else { // 검색 종류가 다른 테이블인 경우...

				ArrayList<ConvenVO> convenList = convenService.getConvenList();
				convenDataList = convenService.getSearchConvenDataList(convenList, searchSelect, searchWord);

				String paging = String.valueOf(convenDataList.size());
				if (Integer.parseInt(paging) <= 1) {
					paging = "1";
				} else {
					paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
				}
				searchData.setPaging(paging);
				// 검색 종류가 다른 테이블인 경우 끝...//
			}

		} else {
			// 페이지 크기...//
			String paging = convenService.countList();
			if (Integer.parseInt(paging) <= 1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
			}
			searchData.setPaging(paging);
			// 페이지 크기 끝...//

			ArrayList<ConvenVO> convenList = convenService.getConvenList();
			convenDataList = convenService.getConvenDataList(convenList);
		}

		model.addAttribute("searchData", searchData);
		model.addAttribute("convenDataList", convenDataList);
		return "/admin/convenienceList";
	}

	// 관리자 편의점 승인 페이지....
	@RequestMapping("/admin/grantConvenience")
	public String grantConvenience(Model model, @RequestParam("conven_idx") String conven_idx) {
		ConvenData convenData = convenService.getConvenData(conven_idx);

		model.addAttribute("convenData", convenData);

		return "/admin/grantConvenience";
	}

	// 관리자 편의점 승인완료 기능....
	@RequestMapping("/admin/updateGrantConvenience")
	public String updateGrantConvenience(@RequestParam("conven_idx") String conven_idx, HttpServletRequest request)
			throws MessagingException, UnsupportedEncodingException {
		// 편의점 Condition 수정
		ConvenVO convenVO = convenService.getConvenVO(conven_idx);
		convenVO.setConven_condition("NORMAL");
		convenService.updateConven(convenVO);

		/* 메일 전송 */
		// 메일전송시 필요한 정보 설정
		MemberVO memberVO = memberService.getMemberVO(convenService.getConvenVO(conven_idx).getMember_idx());
		String email = memberVO.getMember_email();
		String url = request.getRequestURL().toString().replaceAll(request.getServletPath(), "") + "/mainPage";
		String nick = memberVO.getMember_nick();
		String convenName = convenVO.getConven_name();

		// 메일 전송 쓰레드 설정 및 생성
		MailHandler sendMail = new MailHandler(mailSender);
		ThreadSendMail threadSendMail = new ThreadSendMail(email, sendMail);
		threadSendMail.setMessageForReqConven("update", nick, convenName, url);
		new Thread(threadSendMail).start();

		return "redirect:/admin/convenienceList";
	}

	// 관리자 편의점 승인거절 기능...
	@RequestMapping("/admin/rejectGrantConvenience")
	public String rejectGrantConvenience(@RequestParam("conven_idx") String conven_idx, HttpServletRequest request)
			throws MessagingException, UnsupportedEncodingException {
		// 편의점 Condition 수정
		ConvenVO convenVO = convenService.getConvenVO(conven_idx);
		convenVO.setConven_condition("DENIED");
		convenService.updateConven(convenVO);

		/* 메일 전송 */
		// 메일전송시 필요한 정보 설정
		MemberVO memberVO = memberService.getMemberVO(convenService.getConvenVO(conven_idx).getMember_idx());
		String email = memberVO.getMember_email();
		String url = request.getRequestURL().toString().replaceAll(request.getServletPath(), "") + "/mainPage";
		String nick = memberVO.getMember_nick();
		String convenName = convenVO.getConven_name();

		// 메일 전송 쓰레드 설정 및 생성
		MailHandler sendMail = new MailHandler(mailSender);
		ThreadSendMail threadSendMail = new ThreadSendMail(email, sendMail);
		threadSendMail.setMessageForReqConven("reject", nick, convenName, url);
		new Thread(threadSendMail).start();

		return "redirect:/admin/convenienceList";
	}

	// 관리자 편의점 승인취소 기능...
	@RequestMapping("/admin/cancelGrantConvenience")
	public String cancelGrantConvenience(@RequestParam("conven_idx") String conven_idx, HttpServletRequest request)
			throws MessagingException, UnsupportedEncodingException {
		// 편의점 Condition 수정
		ConvenVO convenVO = convenService.getConvenVO(conven_idx);
		convenVO.setConven_condition("DENIED");
		convenService.updateConven(convenVO);

		// 수정할 편의점이 등록한 떨이 물품 삭제
		ArrayList<ProdVO> prodList = prodService.getMyProdList(conven_idx);

		for (ProdVO prodVO : prodList) {// 물품 condition 삭제처리...안보이게

			prodVO.setProd_condition("DELETE");

			prodService.updateProdCondition(prodVO);
		}

		/* 메일 전송 */
		// 메일전송시 필요한 정보 설정
		MemberVO memberVO = memberService.getMemberVO(convenService.getConvenVO(conven_idx).getMember_idx());
		String email = memberVO.getMember_email();
		String url = request.getRequestURL().toString().replaceAll(request.getServletPath(), "") + "/mainPage";
		String nick = memberVO.getMember_nick();
		String convenName = convenVO.getConven_name();

		// 메일 전송 쓰레드 설정 및 생성
		MailHandler sendMail = new MailHandler(mailSender);
		ThreadSendMail threadSendMail = new ThreadSendMail(email, sendMail);
		threadSendMail.setMessageForReqConven("cancel", nick, convenName, url);
		new Thread(threadSendMail).start();

		return "redirect:/admin/convenienceList";
	}

	// 편의점 보기 페이지...
	@RequestMapping("/readConvenience")
	public String readConvenience(HttpSession session, Model model, @RequestParam("conven_idx") String conven_idx,
			@RequestParam(value = "requestPagingNum", required = false) Integer requestPagingNum) {

		// 세션 정보 가져오기
		SessionLoginedData user = (SessionLoginedData) session.getAttribute("sessionLoginedData");

		// 편의점 정보 불러오기...
		ConvenData convenData = convenService.getConvenData(conven_idx);
		model.addAttribute("convenData", convenData);

		// 해당 편의점 떨이 물품 정보 가져오기
		ArrayList<ProdVO> prodList = prodService.getMyProdList(conven_idx);
		ArrayList<ProdData> prodDataList = prodService.getNormalProdDataList(prodList);

		for (ProdData prodData : prodDataList) {// 좋아요 여부, 횟수 prodData에 넣어주기
			int itemLikeCount = itemLikeService.countItemLike(prodData.getItemVO().getItem_idx()); // 물품 좋아요 한 수 뽑아오기...

			// 로그인한 멤버idx가 좋아요 한건지 체크...
			ArrayList<ItemLikeVO> likeMemberList = itemLikeService.likeMemberList(prodData.getItemVO().getItem_idx());
			boolean itemLikeCheck = false;

			if (user != null) {
				for (ItemLikeVO itemLikeVO : likeMemberList) {
					if (user.getMember_idx().equals(itemLikeVO.getMember_idx())) {
						itemLikeCheck = true;
					}
				}
			}

			prodData.setItemLikeCheck(itemLikeCheck);
			prodData.setItemLikeCount(itemLikeCount);
		}

		model.addAttribute("prodDataList", prodDataList);

		// 편의점 댓글 페이징 처리
		if (requestPagingNum == null || requestPagingNum < 0) {
			requestPagingNum = 0;
		}
		PagingData requestPagingData = new PagingData(convenReService.getConvenReListTotalCount(conven_idx), 5,
				requestPagingNum, 5);
		model.addAttribute("pagingData", requestPagingData);

		// 편의점 댓글리스트 가져오기
		ArrayList<ConvenReData> convenReDataList = convenReService.getConvenReListByConvenIdx(conven_idx,
				requestPagingData);
		model.addAttribute("convenReDataList", convenReDataList);

		return "/readConvenience";
	}

	// 편의점 댓글 작성
	@RequestMapping("actionWriteConvenReContent")
	public String actionWriteConvenReContent(ConvenReVO requestConvenReVO, Integer requestPagingNum) {
		convenReService.writeConvenReContent(requestConvenReVO);
		String conven_idx = requestConvenReVO.getConven_idx();

		return "redirect:/readConvenience?requestPagingNum=" + requestPagingNum + "&conven_idx=" + conven_idx;
	}

	// 편의점 댓글 수정
	@RequestMapping("actionUpdateConvenReContent")
	public String actionUpdateConvenReContent(ConvenReVO requestConvenReVO, Integer requestPagingNum) {
		convenReService.updateConvenReContent(requestConvenReVO);
		String conven_idx = requestConvenReVO.getConven_idx();

		return "redirect:/readConvenience?requestPagingNum=" + requestPagingNum + "&conven_idx=" + conven_idx;
	}

	// 편의점 댓글 삭제
	@RequestMapping("actionDeleteConvenReContent")
	public String actionDeleteConvenReContent(ConvenReVO requestConvenReVO) {
		convenReService.deleteConvenRe(requestConvenReVO.getConven_re_idx());
		String conven_idx = requestConvenReVO.getConven_idx();

		return "redirect:/readConvenience?conven_idx=" + conven_idx;
	}

}
