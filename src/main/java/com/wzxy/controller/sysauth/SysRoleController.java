package com.wzxy.controller.sysauth;

import cn.assist.easydao.common.Conditions;
import cn.assist.easydao.common.SqlExpr;
import cn.assist.easydao.pojo.PagePojo;
import com.alibaba.fastjson.JSONObject;
import com.wzxy.base.utils.JsonBean;
import com.wzxy.base.utils.RecordBean;
import com.wzxy.base.utils.ReqUtils;
import com.wzxy.service.auth.ISysRoleService;
import com.wzxy.service.vo.sys.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 角色权限管理
 * 
 * @author caibin
 *
 */
@Controller
@RequestMapping(value = "/sysRole")
public class SysRoleController {

	@Autowired
	private ISysRoleService sysRoleService;

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, Model model){
		return "modules/sys-auth/role/index";
	}


	@RequestMapping(value = "/list")
	@ResponseBody
	public JSONObject list(HttpServletRequest request){
		//参数获取
		int pageNo = ReqUtils.getParamToInt(request, "pageNo", 1);
		int pageSize = ReqUtils.getParamToInt(request, "pageSize", 15);
		String name = ReqUtils.getParam(request,"name",null);
		Conditions conn = new Conditions("name", SqlExpr.EQUAL,name);
		PagePojo<SysRole> page = sysRoleService.getSysRolePage(conn, pageNo, pageSize);
		return JsonBean.success(page);
	}


	@RequestMapping(value = "/getView")
	public String getView(HttpServletRequest request,Model model){
		//参数获取
		Integer roleId = ReqUtils.getParamToInteger(request,"roleId",null);
		SysRole sysRole = new SysRole();
		if (roleId != null) {
			sysRole = sysRoleService.getSysRole(roleId);
		}
		model.addAttribute("data",sysRole);
		return "modules/sys-auth/role/_add";
	}

	@RequestMapping(value = "/add")
	@ResponseBody
	public JSONObject add(SysRole sysRole){
		RecordBean<SysRole> result = sysRoleService.addSysRole(sysRole);
		if (!result.isSuccessCode()) {
			return JsonBean.error(result.getMsg());
		}
		return JsonBean.success("");
	}

	@RequestMapping(value = "/edit")
	@ResponseBody
	public JSONObject edit(SysRole sysRole){
		RecordBean<SysRole> result = sysRoleService.editSysRole(sysRole);
		if (!result.isSuccessCode()) {
			return JsonBean.error(result.getMsg());
		}
		return JsonBean.success("");
	}

	@RequestMapping(value = "/del")
	@ResponseBody
	public JSONObject del(HttpServletRequest request){
		//参数获取
		String[] roleIds = request.getParameterValues("roleIds");
		RecordBean<String> result = sysRoleService.delSysRole(roleIds);
		if (!result.isSuccessCode()) {
			return JsonBean.error(result.getMsg());
		}
		return JsonBean.success("");
	}
}
