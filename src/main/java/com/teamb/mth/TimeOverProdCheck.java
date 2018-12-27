package com.teamb.mth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import com.teamb.mth.majorservice.ProdService;

public class TimeOverProdCheck {
	
	@Autowired
	ProdService prodService;
	
	private class TimeCheckTask implements Runnable {
		public void run() {
			// TODO Auto-generated method stub
			prodService.updateOverTimeProd();
			System.out.println("TimeOverProdCheck가 실행되고 있습니다.");
		}
	}
	
	private TaskExecutor taskExecutor;
	
	public TimeOverProdCheck() {
		
	}
	
	public TimeOverProdCheck(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
	
	public void checkProd() {
		for(int i=0; i <25; i++) {
			taskExecutor.execute(new TimeCheckTask());
		}
	}
}
