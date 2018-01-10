package com.wzxy.job;

import com.alibaba.fastjson.JSONObject;
import com.wzxy.base.constant.BaseConstant;
import com.wzxy.sorket.SocketMessageHandle;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 活动商品状态变更任务
 * 活动商品状态 [报名中---> 进行中 ---> 已结束]
 *
 * @author caixb
 */
public class StatisticsJob {

	private Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
	private SocketMessageHandle socketMessageHandle ;


	protected void execute() throws JobExecutionException {
		if(BaseConstant.isDev){
			logger.info("============================>库存统计start...");
		}
		JSONObject json = new JSONObject();
		socketMessageHandle.sendMessageToUsers(json.toJSONString());
	}
}
