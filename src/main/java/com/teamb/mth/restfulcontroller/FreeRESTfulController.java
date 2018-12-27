package com.teamb.mth.restfulcontroller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamb.mth.boardservice.FreeLikeService;
import com.teamb.mth.boardservice.FreeReService;
import com.teamb.mth.boardservice.FreeService;
import com.teamb.mth.data.SessionLoginedData;
import com.teamb.mth.majorservice.MemberService;
import com.teamb.mth.vo.FreeLikeVO;
import com.teamb.mth.vo.FreeReVO;
import com.teamb.mth.vo.FreeVO;
import com.teamb.mth.vo.MemberVO;

@RestController
public class FreeRESTfulController {
	@Autowired
	FreeService freeService;
	@Autowired
	FreeReService freeReService;
	@Autowired
	MemberService memberService;
	@Autowired
	FreeLikeService freeLikeService;
	
	@RequestMapping("freeboard/likeFreeContent")
	public HashMap<String, String> likeFreeContent(FreeLikeVO requestFreeLikeVO, HttpSession session) {
		SessionLoginedData sessionLoginedData = (SessionLoginedData)session.getAttribute("sessionLoginedData");
		HashMap<String, String> likeFree = new HashMap<String, String>();
		
		if(sessionLoginedData==null) {
			likeFree.put("success", "fail");
			return likeFree;
		} else {
			likeFree.put("success", "success");
			requestFreeLikeVO.setMember_idx(sessionLoginedData.getMember_idx());
			String likeFreeInsert = freeLikeService.likeFreeContent(requestFreeLikeVO);
			String likeFreeCount = String.valueOf(freeLikeService.countFreeLike(requestFreeLikeVO.getFree_idx()));
			likeFree.put("likeFreeInsert", likeFreeInsert);
			likeFree.put("likeFreeCount", likeFreeCount);
			return likeFree;
		}
	}
	
	//댓글 작성
	@RequestMapping("freeboard/writeFreeReContent")
	public HashMap<String, Object> writeFreeReContent(FreeReVO requestFreeReVO, HttpSession session,Model model) {
		SessionLoginedData sessionLoginedData = (SessionLoginedData)session.getAttribute("sessionLoginedData");
		HashMap<String, Object> freeReData = new HashMap<String, Object>();
		
		if(sessionLoginedData == null) {
			
			freeReData.put("success","fail");
			return freeReData;
		}else {
			//댓글 입력 로직
			freeReData.put("success", "success");
			requestFreeReVO.setMember_idx(sessionLoginedData.getMember_idx());
			
			String free_re_idx = freeReService.writeFreeReContent(requestFreeReVO);
			requestFreeReVO.setFree_re_idx(free_re_idx);
			
			//AJAX 댓글 다시 돌려주기 로직
			FreeReVO freeReVO = freeReService.getFreeReVO(requestFreeReVO);
			FreeVO requestFreeVO = new FreeVO();
			requestFreeVO.setFree_idx(requestFreeReVO.getFree_idx());
			FreeVO freeVO = freeService.getFreeVO(requestFreeVO);
			MemberVO memberVO = memberService.getMemberVO(sessionLoginedData.getMember_idx());
			
			freeReData.put("freeReVO", freeReVO);
			freeReData.put("freeVO", freeVO);
			freeReData.put("memberVO", memberVO);
			
			return freeReData;
			
		}
	}
	
}





