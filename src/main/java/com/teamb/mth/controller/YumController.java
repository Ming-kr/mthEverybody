package com.teamb.mth.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamb.mth.boardservice.YumLikeService;
import com.teamb.mth.boardservice.YumReService;
import com.teamb.mth.boardservice.YumService;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.data.SessionLoginedData;
import com.teamb.mth.data.YumData;
import com.teamb.mth.data.YumReData;
import com.teamb.mth.majorservice.MemberService;
import com.teamb.mth.vo.MemberVO;
import com.teamb.mth.vo.ReportVO;
import com.teamb.mth.vo.YumReVO;
import com.teamb.mth.vo.YumVO;

@Controller
public class YumController {

	@Autowired
	MemberService memberService;

	@Autowired
	YumService yumService;

	@Autowired
	YumLikeService yumLikeService;

	@Autowired
	YumReService yumReService;

	@RequestMapping("yummyboard/yummyBoard")
	public String yummyBoard(Model model, SearchData requestSearchData) {

		SearchData searchData = new SearchData(); // 모델에 넣을 searchData 세팅

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
		// 페이지 이동 처리 로직 끝...//

		ArrayList<YumVO> yumVOList = yumService.getYumList(requestSearchData); // 메인보드 VOList 가져옴
		ArrayList<YumData> yumDataList = new ArrayList<YumData>(); // 메인보드에 넣을 DataList

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

			ArrayList<YumVO> yumSearchVOList = yumService.getSearchYumList(requestSearchData); // 메인보드에 출력할 검색한 값 VOList
			ArrayList<YumData> yumSearchDataList = new ArrayList<YumData>(); // 메인보드에 출력할 검색한 값 DataList

			// 페이지 크기...//
			String paging = yumService.countSearchYumList(requestSearchData); // 검색한 값만큼 페이지 개수 SQL문에서 Count
			if (Integer.parseInt(paging) <= 1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
			}
			searchData.setPaging(paging);
			// 페이지 크기 끝...//

			for (YumVO yumVO : yumSearchVOList) { // 검색한 값 메인보드 DataList를 만들기 위해 for문
				int yum_likeCount = yumLikeService.countYumLike(yumVO.getYum_idx());
				MemberVO memberVO = memberService.getMemberVO(yumVO.getMember_idx());
				yumSearchDataList.add(new YumData(yumVO, memberVO, yum_likeCount));
			}
			model.addAttribute("yumDataList", yumSearchDataList);

		} else {
			// 메인 보드 출력 로직...//
			for (YumVO yumVO : yumVOList) { // 메인보드 DataList를 만들기 위해 for문
				int yum_likeCount = yumLikeService.countYumLike(yumVO.getYum_idx()); // yum_idx로 추천수 조회
				MemberVO memberVO = memberService.getMemberVO(yumVO.getMember_idx()); // member_idx로 작성자 조회
				yumDataList.add(new YumData(yumVO, memberVO, yum_likeCount));
			}

			// 페이지 크기...//
			String paging = yumService.countYumList(); // 총 게시글 개수
			if (Integer.parseInt(paging) <= 1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
			}
			searchData.setPaging(paging);
			model.addAttribute("yumDataList", yumDataList);
			// 페이지 크기 끝...//
		}
		model.addAttribute("searchData", searchData);
		// 메인 보드 출력 로직 끝...//

		// 공지 게시글 출력 로직...//
		ArrayList<YumVO> yumNoticeList = yumService.getNoticeYumList();
		model.addAttribute("yumNoticeList", yumNoticeList);
		// 공지 게시글 출력 로직 끝...

		// 베스트 게시글 출력 로직...//
		ArrayList<YumVO> yumBestList = yumService.getBestYumList();
		model.addAttribute("yumBestList", yumBestList);
		// 베스트 게시글 출력 로직 끝...//

