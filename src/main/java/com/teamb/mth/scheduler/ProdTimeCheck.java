package com.teamb.mth.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.teamb.mth.majorservice.ProdService;

@Component
public class ProdTimeCheck {
	
	@Autowired
	ProdService prodService;
	
	@Scheduled(cron = "0 * * * * *")
	public void run() {
		// TODO Auto-generated method stub
		//현재시간-유통기한이 0 보다 적으면 폐기로 업데이트
		prodService.updateOverTimeProd();
		//현재시간-유통기한이 1시간 보다 적으면 최종할인가격이 할인가격으로 업데이트
		prodService.updateSalePriceByFinalPrice();
	}
	
}
