package com.wzxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.wzxy.base.utils.JsonBean;
import com.wzxy.base.utils.ShiroUtils;
import com.wzxy.service.auth.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 首页
 * 
 * @author caibin
 *
 */
@Controller
public class IndexController {

	@Autowired
	private IAuthService iAuthService;

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, Model model){
		return "/WEB-INF/view2.0/index";
	}
	
	@RequestMapping(value = "/main")
	public String main(HttpServletRequest request, Model model){
		return "main";
	}
	
	@RequestMapping(value = "/no_permission")
	public String noPermission(HttpServletRequest request, Model model){
		return "/common/no-permission";
	}

	@RequestMapping(value = "/tables")
	public String tables(HttpServletRequest request, Model model){
		return "/modules/tables";
	}

	
	@RequestMapping(value = "/clearCache")
	@ResponseBody
	public JSONObject clearCache(HttpServletRequest request){
		Integer uid = ShiroUtils.getUserId();
		iAuthService.reload(uid);
		return JsonBean.success("清除缓存成功！");
	}
}
