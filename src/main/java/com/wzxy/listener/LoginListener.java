package com.wzxy.listener;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wzxy.service.vo.sys.SysAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;

import com.wzxy.event.EventModel;
import com.wzxy.event.LoginEvent;

import cn.assist.easydao.dao.BaseDao;

/**
 * 登录成功事件处理
 * 
 * @author caibin
 *
 */
//@Component
public class LoginListener implements ApplicationListener<LoginEvent> {
	Logger logger = LoggerFactory.getLogger(LoginListener.class);
	Logger loginLog = org.slf4j.LoggerFactory.getLogger("login_log");
	
	@Autowired  
	private HttpServletRequest request;
	
	@Async 
    public void onApplicationEvent(final LoginEvent event) {
        if(event instanceof LoginEvent) {
        	EventModel eventModel = (EventModel)event.getSource();
        	if(eventModel == null){
        		return;
        	}
        	SysAction.SysUser sysUser = eventModel.getSysUser();
        	Map<String, Object> param = eventModel.getParam();
        	if(sysUser == null || param == null){
        		return;
        	}
        	int uid = sysUser.getUid();
        	Date lastLoginTime = (Date)param.get("lastLoginTime");
        	//更新登录时间
        	int i = BaseDao.dao.update("update sys_user set last_login_time = ? where uid = ?", lastLoginTime, uid);
        	if(i < 1){
        		logger.warn("更新用户最后登录时间失败,uid:" + uid + ", 最后登录时间:" + lastLoginTime);
        	}
        }
    }
}