		return "/yummyboard/yummyBoard";
	}

	@RequestMapping("yummyboard/readYummyContent")
	public String readYummyContent(Model model, YumVO requestYumVO) {

		// 신고 게시글 로직...//
		ReportVO reportVO = new ReportVO();
		reportVO.setReport_where("mth_yum");
		reportVO.setReport_where_idx(requestYumVO.getYum_idx());

		model.addAttribute("reportVO", reportVO);
		// 신고 게시글 로직 끝...//

		// 게시글 출력 로직..//
		YumVO yumVO = yumService.getYumVO(requestYumVO);
		MemberVO memberVO = memberService.getMemberVO(yumVO.getMember_idx());
		int yum_likeCount = yumLikeService.countYumLike(yumVO.getYum_idx());

		YumData yumData = new YumData(yumVO, memberVO, yum_likeCount);

		model.addAttribute("yumData", yumData);
		// 게시글 출력 로직 끝..//

		// 이전다음글 로직
		ArrayList<YumData> yumBoardData = yumService.getYumBoardData(requestYumVO);// 얌보드 VOList 가져옴

		model.addAttribute("yumBoardData", yumBoardData);
		// 이전다음글 로직 끗

		// 댓글 출력 로직...
		YumReVO requestYumReVO = new YumReVO();
		requestYumReVO.setYum_idx(requestYumVO.getYum_idx());
		ArrayList<YumReVO> yumReList = yumReService.getYumReListByYumIdx(requestYumReVO);
		ArrayList<YumReData> yumReDataList = new ArrayList<YumReData>();

		for (YumReVO yumReVO : yumReList) {
			MemberVO memberReVO = memberService.getMemberVO(yumReVO.getMember_idx());
			YumReData yumReData = new YumReData(yumReVO, memberReVO);
			yumReDataList.add(yumReData);
		}

		model.addAttribute("yumReDataList", yumReDataList);
		// 댓글 출력 로직 끝...

		return "/yummyboard/readYummyContent";
	}

	@RequestMapping("yummyboard/writeYummyContentForm")
	public String writeYummyContentForm() {
		return "/yummyboard/writeYummyContentForm";
	}

	@RequestMapping("yummyboard/actionWriteYummyContentForm")
	public String actionWriteYummyContentForm(HttpSession session, YumVO requestYumVO, Model model) {

		SessionLoginedData sessionLoginedData = (SessionLoginedData) session.getAttribute("sessionLoginedData");

		requestYumVO.setMember_idx(sessionLoginedData.getMember_idx());
		
		if (requestYumVO.getYum_content().length() <= 0 || requestYumVO.getYum_title().length() <= 0) {
			model.addAttribute("message", "글 내용을 작성하셔야 합니다.");
			model.addAttribute("page", "yummyBoard");
			return "/message";
		}

		if (requestYumVO.getYum_content().contains("<script>")) {
			model.addAttribute("message", "script는 사용할 수 없습니다.");
			model.addAttribute("page", "yummyBoard");
			return "/message";
		}

		yumService.writeYumContent(requestYumVO);

		model.addAttribute("message", "성공적으로 작성이 완료 되었습니다.");
		model.addAttribute("page", "yummyBoard");

		return "/message";
	}

	@RequestMapping("yummyboard/updateYummyContentForm")
	public String updateYummyContentForm(Model model, YumVO requestYumVO) {

		YumVO yumVO = yumService.getYumVO(requestYumVO);

		model.addAttribute("yumVO", yumVO);

		return "/yummyboard/updateYummyContentForm";
	}

	@RequestMapping("yummyboard/deleteYummyContent")
	public String deleteYummyContent(HttpSession session, YumVO requestYumVO, Model model) {
		SessionLoginedData sessionLoginedData = (SessionLoginedData) session.getAttribute("sessionLoginedData");
		YumVO yumVO = yumService.getYumVO(requestYumVO);
		
		if(!sessionLoginedData.getMember_idx().equals(yumVO.getMember_idx())) {
			System.out.println("if문 도착");
			model.addAttribute("message", "니 글이 아닙니다.");
			model.addAttribute("page", "freeBoard");
			return "/message";
		}
		
		yumService.deleteYumContent(requestYumVO);
		model.addAttribute("message", "성공적으로 삭제가 되었습니다.");
		model.addAttribute("page", "yummyBoard");

		return "/message";
	}

	// 댓글 삭제
	@RequestMapping("/yummyboard/deleteYumReContent")
	public String deleteYumReContent(HttpSession session, YumReVO requestYumReVO, Model model) {
		SessionLoginedData sessionLoginedData = (SessionLoginedData) session.getAttribute("sessionLoginedData");
		
		YumReVO yumReVO = yumReService.getYumReVO(requestYumReVO);
		
		if(!sessionLoginedData.getMember_idx().equals(yumReVO.getMember_idx())) {
			System.out.println("if문 도착");
			model.addAttribute("message", "니 글이 아닙니다.");
			model.addAttribute("page", "freeBoard");
			return "/message";
		}
		
		yumReService.deleteYumReContent(requestYumReVO);
		yumReService.getYumReVO(requestYumReVO);

		model.addAttribute("message", "댓글 삭제 완료");
		model.addAttribute("page", "readYummyContent?yum_idx=" + requestYumReVO.getYum_idx());

		return "/message";
	}

	@RequestMapping("yummyboard/actionUpdateYummyContentForm")
	public String actionUpdateYummyContentForm(HttpSession session, YumVO requestYumVO, Model model) {
		SessionLoginedData sessionLoginedData = (SessionLoginedData) session.getAttribute("sessionLoginedData");
		YumVO yumVO = yumService.getYumVO(requestYumVO);
		
		if(!sessionLoginedData.getMember_idx().equals(yumVO.getMember_idx())) {
			System.out.println("if문 도착");
			model.addAttribute("message", "니 글이 아닙니다.");
			model.addAttribute("page", "freeBoard");
			return "/message";
		}

		if (requestYumVO.getYum_content().length() <= 0 || requestYumVO.getYum_title().length() <= 0) {
			model.addAttribute("message", "글 내용을 작성하셔야 합니다.");
			model.addAttribute("page", "yummyBoard");
			return "/message";
		}

		if (requestYumVO.getYum_content().contains("<script>")) {
			model.addAttribute("message", "script는 사용할 수 없습니다.");
			model.addAttribute("page", "yummyBoard");
			return "/message";
		}

		yumService.updateYumContent(requestYumVO);
		model.addAttribute("message", "성공적으로 수정이 되었습니다.");
		model.addAttribute("page", "yummyBoard");

		return "/message";
	}

	

}
