//package com.zdy.job;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//@EnableScheduling // 启用定时任务
//public class ScheduledTest {
//
//	private static final Logger logger = LoggerFactory.getLogger(ScheduledTest.class);
//
////	@Scheduled(cron = "*/1 * * * * ?")
//	public void executeFileDownLoadTask() {
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//
//		GregorianCalendar cl = new GregorianCalendar();
//		cl.setTime(new Date());
//		cl.add(1, Calendar.DAY_OF_MONTH);
//
//		String s = df.format(cl.getTime());
//		System.out.println(s);
//		// 间隔2分钟,执行工单上传任务
//		Thread current = Thread.currentThread();
//		System.out.println("定时任务1:" + current.getId());
//		logger.info("ScheduledTest.executeFileDownLoadTask 定时任务1:" + current.getId() + ",name:" + current.getName());
//	}
//
//	@Scheduled(cron = "0 00 20 29 5 ? 2018")
//	public void executeUploadTask() {
//		
//		// 间隔1分钟,执行工单上传任务
//		Thread current = Thread.currentThread();
//		System.out.println("定时任务2:" + current.getId());
//		logger.info("ScheduledTest.executeUploadTask 定时任务2:" + current.getId() + ",name:" + current.getName());
//	}
//
////	@Scheduled(cron = "0 0/3 5-23 * * ?")
////	public void executeUploadBackTask() {
////
////		// 间隔3分钟,执行工单上传任务
////		Thread current = Thread.currentThread();
////		System.out.println("定时任务3:" + current.getId());
////		logger.info("ScheduledTest.executeUploadBackTask 定时任务3:" + current.getId() + ",name:" + current.getName());
////	}
//
//}