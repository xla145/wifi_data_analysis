package com.wzxy.job;

import com.wzxy.base.constant.BaseConstant;
import com.wzxy.sorket.handler.WebSocketEndPointTest;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 活动商品状态变更任务
 * 活动商品状态 [报名中---> 进行中 ---> 已结束]
 *
 * @author caixb
 */
public class StatisticsJob {

	private Logger logger = LoggerFactory.getLogger(getClass());


	WebSocketEndPointTest webSocketEndPointTest = new WebSocketEndPointTest();



	protected void execute() throws JobExecutionException {
		if(BaseConstant.isDev){
			logger.info("============================>库存统计start...");
		}
		webSocketEndPointTest.sendMsg("");
	}
}
