package com.wzxy.base.utils.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * ## 使用spring注解执行定时任务<br>
 * ## 使用@Component 和@scheduled注解进行定时任务的执行<br>
 * ## 使用条件：在applicationContext.xml中要引入以下条件：<br>
 * ## （1） xmlns:task="http://www.springframework.org/schema/task"<br>
 * ## （2）xsi:schemaLocation="http://www.springframework.org/schema/task <br>
 * ## 		http://www.springframework.org/schema/task/spring-task-3.0.xsd"><br>
 * ## （3）<task:annotation-driven/> （配置定时器的开关）<br>
 * ## （4）<context:component-scan base-package="xxx.xx"/>(扫描器要扫到相应的定时器类所在的包)<br>
 * ##  cron定时执行任务使用简单示例（完整格式: * * * * * *,分别对应：秒  分  时  日  月  年 ）：<br>
 * ##	0 0 12 * * ?	每天12点触发<br>
 * ##	0 15 10 ? * *	每天10点15分触发<br>
 * ##	0 15 10 * * ?	每天10点15分触发<br>
 * ##	0 15 10 * * ? *	每天10点15分触发<br>
 * ##	0 15 10 * * ? 2005	2005年每天10点15分触发<br>
 * ##	0 11 11 11 11 ?	每年的11月11号 11点11分触发<br>
 * ##  fixedRate定时任务执行使用：<br>
 * ##  fixesRate=毫秒数     表示启动时运行一次，并在当前运行时间间隔<毫秒数>后循环执行<br>
 * ##  示例：fixesRate=1000*60*1 表示启动时运行一次，以后都将间隔 一分钟 循环执行<br>
 * ##  简单问题记录：
 * ##  （1）在springMVC中不要加载两次配置文件，不然定时任务会执行两次
 * @since 2017-12-22
 * @version 1.0
 *
 */
@Component("springScheduledTaskManager")
public class SpringScheduledTaskManager {
	private Log log=LogFactory.getLog(SpringScheduledTaskManager.class);
	/** 
     * cron表达式：* * * * * *（共6位，使用空格隔开，具体如下） 
     * cron表达式：*(秒0-59) *(分钟0-59) *(小时0-23) *(日期1-31) *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT) 
     */  
    @Scheduled(cron="30 * * * * ?")  
    public void countMac() {  
    	log.info("一小时 运行一次");
    }  
    
  
}
