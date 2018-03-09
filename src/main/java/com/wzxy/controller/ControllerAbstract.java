package com.wzxy.controller;


import com.wzxy.service.vo.sys.SysAction;

import javax.servlet.http.HttpSession;



public abstract class ControllerAbstract {
	public SysAction.SysUser getSession(HttpSession httpSession) {
		return (SysAction.SysUser) httpSession.getAttribute("sessionInfo");
	}
}
