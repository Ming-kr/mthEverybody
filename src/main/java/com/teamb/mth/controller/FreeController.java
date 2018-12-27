package com.teamb.mth.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.teamb.mth.boardservice.FreeLikeService;
import com.teamb.mth.boardservice.FreeReService;
import com.teamb.mth.boardservice.FreeService;
import com.teamb.mth.data.FreeData;
import com.teamb.mth.data.FreeReData;
import com.teamb.mth.data.SearchData;
import com.teamb.mth.data.SessionLoginedData;
import com.teamb.mth.majorservice.MemberService;
import com.teamb.mth.vo.FreeImgVO;
import com.teamb.mth.vo.FreeReVO;
import com.teamb.mth.vo.FreeVO;
import com.teamb.mth.vo.MemberVO;
import com.teamb.mth.vo.ReportVO;

@Controller
public class FreeController {

	@Autowired
	MemberService memberService;

	@Autowired
	FreeService freeService;

	@Autowired
	FreeLikeService freeLikeService;

	@Autowired
	FreeReService freeReService;
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// %%%%%%%%%%%%%%%%%% 단순 매핑용 %%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	@RequestMapping("/freeboard/writeFreeContentForm")
	public String writeFreeContentForm(Model model, int pageNum) {
		model.addAttribute("pageNum", pageNum);
		return "/freeboard/writeFreeContentForm";
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// %%%%%%%%%%%%%%%%%% 로직 처리용 %%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	// 글읽기
	@RequestMapping("/freeboard/readFreeContent")
	public String readFreeContent(FreeVO requestFreeVO, Model model, int pageNum) {

		model.addAttribute("pageNum", pageNum);

		// 신고 게시글 로직
		ReportVO reportVO = new ReportVO();
		reportVO.setReport_where("mth_free");
		reportVO.setReport_where_idx(requestFreeVO.getFree_idx());

		model.addAttribute("reportVO", reportVO);
		// 신고 게시글 로직 끝

		// 클릭한 게시물 정보 불러오기

		FreeData freeData = freeService.getFreeData(requestFreeVO);

		// 추천수 로직
		/*int free_likeCount = freeLikeService.countFreeLike(requestFreeVO.getFree_idx());
		freeData.setFree_likeCount(free_likeCount);*/
		// 추천수 로직 끝

		freeService.updateCountPlus(requestFreeVO);

		model.addAttribute("freeData", freeData);
		// 게시물 정보 불러오기 끗

		// 댓글 출력 로직
		FreeReVO requestFreeReVO = new FreeReVO();
		requestFreeReVO.setFree_idx(requestFreeVO.getFree_idx());
		ArrayList<FreeReVO> freeReList = freeReService.getFreeReListByFreeIdx(requestFreeReVO);
		ArrayList<FreeReData> freeReDataList = new ArrayList<FreeReData>();

		for (FreeReVO freeReVO : freeReList) {
			MemberVO memberReVO = memberService.getMemberVO(freeReVO.getMember_idx());
			FreeReData freeReData = new FreeReData(freeReVO, memberReVO);
			freeReDataList.add(freeReData);
		}

		model.addAttribute("freeReDataList", freeReDataList);
		// 댓글 출력 로직 끝...

		// 다음글 로직(작성필요)///////////////////////////
		ArrayList<FreeData> freeBoardData = freeService.getFreeBoardData(requestFreeVO);// 프리보드 VOList 가져옴

		model.addAttribute("freeBoardData", freeBoardData);
		// 이전다음글 로직(작성필요)///////////////////////////

		return "/freeboard/readFreeContent";
	}

	// 글쓰기 버튼 호출됐을 때
	@RequestMapping("/freeboard/actionWriteFreeContentForm")
	public String actionWriteFreeContentForm(MultipartFile[] files, FreeVO requestFreeVO, Model model,
			HttpSession session,int pageNum) {
		System.out.println(pageNum);
		System.out.println("=======[test : start]=======");
		// 글작성, 글수정에
		if (requestFreeVO.getFree_content().length() <= 0 || requestFreeVO.getFree_title().length() <= 0) {
			/*
			 * if(requestFreeVO.getFree_content()==null ||
			 * requestFreeVO.getFree_content()=="") {
			 */
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("message", "글 내용을 작성하셔야 합니다.");
			model.addAttribute("page", "writeFreeContentForm?pageNum=" + pageNum);
			return "/message";
		}

		if (requestFreeVO.getFree_content().contains("<script>")) {
			model.addAttribute("message", "script는 사용할 수 없습니다.");
			model.addAttribute("page", "freeBoard");
			return "/message";
		}
		ArrayList<FreeImgVO> freeImgList = new ArrayList<FreeImgVO>();

		String uploadRootPath = new File("/upload/").getAbsolutePath();

		System.out.println("=======[test : "+uploadRootPath+"]=======");
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		String dateFolder = simpleDateFormat.format(date);

		dateFolder = dateFolder.replace("-", File.separator);

		File uploadPath = new File(uploadRootPath, dateFolder);

		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
			System.out.println("=======[test : uploadPath.exists() == false]=======");
		}

		for (MultipartFile file : files) {

			System.out.println("=======[test : forforforforfor]=======");
			
			if (file.isEmpty())
				continue;

			// 파일명 변경 ... 시간/랜덤.
			UUID uuid = UUID.randomUUID();
			String uploadFileName = uuid.toString();

			String oriFileName = file.getOriginalFilename();

			uploadFileName += oriFileName.substring(oriFileName.indexOf("."));

			File saveFile = new File(uploadPath, uploadFileName);
			
		

			try {
				System.out.println("=======[test : file.transferTo]=======");
				file.transferTo(saveFile);
			} catch (Exception e) {
				System.out.println("=======[test : file.transferTo Excecption]=======");
				e.printStackTrace();
			}

			// DB 넣을 값 세팅...
			FreeImgVO fileData = new FreeImgVO();

			String tempString = "/upload/";
			tempString += dateFolder.replace(File.separator, "/");
			tempString += "/";
			tempString += uploadFileName;

			fileData.setFree_img_imgfilename(tempString);

			freeImgList.add(fileData);
			System.out.println("=======[test : end]=======");
		}

		// 게시글 내용 SQL 저장처리 로직
		SessionLoginedData sessionLoginedData = (SessionLoginedData) session.getAttribute("sessionLoginedData");

		requestFreeVO.setMember_idx(sessionLoginedData.getMember_idx());

		freeService.writeFreeContent(requestFreeVO, freeImgList);

		model.addAttribute("message", "글쓰기 완료");
		model.addAttribute("page", "freeBoard");

		return "/message";
	}

