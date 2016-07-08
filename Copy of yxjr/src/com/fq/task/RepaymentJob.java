package com.fq.task;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fq.util.FormData;


/**
 * 定时器
 */
@Component
public class RepaymentJob {
	
	/*
	@Resource(name = "daoSupport")
	private DaoSupport daoSupport;*/


	//"0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发 
	 @Scheduled(cron = "0 0 1  * * ? ")
	    public void doUpdateIndexData(){
		 FormData formData=new FormData();
		
		 
	 }
	 
		//"0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发 
		 @Scheduled(cron = "0 30 0  * * ? ")
		 public void bidLoanData(){	
			
			 
		 }

}
 