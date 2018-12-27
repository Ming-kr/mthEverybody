package com.teamb.mth.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

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

import com.teamb.mth.data.AllBoardViewData;
import com.teamb.mth.data.AllReplyViewData;
import com.teamb.mth.data.ItemLikeViewData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.data.SessionLoginedData;
import com.teamb.mth.majorservice.MemberMailService;
import com.teamb.mth.majorservice.MemberService;
import com.teamb.mth.majorservice.ViewService;
import com.teamb.mth.util.CheckRequestMemberInfo;
import com.teamb.mth.util.MailHandler;
import com.teamb.mth.util.PagingData;
import com.teamb.mth.util.TempKeyMaker;
import com.teamb.mth.util.ThreadSendMail;
import com.teamb.mth.vo.MemberMailVO;
import com.teamb.mth.vo.MemberVO;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;

	@Autowired
	ViewService viewService;

	@Inject
	JavaMailSender mailSender;

	@Autowired
	MemberMailService memberMailService;

	////////////////////// 관리자코드 시작////////////////////////////

	@RequestMapping("/admin/memberList")
	public String memberList(Model model, SearchData requestSearchData,
			@RequestParam(value = "member_code", required = false) String member_code) {

		// 필터링을 위한 멤버 코드 값 넘겨주기...
		if (member_code == null) {
			member_code = "all";
		}
		model.addAttribute("member_code", member_code);
		// 필터링을 위한 멤버 코드 값 넘겨주기 끝...

		SearchData searchData = new SearchData(); // 모델에 넘겨줄 searchData 세팅
		ArrayList<MemberVO> memberList = new ArrayList<MemberVO>(); // 모델에 넣을 memberList 세팅

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

			// 멤버 코드 검색에 따른 페이지 크기...//
			String paging = memberService.countSearchList(requestSearchData); // 검색한 값만큼 페이지 개수 SQL문에서 Count
			if (Integer.parseInt(paging) <= 1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
			}
			searchData.setPaging(paging);
			// 페이지 크기 끝...//

			memberList = memberService.getSearchMemberList(requestSearchData);
		} else {
			// 페이지 크기...//
			String paging = memberService.countList(); // 총 게시글 개수
			if (Integer.parseInt(paging) <= 1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
			}
			searchData.setPaging(paging);
			// 페이지 크기 끝...//

			memberList = memberService.getMemberList();
		}

		model.addAttribute("searchData", searchData);

		model.addAttribute("memberList", memberList);
		return "/admin/memberList";
	}

	////////////////////// 관리자코드 끝////////////////////////////

	@RequestMapping("joinMemberForm")
	public String joinMemberForm() {
		return "/joinMemberForm";
	}

	@RequestMapping("requestSellerForm")
	public String requestSellerForm() {
		return "/customer/requestSellerForm";
	}

	// 로그인 폼
	@RequestMapping("loginForm")
	public String loginForm(HttpSession session, Model model) {
		if (session.getAttribute("sessionLoginedData") != null) {
			model.addAttribute("message", "로그인 중인 회원입니다.\n로그아웃 후 로그인하시길 바랍니다.");
			model.addAttribute("page", "mainPage");

			return "/message";
		}
		return "/loginForm";
	}

	// 로그인
	@RequestMapping("actionLoginForm")
	public String actionLoginForm(HttpSession session, HttpServletRequest request, Model model,
			MemberVO requestMemberVO, @RequestParam(value = "beforeUrl", required = false) String beforeUrl) {
		/* 로그인 main 로직 */
		// 만약 로그인한 세션이 존재하면 삭제해주기
		if (session.getAttribute("sessionLoginedData") != null) {
			session.removeAttribute("sessionLoginedData");
		}

		// 아이디, 비밀번호 확인
		SessionLoginedData loginedMemeberData = memberService.login(requestMemberVO);

		if (loginedMemeberData == null) {
			// 로그인 실패시
			model.addAttribute("loginFailed", true);
			return "/loginForm";
		} else if (loginedMemeberData.getMember_code().equals("JOINING")) {
			// 이메일 인증 안끝났을 때
			model.addAttribute("requestMemberIdx", loginedMemeberData.getMember_idx());
			return "redirect:/emailConfirming";
		} else {
			// 로그인 성공
			session.setAttribute("sessionLoginedData", loginedMemeberData);
			session.setMaxInactiveInterval(60 * 60); // 세션 유지 1시간
		}

		/* 로그인 후 이동할 페이지 처리 */
		// NULL처리
		if (beforeUrl == null || beforeUrl == "") {
			beforeUrl = "/mainPage";
		}

		// 이동할 이전 페이지 가공(/프로젝트이름/매핑 -> 프로젝트이름 제거)
		String urlForCut = request.getRequestURL().toString().replaceAll(request.getServletPath(), "");
		String requestUrl = beforeUrl.replaceAll(urlForCut, "");

		/* 예외처리 */
		// requestUrl이 예외페이지에 포함되어 있으면 requestUrl="/mainPage"
		String[] reservedUrlList = { "/message", "/writeReportForm", "/requestSellerForm", "/joinMemberForm",
				"/yummyboard/writeYummyContentForm", "/yummyboard/updateYummyContentForm",
				"/freeboard/updateFreeContentForm", "/freeboard/writeFreeContentForm", "/admin/registerItemForm",
				"/admin/readReport", "/admin/updateItemForm" };

		if (Arrays.asList(reservedUrlList).contains(requestUrl)) {
			requestUrl = "/mainPage";
		}

		// requestUrl이 action* 매핑이면 mainPage로..
		if (requestUrl.startsWith("/action")) {
			requestUrl = "/mainPage";
		}

		return "redirect:" + requestUrl;
	}

	// 로그아웃
	@RequestMapping("actionLogout")
	public String actionLogout(HttpSession session, HttpServletRequest request) {
		session.invalidate();

		/* 로그아웃 후 이동할 페이지 처리 */
		// 이동할 이전 페이지 가공(/프로젝트이름/매핑 -> 프로젝트이름 제거)
		String urlForCut = request.getRequestURL().toString().replaceAll(request.getServletPath(), "");
		String requestUrl = request.getHeader("referer").replaceAll(urlForCut, "");

		/* 예외처리 */
		// NULL처리
		if (requestUrl == null || requestUrl == "") {
			requestUrl = "/mainPage";
		}

		// (이동하면 안될 페이지)
		String[] reservedUrlList = { "/message", "/writeReportForm", "/requestSellerForm", "/joinMemberForm",
				"/yummyboard/writeYummyContentForm", "/yummyboard/updateYummyContentForm",
				"/freeboard/updateFreeContentForm", "/freeboard/writeFreeContentForm", "/admin/registerItemForm",
				"/admin/readReport", "/admin/updateItemForm" };

		if (Arrays.asList(reservedUrlList).contains(requestUrl)) {
			requestUrl = "/mainPage";
		}

		// requestUrl이 action* 매핑이면 mainPage로..
		if (requestUrl.contains("/action")) {
			requestUrl = "/mainPage";
		}

		return "redirect:" + requestUrl;
	}

	// 회원가입(메일인증)
	@RequestMapping("actionJoinMemberForm")
	public String actionJoinMemberForm(MemberVO requestMemberVO, HttpServletRequest request, Model model)
			throws MessagingException, UnsupportedEncodingException {
		// 가입된 메일인지 확인
		boolean checkValidEmail = memberService.checkValidJoinEmail(requestMemberVO);
		MemberVO checkUnValidMemberVO = memberService.getMemberVOById(requestMemberVO);

		// 이미 가입된 회원일때 저장X
		if (checkValidEmail == false) {
			model.addAttribute("message", "이미 가입된 메일입니다.");
			model.addAttribute("page", "joinMemberForm");

			return "/message";
		} else if (checkUnValidMemberVO != null) {
			model.addAttribute("message", "이미 가입된 아이디입니다.");
			model.addAttribute("page", "joinMemberForm");

			return "/message";
		} else if (new CheckRequestMemberInfo(requestMemberVO).checkJoinMemberVO() == false) {
			// 유효성 검사
			model.addAttribute("message", "입력한 값이 유효하지 않습니다.");
			model.addAttribute("page", "joinMemberForm");

			return "/message";
		} else {
			// 회원 정보 저장
			memberService.joinMember(requestMemberVO);

			// 인증키 생성 및 저장
			String member_idx = memberService.getMemberVOById(requestMemberVO).getMember_idx();
			requestMemberVO.setMember_idx(member_idx);
			String key = new TempKeyMaker().getKey(15, false);
			memberMailService.setKeyForJoin(requestMemberVO, key);

			/* 메일 전송 */
			// 메일전송시 필요한 정보 설정
			String email = requestMemberVO.getMember_email();
			String url = request.getRequestURL().toString().replaceAll(request.getServletPath(), "")
					+ "/actionEmailConfirm?member_idx=" + member_idx + "&key=" + key;
			MailHandler sendMail = new MailHandler(mailSender);

			// 메일 전송 쓰레드 설정 및 생성
			ThreadSendMail threadSendMail = new ThreadSendMail(email, sendMail);
			threadSendMail.setMessageForJoiningKey(url);
			new Thread(threadSendMail).start();

			// 인증키 재전송을 위해서 member_idx 셋팅
			model.addAttribute("requestMemberIdx", member_idx);
		}
		return "redirect:/emailConfirming";
	}

	// 메일 인증 페이지
	@RequestMapping("emailConfirming")
	public String emailConfirming(@RequestParam(value = "requestMemberIdx", required = false) String member_idx,
			Model model, HttpServletRequest request) {
		// member_idx = null 인 경우 예외처리
		if (member_idx == null) {
			model.addAttribute("message", "잘못된 접근입니다.");
			model.addAttribute("page", "loginForm");

			return "/message";
		}
		
		//loginForm, joinMemberForm 통해서 오지 않은 경우 예외처리
		if(request.getHeader("referer") == null) {return "redirect:/mainPage";}
		
		String urlForCut = request.getRequestURL().toString().replaceAll(request.getServletPath(), "");
		String requestUrl = request.getHeader("referer").replaceAll(urlForCut, "");
		
		String[] accessUrl = {"/loginForm", "/joinMemberForm"};
		
		if(!Arrays.asList(accessUrl).contains(requestUrl)) 
		{
			model.addAttribute("message", "잘못된 접근입니다.");
			model.addAttribute("page", "loginForm");

			return "/message";
		}

		MemberVO requestMemberVO = memberService.getMemberVO(member_idx);
		model.addAttribute("requestMemberEmail", requestMemberVO.getMember_email());
		model.addAttribute("requestMemberIdx", member_idx);
		return "/customer/emailConfirming";
	}

	// 메일 인증 다시 보내기
	@RequestMapping("actionEmailReConfirm")
	public String actionEmailReConfirm(@RequestParam(value = "requestMemberIdx", required = false) String member_idx,
			HttpServletRequest request, Model model) throws MessagingException, UnsupportedEncodingException {
		// member_idx = null 인 경우 예외처리
		if (member_idx == null) {
			model.addAttribute("message", "잘못된 접근입니다.");
			model.addAttribute("page", "loginForm");

			return "/message";
		}

		// 이미 인증 받은 회원인지 확인
		MemberMailVO requestMemberMailVO = memberMailService.getMemberMailVO(member_idx);
		String certifyCheck = memberMailService.getMemberMailVO(requestMemberMailVO.getMember_idx()).getCertify_check();

		if (certifyCheck.equals("Y")) {
			model.addAttribute("message", "이미 인증 받은 메일입니다.");
			model.addAttribute("page", "loginForm");

			return "/message";
		}

		// 인증키 재생성 및 수정
		String key = new TempKeyMaker().getKey(15, false);
		memberMailService.updateKey(member_idx, key);

		/* 메일 전송 */
		// 메일전송시 필요한 정보 설정
		String email = memberService.getMemberVO(member_idx).getMember_email();
		String url = request.getRequestURL().toString().replaceAll(request.getServletPath(), "")
				+ "/actionEmailConfirm?member_idx=" + member_idx + "&key=" + key;

		// 메일 전송 쓰레드 설정 및 생성
		MailHandler sendMail = new MailHandler(mailSender);
		ThreadSendMail threadSendMail = new ThreadSendMail(email, sendMail);
		threadSendMail.setMessageForJoiningKey(url);
		new Thread(threadSendMail).start();

		model.addAttribute("message", "인증키를 [" + email + "]으로 재전송하였습니다.");
		model.addAttribute("page", "loginForm");

		return "/message";
	}

	// 메일 인증키 확인
	@RequestMapping("actionEmailConfirm")
	public String actionEmailConfirm(MemberMailVO requestMemberMailVO, Model model) {
		// member_idx = null 인 경우 예외처리
		if (requestMemberMailVO.getMember_idx() == null) {
			model.addAttribute("message", "잘못된 접근입니다.");
			model.addAttribute("page", "loginForm");

			return "/message";
		}

		// 이미 인증 받은 회원인지 확인
		String certifyCheck = memberMailService.getMemberMailVO(requestMemberMailVO.getMember_idx()).getCertify_check();

		if (certifyCheck.equals("Y")) {
			model.addAttribute("message", "이미 인증 받은 메일입니다.");
			model.addAttribute("page", "loginForm");

			return "/message";
		}

		boolean checkKeyResult = memberMailService.certifyKey(requestMemberMailVO);

		// 인증 성공/실패 결과
		if (checkKeyResult == true) {
			memberMailService.successCertify(requestMemberMailVO.getMember_idx());
		} else {
			model.addAttribute("message", "잘못된 인증키 입니다.\n인증키를 재전송해주시기 바랍니다.");
			model.addAttribute("page", "loginForm");

			return "/message";
		}

		return "redirect:/mainPage";
	}

	// 판매자회원 신청
	@RequestMapping("actionRequestSellerForm")
	public String actionRequestSellerForm(HttpSession session, MemberVO requestMemberVO, Model model) {
		// requestMemberVO 셋팅
		String member_idx = ((SessionLoginedData) session.getAttribute("sessionLoginedData")).getMember_idx();
		requestMemberVO.setMember_idx(member_idx);

		// 유효성 검사
		if (new CheckRequestMemberInfo(requestMemberVO).checkReqSellerInfo() == false) {
			model.addAttribute("message", "잘못된 입력값입니다.");
			model.addAttribute("page", "mainPage");

			return "/message";
		} else {
			memberService.requestSeller(requestMemberVO);
			((SessionLoginedData) session.getAttribute("sessionLoginedData")).setMember_code("REQ_SELLER");
		}

		return "redirect:/mainPage";
	}

	// 내 정보 페이지 접근
	@RequestMapping("memberMyInfo")
	public String memberMyInfo(Model model, HttpSession session, MemberVO requestMemberVO,
			@RequestParam(value = "requestPagingNums", required = false) Integer[] requestPagingNums) {
		// 페이징 값 없을 때 and 임의로 -n값으로 들어왔을 때 처리
		if (requestPagingNums == null) {
			requestPagingNums = new Integer[] { 0, 0, 0 };
		} else {
			for (int index = 0; index < requestPagingNums.length; index++) {
				if (requestPagingNums[index] == null || requestPagingNums[index] < 0) {
					requestPagingNums[index] = 0;
				}
			}
		}

		// requestMemberVo 세팅
		String member_idx = ((SessionLoginedData) session.getAttribute("sessionLoginedData")).getMember_idx();
		requestMemberVO.setMember_idx(member_idx);

		/* 자기정보관련 불러오기(member_code 한글화) */
		MemberVO memberInfo = memberService.getMemberVO(requestMemberVO);
		if (memberInfo.getMember_code().equals("CUSTOMER")) {
			memberInfo.setMember_code("일반회원");
		} else if (memberInfo.getMember_code().equals("REQ_SELLER")) {
			memberInfo.setMember_code("판매자회원 승인대기중");
		} else if (memberInfo.getMember_code().equals("SELLER")) {
			memberInfo.setMember_code("판매자회원");
		} else if (memberInfo.getMember_code().equals("ADMIN")) {
			memberInfo.setMember_code("관리자");
		} else {
			memberInfo.setMember_code("회원코드 오류: 관리자에게 문의 바랍니다.");
		}

		model.addAttribute("memberInfo", memberInfo);

		/* 좋아하는 물품리스트 뷰 가져오기 */
		// 좋아하는 물품리스트 뷰 페이징 처리
		PagingData requestItemLikeViewPagingData = new PagingData(
				viewService.getItemLikeViewTotalDataCount(requestMemberVO), 5, requestPagingNums[0], 5);

		ItemLikeViewData itemLikeViewDataList = viewService.getItemLikeViewDataList(requestMemberVO,
				requestItemLikeViewPagingData);
		model.addAttribute("itemLikeViewDataList", itemLikeViewDataList);

		/* 내가 쓴 댓글 리스트 뷰 가져오기 */
		// 내가 쓴 댓글 리스트 뷰 페이징 처리
		PagingData requestAllReplyViewPagingData = new PagingData(
				viewService.getAllReplyViewTotalDataCount(requestMemberVO), 5, requestPagingNums[1], 5);

		AllReplyViewData allReplyViewDataList = viewService.getAllReplyViewDataList(requestMemberVO,
				requestAllReplyViewPagingData);
		model.addAttribute("allReplyViewDataList", allReplyViewDataList);

		/* 내가 쓴 게시글 리스트 뷰 가져오기 */
		// 내가 쓴 게시글 리스트 뷰 페이징 처리
		PagingData requestAllBoardViewPagingData = new PagingData(
				viewService.getAllBoardViewTotalDataCount(requestMemberVO), 5, requestPagingNums[2], 10);

		AllBoardViewData allBoardViewDataList = viewService.getAllBoardViewDataList(requestMemberVO,
				requestAllBoardViewPagingData);
		model.addAttribute("allBoardViewDataList", allBoardViewDataList);

		return "/customer/memberMyInfo";
	}

	// 회원정보 수정
	@RequestMapping("actionUpdateMemberMyInfo")
	public String actionUpdateMemberMyInfo(HttpSession session, MemberVO requestMemberVO, Model model) {
		// requestMemberVo 세팅
		SessionLoginedData sessionData = (SessionLoginedData) session.getAttribute("sessionLoginedData");
		requestMemberVO.setMember_idx(sessionData.getMember_idx());
		requestMemberVO.setMember_code(sessionData.getMember_code());

		// 유효성 검사
		if (new CheckRequestMemberInfo(requestMemberVO).checkInfoForUpdate() == false) {
			model.addAttribute("message", "잘못된 입력값입니다.");
			model.addAttribute("page", "memberMyInfo");

			return "/message";
		} else {
			// sessionLoginedData member_nick 수정
			((SessionLoginedData) session.getAttribute("sessionLoginedData"))
					.setMember_nick(requestMemberVO.getMember_nick());

			String member_code = sessionData.getMember_code();
			if (member_code.equals("SELLER") || member_code.equals("ADMIN")) {
				memberService.updateSellerMyInfo(requestMemberVO);
			} else {
				memberService.updateCustomerInfo(requestMemberVO);
			}
		}
		return "redirect:/memberMyInfo";
	}

	// 비밀번호 수정
	@RequestMapping("actionUpdateMemberPw")
	public String actionUpdateMemberPw(HttpSession session, MemberVO requestMemberVO, Model model) {
		// requestMemberVo 세팅
		String member_idx = ((SessionLoginedData) session.getAttribute("sessionLoginedData")).getMember_idx();
		requestMemberVO.setMember_idx(member_idx);

		// 유효성 검사
		if (new CheckRequestMemberInfo(requestMemberVO).checkOnlyPassword() == false) {
			model.addAttribute("message", "잘못된 입력값입니다.");
			model.addAttribute("page", "memberMyInfo");

			return "/message";
		} else {
			memberService.updateMemberPassword(requestMemberVO);
		}

		return "redirect:/memberMyInfo";
	}

	// 회원 탈퇴
	@RequestMapping("actionUnsubscribeMember")
	public String actionUnsubscribeMember(HttpSession session, MemberVO requestMemberVO) {
		// requestMemberVo 세팅
		String member_idx = ((SessionLoginedData) session.getAttribute("sessionLoginedData")).getMember_idx();
		requestMemberVO.setMember_idx(member_idx);

		memberService.deleteMemberVO(requestMemberVO);

		// 로그인세션 삭제
		session.removeAttribute("sessionLoginedData");

		return "redirect:/mainPage";
	}

	// 임시...
	@RequestMapping("updateCustomer")
	public String updateCustomer(HttpSession session) {
		SessionLoginedData user = ((SessionLoginedData) session.getAttribute("sessionLoginedData"));
		memberService.updateCustomer(user.getMember_idx());

		// 세션 다시만들기
		session.removeAttribute("sessionLoginedData");
		MemberVO memberVO = memberService.getMemberVO(user.getMember_idx());
		SessionLoginedData newUser = new SessionLoginedData(memberVO.getMember_idx(), memberVO.getMember_nick(),
				memberVO.getMember_code());
		session.setAttribute("sessionLoginedData", newUser);
		session.setMaxInactiveInterval(60 * 60); // 세션 유지 1시간
		return "redirect:mainPage";
	}

	@RequestMapping("updateSeller")
	public String updateSeller(HttpSession session) {
		SessionLoginedData user = ((SessionLoginedData) session.getAttribute("sessionLoginedData"));
		memberService.updateSeller(user.getMember_idx());

		// 세션 다시만들기
		session.removeAttribute("sessionLoginedData");
		MemberVO memberVO = memberService.getMemberVO(user.getMember_idx());
		SessionLoginedData newUser = new SessionLoginedData(memberVO.getMember_idx(), memberVO.getMember_nick(),
				memberVO.getMember_code());
		session.setAttribute("sessionLoginedData", newUser);
		session.setMaxInactiveInterval(60 * 60); // 세션 유지 1시간 //세션 유지 1시간
		return "redirect:mainPage";
	}

	@RequestMapping("updateAdmin")
	public String updateAdmin(HttpSession session) {
		SessionLoginedData user = ((SessionLoginedData) session.getAttribute("sessionLoginedData"));
		memberService.updateAdmin(user.getMember_idx());

		// 세션 다시만들기
		session.removeAttribute("sessionLoginedData");
		MemberVO memberVO = memberService.getMemberVO(user.getMember_idx());
		SessionLoginedData newUser = new SessionLoginedData(memberVO.getMember_idx(), memberVO.getMember_nick(),
				memberVO.getMember_code());
		session.setAttribute("sessionLoginedData", newUser);
		session.setMaxInactiveInterval(60 * 60); // 세션 유지 1시간 //세션 유지 1시간
		return "redirect:mainPage";
	}

	// 판매자 요청 회원 승인 페이지....
	@RequestMapping("/admin/grantMember")
	public String grantMember(Model model, @RequestParam("member_idx") String member_idx) {
		MemberVO memberVO = memberService.getMemberVO(member_idx);

		model.addAttribute("memberVO", memberVO);

		return "/admin/grantMember";
	}

	// 판매자 회원으로 승인완료 기능....
	@RequestMapping("/admin/updateGrantSeller")
	public String updateGrantSeller(@RequestParam("member_idx") String member_idx, HttpServletRequest request)
			throws MessagingException, UnsupportedEncodingException {
		System.out.println("updateGrantMember member_idx:" + member_idx);
		memberService.updateSeller(member_idx);

		MemberVO memberVO = memberService.getMemberVO(member_idx);

		/* 메일 전송 */
		// 메일전송시 필요한 정보 설정
		String email = memberVO.getMember_email();
		String url = request.getRequestURL().toString().replaceAll(request.getServletPath(), "") + "/mainPage";
		String nick = memberVO.getMember_nick();

		// 메일 전송 쓰레드 설정 및 생성
		MailHandler sendMail = new MailHandler(mailSender);
		ThreadSendMail threadSendMail = new ThreadSendMail(email, sendMail);
		threadSendMail.setMessageForReqSeller("update", nick, url);
		new Thread(threadSendMail).start();

		return "redirect:/admin/memberList";
	}

	// 판매자 회원으로 승인거절 기능....
	@RequestMapping("/admin/rejectGrantSeller")
	public String rejectGrantSeller(@RequestParam("member_idx") String member_idx, HttpServletRequest request)
			throws MessagingException, UnsupportedEncodingException {
		System.out.print("rejectGrantMember member_idx:" + member_idx);
		memberService.rejectReqSeller(member_idx);

		MemberVO memberVO = memberService.getMemberVO(member_idx);

		/* 메일 전송 */
		// 메일전송시 필요한 정보 설정
		String email = memberVO.getMember_email();
		String url = request.getRequestURL().toString().replaceAll(request.getServletPath(), "") + "/mainPage";
		String nick = memberVO.getMember_nick();

		// 메일 전송 쓰레드 설정 및 생성
		MailHandler sendMail = new MailHandler(mailSender);
		ThreadSendMail threadSendMail = new ThreadSendMail(email, sendMail);
		threadSendMail.setMessageForReqSeller("reject", nick, url);
		new Thread(threadSendMail).start();

		return "redirect:/admin/memberList";
	}

	// 관리자 회원 변경 페이지....
	@RequestMapping("/admin/grantAdminForm")
	public String grantAdminForm(Model model, @RequestParam("member_idx") String member_idx) {
		MemberVO memberVO = memberService.getMemberVO(member_idx);

		model.addAttribute("memberVO", memberVO);

		return "/admin/grantAdminForm";
	}

	// 관리자 회원 변경 처리....
	@RequestMapping("/admin/actionGrantAdminForm")
	public String actionGrantAdminForm(Model model, MemberVO requestMemberVO) {
		memberService.updateGrantAdmin(requestMemberVO);
		return "redirect:/admin/memberList";
	}

}