	// 게시글 수정을 위한 페이지이동
	@RequestMapping("/freeboard/updateFreeContentForm")
	public String updateFreeContentForm(FreeVO requestFreeVO, Model model, int pageNum) {

		FreeData freeData = freeService.getFreeData(requestFreeVO);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("freeData", freeData);

		return "/freeboard/updateFreeContentForm";

	}

	// 게시글 수정 액션
	@RequestMapping("/freeboard/actionUpdateFreeContentForm")
	public String actionUpdateFreeContentForm(HttpSession session, FreeVO requestFreeVO, Model model, int pageNum) {
		
		SessionLoginedData sessionLoginedData = (SessionLoginedData) session.getAttribute("sessionLoginedData");
		FreeVO freeVO = freeService.getFreeVO(requestFreeVO);
		
		if(!sessionLoginedData.getMember_idx().equals(freeVO.getMember_idx())) {
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("message", "니 글이 아닙니다.");
			model.addAttribute("page", "freeBoard");
			return "/message";
		}
		
		// 글작성, 글수정에
		if (requestFreeVO.getFree_content().length() <= 0 || requestFreeVO.getFree_title().length() <= 0) {
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("message", "글 내용을 작성하셔야 합니다.");
			model.addAttribute("page", "freeBoard");
			return "/message";
		}

		if (requestFreeVO.getFree_content().contains("<script>")) {
			model.addAttribute("message", "script는 사용할 수 없습니다.");
			model.addAttribute("page", "freeBoard");
			return "/message";
		}

		freeService.updateFreeContent(requestFreeVO);
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("message", "수정 완료");
		model.addAttribute("page", "freeBoard?pageNum="+pageNum);

		return "/message";
	}

	// 게시글 삭제
	@RequestMapping("/freeboard/deleteFreeContent")
	public String deleteFreeContent(HttpSession session, FreeVO requestFreeVO, Model model) {
		
		SessionLoginedData sessionLoginedData = (SessionLoginedData) session.getAttribute("sessionLoginedData");
		FreeVO freeVO = freeService.getFreeVO(requestFreeVO);
		
		
		if(!sessionLoginedData.getMember_idx().equals(freeVO.getMember_idx())) {
			
			model.addAttribute("message", "니 글이 아닙니다.");
			model.addAttribute("page", "freeBoard");
			return "/message";
		}
		
		freeService.deleteFreeContent(requestFreeVO);

		model.addAttribute("message", "삭제 완료");
		model.addAttribute("page", "freeBoard");

		return "/message";

	}

