package com.teamb.mth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MoveController {
	
	@RequestMapping("/")
	public String move() { //포트폴리오(페이지 이용 설명서) 적을 곳과 적어서 이동할 곳을 여기다가...
		return about();
	}
	
	@RequestMapping("/commons/about")
	public String about() {
		return "/commons/about";
	}

	@RequestMapping("/commons/services")
	public String service() {
		return "/commons/services";
	}

	@RequestMapping("/commons/contact")
	public String contact() {
		return "/commons/contact";
	}
	
	@RequestMapping("writeReportForm")
	public String writeReportForm() {
		return "/popup/writeReportForm";
	}
	
	@RequestMapping("aroundConvenience")
	public String aroundConvenience() {
		return "/customer/aroundConvenience";
	}
	
	@RequestMapping("/commons/pacmanErrorpage")
	public String pacman404() {
		return "/commons/pacmanErrorpage";
	}
}
