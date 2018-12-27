package com.teamb.mth.restfulcontroller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;
import com.teamb.mth.boardservice.YumLikeService;
import com.teamb.mth.boardservice.YumReService;
import com.teamb.mth.boardservice.YumService;
import com.teamb.mth.data.SessionLoginedData;
import com.teamb.mth.data.YumReData;
import com.teamb.mth.majorservice.MemberService;
import com.teamb.mth.vo.MemberVO;
import com.teamb.mth.vo.YumLikeVO;
import com.teamb.mth.vo.YumReVO;
import com.teamb.mth.vo.YumVO;

@RestController
public class YumRESTfulController {
	@Autowired
	YumService yumService;
	
	@Autowired
	YumLikeService yumLikeService;
	
	@Autowired
	YumReService yumReService;
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("yummyboard/likeYummyContent")
	public HashMap<String, String> likeYummyContent(YumLikeVO requestYumLikeVO, HttpSession session) {
		SessionLoginedData sessionLoginedData = (SessionLoginedData)session.getAttribute("sessionLoginedData");
		HashMap<String, String> likeYum = new HashMap<String, String>();
		
		if(sessionLoginedData==null) {
			likeYum.put("success", "fail");
			return likeYum;
		} else {
			likeYum.put("success", "success");
			requestYumLikeVO.setMember_idx(sessionLoginedData.getMember_idx());
			String likeYumInsert = yumLikeService.likeYumContent(requestYumLikeVO);
			String likeYumCount = String.valueOf(yumLikeService.countYumLike(requestYumLikeVO.getYum_idx()));
			likeYum.put("likeYumInsert", likeYumInsert);
			likeYum.put("likeYumCount", likeYumCount);
			return likeYum;
		}
	}
	
	@RequestMapping("yummyboard/writeYumReContent")
	public YumReData writeYumReContent(YumReVO requestYumReVO, HttpSession session) {
		SessionLoginedData sessionLoginedData = (SessionLoginedData)session.getAttribute("sessionLoginedData");
		if(sessionLoginedData==null || requestYumReVO.getYum_re_content().length()<=0) {
			return null;
		} else {
			//댓글 입력 로직...//
			requestYumReVO.setMember_idx(sessionLoginedData.getMember_idx());
			
			String yum_re_idx = yumReService.writeYumReContent(requestYumReVO);
			requestYumReVO.setYum_re_idx(yum_re_idx);
			
			//ajax 댓글 다시 돌려주기 로직...//
			YumReVO yumReVO = yumReService.getYumReVO(requestYumReVO);
			YumVO requestYumVO = new YumVO();
			requestYumVO.setYum_idx(requestYumReVO.getYum_idx());
			YumVO yumVO = yumService.getYumVO(requestYumVO);
			MemberVO memberVO = memberService.getMemberVO(sessionLoginedData.getMember_idx());
			
			YumReData yumReData = new YumReData(yumReVO, yumVO, memberVO);
			return yumReData;
		}
	}
	
	//이미지 업로드
		@RequestMapping("yummyboard/yummyImageUpload")
		public String fileUpload(HttpServletRequest requeset, HttpServletResponse response, 
	                 MultipartHttpServletRequest multiFile) throws Exception {
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			String dateFolder = sdf.format(date);
			dateFolder= dateFolder.replace("-", File.separator);
			
			JsonObject json = new JsonObject();
			PrintWriter printWriter = null;
			OutputStream out = null;
			MultipartFile file = multiFile.getFile("upload");
			if(file != null){
				if(file.getSize() > 0 && StringUtils.isNotBlank(file.getName())){
					if(file.getContentType().toLowerCase().startsWith("image/")){
						try{
							String fileName = file.getName();
							byte[] bytes = file.getBytes();
							String uploadPath = requeset.getServletContext().getRealPath("/img/");
							uploadPath += dateFolder.replace(File.separator, "/");
							
							File uploadFile = new File(uploadPath);
							if(!uploadFile.exists()){
								uploadFile.mkdirs();
							}
							fileName = UUID.randomUUID().toString();
							uploadPath = uploadPath + "/" + fileName;
							out = new FileOutputStream(new File(uploadPath));
	                        out.write(bytes);
	                        
	                        printWriter = response.getWriter();
	                        response.setContentType("text/html");
	                        String fileUrl = requeset.getContextPath() + "/img/"+dateFolder.replace(File.separator, "/")+"/" + fileName;
	                        
	                        // json 데이터로 등록
	                        json.addProperty("uploaded", 1);
	                        json.addProperty("fileName", fileName);
	                        json.addProperty("url", fileUrl);
	                        
	                        printWriter.println(json);
	                        System.out.println(json);
	                    }catch(IOException e){
	                        e.printStackTrace();
	                    }finally{
	                        if(out != null){
	                            out.close();
	                        }
	                        if(printWriter != null){
	                            printWriter.close();
	                        }		
						}
					}
				}
			}
			return null;
		}	
}