	// 댓글 삭제
	@RequestMapping("/freeboard/deleteFreeReContent")
	public String deleteFreeReContent(HttpSession session, FreeReVO requestFreeReVO, Model model, int pageNum) {
		SessionLoginedData sessionLoginedData = (SessionLoginedData) session.getAttribute("sessionLoginedData");
		FreeReVO freeReVO = freeReService.getFreeReVO(requestFreeReVO);
		
		if(!sessionLoginedData.getMember_idx().equals(freeReVO.getMember_idx())) {
			
			model.addAttribute("message", "니 글이 아닙니다.");
			model.addAttribute("page", "freeBoard");
			return "/message";
		}
		
		
		freeReService.deleteFreeReContent(requestFreeReVO);
		freeReService.getFreeReVO(requestFreeReVO);

		model.addAttribute("message", "댓글 삭제 완료");
		model.addAttribute("page", "readFreeContent?free_idx=" + requestFreeReVO.getFree_idx() + "&pageNum=" + pageNum);
		return "/message";
	}

	// 게시판 호출
	@RequestMapping("/freeboard/freeBoard")
	public String freeBoard(HttpSession session, Model model, SearchData requestSearchFreeData) {

		// 페이지 이동 처리
		if (requestSearchFreeData.getPageNum() == null) {// 페이지 넘버 없을 때
			requestSearchFreeData.setPageNum("1");
			requestSearchFreeData.setStartNum("1");
			requestSearchFreeData.setEndNum("10");
		} else {
			String pageNum = requestSearchFreeData.getPageNum();
			requestSearchFreeData.setPageNum(pageNum); // 페이지 넘버 세팅
			requestSearchFreeData.setStartNum(String.valueOf(Integer.parseInt(pageNum) * 10 - 9)); // 페이지 넘버로 rownum 시작값
																									// 세팅
			requestSearchFreeData.setEndNum(String.valueOf(Integer.parseInt(pageNum) * 10)); // 페이지 넘버로 rownum 끝나는 값 세팅
		}

		ArrayList<FreeVO> freeVOList = freeService.getFreeList(requestSearchFreeData);// 메인보드 VOList 가져옴

		model.addAttribute("pageNum", requestSearchFreeData.getPageNum());
		// 페이지 이동 처리 로직 끝

		ArrayList<FreeData> freeDataList = new ArrayList<FreeData>(); // 메인보드에 넣을 DataList

		// 메인 보드 출력 로직
		for (FreeVO freeVO : freeVOList) { // 메인보드 DataList를 만들기 위해 for문
			int free_likeCount = freeLikeService.countFreeLike(freeVO.getFree_idx()); // free_idx로 추천수 조회
			MemberVO memberVO = memberService.getMemberVO(freeVO.getMember_idx()); // member_idx로 작성자 조회
			String free_re_count = freeReService.countFreeReply(freeVO.getFree_idx());// 댓글 갯수 조회
			freeDataList.add(new FreeData(freeVO, memberVO, null, free_likeCount, free_re_count));
		}

		// 검색 출력 로직
		if (requestSearchFreeData.getSearchWord() != "" && requestSearchFreeData.getSearchWord() != null) { // 검색 워드를
																											// 넣었을 때

			// 검색 선택 넣기 로직
			String searchSelect = requestSearchFreeData.getSearchSelect();
			model.addAttribute("searchSelect", searchSelect);
			// 끝

			// 검색 내용 넣기 로직
			String searchWord = requestSearchFreeData.getSearchWord();
			model.addAttribute("searchWord", searchWord); // 검색한 내용 검색창에 남아 있게 하기 위하여
			// 끝

			ArrayList<FreeVO> freeSearchVOList = freeService.getSearchFreeList(requestSearchFreeData); // 메인보드에 출력할 검색한
																										// 값 VOList
			ArrayList<FreeData> freeSearchDataList = new ArrayList<FreeData>(); // 메인보드에 출력할 검색한 값 DataList

			// 페이지 크기
			String paging = freeService.countSearchFreeList(requestSearchFreeData); // 검색한 값만큼 페이지 개수 SQL문에서 Count
			if (Integer.parseInt(paging) <= 1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
			}
			model.addAttribute("paging", paging);
			// 페이지 크기 끝

			for (FreeVO freeVO : freeSearchVOList) { // 검색한 값 메인보드 DataList를 만들기 위해 for문
				int free_likeCount = freeLikeService.countFreeLike(freeVO.getFree_idx());
				MemberVO memberVO = memberService.getMemberVO(freeVO.getMember_idx());
				String free_re_count = freeReService.countFreeReply(freeVO.getFree_idx());
				freeSearchDataList.add(new FreeData(freeVO, memberVO, null, free_likeCount, free_re_count));
			}
			model.addAttribute("freeDataList", freeSearchDataList);

		} else {
			// 페이지 크기
			String paging = freeService.countFreeList(); // 총 게시글 개수
			if (Integer.parseInt(paging) <= 1) {
				paging = "1";
			} else {
				paging = String.valueOf((Integer.parseInt(paging) - 1) / 10 + 1);
			}
			model.addAttribute("paging", paging);
			model.addAttribute("freeDataList", freeDataList);
			// 페이지 크기 끝
		}

		// 메인 보드 출력 로직 끝

		return "/freeboard/freeBoard";

	}

}
